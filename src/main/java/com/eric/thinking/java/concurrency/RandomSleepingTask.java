package com.eric.thinking.java.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RandomSleepingTask implements Runnable {

	private static final Random random = new Random(47);

	@Override
	public void run() {
		int sleep = random.nextInt(10);
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sleep + " seconds.");
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 20; i++) {
			exec.execute(new RandomSleepingTask());
		}
		exec.shutdown();
	}
}
