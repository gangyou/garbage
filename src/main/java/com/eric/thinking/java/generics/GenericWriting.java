package com.eric.thinking.java.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericWriting {

	static <T> void writeExact(List<T> list, T item){
		list.add(item);
	}
	static List<Fruit> fruits = new ArrayList<Fruit>();
	static List<Apple> apples = new ArrayList<Apple>();
	static <T> void writeWildcard(List<? super T> list, T item){
		list.add(item);
	}
	static void f1(){
		writeExact(apples, new Apple());
	}
	static void f2(){
		writeWildcard(apples, new Apple());
	}
}

class Fruit {
}

class Apple extends Fruit {

}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}