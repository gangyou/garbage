package com.eric.thinking.java.containers;

import java.util.LinkedList;
import java.util.Queue;

import com.eric.thinking.java.generics.Generator;

public class QueueBehavior {
	private static int count = 10;

	static <T> void test(Queue<T> queue, Generator<T> gen) {
		for (int i = 0; i < count; i++) {
			queue.offer(gen.next());
		}
		while (queue.peek() != null) {
			// System.out.println(queue.poll() + " ");
			System.out.println(queue.remove() + " ");
		}
		System.out.println();
	}

	static class Gen implements Generator<String> {
		String[] s = ("one two three four five six seven eight nine ten")
				.split(" ");
		int i = 0;

		@Override
		public String next() {
			return s[i++];
		}

	}
	
	public static void main(String[] args) {
		test(new LinkedList<String>(), new Gen());
	}
}
