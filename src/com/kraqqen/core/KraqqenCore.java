package com.kraqqen.core;

import java.io.File;
import java.io.IOException;

import com.kraqqen.core.physics.AABB;
import com.kraqqen.core.physics.BoundingSphere;
import com.kraqqen.core.physics.PhysicsObject;
import com.kraqqen.core.physics.Plane;
import com.kraqqen.editor.MainTestLoop;
import com.kraqqen.util.envr.EnvironmentSettings;
import com.kraqqen.util.logging.Log;
import com.kraqqen.util.timing.Timer;

public class KraqqenCore {

	static EnvironmentSettings environmentSettings = new EnvironmentSettings();
	static Log logger = new Log();
	static Timer saveTimer = new Timer();
	static Timer loadTimer = new Timer();
	static Timer runTimer = new Timer();
	static File file = new File("scripts/matrix.bat");
	

	public static void main(String[] args) {

		init();

		run();

		stop();
	}

	private static void init() {
		System.out.println("ENGINE INITIALIZING");
		loadTimer.StartInvocation();
		environmentSettings.load();
		loadTimer.StopInvocation();
		loadTimer.DisplayAndResetMilli("LOADING COMPLETED IN:", 0, 50);

		try {
			Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("INITALIZATION COMPLETE");
	}

	private static void run() {
		System.out.println("STARTING ENGINE");
		runTimer.StartInvocation();
//		// Map<String, String> env = System.getenv();
//		// for (String envName : env.keySet()) {
//		// System.out.format("%s=%s%n", envName, env.get(envName));
//		// }
		logger.doSomeThingAndLog();

		BoundingSphere.test();
		System.out.println("");
		AABB.test();
		System.out.println("");
		Plane.test();
		System.out.println("");
		PhysicsObject.test();
		new MainTestLoop().run();

		runTimer.StopInvocation();
		runTimer.DisplayAndResetFull("Total Time Running:", 0, 50);
	}

	private static void stop() {
		System.out.println("STOPPING ENGINE");
		saveTimer.StartInvocation();
		environmentSettings.save();
		saveTimer.StopInvocation();
		saveTimer.DisplayAndResetMilli("SAVING COMPLETED IN:", 0, 50);
		System.out.println("ENGINE STOPPED");
	}

}
