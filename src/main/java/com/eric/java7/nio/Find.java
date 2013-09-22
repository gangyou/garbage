package com.eric.java7.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Find extends Displayer {
	public static void main(String[] args) throws IOException {
		Path startingDir = Paths.get("/home/eric/workspace-java/garbage");
		Files.walkFileTree(startingDir, new FileJavaVisitor());
	}

	private static class FileJavaVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			if (file.toString().endsWith(".java")) {
				println(file.getFileName().toString());
			}
			return FileVisitResult.CONTINUE;
		}
	}
}
