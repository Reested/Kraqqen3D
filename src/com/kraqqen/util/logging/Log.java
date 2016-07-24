package com.kraqqen.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public void doSomeThingAndLog() {

		// ... more code

		// now we demo the logging

		// set the LogLevel to Severe, only severe Messages will be written

		LOGGER.setLevel(Level.SEVERE);

		LOGGER.severe("Info Log5");

		LOGGER.warning("Info Log6");

		LOGGER.info("Info Log7");

		LOGGER.finest("Really not important8");

		// set the LogLevel to Info, severe, warning and info will be written

		// finest is still not written

		LOGGER.setLevel(Level.INFO);

		LOGGER.severe("Info Log1");

		LOGGER.warning("Info Log2");

		LOGGER.info("Info Log3");

		LOGGER.finest("Really not important4");

	}
}
