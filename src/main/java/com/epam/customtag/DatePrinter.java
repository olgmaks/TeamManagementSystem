package com.epam.customtag;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DatePrinter extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jspContext = getJspContext();
		JspWriter writer = jspContext.getOut();
		String lang = (String) jspContext.getAttribute("language");
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yyyy",
				Locale.ENGLISH);
		if (lang != null && lang.equals("ua_UA")) {
			dateFormat = new SimpleDateFormat("EEE, dd, MMM, yyyy");
		}
		String resultString = new String(dateFormat.format(new Date()));
		writer.write(resultString);
	}

}