package com.eric.thinking.java.generics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

class MixinProxy implements InvocationHandler {
	Map<String, Object> delegatesByMethod;

	public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
		delegatesByMethod = new HashMap<String, Object>();
		for (TwoTuple<Object, Class<?>> pair : pairs) {
			for (Method method : pair.second.getMethods()) {
				String methodName = method.getName();
				if (!delegatesByMethod.containsKey(methodName)) {
					delegatesByMethod.put(methodName, pair.first);
				}
			}
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		Object delegate = delegatesByMethod.get(methodName);
		return method.invoke(delegate, args);
	}

	@SuppressWarnings("unchecked")
	public static Object newInstance(TwoTuple... tuples) {
		Class[] interfaces = new Class[tuples.length];
		for (int i = 0; i < tuples.length; i++) {
			interfaces[i] = (Class) tuples[i].second;
		}
		ClassLoader cl = tuples[0].first.getClass().getClassLoader();
		return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(tuples));
	}
}

public class DynamicProxyMixin {
	public static void main(String[] args) {
//		Object mixin = MixinProxy.newInstance(
//				tuple(new BasicImpl(), Basic.class),
//				tuple(new TimestampImpl(), Timestamp.class),
//				tuple(new SerialNumberedImpl(), SerialNUmbered.class)
//				);
		// Basic b = (Basic) mixin;
		// b.get();
	}
}
