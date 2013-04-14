package com.eric.thinking.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class BusyGuy implements Runnable {
	private boolean flag = false;

	public synchronized void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (!flag) {
			synchronized (this) {
				try {
					System.out.println("the flag value is " + flag);
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		setFlag(false);
		System.out.println("Reseting flag to 'false'.");
	}

}

class NotifyGuy implements Runnable {

	private BusyGuy b;

	public NotifyGuy(BusyGuy b) {
		this.b = b;
	}

	@Override
	public void run() {
		synchronized (b) {
			b.setFlag(true);
			b.notify();
		}
	}

}

public class BusyWaiting {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		BusyGuy b = new BusyGuy();
		exec.execute(b);
		exec.execute(new NotifyGuy(b));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdown();
	}
}
