package com.kraqqen.util.timing;

public class ReferenceCounter 
{
	private int m_refCount;
	
	public ReferenceCounter()
	{
		m_refCount = 1;
	}
	
	public int GetReferenceCount() { return m_refCount; }
	
	public void AddReference() { m_refCount++; }
	
	public boolean RemoveReference() { m_refCount--; return m_refCount == 0; }
}
