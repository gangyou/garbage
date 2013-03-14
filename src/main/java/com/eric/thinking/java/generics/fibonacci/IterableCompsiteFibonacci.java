package com.eric.thinking.java.generics.fibonacci;

import java.util.Iterator;

public class IterableCompsiteFibonacci implements Iterable<Integer> {

	private int size = 0;

	public IterableCompsiteFibonacci(int size) {
		this.size = size;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private Fibonacci fib = new Fibonacci();
			private int count = size;
			private int index = 0;
			@Override
			public boolean hasNext() {
				return index < count;
			}

			@Override
			public Integer next() {
				int result = fib.next();
				index++;
				return result;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
			}
		};
	}
	
	public static void main(String[] args) {
		for(int i : new IterableCompsiteFibonacci(18)){
			System.out.println(i);
		}
	}

}
