package com.kraqqen.util.timing;

import java.util.concurrent.TimeUnit;

public class Timer {
	
	private int m_numInvocations;
	private double m_totalTime;
	private double m_startTime;
	private double dividend = 0;
	
	
	public void ProfileTimer()
	{
		m_numInvocations = 0;
		m_startTime = 0.0;
		m_totalTime = 0.0;
	}
	
	public void StartInvocation()
	{
		m_startTime = Time.GetTime();
	}
	
	public void StopInvocation()
	{
		if(m_startTime == 0)
		{
			System.out.println("Error: StopInvocation called without matching start invocation");
			assert(m_startTime != 0);
		}
		m_numInvocations++;
		m_totalTime += Time.GetTime() - m_startTime;
		m_startTime = 0;
	}
	
	
	public double GetTimeAndReset(double dividend)
	{
		double result;
		
		if(dividend == 0)
		{
			dividend = m_numInvocations;
		}
		
		if(dividend == 0)
		{
			result = 0;
		}
		else 
		{
			result = (1000.0 * m_totalTime)/((double)dividend);
		}
		
		m_totalTime = 0.0;
		m_numInvocations = 0;
		
		return result;
	}
	
	public double DisplayAndResetMilli(String message, double inputDividend, int displayedMessageLength)
	{
		String whiteSpace = "";
		for(int i = message.length(); i < displayedMessageLength; i++)
		{
			whiteSpace += " ";
		}
		
		double time = GetTimeAndReset(dividend);
		System.out.println(message + whiteSpace + time + " ms");
		
		return time;
	}
	
	public double DisplayAndResetSec(String message, double inputDividend, int displayedMessageLength)
	{
		String whiteSpace = "";
		for(int i = message.length(); i < displayedMessageLength; i++)
		{
			whiteSpace += " ";
		}
		
		double time = GetTimeAndReset(dividend);
		System.out.println(message + whiteSpace + TimeUnit.MILLISECONDS.toSeconds((long) time) % TimeUnit.MINUTES.toSeconds(1) + " second(s)");
		
		return time;
	}
	
	public double DisplayAndResetMin(String message, double inputDividend, int displayedMessageLength)
	{
		String whiteSpace = "";
		for(int i = message.length(); i < displayedMessageLength; i++)
		{
			whiteSpace += " ";
		}
		
		double time = GetTimeAndReset(dividend);
		System.out.println(message + whiteSpace + TimeUnit.MILLISECONDS.toMinutes((long) time) % TimeUnit.HOURS.toMinutes(1) + " minute(s)");
		
		return time;
	}
	
	public double DisplayAndResetHour(String message, double inputDividend, int displayedMessageLength)
	{
		String whiteSpace = "";
		for(int i = message.length(); i < displayedMessageLength; i++)
		{
			whiteSpace += " ";
		}
		
		double time = GetTimeAndReset(dividend);
		System.out.println(message + whiteSpace + TimeUnit.MILLISECONDS.toHours((long) time) + " hour(s)");
		
		return time;
	}
	
	public double DisplayAndResetFull(String message, double inputDividend, int displayedMessageLength)
	{
		String whiteSpace = "";
		for(int i = message.length(); i < displayedMessageLength; i++)
		{
			whiteSpace += " ";
		}
		
		double time = GetTimeAndReset(dividend);

		System.out.println(message + whiteSpace + String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours((long) time),
			    										 						TimeUnit.MILLISECONDS.toMinutes((long) time) % TimeUnit.HOURS.toMinutes(1),
			    										 						TimeUnit.MILLISECONDS.toSeconds((long) time) % TimeUnit.MINUTES.toSeconds(1)));
		
		return time;
	}
}
