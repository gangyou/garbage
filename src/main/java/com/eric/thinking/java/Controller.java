package com.eric.thinking.java;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	private List<Event> eventList = new ArrayList<Event>();
	public void addEvent(Event e){
		eventList.add(e);
	}
	
	public void run(){
		while(eventList.size() > 0){
			for(Event e: new ArrayList<Event>(eventList)){
				if (e.ready()){
					System.out.println(e);
					e.action();
					eventList.remove(e);
				}
			}
		}
	}
}
