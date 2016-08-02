package com.kraqqen.util.system_info;

import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import javax.management.ObjectName;

import org.lwjgl.Version;

public class SystemInfo {

	private static final int DISPLAYED_MESSAGE_LENGTH = 50;

	public static final String osName = System.getProperty("os.name");
	public static final String dataModel = System.getProperty("sun.arch.data.model");
	public static final String vmVersion = System.getProperty("java.vm.version");
	public static final String osArch = System.getProperty("os.arch");

	RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
	CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
	MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	PlatformLoggingMXBean platformLoggingMXBean = ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);
	// PlatformManagedObject platformManagedObject =
	// ManagementFactory.getPlatformMXBean(PlatformManagedObject.class);

	List<MemoryPoolMXBean> memoryPoolMXBean = ManagementFactory.getMemoryPoolMXBeans();
	List<MemoryManagerMXBean> memoryManagerMXBean = ManagementFactory.getMemoryManagerMXBeans();
	List<GarbageCollectorMXBean> garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans();
	List<MemoryManagerMXBean> bufferPoolMXBean = ManagementFactory.getMemoryManagerMXBeans();

	File[] roots = File.listRoots();
	
	
	
	public SystemInfo() {

	}

	public void printInfo() {

		pl("","");
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		pl("","");
		
		
		//********************************************************************
		System.out.println("SYSTEM OVERVIEW");
		System.out.println("============================================================================");
		pl("OS Name: " , getOsname());
		pl("OS Arch: " , getOsarch());
		pl("Data Model: " , getDatamodel());
		pl("VM Version: " , getVmversion());
		pl("" , "");
		pl("Available Processores:", Runtime.getRuntime().availableProcessors());
		pl("Free Memory:", Runtime.getRuntime().freeMemory());
		pl("Total Memory:", Runtime.getRuntime().totalMemory());
		pl("Max Memory:", Runtime.getRuntime().maxMemory());
		for(File root : roots){
			pl("" , "");
			pl("" , "");
			pl("Absolute Path:", root.getAbsolutePath());
			pl("Total Space:", root.getTotalSpace());
			pl("Free Space", root.getFreeSpace());
			pl("Usable Space:", root.getUsableSpace());
		}
		System.out.println("============================================================================");
		pl("","");
		pl("" , "");
		pl("" , "");

		
		//********************************************************************
		System.out.println("RUNTIME SETTINGS");
		System.out.println("============================================================================");
		pl("VM Name:" , runtimeMxBean.getVmName());
		pl("VM Vendor:" , runtimeMxBean.getVmVendor());
		pl("VM Version:" , runtimeMxBean.getVmVersion());
		pl("VM Spec Name" , runtimeMxBean.getSpecName());
		pl("VM Spec Vendor:" , runtimeMxBean.getSpecVendor());
		pl("VM Spec Version:" , runtimeMxBean.getSpecVersion());
		pl("VM Managment Version:" , runtimeMxBean.getManagementSpecVersion());
		pl("VM User Name:" , runtimeMxBean.getName());
		pl("VM Boot Class Supported:" , runtimeMxBean.isBootClassPathSupported());
		if(runtimeMxBean.isBootClassPathSupported()){
			pl("Boot Class Path:" , runtimeMxBean.getBootClassPath());
		}else{
			pl("Boot Class Path:" , "NULL");
		}
		pl("Library Path:" , runtimeMxBean.getLibraryPath());
		pl("VM Input Args:" , runtimeMxBean.getInputArguments());
		pl("VM Start Time:" , runtimeMxBean.getStartTime());
		pl("VM Uptime:" , runtimeMxBean.getUptime());
		pl("VM Object Name:" , runtimeMxBean.getObjectName());
		pl("VM System Properties:" , runtimeMxBean.getSystemProperties());
		System.out.println("============================================================================");
		pl("","");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("OS SETTINGS");
		System.out.println("============================================================================");
		pl("Arch:" , operatingSystemMXBean.getArch());
		pl("OS:" , operatingSystemMXBean.getName());
		pl("OS Version:" , operatingSystemMXBean.getVersion());
		pl("Available Processors:" , operatingSystemMXBean.getAvailableProcessors());
		if(operatingSystemMXBean.getSystemLoadAverage() > 0){
			pl("System Load Average:" , operatingSystemMXBean.getSystemLoadAverage());
		}else{
			pl("System Load Average:" , "Not Availiable");
		}
		pl("OS Obj Name:" , operatingSystemMXBean.getObjectName());
		plo("OS Class Name: " , operatingSystemMXBean.getClass());	
		System.out.println("============================================================================");
		pl("" , "");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("THREAD SETTINGS");
		System.out.println("============================================================================");
		pl("Thread CPU Supported:" , threadMXBean.isThreadCpuTimeSupported());
		if(threadMXBean.isThreadCpuTimeSupported()){
			pl("Thread CPU Enabled:" , threadMXBean.isThreadCpuTimeEnabled());
			if(threadMXBean.isThreadCpuTimeSupported()){
				for(int id=1; id<threadMXBean.getThreadCount()*2; id++){
					int TID = id-1;
					pl("Thread ("+TID+") CPU Time:" , threadMXBean.getThreadCpuTime(id));
				}
				for(int id=1; id<threadMXBean.getThreadCount()*2; id++){
					int TID = id-1;
					pl("Thread ("+TID+") User Time:" , threadMXBean.getThreadUserTime(id));
				}
			}else{
				pl("Thread CPU Time:" , "NOT SUPPORTED");
				pl("Thread User Time:", "NOT SUPPORTED");
			}
		}else{
			pl("Thread CPU Enabled:" , "NOT SUPPORTED");
		}
		pl("Object Monitor Usage Support:" , threadMXBean.isObjectMonitorUsageSupported());
		pl("Synchronizer Usage Support:" , threadMXBean.isSynchronizerUsageSupported());
		pl("Thread Contention Monitoring Supported:" , threadMXBean.isThreadContentionMonitoringSupported());
		if(threadMXBean.isThreadContentionMonitoringSupported()){
			pl("Thread Contention Monitoring Enabled:" , threadMXBean.isThreadContentionMonitoringEnabled());
		}else{
			pl("Thread Contention Monitoring Enabled:" , "NOT SUPPORTED");
		}
		pl("Thread CPU Time Supported:" , threadMXBean.isThreadCpuTimeSupported());
		
		pl("Current Thread CPU Time Supported:" , threadMXBean.isCurrentThreadCpuTimeSupported());
		if(threadMXBean.isCurrentThreadCpuTimeSupported()){
			pl("Current Tread CPU Time:" , threadMXBean.getCurrentThreadCpuTime());
		}else{
			pl("Current Tread CPU Time:" , "NOT SUPPORTED");
		}
		pl("Total Started Thread Count:" , threadMXBean.getTotalStartedThreadCount());
		pl("Daemon Thread Count:" , threadMXBean.getDaemonThreadCount());
		pl("Peak Thread Count:" , threadMXBean.getPeakThreadCount());
		pl("Current Number Of Live Threads:" , threadMXBean.getThreadCount());
		plm("Monitor Deadlocked Threads:" , threadMXBean.findMonitorDeadlockedThreads());
		plm("Deadlocked Threads:" , threadMXBean.findDeadlockedThreads());
		pl("All Thread ID's:" , threadMXBean.getAllThreadIds());
//		for(int id=1; id<threadMXBean.getAllThreadIds().length;id++){
//			int TID = id - 1;
//			String lid = threadMXBean.getAllThreadIds().toString();
//			pl("Thread ("+TID+") Info:" , threadMXBean.getThreadInfo(id));
//		}
		pl("Thread Obj Name:" , threadMXBean.getObjectName());
		// pl("" , threadMXBean.getThreadUserTime(id));

		// pl("" , threadMXBean.dumpAllThreads(lockedMonitors, lockedSynchronizers));
		// pl("" , threadMXBean.getThreadInfo(id, maxDepth));

		// pl("" , threadMXBean.getThreadInfo(id));
		// pl("" , threadMXBean.getThreadInfo(ids, maxDepth));
		// pl("" , threadMXBean.getThreadInfo(ids, lockedMonitors,lockedSynchronizers);
		// pl("" , threadMXBean.resetPeakThreadCount();
		System.out.println("============================================================================");
		pl("","");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("CLASS LOADING");
		System.out.println("============================================================================");
		pl("Total Number Of Classes Loaded:" , classLoadingMXBean.getTotalLoadedClassCount());
		pl("Number Of Currently Loaded Classes:" , classLoadingMXBean.getLoadedClassCount());
		pl("Number Of Unloaded Classes:" , classLoadingMXBean.getUnloadedClassCount());
		if(classLoadingMXBean.isVerbose()){
			pl("Verbose Output For Class Loading System:" , "Enabled");
		}else{
			pl("Verbose Output For Class Loading System:" , "Disabled");
		}
		pl("Class Loading Obj Name:" , classLoadingMXBean.getObjectName());
		System.out.println("============================================================================");
		pl("" , "");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("LOGGER NAMES");
		System.out.println("============================================================================");
		//pl(platformLoggingMXBean.getLoggerLevel(loggerName));
		pl("Logger Names:" , platformLoggingMXBean.getLoggerNames());
		pl("Logger Names Obj Name:" , platformLoggingMXBean.getObjectName());
		//pl(platformLoggingMXBean.getParentLoggerName(loggerName));
		System.out.println("============================================================================");
		pl("" , "");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("MEMORY USAGE ");
		System.out.println("============================================================================");
		if(memoryMXBean.isVerbose()){
			pl("Verbose Output For Memory System:" , "Enabled");
		}else{
			pl("Verbose Output For Memory System:" , "Disabled");
		}
		pl("Heap Usage:" , memoryMXBean.getHeapMemoryUsage());
		pl("Non-Heap Usage:" , memoryMXBean.getNonHeapMemoryUsage());
		pl("Approximate Number Of Obj Awaiting Finalization: " , memoryMXBean.getObjectPendingFinalizationCount());
		pl("Memory Usage Obj Name:" , memoryMXBean.getObjectName());
		plm("Memory Usage Class Name:" , memoryMXBean.getClass());
		System.out.println("============================================================================");
		pl("" , "");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("COMPILER");
		System.out.println("============================================================================");
		pl("Compiler Name:" , compilationMXBean.getName());
		pl("Compilation Time Supported:" , compilationMXBean.isCompilationTimeMonitoringSupported());
		if(compilationMXBean.isCompilationTimeMonitoringSupported()){
			pl("Compilation Time(ms):" , compilationMXBean.getTotalCompilationTime());
		}else{
			pl("Compilation Time(ms):" , "NOT SUPPORTED");
		}
		pl("Compiler Obj Name:" , compilationMXBean.getObjectName());
		System.out.println("============================================================================");
		pl("" , "");
		pl("" , "");
		pl("" , "");
		
		
		//********************************************************************
		System.out.println("============================================================================");
		plbm("BUFFER POOL:" , bufferPoolMXBean);
		pl("","");

		
		//********************************************************************
		System.out.println("============================================================================");
		plbp("MEMORY POOL:" , memoryPoolMXBean);
		pl("","");

		
		//********************************************************************
		System.out.println("============================================================================");
		plbm("MEMORY MANAGER:" , memoryManagerMXBean);
		pl("","");

		
		//********************************************************************
		System.out.println("============================================================================");
		plbg("GARBAGE COLLECTOR:" , garbageCollectorMXBean);
		pl("","");
	}
	
	private void pl(String message, ThreadInfo threadInfo) {
		String s = String.valueOf(threadInfo);
		pl(message, s);
	}

	private void pl(String message, String s) {
		String whitespace = "";
		for (int i = message.length(); i < DISPLAYED_MESSAGE_LENGTH; i++) {
			whitespace += " ";
		}
		System.out.println(message + whitespace + s);
	}
	
	private void pl(int messageLength, String message, String s) {
		String whitespace = "";
		for (int i = message.length(); i < messageLength; i++) {
			whitespace += " ";
		}
		System.out.println(message + whitespace + s);
	}

	private void pl(String Message, double n) {
		String s = String.valueOf(n);
		pl(Message, s);
	}

	private void plo(String Message, Class<? extends OperatingSystemMXBean> class1) {
		String s = String.valueOf(class1);
		pl(Message, s);
	}

	private void plm(String Message, Class<? extends MemoryMXBean> class1) {
		String s = String.valueOf(class1);
		pl(Message, s);
	}

	private void pl(String Message, MemoryUsage heapMemoryUsage) {
		String s = String.valueOf(heapMemoryUsage);
		pl(Message, s);
	}

	private void pl(int messageLength, String Message, MemoryUsage heapMemoryUsage) {
		String s = String.valueOf(heapMemoryUsage);
		pl(messageLength, Message, s);
	}

	private void plbg(String Message, List<GarbageCollectorMXBean> garbageCollectorMXBean2) {
		for (int i = 0; i < garbageCollectorMXBean2.size(); i++) {
			if (i == 0) {
				pl(Message, "");
				pl("", "");
				pl(43, "\tName:", garbageCollectorMXBean2.get(i).getName());
				pl(43, "\tIs Valid:", garbageCollectorMXBean2.get(i).isValid());
				pl(43, "\tCollection Count:", garbageCollectorMXBean2.get(i).getCollectionCount());
				pl(43, "\tCollection Time:", garbageCollectorMXBean2.get(i).getCollectionTime());
				pl(43, "\tMemory Pool Names:", garbageCollectorMXBean2.get(i).getMemoryPoolNames());
				pl(43, "\t" + Message.replace(":", "") + " Obj Name:", garbageCollectorMXBean2.get(i).getObjectName());
				pl("", "");

			} else {
				pl(43, "\tName:", garbageCollectorMXBean2.get(i).getName());
				pl(43, "\tIs Valid:", garbageCollectorMXBean2.get(i).isValid());
				pl(43, "\tCollection Count:", garbageCollectorMXBean2.get(i).getCollectionCount());
				pl(43, "\tCollection Time:", garbageCollectorMXBean2.get(i).getCollectionTime());
				pl(43, "\tMemory Pool Names:", garbageCollectorMXBean2.get(i).getMemoryPoolNames());
				pl(43, "\t" + Message.replace(":", "") + " Obj Name:", garbageCollectorMXBean2.get(i).getObjectName());
				pl("", "");
			}
		}

	}

	private void plbp(String Message, List<MemoryPoolMXBean> memoryPoolMXBean2) {
		for (int i = 0; i < memoryPoolMXBean2.size(); i++) {
			if (i == 0) {
				pl(Message, "");
				pl("", "");
				pl(43, "\tName:", memoryPoolMXBean2.get(i).getName());
				pl(43, "\tType:", memoryPoolMXBean2.get(i).getType());
				pl(43, "\tIs Valid:", memoryPoolMXBean2.get(i).isValid());
				pl(43, "\tCollection usage:", memoryPoolMXBean2.get(i).getCollectionUsage());
				pl(43, "\tPeak Usage:", memoryPoolMXBean2.get(i).getPeakUsage());
				pl(43, "\tUsage:", memoryPoolMXBean2.get(i).getUsage());
				pl(43, "\tCollection Usage Threshold Supported:", memoryPoolMXBean2.get(i).isCollectionUsageThresholdSupported());
				if(memoryPoolMXBean2.get(i).isCollectionUsageThresholdSupported()){
					pl(43, "\tCollection Usage Threshold:", memoryPoolMXBean2.get(i).getCollectionUsageThreshold());
					pl(43, "\tCollection Usage Threshold Count:", memoryPoolMXBean2.get(i).getCollectionUsageThresholdCount());
					pl(43, "\tCollection Usage Threshold Exceeded:", memoryPoolMXBean2.get(i).isCollectionUsageThresholdExceeded());
					
				}else{
					pl(43, "\tCollection Usage Threshold:", "NOT SUPPORTED");
					pl(43, "\tCollection Usage Threshold Count:", "NOT SUPPORTED");
					pl(43, "\tCollection Usage Threshold Exceeded:", "NOT SUPPORTED");
				}
				pl(43, "\tUsage Threshold Supported:", memoryPoolMXBean2.get(i).isUsageThresholdSupported());
				if(memoryPoolMXBean2.get(i).isUsageThresholdSupported()){
					pl(43, "\tUsage Threshold:", memoryPoolMXBean2.get(i).getUsageThreshold());
					pl(43, "\tUsage Threshold Count:", memoryPoolMXBean2.get(i).getUsageThresholdCount());
					pl(43, "\tUsage Threshold Count Exceeded:", memoryPoolMXBean2.get(i).isUsageThresholdExceeded());
					
				}else{
					pl(43, "\tUsage Threshold:", "NOT SUPPORTED");
					pl(43, "\tUsage Threshold Count:", "NOT SUPPORTED");
					pl(43, "\tUsage Threshold Count Exceeded:", "NOT SUPPORTED");
				}
				pl(43, "\tMemory Manager Names:", memoryPoolMXBean2.get(i).getMemoryManagerNames());				
				pl(43, "\t" + Message.replace(":", "") + " Obj Name:", memoryPoolMXBean2.get(i).getObjectName());
				pl(43, "\t" + Message.replace(":", "") + " Class Name:", memoryPoolMXBean2.get(i).getClass());
				pl("", "");
			} else {
				pl(43, "\tName:", memoryPoolMXBean2.get(i).getName());
				pl(43, "\tType:", memoryPoolMXBean2.get(i).getType());
				pl(43, "\tIs Valid:", memoryPoolMXBean2.get(i).isValid());
				pl(43, "\tCollection usage:", memoryPoolMXBean2.get(i).getCollectionUsage());
				pl(43, "\tPeak Usage:", memoryPoolMXBean2.get(i).getPeakUsage());
				pl(43, "\tUsage:", memoryPoolMXBean2.get(i).getUsage());
				pl(43, "\tCollection Usage Threshold Supported:", memoryPoolMXBean2.get(i).isCollectionUsageThresholdSupported());
				if(memoryPoolMXBean2.get(i).isCollectionUsageThresholdSupported()){
					pl(43, "\tCollection Usage Threshold:", memoryPoolMXBean2.get(i).getCollectionUsageThreshold());
					pl(43, "\tCollection Usage Threshold Count:", memoryPoolMXBean2.get(i).getCollectionUsageThresholdCount());
					pl(43, "\tCollection Usage Threshold Exceeded:", memoryPoolMXBean2.get(i).isCollectionUsageThresholdExceeded());
					
				}else{
					pl(43, "\tCollection Usage Threshold:", "NOT SUPPORTED");
					pl(43, "\tCollection Usage Threshold Count:", "NOT SUPPORTED");
					pl(43, "\tCollection Usage Threshold Exceeded:", "NOT SUPPORTED");
				}
				pl(43, "\tUsage Threshold Supported:", memoryPoolMXBean2.get(i).isUsageThresholdSupported());
				if(memoryPoolMXBean2.get(i).isUsageThresholdSupported()){
					pl(43, "\tUsage Threshold:", memoryPoolMXBean2.get(i).getUsageThreshold());
					pl(43, "\tUsage Threshold Count:", memoryPoolMXBean2.get(i).getUsageThresholdCount());
					pl(43, "\tUsage Threshold Count Exceeded:", memoryPoolMXBean2.get(i).isUsageThresholdExceeded());
					
				}else{
					pl(43, "\tUsage Threshold:", "NOT SUPPORTED");
					pl(43, "\tUsage Threshold Count:", "NOT SUPPORTED");
					pl(43, "\tUsage Threshold Count Exceeded:", "NOT SUPPORTED");
				}
				pl(43, "\tMemory Manager Names:", memoryPoolMXBean2.get(i).getMemoryManagerNames());				
				pl(43, "\t" + Message.replace(":", "") + " Obj Name:", memoryPoolMXBean2.get(i).getObjectName());
				pl(43, "\t" + Message.replace(":", "") + " Class Name:", memoryPoolMXBean2.get(i).getClass());
				pl("", "");
			}
		}

	}
	
	private void pl(int messageLength, String message, MemoryType type) {
		String s = String.valueOf(type);
		pl(messageLength, message, s);
		
	}
	
	private void pl(int messageLength, String message, String[] memoryManagerNames) {
		for(int i=0; i<memoryManagerNames.length; i++){
			pl(messageLength, message, memoryManagerNames[i]);
		}
	}
	
	private void pl(int messageLength, String message, Class<? extends MemoryPoolMXBean> class1) {
		String s = String.valueOf(class1);
		pl(messageLength, message, s);
	}

	private void plm(String Message, long[] findMonitorDeadlockedThreads) {
		if (threadMXBean.isThreadContentionMonitoringEnabled()) {
			for (int i = 1; i < findMonitorDeadlockedThreads.length - 1; i++) {
				if (i == 0) {
					pl(Message, findMonitorDeadlockedThreads[i]);
				} else {
					pl("", findMonitorDeadlockedThreads[i]);
				}
			}
		} else {
			pl(Message,"NULL");
		}

	}

	private void pl(String Message, long[] findMonitorDeadlockedThreads) {
		for (int i = 0; i < findMonitorDeadlockedThreads.length - 1; i++) {
			if (i == 0) {
				pl(Message, findMonitorDeadlockedThreads[i]);
			} else {
				pl("", findMonitorDeadlockedThreads[i]);
			}
		}
	}

	private void plbm(String Message, List<MemoryManagerMXBean> bufferPoolMXBean2) {
		for (int i = 0; i < bufferPoolMXBean.size(); i++) {
			if (i == 0) {
				pl(Message,"");
				pl("", "");
				pl(43, "\tName:", bufferPoolMXBean2.get(i).getName());
				pl(43, "\tIs Valid:", bufferPoolMXBean2.get(i).isValid());
				pl(43, "\tMemory Pool Names:", bufferPoolMXBean2.get(i).getMemoryPoolNames());
				pl(43, "\t" + Message.replace(":", "") + " Obj Name:", bufferPoolMXBean2.get(i).getObjectName());	
				pl("", "");
			} else {
				pl(43, "\tName:", bufferPoolMXBean2.get(i).getName());
				pl(43, "\tIs Valid:", bufferPoolMXBean2.get(i).isValid());
				pl(43, "\tMemory Pool Names:", bufferPoolMXBean2.get(i).getMemoryPoolNames());
				pl(43, "\t" + Message.replace(":", "") + " Obj Name:", bufferPoolMXBean2.get(i).getObjectName());	
				pl("", "");
			}
		}
	}

	private void pl(String Message, boolean bootClassPathSupported) {
		String s = String.valueOf(bootClassPathSupported);
		pl(Message, s);
	}
	
	private void pl(int messageLength, String Message, boolean bootClassPathSupported) {
		String s = String.valueOf(bootClassPathSupported);
		pl(messageLength, Message, s);
	}

	private void pl(String Message, Map<String, String> systemProperties) {
		pl(Message, Arrays.toString(systemProperties.entrySet().toArray()));

	}

	private void pl(String Message, ObjectName objectName) {
		pl(Message, objectName.toString());
	}
	
	private void pl(int messageLength, String Message, ObjectName objectName) {
		pl(messageLength, Message, objectName.toString());
	}

	private void pl(String Message, long n) {
		String s = String.valueOf(n);
		pl(Message, s);
	}
	
	private void pl(int messageLength, String Message, long n) {
		String s = String.valueOf(n);
		pl(messageLength, Message, s);
	}

	private void pl(String Message, List<String> inputArguments) {
		for (int i = 0; i < inputArguments.size(); i++) {
			if (i == 0) {
				pl(Message, inputArguments.get(i));
			} else {
				pl("", inputArguments.get(i));
			}
		}
	}

	

	public static String getOsname() {
		return osName;
	}

	public static String getDatamodel() {
		return dataModel;
	}

	public static String getVmversion() {
		return vmVersion;
	}

	public static String getOsarch() {
		return osArch;
	}

}
