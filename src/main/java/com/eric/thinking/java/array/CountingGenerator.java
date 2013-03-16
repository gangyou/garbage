package com.eric.thinking.java.array;

import java.util.Arrays;

import com.eric.thinking.java.generics.Generator;

public class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean> {
		private boolean value = false;

		@Override
		public java.lang.Boolean next() {
			value = !value;
			return value;
		}

	}

	public static class Byte implements Generator<java.lang.Byte> {
		private byte value = 0;

		@Override
		public java.lang.Byte next() {
			return value++;
		}
	}

	public static void main(String[] args) {
		for (Class<?> type : CountingGenerator.class.getClasses()) {
			System.out.println(type.getSimpleName() + ": ");
			try {
				Generator<?> g = (Generator<?>) type.newInstance();
				for (int i = 0; i < 10; i++) {
					System.out.printf(g.next() + " ");
				}
				System.out.println();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
