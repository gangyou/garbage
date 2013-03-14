package com.eric.thinking.java.generics.cargoship;

import java.util.ArrayList;

import com.eric.thinking.java.generics.Generator;

public class CargoBox extends ArrayList<Cargo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1044552729223948621L;

	public CargoBox(int size){
		Generator<Cargo> generator = Cargo.generator;
		for(int i =0; i < size; i++){
			add(generator.next());
		}
	}
}
