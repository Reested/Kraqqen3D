package com.kraqqen.util.math;

public class Rect {
	
	private float x = 0, y = 0, width = 0, height = 0;
	
	public Rect() {  }
	
	public Rect(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Rect(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rect(Vector2f position, Vector2f size)
	{
		this.x = position.GetX();
		this.y = position.GetY();
		this.width = size.GetX();
		this.height = size.GetY();
	}

	public float GetX() {
		return x;
	}

	public void SetX(float x) {
		this.x = x;
	}

	public float GetY() {
		return y;
	}

	public void SetY(float y) {
		this.y = y;
	}

	public float GetWidth() {
		return width;
	}

	public void SetWidth(float width) {
		this.width = width;
	}

	public float GetHeight() {
		return height;
	}

	public void SetHeight(float height) {
		this.height = height;
	}
	
	public void SetPosition(Vector2f position)
	{
		this.x = position.GetX();
		this.y = position.GetY();
	}
	
	public void SetSize(Vector2f size)
	{
		this.width = size.GetX();
		this.height = size.GetY();
	}
	
}
