package com.epam.web.log4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jInit implements ServletContextListener {

	public void contextInitialized(final ServletContextEvent event) {
		final ServletContext servletContext = event.getServletContext();
		final String log4jConfigLocation = servletContext.getInitParameter("log4jConfigLocation");
		final String log4jFilename = servletContext.getRealPath(log4jConfigLocation);
		final DOMConfigurator configurator = new DOMConfigurator();
		configurator.doConfigure(log4jFilename, LogManager.getLoggerRepository());
	}

	public void contextDestroyed(final ServletContextEvent event) {
		// nothing
	}

}