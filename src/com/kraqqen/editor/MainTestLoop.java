package com.kraqqen.editor;

import com.kraqqen.core.renderer.Window;

public class MainTestLoop {	

	boolean running = true;
	public void run()
	{
		Window window = new Window();
		window.show();
		
		window.setOnClose(() -> { running = false; });
		while(running)
		{
			window.Frame();
		}
		window.close();
	}
	
	public static void main(String[] args)
	{
		new MainTestLoop().run();
	}
	
}