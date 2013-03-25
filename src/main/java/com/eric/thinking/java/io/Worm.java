package com.eric.thinking.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2290079251381937065L;
	private int n;

	public Data(int n) {
		this.n = n;
	}

	public String toString() {
		return Integer.toString(n);
	}

}

public class Worm implements Serializable {

	private static final long serialVersionUID = 6398276871271859136L;
	private static Random random = new Random(47);
	private Data[] d = { new Data(random.nextInt(10)),
			new Data(random.nextInt(10)), new Data(random.nextInt(10)) };
	private Worm next;
	private char c;

	public Worm(int i, char x) {
		System.out.println("Worm constructor: " + i);
		c = x;
		if (--i > 0) {
			next = new Worm(i, (char) (x + 1));
		}
	}

	public Worm() {
		System.out.println("Default constructor");
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for (Data data : d) {
			result.append(data);
		}
		result.append(")");
		if (next != null) {
			result.append(next);
		}
		return result.toString();
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		Worm w = new Worm(6, 'a');
		System.out.println("w=" + w);

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"worm.out"));
		out.writeObject("Worm Storage\n");
		out.writeObject(w);
		out.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"worm.out"));
		String s = (String) in.readObject();
		Worm w1 = (Worm) in.readObject();
		System.out.println(s + " w1= " + w1);
		in.close();
	}
}
