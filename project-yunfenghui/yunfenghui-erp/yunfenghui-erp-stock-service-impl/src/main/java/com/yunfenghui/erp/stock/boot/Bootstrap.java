package com.yunfenghui.erp.stock.boot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {
	private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	private static final ReentrantLock LOCK = new ReentrantLock();
	private static final Condition STOP = LOCK.newCondition();
	private static ClassPathXmlApplicationContext context;

	private static void startup() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("spring-context.xml",
					"stock-service-provider.xml");
			context.start();
		}
	}

	private static void shutdown() {
		if (context != null) {
			context.stop();
			context.close();
			context = null;
		}
	}

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						shutdown();
						logger.info("StockService shutdown!");
					} catch (Throwable t) {
						logger.error("StockService shutdown failed!", t);
					}
					try {
						LOCK.lock();
						STOP.signal();
					} finally {
						LOCK.unlock();
					}
				}
			});

			startup();
			logger.info("StockService started!");
		} catch (RuntimeException e) {
			logger.error("StockService start failed!", e);
			System.exit(1);
		}
		try {
			LOCK.lock();
			STOP.await();
		} catch (InterruptedException e) {
		} finally {
			LOCK.unlock();
		}
	}
}
