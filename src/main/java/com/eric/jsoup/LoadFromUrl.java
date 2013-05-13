package com.eric.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LoadFromUrl {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://jsoup.org").get();
		String title = doc.title();
		System.out.println(title);
		
		Document doc1 = Jsoup.connect("http://www.example.org")
				.data("query", "Java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(3000)
				.post();
		System.out.println(doc1);
	}
}
