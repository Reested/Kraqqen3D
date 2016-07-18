package com.kraqqen.util.timing;

public class Time {
	
	private static final long SECOND = 1000000000L;
	private float m_FrameDelta, m_TimeSinceStart;
	private long m_TimeSinceStartMs, m_AppStartTime, m_LastFrameTime;
	

	public static double GetTime()
	{
		return (double)System.nanoTime()/(double)SECOND;
	}

}
