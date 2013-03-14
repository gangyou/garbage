package com.eric.thinking.java.generics.fibonacci;

import java.util.Iterator;

public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {

	private int size;
	public IterableFibonacci(int size){
		this.size = size;
	}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int count = size;
			@Override
			public boolean hasNext() {
				return count > 0;
			}

			@Override
			public Integer next() {
				count--;
				return IterableFibonacci.this.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	public static void main(String[] args) {
		for(int i : new IterableFibonacci(18)){
			System.out.println(i);
		}
	}
}
