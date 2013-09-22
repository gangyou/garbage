package com.eric.java7.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListingXML extends Displayer {
	public static void main(String[] args) {
		Path dir = Paths.get("/home/eric/workspace-java/garbage");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
				"*.xml")) {
			for (Path path : stream) {
				println(path.getFileName().toString());
			}
		} catch (IOException e) {
			println(e.getMessage());
		}
	}
}
