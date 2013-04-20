package com.eric.thinking.java.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class QueueSender implements Runnable {
	private BlockingQueue<Character> queue;
	private Random rand = new Random(47);

	public QueueSender(BlockingQueue<Character> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		char c = 'A';
		try {
			while (!Thread.interrupted() && c <= 'z') {
				queue.put(c++);
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
			}
		} catch (InterruptedException e) {
			System.out.println("QueueSender interrupted");
		}
	}

}

class QueueReceiver implements Runnable {
	private BlockingQueue<Character> queue;

	public QueueReceiver(BlockingQueue<Character> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Read: " + queue.take() + ", ");
			}
		} catch (InterruptedException e) {
			System.out.println("QueueReceiver interrupted");
		}
	}
}

public class BlockQueuePipedIO {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Character> queue = new LinkedBlockingQueue<Character>();
		QueueSender sender = new QueueSender(queue);
		QueueReceiver receiver = new QueueReceiver(queue);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}
