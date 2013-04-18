package com.eric.thinking.java.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> q) {
		rockets = q;
	}

	public void add(LiftOff l) {
		try {
			rockets.put(l);
		} catch (InterruptedException e) {
			System.out.println("Interrupted during put");
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff lo = rockets.take();
				lo.run();
			}
		} catch (InterruptedException e) {
			System.out.println("Waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}

}

class FillBlockingQueue implements Runnable{
	private BlockingQueue<LiftOff> queue;
	public FillBlockingQueue(BlockingQueue<LiftOff> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				queue.put(new LiftOff(5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class TestBlockingQueues {
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static void getKey(String message) {
		System.out.println(message);
		getKey();
	}
	
	static void test(String message, BlockingQueue<LiftOff> queue){
		System.out.println(message);
		LiftOffRunner lor = new LiftOffRunner(queue);
		Thread t = new Thread(lor);
		t.start();
		for(int i = 0; i < 5; i++){
			lor.add(new LiftOff(5));
		}
		getKey("Press 'Enter' (" + message + ")");
		t.interrupt();
		System.out.println("Finished " + message + " test");
	}
	
	public static void main(String[] args) {
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());
	}
}
