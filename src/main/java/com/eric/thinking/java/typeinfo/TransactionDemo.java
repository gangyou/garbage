package com.eric.thinking.java.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface DB {
	void add() throws Exception;

	void update() throws Exception;

	void delete() throws Exception;

	void select() throws Exception;
}

class DBImpl implements DB {

	@Override
	public void add() throws Exception {
		System.out.println("add");
	}

	@Override
	public void update() throws Exception {
		System.out.println("update with exception");
		throw new Exception("Update exception");
	}

	@Override
	public void delete() throws Exception {
		System.out.println("delete");
	}

	@Override
	public void select() throws Exception {
		System.out.println("Select");
	}
}

class TransactionHandler implements InvocationHandler {
	private DB proxied;

	public TransactionHandler(DB proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		try {
			result = method.invoke(proxied, args);
		} catch (Exception e) {
			System.out.println("Rollback");
			return null;
		}
		System.out.println("Commit");
		return result;
	}

}

public class TransactionDemo {
	public static void main(String[] args) throws Exception{
		DB db = new DBImpl();
		DB proxy = (DB) Proxy.newProxyInstance(DB.class.getClassLoader(),
				new Class[] { DB.class }, new TransactionHandler(db));
		
		proxy.add();
		proxy.update();
		proxy.delete();
		proxy.select();
	}
}
