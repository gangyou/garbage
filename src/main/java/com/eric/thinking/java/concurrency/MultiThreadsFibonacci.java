package com.eric.thinking.java.concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.eric.thinking.java.generics.fibonacci.Fibonacci;

public class MultiThreadsFibonacci {
	public static void main(String[] args) {
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 20; i++) {
			results.add(exec.submit(new FibonacciTask(i)));
		}
		for (Future<String> fs : results) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}
		}
	}
}

class FibonacciTask implements Callable<String> {

	private Fibonacci fib;
	private int length = 0;

	public FibonacciTask(int length) {
		fib = new Fibonacci();
		this.length = length;
	}

	@Override
	public String call() throws Exception {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(fib.next()).append(",");
		}
		return result.toString();
	}
}