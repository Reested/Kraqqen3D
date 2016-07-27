package com.kraqqen.util.crash_handling;

/*
 * An exception thrown by a Kraqqen object
 */
public class KraqqenException extends Exception {
	
	private static final long serialVersionUID = 6020612255599750454L;
	
	/**
	 * @parameter layer The layer of the object that caused this error
	 * @parameter message The message displayed
	 */
	public KraqqenException(String layer, String message)
	{
		super("[" + layer + "] " + message);
	}

}
