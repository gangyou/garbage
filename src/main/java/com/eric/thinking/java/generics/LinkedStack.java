package com.eric.thinking.java.generics;

public class LinkedStack<T> {
	private static class Node<T>{
		T item;
		Node<T> next;
		Node() { item = null; next = null;}
		Node(T item, Node<T> next){
			this.item = item;
			this.next = next;
		}
		boolean empty(){
			return item == null && next == null;
		}
	}
	private Node<T> top = new Node<T>();
	public void push(T item){
		top = new Node<T>(item, top);
	}
	public T pop(){
		T result = top.item;
		if(!top.empty()){
			top = top.next;
		}
		return result;
	}
}
