package com.eric.thinking.java.generics.coffee;

import java.util.Collection;

import com.eric.thinking.java.generics.Generator;

public class Fill2 {
	public static <T> void fill(Addable<T> addable,
			Class<? extends T> classToken, int size) {
		for (int i = 0; i < size; i++) {
			try {
				addable.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}

	public static <T> void fill(Addable<T> addable, Generator<T> generator,
			int size) {
		for (int i = 0; i < size; i++) {
			addable.add(generator.next());
		}
	}
}

interface Addable<T> {
	void add(T t);
}

class AddableCollectionAdapter<T> implements Addable<T> {
	private Collection<T> c;

	public AddableCollectionAdapter(Collection<T> c) {
		this.c = c;
	}

	public void add(T item) {
		c.add(item);
	}
}