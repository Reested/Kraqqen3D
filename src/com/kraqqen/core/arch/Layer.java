package com.kraqqen.core.arch;

/***
 * <h1>The Layer Class</h1>
 */
public abstract class Layer {
	
	private String name;
	
	public Layer(String name)
	{
		this.name = name;
	}
	
	/** Initialize this Layer */
	public abstract void Initialize();
	/** Check if this Layer is still functional */
	public abstract boolean Check();
	/** Update this Layer */
	public abstract void Update();
	/** Terminate this Layer */
	public abstract void Terminate();
	
	/** The name of this Layer */
	public String getName()
	{
		return name;
	}
}
