package com.eric.java7.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Normalize extends Displayer {
	public static void main(String[] args) {
		Path path = Paths.get("./Listing.java");
		println(path.toString());
		
		path = path.normalize();
		println(path.toString());
	}
}
