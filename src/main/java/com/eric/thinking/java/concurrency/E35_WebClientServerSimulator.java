package com.eric.thinking.java.concurrency;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WebClient {
	private final int serviceTime;

	public WebClient(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return this.serviceTime;
	}

	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}

class WebClientLine extends ArrayBlockingQueue<WebClient> {

	public WebClientLine(int capacity) {
		super(capacity);
	}

	private static final long serialVersionUID = 6756971479468323595L;

	@Override
	public String toString() {
		if (this.size() == 0) {
			return "[Empty]";
		}
		StringBuilder result = new StringBuilder();
		for (WebClient wc : this) {
			result.append(wc);
		}
		return result.toString();
	}
}

class WebClientGenerator implements Runnable {
	private WebClientLine clients;
	volatile int loadFactor = 1;
	private static Random rand = new Random();

	public WebClientGenerator(WebClientLine clients) {
		this.clients = clients;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				clients.put(new WebClient(rand.nextInt(1000)));
				TimeUnit.MILLISECONDS.sleep(1000 / loadFactor);
			}
		} catch (InterruptedException e) {
			System.out.println("WebClientGenerator interrupted");
		}
		System.out.println("WebClientGenerator terminating");
	}
}

class Server implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private WebClientLine clients;

	public Server(WebClientLine clients) {
		this.clients = clients;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				WebClient client = clients.take();
				TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " terminating");
	}

	@Override
	public String toString() {
		return "Server " + id;
	}

	public String shortString() {
		return "S" + id;
	}
}

class SimulationManager implements Runnable {
	private ExecutorService exec;
	private WebClientGenerator gen;
	private WebClientLine clients;
	private Queue<Server> servers = new LinkedList<Server>();
	private int adjustmentPeriod;
	// Indicates whether the queue is stable
	private boolean stable = true;
	private int prevSize = 0;

	public SimulationManager(ExecutorService exec, WebClientGenerator gen,
			WebClientLine clients, int adjustmentPeriod, int n) {
		this.exec = exec;
		this.gen = gen;
		this.clients = clients;
		this.adjustmentPeriod = adjustmentPeriod;
		for (int i = 0; i < n; i++) {
			Server server = new Server(clients);
			exec.execute(server);
			servers.add(server);
		}
	}

	// check if the former request has been processed by server
	// if the requests in queue is increasing, the server become unstable
	public void adjustLoadFactor() {
		if (clients.size() > prevSize) {
			// Was stable last time
			if (stable) {
				stable = false;
			} else if (!stable) {
				// Not stable for a second time
				System.out.println("Peak load factor: " + gen.loadFactor);
				exec.shutdownNow();
			}
		} else {
			System.out.println("New load factor: " + ++gen.loadFactor);
			stable = true;
		}
		prevSize = clients.size();
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				System.out.print(clients + "{");
				for (Server server : servers) {
					System.out.print(server.shortString() + " ");
				}
				System.out.println("}");
				adjustLoadFactor();
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " terminating");
	}

	@Override
	public String toString() {
		return "SimulationManager";
	}
}

public class E35_WebClientServerSimulator {
	static final int MAX_LINE_SIZE = 50;
	static final int NUM_OF_SERVERS = 3;
	static final int ADJUSTMENT_PERIOD = 1000;

	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		WebClientLine clients = new WebClientLine(MAX_LINE_SIZE);
		WebClientGenerator gen = new WebClientGenerator(clients);
		exec.execute(gen);
		exec.execute(new SimulationManager(exec, gen, clients,
				ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
		System.out.println("Press 'Enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}
}
