package com.eric.thinking.java.enumerated.menu;

import com.eric.thinking.java.util.Enums;

public enum Course {
	APPETIZER(Food.Appetizer.class), MAINCOURSE(Food.MainCourse.class), DESSERT(
			Food.Dessert.class), COFFEE(Food.Coffee.class);

	private Food[] values;

	private Course(Class<? extends Food> course) {
		values = course.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}
}
