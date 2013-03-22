package com.eric.thinking.java.io;

import java.io.File;
import java.io.IOException;

public class ProcessFiles {
	public interface Strategy {
		void process(File file);
	}

	private Strategy strategy;
	private String ext;

	public ProcessFiles(Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}

	public void start(String[] files) {
		try {
			if (files.length == 0) {
				processDirectoryTree(new File("."));
			} else {
				for (String file : files) {
					File fileArg = new File(file);
					if (fileArg.isDirectory()) {
						processDirectoryTree(fileArg);
					} else {
						if (!file.endsWith("." + ext)) {
							file = file + "." + ext;
						}
						strategy.process(new File(file).getCanonicalFile());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processDirectoryTree(File root) {
		for (File file : Directory.walk(root, ".*\\." + ext)) {
			strategy.process(file);
		}
	}

	public static void main(String[] args) {
		new ProcessFiles(new ProcessFiles.Strategy() {
			@Override
			public void process(File file) {
				try {
					System.out.println(file.getCanonicalPath());
				} catch (IOException e) {

				}
			}
		}, "java").start(args);
	}
}
