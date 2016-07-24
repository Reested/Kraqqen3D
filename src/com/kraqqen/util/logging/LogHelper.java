package com.kraqqen.util.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class LogHelper {
	
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;
	static private FileHandler fileHTML;
	static private Formatter formatterHTML;

	public static void setup() throws IOException {
		


		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

	    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	    Logger rootLogger = Logger.getLogger("");
	    Handler[] handlers = rootLogger.getHandlers();
	    
	    if (handlers[0] instanceof ConsoleHandler) {
	    	rootLogger.removeHandler(handlers[0]);
	    }

	    logger.setLevel(Level.INFO);
	    fileTxt = new FileHandler("Error_Log/txt/Logging"+timeStamp+".txt");
	    fileHTML = new FileHandler("Error_Log/html/Logging"+timeStamp+".html");

	    formatterTxt = new SimpleFormatter();
	    fileTxt.setFormatter(formatterTxt);
	    logger.addHandler(fileTxt);

	    formatterHTML = new MyHtmlFormatter();
	    fileHTML.setFormatter(formatterHTML);
	    logger.addHandler(fileHTML);

	 }
}


