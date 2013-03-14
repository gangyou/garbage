package com.eric.thinking.java.generics.ducktype;
class Dog implements DuckType{
	public void speak(){
		System.out.println("Woof!");
	}
	public void sit(){
		System.out.println("Sitting!");
	}
}

class Robot implements DuckType{
	public void speak(){
		System.out.println("Click!");
	}
	public void sit(){
		System.out.println("Clank!");
	}
}

interface DuckType{
	void speak();
	void sit();
}
public class DuckTypeDemo {
	public static <T extends DuckType> void perform(T ducktype){
		ducktype.speak();
		ducktype.sit();
	}
	
	public static void main(String[] args) {
		Dog dog = new Dog();
		Robot robot = new Robot();
		perform(dog);
		perform(robot);
	}
}
