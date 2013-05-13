package com.eric.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LoadFromFile {
	public static void main(String[] args) throws IOException {
		File input = new File("/tmp/index.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://www.yuyanchinatea.com");
		System.out.println(doc);
	}
}
