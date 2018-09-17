package com.yunfenghui.jf.score.service.support;

import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yunfenghui.common.Commons;

/**
 * 可重试的MessageSender
 * 
 * @author Administrator
 *
 */
public class RetriedMessageSender {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected MQProducer producer;
	protected ScheduledExecutorService messageExecutor;
	private static final long[] retryDelayGroup = new long[] { TimeUnit.SECONDS.toMillis(10),
			TimeUnit.MINUTES.toMillis(5), TimeUnit.HOURS.toMillis(1), TimeUnit.HOURS.toMillis(5),
			TimeUnit.HOURS.toMillis(12) };
	private String poolNameFormat = "default-msg-pool-%d";
	private int poolSize = Runtime.getRuntime().availableProcessors();
	private String defaultTopicName;

	public void send(Object obj) {
		send(obj, defaultTopicName);
	}

	public void send(Object obj, String topicName) {
		Message msg = buildMessage(obj, topicName);
		sendToMQ(new RetriedMessage(msg));
	}

	protected Message buildMessage(Object obj, String topicName) {
		return new Message(topicName,
				JSON.toJSONString(obj).getBytes(Charset.forName(Commons.UTF8)));
	}

	private void sendToMQ(RetriedMessage retriedMessage) {
		try {
			producer.send(retriedMessage.getMessage(), new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					logger.debug("sendResult, messageId:{}, queueId:{}", sendResult.getMsgId(),
							sendResult.getMessageQueue().getQueueId());
				}

				@Override
				public void onException(Throwable e) {
					logger.error("Failed to sendToMQ onException", e);
					retrySend(retriedMessage.incrRetriedCount());
				}
			});
		} catch (MQClientException | RemotingException | InterruptedException e) {
			logger.error("Failed to sendToMQ", e);
			retrySend(retriedMessage.incrRetriedCount());
		}
	}

	private void retrySend(RetriedMessage retriedMessage) {
		if (retriedMessage.getRetriedCount() <= getMaxRetryCount()) {
			long retryDelay = resolveRetryDelay(retriedMessage.getRetriedCount());
			if (retryDelay > 0) {
				initMessageExecutor();
				messageExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						sendToMQ(retriedMessage);
					}
				}, retryDelay, TimeUnit.MILLISECONDS);
			}
		}
	}

	private long resolveRetryDelay(int retriedCount) {
		for (int i = 0; i < retryDelayGroup.length; i++) {
			if (i == retriedCount) {
				return retryDelayGroup[i];
			}
		}
		return 0;
	}

	private int getMaxRetryCount() {
		return retryDelayGroup.length;
	}

	private void initMessageExecutor() {
		if (messageExecutor == null) {
			synchronized (this) {
				if (messageExecutor == null) {
					messageExecutor = Executors.newScheduledThreadPool(poolSize,
							buildThreadFactory(poolNameFormat));
				}
			}
		}
	}

	public void setPoolNameFormat(String poolNameFormat) {
		this.poolNameFormat = poolNameFormat;
	}

	public void setProducer(MQProducer producer) {
		this.producer = producer;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public void setDefaultTopicName(String defaultTopicName) {
		this.defaultTopicName = defaultTopicName;
	}

	private static ThreadFactory buildThreadFactory(String nameFormat) {
		ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
		builder.setNameFormat(nameFormat);
		return builder.build();
	}

	static class RetriedMessage {
		private int retriedCount = 0;
		private final Message message;

		public RetriedMessage(Message message) {
			this.message = message;
		}

		public int getRetriedCount() {
			return retriedCount;
		}

		public void setRetriedCount(int retriedCount) {
			this.retriedCount = retriedCount;
		}

		public Message getMessage() {
			return message;
		}

		public RetriedMessage incrRetriedCount() {
			++retriedCount;
			return this;
		}
	}
}
