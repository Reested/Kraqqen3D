package com.kraqqen.util.dll;

import java.io.File;

public class DllLoader {

	public void loadDll(String name) {
		
		File file = new File(name);
		String location = file.getAbsolutePath();
		
		try {
			System.load(location);
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

}
