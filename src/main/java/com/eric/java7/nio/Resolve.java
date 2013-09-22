package com.eric.java7.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Resolve extends Displayer {
	public static void main(String[] args) {
		Path prefix = Paths.get("/uat");
		Path completePath = prefix.resolve("conf/application.properties");
		println(completePath.toString());
	}
}
