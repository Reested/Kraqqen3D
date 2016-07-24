package com.kraqqen.engine.application;

import com.kraqqen.util.crh_hndl.KraqqenException;

public class Application {
	
	private static int renderAPI = RenderAPI.CPU;
	
	/** Sets the rendering API Kraqqen will use. */
	public static void UseAPI(int api)
	{
		renderAPI = api;
	}

	/** Returns the rendering API Kraqqen is using. */
	public static int GetCurrentAPI()
	{
		return renderAPI;
	}
	
	/** Returns the absolute path to the builtin managed engine assembly file. */
	public static String getEngineAssemblyPath()
	{
		//get installed location of Kraqqen from a file, from a .AppData folder or something
		return "C:\\";
	}

	/** Returns the absolute path to the game managed assembly file. */
	public static String getGameAssemblyPath()
	{
		try {
			return System.getProperty(Application.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (Exception e) {
			e.printStackTrace();
			
			try
			{
				throw new KraqqenException("ENGINE", "Could not locate the game assembly path.");
			} catch (Exception e1)
			{
				e.printStackTrace();
			}
			
			return "";
		}
	}

	/** Returns the absolute path to the folder where script assemblies are located in. */
	public static String getScriptAssemblyPath()
	{
		String g = getGameAssemblyPath();
		return (g.endsWith("\\") || g.endsWith("/") ? g + ".ksp" : "\\.ksp");
	}
}
