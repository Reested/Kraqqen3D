package com.kraqqen.core.arch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LayerStack {
	
	private List<Layer> m_layers = new ArrayList<Layer>();
	
	public LayerStack() {}
	public LayerStack(Layer[] layers)
	{
		m_layers.addAll(Arrays.asList(layers));
	}
	
	public void Initialize()
	{
		for(Layer l : m_layers)
			l.Initialize();
	}
	
	public void Update()
	{
		for(Layer l : m_layers)
			if(l.Check())
				l.Update();
	}
	
	public void Terminate()
	{
		for(Layer l : m_layers)
			l.Terminate();
	}
	
	public String[] getNames()
	{
		String[] res;
		List<String> names = new ArrayList<String>();
		for(Layer l : m_layers)
		{
			names.add(l.getName());
		}
		
		res = new String[names.size()];
		
		for(int i = 0; i < res.length; i++)
		{
			res[i] = names.get(i);
		}
		
		return res;
	}
}
