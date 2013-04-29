package com.eric.thinking.java.concurrency.restaurant2;

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

class Order {
	private static int counter = 0;
	private final int id = counter++;
	private final Customer customer;
	private final WaitPerson waitPerson;
	private final Food food;

	public Order(Customer customer, WaitPerson waitPerson, Food food) {
		this.customer = customer;
		this.waitPerson = waitPerson;
		this.food = food;
	}

	public Food item() {
		return food;
	}

	public Customer getCustomer() {
		return customer;
	}

	public WaitPerson getWaitPerson() {
		return waitPerson;
	}

	@Override
	public String toString() {
		return String.format(
				"Order: %1$s Item: %2$s for: %3$s server by: %4$s", id, food,
				customer, waitPerson);
	}

}

class Plate {
	private final Order order;
	private final Food food;

	public Plate(Order order, Food food) {
		this.order = order;
		this.food = food;
	}

	public Order getOrder() {
		return order;
	}

	public Food getFood() {
		return food;
	}

	@Override
	public String toString() {
		return food.toString();
	}
}

class Customer implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final WaitPerson waitPerson;
	// Only one course at a time can be received
	private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<Plate>();

	public Customer(WaitPerson waitPerson) {
		this.waitPerson = waitPerson;
	}

	public void deliver(Plate p) throws InterruptedException {
		placeSetting.put(p);
	}

	@Override
	public void run() {
		for (Course course : Course.values()) {
			Food food = course.randomSelection();
			try {
				waitPerson.placeOrder(this, food);
				System.out.println(this + "eating" + placeSetting.take());
			} catch (InterruptedException e) {
				System.out.println(this + " waiting for " + course
						+ " interrupt");
				break;
			}
		}
		System.out.println(this + " finished meal, leaving");
	}

	@Override
	public String toString() {
		return "Customer: " + id + " ";
	}

}

class WaitPerson implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();

	public WaitPerson(Restaurant rest) {
		this.restaurant = rest;
	}

	public void placeOrder(Customer customer, Food food) {
		try {
			restaurant.orders.put(new Order(customer, this, food));
		} catch (InterruptedException e) {
			System.out.println(this + " placeOrder interrupted");
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Block until a course is ready
				Plate plate = filledOrders.take();
				System.out.println(this + " received " + plate
						+ " delivering to " + plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}

	@Override
	public String toString() {
		return "WaitPerson " + id + " ";
	}
}

class Chef implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private Restaurant restaurant;
	private Random rand = new Random(47);

	public Chef(Restaurant rest) {
		this.restaurant = rest;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Block until an order appears
				Order order = restaurant.orders.take();
				Food requestItem = order.item();
				// Time to prepare item
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				Plate plate = new Plate(order, requestItem);
				order.getWaitPerson().filledOrders.put(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}
}

class Restaurant implements Runnable {
	private List<WaitPerson> waitPersons = new ArrayList<WaitPerson>();
	private List<Chef> chefs = new ArrayList<Chef>();
	private ExecutorService exec;
	private static Random rand = new Random(47);
	public BlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();

	public Restaurant(ExecutorService exec, int nWaitPerson, int nChefs) {
		this.exec = exec;
		for (int i = 0; i < nWaitPerson; i++) {
			WaitPerson waitPerson = new WaitPerson(this);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
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
				// A new customer arrives: assign a WaitPerson
				WaitPerson wp = waitPersons
						.get(rand.nextInt(waitPersons.size()));
				Customer c = new Customer(wp);
				exec.execute(c);
				TimeUnit.MILLISECONDS.sleep(100);
 			}
		} catch (InterruptedException e) {
			System.out.println("Restaurant interrupted");
		}
		System.out.println("Restaurant closing");
	}

}

public class RestaurantWithQueues {
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Restaurant restaurant = new Restaurant(exec, 5, 2);
		exec.execute(restaurant);
		System.out.println("Press 'Enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}
}
