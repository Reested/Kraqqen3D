package com.kraqqen.util.thread_primitive;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

public class AllConstructors {
	
	RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
	CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
	MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	PlatformLoggingMXBean platformLoggingMXBean = ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);

}
