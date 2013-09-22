package com.eric.java7.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Relative extends Displayer {
	public static void main(String[] args) {
		//TODO
		Path root = Paths.get("/");
		Path currentDir = Paths.get("./Listing.java");
		Path rootToWorkDir = currentDir.relativize(root);

		println(rootToWorkDir.toString());
	}
}
