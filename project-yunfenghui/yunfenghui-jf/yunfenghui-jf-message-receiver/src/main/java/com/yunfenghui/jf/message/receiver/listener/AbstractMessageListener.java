package com.yunfenghui.jf.message.receiver.listener;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yunfenghui.common.Commons;
import com.yunfenghui.jf.score.service.ScoreService;

public abstract class AbstractMessageListener<E> implements MessageListenerConcurrently {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected ScoreService scoreService;

	private final Class<E> clazz;

	protected AbstractMessageListener(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
			ConsumeConcurrentlyContext context) {
		logger.debug("Consumed message size:{}", msgs.size());
		List<E> objs = convert(msgs);
		if (objs != null && !objs.isEmpty()) {
			try {
				handle(objs);
			} catch (Exception e) {
				logger.error("Failed to handle", e);
				return handleOnException(e, objs);
			}
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

	protected List<E> convert(List<MessageExt> msgs) {
		if (msgs != null && !msgs.isEmpty()) {
			List<E> result = new ArrayList<>(msgs.size());
			for (MessageExt msg : msgs) {
				result.add(convert(msg));
			}
			return result;
		}
		return null;
	}

	protected E convert(MessageExt msg) {
		return JSON.parseObject(new String(msg.getBody(), Charset.forName(Commons.UTF8)), clazz);
	}

	protected ConsumeConcurrentlyStatus handleOnException(Exception e, List<E> objs) {
		return ConsumeConcurrentlyStatus.RECONSUME_LATER;
	}

	protected abstract void handle(List<E> objs) throws Exception;

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

}
