package com.eric.java7.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FileCreate extends Displayer {
	public static void main(String[] args) throws IOException {
		Path target = Paths.get("/var/tmp/test");
		Set<PosixFilePermission> perms = PosixFilePermissions
				.fromString("rw-rw-rw-");
		FileAttribute<Set<PosixFilePermission>> attrs = PosixFilePermissions
				.asFileAttribute(perms);
		if (target.toFile().exists()) {
			println("Deleting file [%s]", target);
			Files.delete(target);
		}
		println("Creating File [%s]", target);
		Files.createFile(target, attrs);
	}
}
