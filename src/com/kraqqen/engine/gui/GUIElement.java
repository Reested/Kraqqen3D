package com.kraqqen.engine.gui;

import java.io.IOException;

import com.kraqqen.test.Mesh;
import com.kraqqen.test.RenderContext;
import com.kraqqen.test.Transform;
import com.kraqqen.util.math.Rect;

public abstract class GUIElement {
	
	protected static Mesh QuadMesh = GetQuad();
	private static Mesh GetQuad()
	{
		try {
			return new Mesh("res/engine/default_models/quad.obj");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GUIContent content = new GUIContent();
	
	protected Transform transform = new Transform();
	public Rect rect = new Rect();
	
	public abstract void Draw(RenderContext context);

}
