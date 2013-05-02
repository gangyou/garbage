package com.eric.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DomNavigate {
	public static void main(String[] args) throws IOException {
		File input = new File("/tmp/index.html");
		Document doc = Jsoup.parse(input, "utf-8", "http://example.com");

		Element content = doc.getElementById("qrcode");
		System.out.println(content);

		Elements links = doc.getElementsByTag("a");
		for (Element link : links) {
			String linkHref = link.attr("href");
			String linkText = link.text();
			System.out.println(String.format("%s : %s", linkHref, linkText));
		}
	}
}
