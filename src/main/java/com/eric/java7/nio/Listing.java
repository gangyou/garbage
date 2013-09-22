package com.eric.java7.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Listing extends Displayer {
	public static void main(String[] args) {
		Path listing = Paths.get("/usr/bin/zip");
		println(String.format("File Name [%s]", listing.getFileName()));
		println(String.format("Number of name elements in paht [%d]",
				listing.getNameCount()));
		println(String.format("Parent Path [%s]", listing.getParent()));
		println(String.format("Root of path [%s]", listing.getRoot()));
		println(String.format("Subpath from Root [%s]", listing.subpath(0, 2)));
	}

}
