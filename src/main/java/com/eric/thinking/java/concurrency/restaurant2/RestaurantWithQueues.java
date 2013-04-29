package com.eric.thinking.java.concurrency.restaurant2;
//TODO
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.eric.thinking.java.enumerated.menu.Course;
import com.eric.thinking.java.enumerated.menu.Food;

class Waiter implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Kitchen kitchen;
	private BlockingQueue<Order> preparedOrders = new LinkedBlockingQueue<Order>();

	public Waiter(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Order order = preparedOrders.take();
				System.out.println(this + " received " + order
						+ " delivering to " + order.getCustomer());
				this.deliver(order);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " finished work");
	}

	private void deliver(Order order) throws InterruptedException {
		order.getCustomer().deliver(order.getFood());
	}

	public void prepared(Order order) throws InterruptedException {
		preparedOrders.put(order);
	}

	@Override
	public String toString() {
		return "Waiter No." + id;
	}

	public void placeOrder(Customer customer, Food food)
			throws InterruptedException {
		System.out.println(this + " get order {" + food + "} from " + customer);
		Order order = new Order(this, customer, food);
		kitchen.placeOrder(order);
	}
}

class Chef implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private final Kitchen kitchen;
	private static Random rand = new Random(47);

	public Chef(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Order order = kitchen.getOrder();
				System.out.println(this + " cooking " + order.getFood());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				System.out.println(", give order to " + order.getWaiter());
				order.getWaiter().prepared(order);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " finished work");
	}

	@Override
	public String toString() {
		return "Chef No." + id;
	}
}

class Customer implements Runnable {
	private final Waiter waiter;
	private static int counter = 0;
	private final int id = counter++;
	private SynchronousQueue<Food> dish = new SynchronousQueue<Food>();

	public Customer(Waiter waiter) {
		this.waiter = waiter;
	}

	public void deliver(Food food) throws InterruptedException {
		dish.put(food);
	}

	@Override
	public void run() {
		for (Course course : Course.values()) {
			Food food = course.randomSelection();
			try {
				waiter.placeOrder(this, food);
				System.out.println(this + " eating " + dish.take());
			} catch (InterruptedException e) {
				System.out.println(this + " waiting for " + course
						+ " interrupted");
				break;
			}
		}
		System.out.println(this + " finished meal, leaving");
	}

	@Override
	public String toString() {
		return "Customer " + id;
	}
}

class Order {
	private final Waiter waiter;
	private final Food food;
	private final Customer customer;

	public Order(Waiter waiter, Customer customer, Food food) {
		this.waiter = waiter;
		this.customer = customer;
		this.food = food;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public Food getFood() {
		return food;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "Order { " + food + " , from " + waiter + "}";
	}
}

class Kitchen implements Runnable {

	private List<Waiter> waiters = new ArrayList<Waiter>();
	private List<Chef> chefs = new ArrayList<Chef>();
	private BlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();
	private static Random rand = new Random(47);
	private ExecutorService exec;

	public void placeOrder(Order order) throws InterruptedException {
		orders.put(order);
	}

	public Order getOrder() throws InterruptedException {
		return orders.take();
	}

	public Kitchen(ExecutorService exec, int nWaiters, int nChefs) {
		this.exec = exec;
		for (int i = 0; i < nWaiters; i++) {
			Waiter waiter = new Waiter(this);
			waiters.add(waiter);
			exec.execute(waiter);
		}

		for (int i = 0; i < nChefs; i++) {
			Chef chef = new Chef(this);
			chefs.add(chef);
			exec.execute(chef);
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Waiter waiter = waiters.get(rand.nextInt(waiters.size()));
				Customer c = new Customer(waiter);
				exec.execute(c);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Kitchen interrupted");
		}
		System.out.println("Kitchen closed");
	}

}

public class RestaurantWithQueues {
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Kitchen kitchen = new Kitchen(exec, 5, 2);
		exec.execute(kitchen);
		System.out.println("Press 'Enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}
}
