package com.eric.thinking.java.containers;

import java.util.PriorityQueue;

public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
	private static final long serialVersionUID = 1204686055358199864L;

	static class ToDoItem implements Comparable<ToDoItem> {
		private char primary;
		private int secondary;
		private String item;

		public ToDoItem(String td, char pri, int sec) {
			primary = pri;
			secondary = sec;
			item = td;
		}

		@Override
		public int compareTo(ToDoItem o) {
			if (primary > o.primary)
				return +1;
			if (primary == o.primary) {
				if (secondary > o.secondary) {
					return +1;
				} else if (secondary == o.secondary) {
					return 0;
				}
			}
			return -1;
		}

		public String toString() {
			return Character.toString(primary) + secondary + ": " + item;
		}
	}

	public void add(String td, char pri, int sec) {
		super.add(new ToDoItem(td, pri, sec));
	}

	public static void main(String[] args) {
		ToDoList toDoList = new ToDoList();
		toDoList.add("Empty trash", 'C', 4);
		toDoList.add("Feed Dog", 'A', 2);
		while (!toDoList.isEmpty()) {
			System.out.println(toDoList.poll());
		}
	}
}
