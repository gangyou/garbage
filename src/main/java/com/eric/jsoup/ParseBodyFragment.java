package com.eric.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseBodyFragment {
	public static void main(String[] args) {
		String html = "<div><p>Lorem ipsum.</p>";
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		System.out.println(body);

		// Stay safe
		// If you are going to accept HTML input from a user,
		// you need to be careful to avoid cross-site scripting attacks.
		// See the documentation for the Whitelist based cleaner,
		// and clean the input with clean(String bodyHtml, Whitelist whitelist).
	}
}
