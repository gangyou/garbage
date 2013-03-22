package com.eric.thinking.java.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {
	public static void command(String command) {
		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader result = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String s;
			while ((s = result.readLine()) != null) {
				System.out.println(s);
			}

			BufferedReader errors = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			while ((s = errors.readLine()) != null) {
				System.err.println(s);
				err = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (err) {
			throw new OSExecuteException("Error executing " + command);
		}
	}
	
	public static void main(String[] args) {
		OSExecute.command("ls -alt");
	}
}
