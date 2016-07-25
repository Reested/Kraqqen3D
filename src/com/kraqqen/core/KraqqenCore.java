package com.kraqqen.core;

import java.io.File;
import java.io.IOException;

import com.kraqqen.core.physics.AABB;
import com.kraqqen.core.physics.BoundingSphere;
import com.kraqqen.core.physics.PhysicsEngine;
import com.kraqqen.core.physics.PhysicsObject;
import com.kraqqen.core.physics.Plane;
import com.kraqqen.editor.MainTestLoop;
import com.kraqqen.util.envr.EnvironmentSettings;
import com.kraqqen.util.logging.Log;
import com.kraqqen.util.math.Vector3f;
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

//		try {
//			Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		System.out.println("INITALIZATION COMPLETE");
	}

	private static void run() {
		System.out.println("STARTING ENGINE");
		runTimer.StartInvocation();
//		// Map<String, String> env = System.getenv();
//		// for (String envName : env.keySet()) {
//		// System.out.format("%s=%s%n", envName, env.get(envName));
//		// }
		//logger.doSomeThingAndLog();

		BoundingSphere.test(true);
		AABB.test(true);
		Plane.test(true);
		PhysicsObject.test(true);
		
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f, 4.0f, 0.0f), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
		
		AABB aabb1 = new AABB(new Vector3f(0.0f, 0.0f, 0.0f),  new Vector3f(1.0f, 1.0f, 1.0f));
		AABB aabb2 = new AABB(new Vector3f(1.0f, 1.0f, 1.0f),  new Vector3f(2.0f, 2.0f, 2.0f));
		
		Plane plane1 = new Plane(new Vector3f(0.0f, 0.0f, 0.0f), 0.0f);
		
		PhysicsEngine pEngine = new PhysicsEngine();
		
		pEngine.AddObject(new PhysicsObject(sphere1, new Vector3f(0, -1, 0)));
		//pEngine.AddObject(new PhysicsObject(sphere2, new Vector3f(0, 0, 0)));
		//pEngine.AddObject(new PhysicsObject(aabb1, new Vector3f(0, -1, 0)));
		//pEngine.AddObject(new PhysicsObject(aabb2, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(plane1, new Vector3f(0, 0, 0)));
		
		int i = 0;
		while(i != 100){
			pEngine.Simulate(1.0f);
			pEngine.HandleCollision();
			i++;
		}
		
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
