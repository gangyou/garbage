package com.eric.thinking.java;

public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	
	Event(long delayTime){
		this.delayTime = delayTime;
	}
	
	public void start(){
		eventTime = System.nanoTime() + delayTime;
	}
	
	public boolean ready(){
		return System.nanoTime() >= eventTime;
	}
	
	public abstract void action();
}
