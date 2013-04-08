package com.eric.thinking.java.concurrency;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread daemon = new Thread(r);
		daemon.setDaemon(true);
		return daemon;
	}

}
