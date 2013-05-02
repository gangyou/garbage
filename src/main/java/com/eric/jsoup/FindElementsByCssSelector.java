package com.eric.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FindElementsByCssSelector {
	public static void main(String[] args) throws IOException {
		File input = new File("/tmp/index.html");
		Document doc = Jsoup.parse(input, "utf-8", "http://example.org");

		Elements links = doc.select("a[href]");
		System.out.println(links);
		Elements pngs = doc.select("img[src$=.png]");
		System.out.println(pngs);

		Element masthead = doc.select("div.footer").first();
		System.out.println(masthead);

		Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
		System.out.println(resultLinks);
	}
}
