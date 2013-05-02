package com.eric.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExtractThingsFromElement {
	public static void main(String[] args) {
		String html = "<p>An <a id='example' class='link' href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);
		Element link = doc.select("a").first();

		String text = doc.body().text(); // An example link.
		String linkHref = link.attr("href"); // http://example.com/
		String linkText = link.text(); // example

		String linkOuterH = link.outerHtml(); // <a
												// href="http://example.com/"><b>example</b></a>
		String linkInnerH = link.html(); // <b>example</b>

		String id = link.id(); // example
		String tagName = link.tagName(); // a
		String className = link.className(); // link

	}
}
