package com.eric.java7.nio;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileToPath extends Displayer {
	public static void main(String[] args) {
		// File to Path
		File file = new File("./Listing.java");
		Path path = file.toPath();

		println(path.toString());

		// Path to File
		path = Paths.get("/usr/bin/zip");
		file = path.toFile();
		println(file.getAbsolutePath());
	}
}
