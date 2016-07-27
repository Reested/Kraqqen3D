package com.kraqqen.util.system_info;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

public class SystemSettings {

	RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
	CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
	MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	PlatformLoggingMXBean platformLoggingMXBean = ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);

	public void setThreadCpuTime(boolean b) {
		threadMXBean.setThreadCpuTimeEnabled(b);
	}

	public void setThreadContentionMonitoringEnabled(boolean b) {
		threadMXBean.setThreadContentionMonitoringEnabled(b);
	}

	public void resetPeakThreadCount() {
		threadMXBean.resetPeakThreadCount();
	}

	public void dumpAllThreads(boolean lockedMonitors, boolean lockedSynchronizers) {
		threadMXBean.dumpAllThreads(lockedMonitors, lockedSynchronizers);
	}

	public void setClassLoadingVerbose(boolean b) {
		classLoadingMXBean.setVerbose(b);
	}

	public void memoryGarbageColector() {
		memoryMXBean.gc();
	}

	public void setMemoryVerbose(boolean b) {
		memoryMXBean.setVerbose(b);
	}
	
	public boolean getThreadCpuTime(){
		if(threadMXBean.isThreadCpuTimeSupported()){
			return threadMXBean.isThreadCpuTimeEnabled();
		}else{
			return false;
		}
	}
	
	public boolean getThreadContentionMonitoringEnabled(){
		if(threadMXBean.isThreadContentionMonitoringSupported()){
			return threadMXBean.isThreadContentionMonitoringEnabled();
		}else{
			return false;
		}
	}
	
	public boolean getClassLoadingVerbose(){
		return classLoadingMXBean.isVerbose();
	}
	
	public boolean getMemoryVerbose(){
		return memoryMXBean.isVerbose();
	}
	
	
}
