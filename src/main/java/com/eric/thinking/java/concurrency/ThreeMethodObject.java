package com.eric.thinking.java.concurrency;

import java.util.Timer;
import java.util.TimerTask;

class ThreeMethod {
	final private Object syncOne = new Object(), syncTwo = new Object(),
			syncThree = new Object();

	public void f() {
		synchronized (syncOne) {
			while (true) {
				System.out.println("f()");
			}
		}
	}

	public void g() {
		synchronized (syncTwo) {
			while (true) {
				System.out.println("g()");
			}
		}

	}

	public void h() {
		synchronized (syncThree) {
			System.out.println("h()");
		}
	}
}

public class ThreeMethodObject {
	public static void main(String[] args) {
		final ThreeMethod tm = new ThreeMethod();
		new Thread() {
			@Override
			public void run() {
				tm.f();
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				tm.g(); // never happen
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				tm.h();
			}
		}.start();

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("Exit....");
				System.exit(0);
			}

		}, 100);
	}
}
