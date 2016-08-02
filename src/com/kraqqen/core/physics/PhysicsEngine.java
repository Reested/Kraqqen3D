package com.kraqqen.core.physics;

import java.util.ArrayList;

import com.kraqqen.util.logging.KELogger;
import com.kraqqen.util.math.Vector3f;

public class PhysicsEngine {

	private ArrayList<PhysicsObject> objects;

	public PhysicsEngine() {
		objects = new ArrayList<PhysicsObject>();
	}

	public void AddObject(PhysicsObject object) {
		objects.add(object);
		if (KELogger.isPELogging()) {
			System.out.println(format(object) + " has been add at posision: " + (objects.size() -1));
		}
	}

	public void Simulate(float delta) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).Integrate(delta);
			if (KELogger.isPELogging()) {
				System.out.println(
						format(objects.get(i)) + " " + i + " has moved to pos: " + objects.get(i).getPosition());
			}
		}
	}

	public void HandleCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				IntersectData intersectData = objects.get(i).getCollider().Intersect((objects.get(j).getCollider()));

				if (intersectData.isDoesIntersect()) {

					if (KELogger.isPELogging()) {
						System.out.println("'" +
										   format(objects.get(i)) + " " + i +
										   "' collided with '" +
										   format(objects.get(j)) +  " " + j + 
										   "'");
					}

					Vector3f direction = intersectData.getDirection().Normalized();
					Vector3f otherDirection = (Vector3f) (direction.Reflect(objects.get(i).getVelocity().Normalized()));

					objects.get(i).setVelocity((Vector3f) (objects.get(i).getVelocity().Reflect(otherDirection)));
					objects.get(j).setVelocity((Vector3f) (objects.get(j).getVelocity().Reflect(direction)));

					if (KELogger.isPELogging()) {
						
						System.out.println(format(objects.get(i)) + " reflects " + otherDirection);
						System.out.println(format(objects.get(j)) + " reflects " + direction);
						
						System.out.println(format(objects.get(i)) + " " + i + " new pos: (" + objects.get(i).getPosition().GetX() + ","
								+ objects.get(i).getPosition().GetY() + "," + objects.get(i).getPosition().GetZ()
								+ ") vel: (" + objects.get(i).getVelocity().GetX() + ","
								+ objects.get(i).getVelocity().GetY() + "," + objects.get(i).getVelocity().GetZ()
								+ ")");
						System.out.println(format(objects.get(j)) + " " + j + " new pos: (" + objects.get(j).getPosition().GetX() + ","
								+ objects.get(j).getPosition().GetY() + "," + objects.get(j).getPosition().GetZ()
								+ ") vel: (" + objects.get(j).getVelocity().GetX() + ","
								+ objects.get(j).getVelocity().GetY() + "," + objects.get(j).getVelocity().GetZ()
								+ ")");
					}
				}
			}
		}
	}

	public void listObjects() {
		for (int i = 0; i < objects.size(); i++) {
			System.out.println("'" + objects.get(i).getCollider().toString().replaceAll("com.kraqqen.core.physics.", "")
					.replaceAll("@.*", "") + "' at location: " + i);
		}
	}

	private String format(PhysicsObject object) {
		return object.getCollider().toString().replaceAll("com.kraqqen.core.physics.", "").replaceAll("@.*", "");
	}
	
}
