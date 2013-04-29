package com.eric.thinking.java.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FastSimulation {
	static final int N_ELEMENTS = 100;
	static final int N_GENES = 30;
	static final int N_EVOLVERS = 50;
	static final AtomicInteger[][] grid = new AtomicInteger[N_ELEMENTS][N_GENES];
	static Random rand = new Random(47);
	private static Lock lock = new ReentrantLock();

	static class Evolver implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					// Randomly select an element to work on
					int element = rand.nextInt(N_ELEMENTS);
					for (int i = 0; i < N_GENES; i++) {
						int previous = element - 1;
						if (previous < 0)
							previous = N_ELEMENTS - 1;
						int next = element + 1;
						if (next >= N_ELEMENTS)
							next = 0;
						int oldValue = grid[element][i].get();
						int newValue = oldValue + grid[previous][i].get()
								+ grid[next][i].get();
						newValue /= 3;
						if (!grid[element][i].compareAndSet(oldValue, newValue)) {
							// update failed
							System.out.println("Old value changed from "
									+ oldValue);
						}
					}
				} finally {
					lock.unlock();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		// initial
		for (int i = 0; i < N_ELEMENTS; i++) {
			for (int j = 0; j < N_GENES; j++) {
				grid[i][j] = new AtomicInteger(rand.nextInt(1000));
			}
		}

		for (int i = 0; i < N_EVOLVERS; i++) {
			exec.execute(new Evolver());
		}
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
