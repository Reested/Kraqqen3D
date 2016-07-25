package com.kraqqen.core.physics;

import com.kraqqen.util.math.Vector3f;

import com.kraqqen.core.physics.BoundingSphere;

public class PhysicsObject {

	private Vector3f position, velocity, oldPosition;
	private Collider collider;

	public PhysicsObject(Collider collider, Vector3f velocity) {
		this.position = collider.getCenter();
		oldPosition = collider.getCenter();
		this.velocity = velocity;
		this.collider = collider;
		collider.AddReference();
	}

	public PhysicsObject(PhysicsObject other) {
		this(other.collider, other.velocity);
	}

	public void Integrate(float delta) {
		position = position.Add(velocity.Mul(delta));
	}

	public static void test(boolean print) {

		PhysicsObject physicsObjectBS = new PhysicsObject(
				new BoundingSphere(new Vector3f(0.0f, 1.0f, 0.0f), 1.0f),
				new Vector3f(1.0f, 2.0f, 3.0f));

		PhysicsObject physicsObjectAABB = new PhysicsObject(
				new AABB(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(2.0f, 2.0f, 2.0f)),
				new Vector3f(1.0f, 2.0f, 3.0f));

		PhysicsObject physicsObjectPlane = new PhysicsObject(
				new Plane(new Vector3f(0.0f, 1.0f, 0.0f), 1.0f),
				new Vector3f(1.0f, 2.0f, 3.0f));

		physicsObjectBS.Integrate(20.0f);
		physicsObjectAABB.Integrate(20.0f);
		physicsObjectPlane.Integrate(20.0f);

		Vector3f testPos = physicsObjectBS.getPosition();
		Vector3f testVel = physicsObjectBS.getVelocity();

		Vector3f testPos1 = physicsObjectAABB.getPosition();
		Vector3f testVel1 = physicsObjectAABB.getVelocity();

		Vector3f testPos2 = physicsObjectPlane.getPosition();
		Vector3f testVel2 = physicsObjectPlane.getVelocity();

		assert (testPos.GetX() == 20.0f);  assert (testPos.GetY() == 41.0f);  assert (testPos.GetZ() == 60.0f);
		assert (testVel.GetX() == 1.0f);   assert (testVel.GetY() == 2.0f);	  assert (testVel.GetZ() == 3.0f);
		
		assert (testPos1.GetX() == 21.0f); assert (testPos1.GetY() == 41.0f); assert (testPos1.GetZ() == 61.0f);
		assert (testVel1.GetX() == 1.0f);  assert (testVel1.GetY() == 2.0f);  assert (testVel1.GetZ() == 3.0f);
		
		assert (testPos2.GetX() == 20.0f); assert (testPos2.GetY() == 41.0f); assert (testPos2.GetZ() == 60.0f);
		assert (testVel2.GetX() == 1.0f);  assert (testVel2.GetY() == 2.0f);  assert (testVel2.GetZ() == 3.0f);

		if (print) {
			System.out.println("");

			System.out.println("BoundingSphere Position: (" + testPos.GetX() + ", " + testPos.GetY() + ", " + testPos.GetZ() + ")");
			System.out.println("BoundingSphere Velocity: (" + testVel.GetX() + ", " + testVel.GetY() + ", " + testVel.GetZ() + ")");

			System.out.println("");

			System.out.println("AABB Position: (" + testPos1.GetX() + ", " + testPos1.GetY() + ", " + testPos1.GetZ() + ")");
			System.out.println("AABB Velocity: (" + testVel1.GetX() + ", " + testVel1.GetY() + ", " + testVel1.GetZ() + ")");

			System.out.println("");

			System.out.println("Plane Position: (" + testPos2.GetX() + ", " + testPos2.GetY() + ", " + testPos2.GetZ() + ")");
			System.out.println("Plane Velocity: (" + testVel2.GetX() + ", " + testVel2.GetY() + ", " + testVel2.GetZ() + ")");

			System.out.println("");
		}
	}

	public Collider getCollider() {
		Vector3f translation = position.Sub(oldPosition);
		oldPosition = position;
		collider.Transform(translation);
		return collider;
	}

	public void removePhysicsObject() {
		if (collider.RemoveReference()) {
			if (collider.GetReferenceCount() == 0) {
				collider = null;
			}
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f r) {
		this.velocity = r;
	}

	public void setCollider(Collider collider) {
		this.collider = collider;
	}

	// public float getRadius() {
	// return radius;
	// }

}
