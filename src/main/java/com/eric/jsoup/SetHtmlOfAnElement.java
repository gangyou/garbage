package com.eric.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SetHtmlOfAnElement {
	public static void main(String[] args) {
		Document doc = Jsoup.parse("<div>");
		Element div = doc.select("div").first();
		div.html("<p>lorem ipsum</p>");
		div.prepend("<p>First</p>");
		div.append("<p>Last</p>");
		System.out.println(div);
		
		div.wrap("<li></li>");
		System.out.println(div);
	}
}
