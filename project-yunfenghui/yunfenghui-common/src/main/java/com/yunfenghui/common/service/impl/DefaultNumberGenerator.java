package com.yunfenghui.common.service.impl;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.yunfenghui.common.service.NumberGenerator;

public class DefaultNumberGenerator implements NumberGenerator {
	private AtomicInteger counter = new AtomicInteger(new Random().nextInt());
	private static final int MACHINE;

	@Override
	public String generate() {
		byte bytes[] = new byte[12];
		ByteBuffer byteBuf = ByteBuffer.wrap(bytes);
		byteBuf.putInt((int) (System.currentTimeMillis() / 1000));
		byteBuf.putInt(MACHINE);
		byteBuf.putInt(counter.incrementAndGet());
		final StringBuilder buffer = new StringBuilder(24);
		for (final byte b : bytes) {
			buffer.append(String.format("%02x", b & 0xff));
		}
		return buffer.toString();
	}

	static {
		try {
			int machinePiece;
			{
				try {
					StringBuilder sb = new StringBuilder();
					Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
					while (e.hasMoreElements()) {
						NetworkInterface ni = e.nextElement();
						sb.append(ni.toString());
					}
					machinePiece = sb.toString().hashCode() << 16;
				} catch (Throwable e) {
					machinePiece = (new Random().nextInt()) << 16;
				}
			}

			final int processPiece;
			{
				int processId = new Random().nextInt();
				try {
					processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName()
							.hashCode();
				} catch (Throwable t) {
				}

				ClassLoader loader = DefaultNumberGenerator.class.getClassLoader();
				int loaderId = loader != null ? System.identityHashCode(loader) : 0;

				StringBuilder sb = new StringBuilder();
				sb.append(Integer.toHexString(processId));
				sb.append(Integer.toHexString(loaderId));
				processPiece = sb.toString().hashCode() & 0xFFFF;
			}

			MACHINE = machinePiece | processPiece;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
