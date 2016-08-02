package com.kraqqen.util.logging;

public class KELogger {
	
	static boolean physicsEngineLogging = false;
	
	//GETTERS
	public static boolean isPELogging(){
		return physicsEngineLogging;
	}
	
	//SETTERS
	public static void setPELogging(boolean bool){
		physicsEngineLogging = bool;
	}

}
