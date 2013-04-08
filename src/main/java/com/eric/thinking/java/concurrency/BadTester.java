package com.eric.thinking.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BadTester {
	private int field1;
	private int field2;
	
	public synchronized void  increase(){
		field1++;
		field2++;
	}
	
	public void print(){
		System.out.println("field1: " + field1 + "\n field2:" + field2);
	}
	
	public static void main(String[] args) {
		BadTester t = new BadTester();
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			exec.execute(new Task(t));
		}
	}
}

class Task implements Runnable{

	private BadTester instance;
	public Task(BadTester t){
		this.instance = t;
	}
	@Override
	public void run() {
		instance.increase();
		instance.print();
	}
	
}
