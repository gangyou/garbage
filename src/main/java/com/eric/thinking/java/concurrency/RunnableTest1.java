package com.eric.thinking.java.concurrency;

public class RunnableTest1 {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			new Thread(new MyThread()).start();
		}
	}
}


class MyThread implements Runnable{

	protected static int count = 0;
	private final int id = count++;
	public MyThread(){
		System.out.println("MyThread" + id +
				" Start");
	}
	@Override
	public void run() {
		System.out.println("Message 1 form MyThread " + id);
		Thread.yield();
		System.out.println("Message 2 form MyThread " + id);
		Thread.yield();
		System.out.println("Message 3 form MyThread " + id);
		Thread.yield();
		
		System.out.println("MyThread " + id + " exit!!!");
	}
	
}