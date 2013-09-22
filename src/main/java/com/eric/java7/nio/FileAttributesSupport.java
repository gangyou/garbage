package com.eric.java7.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public class FileAttributesSupport extends Displayer {
	public static void main(String[] args) throws IOException {
		Path profile = Paths.get("/usr/eric/.profile");
		PosixFileAttributes attrs = Files.readAttributes(profile,
				PosixFileAttributes.class);
		
		Set<PosixFilePermission> posixPermissions = attrs.permissions();
		posixPermissions.clear();
		
	}
}
