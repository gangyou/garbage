package com.eric.thinking.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						wait();
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);
				synchronized (restaurant.chief) {
					restaurant.meal = null;
					restaurant.chief.notifyAll();
					synchronized (restaurant.boy) {
						restaurant.boy.notifyAll();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupt");
		}
	}

}

class Chief implements Runnable {
	private Restaurant restaurant;
	private int count = 0;

	public Chief(Restaurant r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait(); // for the meal to be taken
					}
				}
				if (++count == 10) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order Up!");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chief interrupted");
		}
	}
}

class BusBoy implements Runnable{
	
	@Override
	public void run() {
		try{
			synchronized(this){
				while(true){
					wait();
					System.out.println("BusBoy Cleaning");
				}
			}
		}catch(InterruptedException e){
			System.out.println("BusBoy Interrupted");
		}
	}
	
}

public class Restaurant {

	public WaitPerson waitPerson = new WaitPerson(this);
	public ExecutorService exec = Executors.newCachedThreadPool();
	public Chief chief = new Chief(this);
	public Meal meal;
	public BusBoy boy = new BusBoy();

	public Restaurant() {

		exec.execute(chief);
		exec.execute(waitPerson);
		exec.execute(boy);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
}
