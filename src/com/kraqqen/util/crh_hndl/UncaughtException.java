package com.kraqqen.util.crh_hndl;

import com.kraqqen.util.logging.Log;

public class UncaughtException implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Throwable: " + e.getMessage());
		System.out.println(t.toString());
	}

	public void uncaughtException(Thread t, Throwable e, String loggerName) {
		Log.getLogger(loggerName).severe("Uncaught Exception in thread '" + t.getName() + "'" + e.getMessage());;
		System.exit(1);
	}
}
