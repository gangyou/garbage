package com.eric.thinking.java.typeinfo;

public class Person {
	public final String first;
	public final String last;
	public final String address;

	public Person(String first, String last, String address) {
		this.first = first;
		this.last = last;
		this.address = address;
	}

	public String toString() {
		return "Person: " + first + " " + last + " " + address;
	}

	public static class NullPerson extends Person implements Null {

		public NullPerson() {
			super("", "", "");
		}

		@Override
		public String toString() {
			return "Null Person";
		}

	}
	
	public static final NullPerson NULL = new NullPerson();
}

interface Null {
}