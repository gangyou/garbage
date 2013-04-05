package com.eric.thinking.java.concurrency;

import com.eric.thinking.java.generics.fibonacci.Fibonacci;

public class MultiThreadsFibonacci {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			new Thread(new FibonacciTask(10)).start();
		}
	}
}

class FibonacciTask implements Runnable {

	private Fibonacci fib;
	private int length = 0;

	public FibonacciTask(int length) {
		fib = new Fibonacci();
		this.length = length;
	}

	public void run() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(fib.next()).append(",");
		}
		System.out.println(result.toString());
	}
}