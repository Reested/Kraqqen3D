package com.kraqqen.core.renderer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.logging.Logger;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import com.kraqqen.util.logging.Log;

public class Window {
	
	private static Logger logger = Log.getLogger("global");
	
	private WindowAction closeAction;
	
	static
	{
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit())
		{			
			logger.severe("Unable to create a window context");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
	}
	
	private long glfwID;
	
	private String title;
	private int width;
	private int height;
	
	private boolean destroyed = false;
	
	public Window()
	{
		vals("Kraqqen3D Game", 800, 600);
		createWindow();
	}
	
	public Window(String title, int width, int height)
	{
		vals(title, width, height);
		createWindow();
	}
	
	public Window(String title)
	{
		vals(title, 800, 600);
		createWindow();
	}
	
	public Window(int width, int height)
	{
		vals("Kraqqen3D Game", width, height);
		createWindow();
	}
	
	public void setOnClose(WindowAction action)
	{
		closeAction = action;
	}
	
	public void Frame()
	{
		glfwSwapBuffers(glfwID);
		glfwPollEvents();
		if(glfwWindowShouldClose(glfwID))
		{
			closeAction.action();
		}
	}
	
	private void createWindow()
	{
		glfwID = glfwCreateWindow(width, height, title, NULL, NULL);
		if ( glfwID == NULL )
		{
			logger.severe("Could not create a new window");
		}
		
		glfwSetKeyCallback(glfwID, (window, key, scancode, action, mods) -> {
			//TODO: set window input data, and the let the Input system use the data from the active/top window
		});
		
		centerPosition();
	}
	
	public void show()
	{
		if(!destroyed)
		{
			glfwShowWindow(glfwID);
		} else
		{
			createWindow();
			glfwShowWindow(glfwID);
		}
	}
	
	public void hide()
	{
		glfwHideWindow(glfwID);
	}
	
	public void close()
	{
		glfwDestroyWindow(glfwID);
		destroyed = true;
	}
	
	protected void Active()
	{
		glfwMakeContextCurrent(glfwID);
	}
	
	public static void EnableVerticalSync()
	{
		glfwSwapInterval(1);
	}
	
	protected void DisableVerticalSync()
	{
		glfwSwapInterval(0);
	}
	
	public void setPosition(int x, int y)
	{
		glfwSetWindowPos(glfwID, x, y);
	}
	
	public void centerPosition()
	{
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		setPosition((vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
	}
	
	private void vals(String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public long getGlfwID() {
		return glfwID;
	}

}
