package com.eric.thinking.java.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.eric.thinking.java.generics.BasicGenerator;
import com.eric.thinking.java.generics.Generator;

class ExchangeProducer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private Generator<T> generator;
	private List<T> holder;

	public ExchangeProducer(Exchanger<List<T>> exchanger,
			Generator<T> generator, List<T> list) {
		this.exchanger = exchanger;
		this.generator = generator;
		this.holder = list;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int i = 0; i < ExchangerDemo.size; i++) {
					T value = generator.next();
					holder.add(value);
					System.out.println("Produce " + value);
				}
				holder = exchanger.exchange(holder);
			}
		} catch (InterruptedException e) {

		}
	}
}

class ExchangeConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;

	public ExchangeConsumer(Exchanger<List<T>> exchanger, List<T> list) {
		this.exchanger = exchanger;
		this.holder = list;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for (T x : holder) {
					value = x;
					holder.remove(x);
					System.out.println("Consume " + value);
				}
			}
		} catch (InterruptedException e) {

		}
		System.out.println("Final value : " + value);
	}
}

public class ExchangerDemo {

	public static int size = 10;
	public static int delay = 5;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
		List<Fat> producerList = new CopyOnWriteArrayList<Fat>();
		List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();
		exec.execute(new ExchangeProducer<Fat>(xc, BasicGenerator
				.create(Fat.class), producerList));
		exec.execute(new ExchangeConsumer<Fat>(xc, consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}

}
