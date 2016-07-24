package com.kraqqen.core.physics;

import java.util.ArrayList;

import com.kraqqen.util.math.Vector3f;

public class PhysicsEngine {

	private ArrayList<PhysicsObject> objects;

	public PhysicsEngine() {
	}

	public void AddObject(PhysicsObject object) {
		objects.add(object);
	}

	public void Simulate(float delta) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).Integrate(delta);
		}
	}

	public void HandleCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				IntersectData intersectData = objects.get(i).getCollider()
						.Intersect((objects.get(j).getCollider()));

				if (intersectData.isDoesIntersect()) {
					
					Vector3f direction = intersectData.getDirection().Normalized();
					Vector3f otherDirection = (Vector3f)(direction.Reflect(objects.get(i).getVelocity().Normalized()));
					
					objects.get(i).setVelocity((Vector3f)(objects.get(i).getVelocity().Reflect(otherDirection)));
					objects.get(j).setVelocity((Vector3f)(objects.get(j).getVelocity().Reflect(direction)));
				}
			}
		}
	}
}
