package com.kraqqen.util.environment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kraqqen.util.system_info.SystemSettings;

public class EnvironmentSettings {
	
	private static final String ENVIRONMENT_SETTING_LOCATION = "CONFIG/settings.cfg";
	
	SystemSettings settings = new SystemSettings();
	
	public void load(){
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(ENVIRONMENT_SETTING_LOCATION));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties properties = new Properties(System.getProperties());
		try {
			properties.load(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//System.getProperties().list(System.out);

		String line;

		try {
			while((line = file.readLine()) != null)
			{
				String[] tokens = line.split(" ");
				tokens = RemoveEmptyStrings(tokens);

				if(tokens.length == 0 || tokens[0].equals("#"))
					continue;
				else if(tokens[0].equals("ThreadCpuTime"))
				{
					//System.out.println("I Found ThreadCpuTime");
					if(tokens[2].equals("true")){
						settings.setThreadCpuTime(true);
					}else if (tokens[2].equals("false")){
						settings.setThreadCpuTime(false);
					}
				}
				else if(tokens[0].equals("ThreadContentionMonitoring"))
				{
					//System.out.println("I Found ThreadContentionMonitoring");
					if(tokens[2].equals("true")){
						settings.setThreadContentionMonitoringEnabled(true);
					}else if (tokens[2].equals("false")){
						settings.setThreadContentionMonitoringEnabled(false);
					}
				}
				else if(tokens[0].equals("ClassLoadingVerbose"))
				{
					//System.out.println("I Found ClassLoadingVerbose");
					if(tokens[2].equals("true")){
						settings.setClassLoadingVerbose(true);
					}else if (tokens[2].equals("false")){
						settings.setClassLoadingVerbose(false);
					}
				}
				else if(tokens[0].equals("MemoryVerbose"))
				{
					//System.out.println("I Found MemoryVerbose");
					if(tokens[2].equals("true")){
						settings.setMemoryVerbose(true);
					}else if (tokens[2].equals("false")){
						settings.setMemoryVerbose(false);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileOutputStream(ENVIRONMENT_SETTING_LOCATION));
			
			printWriter.println("#########################");
			printWriter.println("# KRAQQEN3D CONFIG FILE # ");
			printWriter.println("#########################");
			printWriter.println("#");
			printWriter.println("# WARNING: Do Not Modify. If Modified, Game Engine May Not Work.");
			printWriter.println("#");
			
			
			// This is where all of the code goes for modifying the "settings.cfg" file 
			printWriter.println("ThreadCpuTime = " + settings.getThreadCpuTime());
			printWriter.println("ThreadContentionMonitoring = " + settings.getThreadContentionMonitoringEnabled());
			printWriter.println("ClassLoadingVerbose = " + settings.getClassLoadingVerbose());
			printWriter.println("MemoryVerbose = " + settings.getMemoryVerbose());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}
	
	private static String[] RemoveEmptyStrings(String[] data)
	{
		List<String> result = new ArrayList<String>();
		
		for(int i = 0; i < data.length; i++)
			if(!data[i].equals(""))
				result.add(data[i]);
		
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
}
