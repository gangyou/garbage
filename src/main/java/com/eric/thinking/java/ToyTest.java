package com.eric.thinking.java;

interface HasBatteries {
}

interface WaterProof {
}

interface Shoots {
}

class Toy {
	Toy() {
	}

	Toy(int i) {
	}
}

class FancyToy extends Toy implements HasBatteries, WaterProof, Shoots {
	FancyToy() {
		super(1);
	}
}

public class ToyTest {

	static void printInfo(Class cc) {
		System.out.println("Class Name: " + cc.getName() + " is interface? ["
				+ cc.isInterface() + "]");
		System.out.println("Simple Name: " + cc.getSimpleName());
		System.out.println("Canonical Name: " + cc.getCanonicalName());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class c = null;
		try{
			c = Class.forName("com.eric.thinking.java.FancyToy");
		}catch(ClassNotFoundException e){
			System.out.println("Can't find FancyToy!");
			System.exit(1);
		}
		
		printInfo(c);
		for(Class face : c.getInterfaces()){
			printInfo(face);
		}
		Class up = c.getSuperclass();
		Object obj = null;
		try{
			obj = up.newInstance();
		}catch(InstantiationException e){
			System.out.println("Can't instantiate");
			System.exit(1);
		}catch(IllegalAccessException e){
			System.out.println("Cannot Access");
			System.exit(1);
		}
		printInfo(obj.getClass());
	}

}
