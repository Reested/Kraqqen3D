package com.kraqqen.core;

import java.io.IOException;

import com.kraqqen.editor.MainTestLoop;
import com.kraqqen.util.envr.EnvironmentSettings;
import com.kraqqen.util.sys_info.SystemSettings;

public class KraqqenCore {
	
	public static void main(String[] args) {
		EnvironmentSettings environmentSettings = new EnvironmentSettings();
		SystemSettings settings = new SystemSettings();
		

		environmentSettings.load();

		
		System.out.println("DONE!");
		
		new MainTestLoop().run();
		
		environmentSettings.save();
		
		System.out.println("saved!");
	}

}
