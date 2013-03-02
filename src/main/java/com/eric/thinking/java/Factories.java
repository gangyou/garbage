package com.eric.thinking.java;
/**
 * 内部类工厂实例
 * @author eric
 *
 */
interface Service{
	void method1();
	void method2();
}

interface ServiceFactory{
	Service getService();
}

class ServiceImpl1 implements Service{
	ServiceImpl1(){}

	@Override
	public void method1() {
		System.out.println("Service Implementation1 method1");;
	}

	@Override
	public void method2() {
		System.out.println("Service Implementation1 method2");
	}
	
	public static ServiceFactory factory = new ServiceFactory() {
		@Override
		public Service getService() {
			return new ServiceImpl1();
		}
	};
}

class ServiceImpl2 implements Service{
	ServiceImpl2(){}

	@Override
	public void method1() {
		System.out.println("Service Implementation2 method1");;
	}

	@Override
	public void method2() {
		System.out.println("Service Implementation2 method2");;
	}
	
	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new ServiceImpl2();
		}
	};
}

public class Factories {
	public static void serviceCustomer(ServiceFactory fact){
		Service s = fact.getService();
		s.method1();
		s.method2();
	}
	
	public static void main(String[] args) {
		serviceCustomer(ServiceImpl1.factory);
		serviceCustomer(ServiceImpl2.factory);
	}
}
