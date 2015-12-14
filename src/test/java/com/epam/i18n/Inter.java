package com.epam.i18n;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

public class Inter {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}


	@Test
	public void test() {
		Locale locale = new Locale("ua_UA");
		System.out.println("local" + locale);
//		ResourceBundle bundle = ResourceBundle.getBundle(
//				"messages", locale, new UTF8Control());
//		String resultString = bundle.getString("hello");
//		System.out.println("result " +resultString);
	}

}
