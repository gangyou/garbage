package com.eric.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class CleanUntrustHml {
	public static void main(String[] args) {
		String unsafe = 
				  "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
		String safe = Jsoup.clean(unsafe, Whitelist.basic());
		System.out.println(safe);
	}
}
