package com.kraqqen.core;

import com.kraqqen.core.arch.LayerStack;

public class KraqqenCore {
	
	private static LayerStack s_stack;
	
	public static LayerStack DefaultLayerStack = new DefaultLayerStack();
	
	public static void Init(LayerStack stack)
	{
		s_stack = stack;
	}

}
