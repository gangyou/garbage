package com.eric.thinking.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WaitingPerson implements Runnable {

	@Override
	public void run() {
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Wake up from waiting");
	}

}

class WakingPerson implements Runnable {
	private Runnable waitingOne;

	public WakingPerson(Runnable waitingOne) {
		this.waitingOne = waitingOne;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			System.out.println("Waiting Error");
		}
		synchronized (waitingOne) {
			waitingOne.notifyAll();
		}
	}

}

public class WaitFoolish {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Runnable waiting = new WaitingPerson();
		Runnable waking = new WakingPerson(waiting);
		exec.execute(waiting);
		exec.execute(waking);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}
