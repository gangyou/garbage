package com.eric.thinking.java.io;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1492993486906615061L;
	private int no;

	public Address(int no) {
		this.no = no;
	}

	public String toString() {
		return "NO: " + no;
	};
}
