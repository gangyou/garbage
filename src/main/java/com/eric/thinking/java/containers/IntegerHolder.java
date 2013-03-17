package com.eric.thinking.java.containers;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class IntegerHolder implements Comparable<IntegerHolder> {
	private Integer value;
	private static Random random = new Random();

	public IntegerHolder() {
		value = random.nextInt(100);
	}

	@Override
	public int compareTo(IntegerHolder o) {
		return value.compareTo(o.value);
	}

	@Override
	public String toString() {
		return "hold " + value;
	};

	public static void main(String[] args) {
		Queue<IntegerHolder> q = new PriorityQueue<IntegerHolder>();
		for (int i = 0; i < 10; i++) {
			q.offer(new IntegerHolder());
		}
		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}
}
