package com.eric.thinking.java.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {
	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		for (Method m : cl.getDeclaredMethods()) {
			UseCase uc = m.getAnnotation(UseCase.class);
			if (uc != null) {
				int id = uc.id();
				System.out.println("Found Use Case: " + id + " "
						+ uc.description());
				useCases.remove(new Integer(id));
			}
		}

		for (Integer id : useCases) {
			System.out.println("Warning: Missing use case-" + id);
		}
	}

	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 47, 48, 49, 50);
		trackUseCases(useCases, PasswordUtils.class);
	}
}
