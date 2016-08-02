package com.kraqqen.core;

import java.io.File;
import java.io.IOException;

import com.kraqqen.core.physics.AABB;
import com.kraqqen.core.physics.BoundingSphere;
import com.kraqqen.core.physics.PhysicsEngine;
import com.kraqqen.core.physics.PhysicsObject;
import com.kraqqen.core.physics.Plane;
import com.kraqqen.engine.Game;
import com.kraqqen.util.environment.EnvironmentSettings;
import com.kraqqen.util.logging.KELogger;
import com.kraqqen.util.logging.Log;
import com.kraqqen.util.math.Vector3f;
import com.kraqqen.util.timing.Timer;

public class KraqqenCore {

	static EnvironmentSettings environmentSettings = new EnvironmentSettings();
	static Log logger = new Log();
	static Timer saveTimer = new Timer();
	static Timer loadTimer = new Timer();
	static Timer runTimer = new Timer();
	//static File file = new File("scripts/jvm_args.bat");
	

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
		KELogger.setPELogging(false);

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

		BoundingSphere.test(false);
		AABB.test(false);
		Plane.test(false);
		PhysicsObject.test(false);
		
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f, 1.0f, 0.0f), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f, 2.0f, 0.0f), 1.0f);
		BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
		BoundingSphere sphere4 = new BoundingSphere(new Vector3f(0.0f, 4.0f, 0.0f), 1.0f);
		BoundingSphere sphere5 = new BoundingSphere(new Vector3f(0.0f, 5.0f, 0.0f), 1.0f);
		BoundingSphere sphere6 = new BoundingSphere(new Vector3f(0.0f, 6.0f, 0.0f), 1.0f);
		BoundingSphere sphere7 = new BoundingSphere(new Vector3f(0.0f, 7.0f, 0.0f), 1.0f);
		BoundingSphere sphere8 = new BoundingSphere(new Vector3f(0.0f, 8.0f, 0.0f), 1.0f);
		BoundingSphere sphere9 = new BoundingSphere(new Vector3f(0.0f, 9.0f, 0.0f), 1.0f);
		BoundingSphere sphere10 = new BoundingSphere(new Vector3f(0.0f, 10.0f, 0.0f), 1.0f);
		BoundingSphere sphere11 = new BoundingSphere(new Vector3f(0.0f, 11.0f, 0.0f), 1.0f);
		BoundingSphere sphere12 = new BoundingSphere(new Vector3f(0.0f, 12.0f, 0.0f), 1.0f);
		BoundingSphere sphere13 = new BoundingSphere(new Vector3f(0.0f, 13.0f, 0.0f), 1.0f);
		BoundingSphere sphere14 = new BoundingSphere(new Vector3f(0.0f, 14.0f, 0.0f), 1.0f);
		BoundingSphere sphere15 = new BoundingSphere(new Vector3f(0.0f, 15.0f, 0.0f), 1.0f);
		BoundingSphere sphere16 = new BoundingSphere(new Vector3f(0.0f, 16.0f, 0.0f), 1.0f);
		BoundingSphere sphere17 = new BoundingSphere(new Vector3f(0.0f, 17.0f, 0.0f), 1.0f);
		BoundingSphere sphere18 = new BoundingSphere(new Vector3f(0.0f, 18.0f, 0.0f), 1.0f);
		BoundingSphere sphere19 = new BoundingSphere(new Vector3f(0.0f, 19.0f, 0.0f), 1.0f);
		BoundingSphere sphere20 = new BoundingSphere(new Vector3f(0.0f, 20.0f, 0.0f), 1.0f);
		BoundingSphere sphere21 = new BoundingSphere(new Vector3f(0.0f, 21.0f, 0.0f), 1.0f);
		BoundingSphere sphere22 = new BoundingSphere(new Vector3f(0.0f, 22.0f, 0.0f), 1.0f);
		BoundingSphere sphere23 = new BoundingSphere(new Vector3f(0.0f, 23.0f, 0.0f), 1.0f);
		BoundingSphere sphere24 = new BoundingSphere(new Vector3f(0.0f, 24.0f, 0.0f), 1.0f);
		BoundingSphere sphere25 = new BoundingSphere(new Vector3f(0.0f, 25.0f, 0.0f), 1.0f);
		
		AABB aabb1 = new AABB(new Vector3f(0.0f, 0.0f, 0.0f),  new Vector3f(1.0f, 1.0f, 1.0f));
		AABB aabb2 = new AABB(new Vector3f(1.0f, 1.0f, 1.0f),  new Vector3f(2.0f, 2.0f, 2.0f));
		
		Plane plane1 = new Plane(new Vector3f(0.0f, 0.0f, 0.0f), 0.0f);
		
		PhysicsEngine pEngine = new PhysicsEngine();
		
		pEngine.AddObject(new PhysicsObject(sphere1, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere2, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere3, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere4, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere5, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere6, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere7, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere8, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere9, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere10, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere11, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere12, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere13, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere14, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere15, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere16, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere17, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere18, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere19, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere20, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere21, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere22, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere23, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere24, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(sphere25, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(aabb1, new Vector3f(0, -1, 0)));
		pEngine.AddObject(new PhysicsObject(aabb2, new Vector3f(0, 1, 0)));
		pEngine.AddObject(new PhysicsObject(plane1, new Vector3f(0, 0, 0)));
		
		//pEngine.listObjects();
		System.out.println("");
		
		int i = 0;
		while(i != 10){
			pEngine.Simulate(1.0f);
			pEngine.HandleCollision();
			//System.out.println("next loop");
			i++;
		}
		System.out.println("");
		
		new Game().run();

		runTimer.StopInvocation();
		runTimer.DisplayAndResetFull("Total Time Running:", 0, 50);
	}

	private static void stop() {
		System.out.println("");
		System.out.println("STOPPING ENGINE");
		saveTimer.StartInvocation();
		environmentSettings.save();
		saveTimer.StopInvocation();
		saveTimer.DisplayAndResetMilli("SAVING COMPLETED IN:", 0, 50);
		System.out.println("ENGINE STOPPED");
	}

}
