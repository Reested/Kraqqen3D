package com.kraqqen.engine.gui;

public class GUIContent {
	
	public enum ContentType
	{
		String,
		Texture
	}
	
	public Object content;
	
	public ContentType type;
	
	public GUIContent() { }
	
	public GUIContent(ContentType type, Object content)
	{
		this.type = type;
		this.content = content;
	}

}
