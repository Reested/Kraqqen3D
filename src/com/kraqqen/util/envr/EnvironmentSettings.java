package com.kraqqen.util.envr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.kraqqen.util.sys_info.SystemSettings;

public class EnvironmentSettings {
	
	private static final String SETTING_LOCATION = "CONFIG/settings.cfg";
	
	SystemSettings settings = new SystemSettings();
	
	public void load(){
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(SETTING_LOCATION));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
					System.out.println("I Found ThreadCpuTime");
					if(tokens[2].equals("true")){
						settings.setThreadCpuTime(true);
					}else if (tokens[2].equals("false")){
						settings.setThreadCpuTime(false);
					}
				}
				else if(tokens[0].equals("ThreadContentionMonitoring"))
				{
					System.out.println("I Found ThreadContentionMonitoring");
					if(tokens[2].equals("true")){
						settings.setThreadContentionMonitoringEnabled(true);
					}else if (tokens[2].equals("false")){
						settings.setThreadContentionMonitoringEnabled(false);
					}
				}
				else if(tokens[0].equals("ClassLoadingVerbose"))
				{
					System.out.println("I Found ClassLoadingVerbose");
					if(tokens[2].equals("true")){
						settings.setClassLoadingVerbose(true);
					}else if (tokens[2].equals("false")){
						settings.setClassLoadingVerbose(false);
					}
				}
				else if(tokens[0].equals("MemoryVerbose"))
				{
					System.out.println("I Found MemoryVerbose");
					if(tokens[2].equals("true")){
						settings.setMemoryVerbose(true);
					}else if (tokens[2].equals("false")){
						settings.setMemoryVerbose(false);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save(){
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileOutputStream(SETTING_LOCATION));
			
			printWriter.println("#########################");
			printWriter.println("# KRAQQEN3D CONFIG FILE # ");
			printWriter.println("#########################");
			printWriter.println("#");
			printWriter.println("# WARNING: Do Not Modify");
			printWriter.println("#");
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
