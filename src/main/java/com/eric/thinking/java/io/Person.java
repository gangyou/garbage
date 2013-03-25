package com.eric.thinking.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Assert;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -831869912864939346L;
	private String name;
	private Address address;

	public Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Name:" + name + ", Address:" + address;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		Person eric = new Person("eric", new Address(2222));
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"person.out"));
		out.writeObject(eric);
		out.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"person.out"));
		Person ericRestore = (Person) in.readObject();
		in.close();
		Assert.assertEquals(eric.toString(), ericRestore.toString());
	}
}
