package com.eric.java7.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAttributes extends Displayer {
	public static void main(String[] args) throws IOException {
		Path zip = Paths.get("/usr/bin/zip");
		println(Files.getLastModifiedTime(zip));
		println(Files.size(zip));
		println(Files.isSymbolicLink(zip));
		println(Files.isDirectory(zip));
		println(Files.readAttributes(zip, "*"));
	}
}
