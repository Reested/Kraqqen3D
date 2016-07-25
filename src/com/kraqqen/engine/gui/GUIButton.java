package com.kraqqen.engine.gui;

import com.kraqqen.test.Bitmap;
import com.kraqqen.test.Main;
import com.kraqqen.test.RenderContext;
import com.kraqqen.test.Vector4f;
import com.kraqqen.util.math.Rect;

public class GUIButton extends GUIElement {

	public static Bitmap idleTexture;
	
	public Bitmap m_idleTexture;
	
	static {
		try {
			idleTexture = new Bitmap("res/bricks2.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GUIButton(Rect rect, GUIContent content)
	{
		this.rect = rect;
		this.content = content;
		
		m_idleTexture = idleTexture;
	}
	
	public GUIButton(Rect rect, String content)
	{
		this.rect = rect;
		this.content = new GUIContent(GUIContent.ContentType.String, "");
		
		m_idleTexture = idleTexture;
	}
	
	public GUIButton(Rect rect)
	{
		this.rect = rect;
		this.content = new GUIContent(GUIContent.ContentType.String, "");
		
		m_idleTexture = idleTexture;
	}

	@Override
	public void Draw(RenderContext context) {
		UpdateTransform();
		QuadMesh.Draw(context, Main.Ortho, transform.GetTransformation(), m_idleTexture);
	}
	
	private void UpdateTransform()
	{
		transform = transform.SetPos(new Vector4f(rect.GetX(), rect.GetY(), 0)).
				SetScale(new Vector4f(rect.GetWidth(), rect.GetHeight(), 0));
	}

}
