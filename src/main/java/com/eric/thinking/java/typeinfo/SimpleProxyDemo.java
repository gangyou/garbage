package com.eric.thinking.java.typeinfo;

interface Interface{
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface{
	public void doSomething(){
		System.out.println("doSomething");
	}
	public void somethingElse(String arg){
		System.out.println("somethingElse "+ arg);
	}
}

class SimpleProxy implements Interface{
	private Interface proxied;
	private static int doSomethingTimes = 0;
	private static int somethingElseTimes = 0;
	public SimpleProxy(Interface inte){
		this.proxied = inte;
	}
	public void doSomething(){
		System.out.println("invoked doSomething:" + (++doSomethingTimes) + " Times");
		System.out.println("SimpleProxy doSomething");
		proxied.doSomething();
	}
	public void somethingElse(String arg){
		System.out.println("invoked somethingElse:" + (++somethingElseTimes) + " Times");
		System.out.println("SimpleProxy somethingElse " + arg);
		proxied.somethingElse(arg);
	}
}
public class SimpleProxyDemo {
	public static void consumer(Interface iface){
		iface.doSomething();
		iface.somethingElse("Bonobo");
	}
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}
}
