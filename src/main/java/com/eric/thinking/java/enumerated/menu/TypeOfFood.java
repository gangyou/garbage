package com.eric.thinking.java.enumerated.menu;

import com.eric.thinking.java.enumerated.menu.Food.Appetizer;
import com.eric.thinking.java.enumerated.menu.Food.Coffee;
import com.eric.thinking.java.enumerated.menu.Food.Dessert;
import com.eric.thinking.java.enumerated.menu.Food.MainCourse;

public class TypeOfFood {
	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.BURRITO;
		food = Dessert.BLACK_FOREST_CAKE;
		food = Coffee.BLACK_COFFEE;
		System.out.println(food);
	}
}
