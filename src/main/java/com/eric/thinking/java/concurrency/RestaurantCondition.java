package com.eric.thinking.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConditionMeal {
	private final int orderNum;

	public ConditionMeal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class ConditionWaitPerson implements Runnable {
	private RestaurantCondition restaurant;

	public ConditionWaitPerson(RestaurantCondition r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				restaurant.lock.lock();
				try{
					while(restaurant.meal == null){
						restaurant.condition.await();
					}
				}finally{
					restaurant.lock.unlock();
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

class ConditionChef implements Runnable {
	private RestaurantCondition restaurant;
	private int count = 0;

	public ConditionChef(RestaurantCondition r) {
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
					restaurant.meal = new ConditionMeal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chief interrupted");
		}
	}
}

class ConditionBusyBus implements Runnable{
	
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

public class RestaurantCondition {

	public ConditionWaitPerson waitPerson = new ConditionWaitPerson(this);
	public ExecutorService exec = Executors.newCachedThreadPool();
	public ConditionChef chief = new ConditionChef(this);
	public ConditionMeal meal;
	public ConditionBusyBus boy = new ConditionBusyBus();
	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();

	public RestaurantCondition() {

		exec.execute(chief);
		exec.execute(waitPerson);
		exec.execute(boy);
	}

	public static void main(String[] args) {
		new RestaurantCondition();
	}
}
