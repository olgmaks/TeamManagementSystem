package com.epam.web.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ChangeLanguageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(ChangeLanguageServlet.class);

	public ChangeLanguageServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("change language do get");
		HttpSession session = request.getSession(true);
		String language = (String) request.getSession(true).getAttribute(
				"language");
		LOG.info("current language : " + language);

		if (language != null) {
			if (language.equals("ua_UA")) {
				LOG.info("language was ua_UA. changing to en_US ...");
				language = "en_US";
				session.setAttribute("languageimageref", "img/icons/change-language-ua.png");
			} else if (language.equals("en_US")) {
				LOG.info("language was en_US. changing to ua_UA ...");
				language = "ua_UA";
				session.setAttribute("languageimageref", "img/icons/change-language-eng.png");
			}
		} else {
			LOG.info("something is going wrong. language has still null value");
		}

		session.setAttribute("language", language);
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
