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
	
	public void Integrate(float delta){	
		position = position.Add(velocity.Mul(delta));
	}
	
	public static void test(){
		
		PhysicsObject physicsObject = new PhysicsObject(new BoundingSphere(new Vector3f(0.0f, 1.0f, 0.0f), 1.0f), new Vector3f(1.0f, 2.0f, 3.0f));
		
		physicsObject.Integrate(20.0f);
		
		Vector3f testPos = physicsObject.getPosition();
		Vector3f testVel = physicsObject.getVelocity();
		
		assert(testPos.GetX() == 20.0f); assert(testPos.GetY() == 41.0f); assert(testPos.GetZ() == 60.0f);
		assert(testVel.GetX() ==  1.0f); assert(testVel.GetY() ==  2.0f); assert(testVel.GetZ() ==  3.0f);
		
//		System.out.println("(" + testPos.GetX() + ", " + testPos.GetY() + ", " + testPos.GetZ() + ")");
//		System.out.println("(" + testVel.GetX() + ", " + testVel.GetY() + ", " + testVel.GetZ() + ")");
	}
	
	
	public Collider getCollider(){
		Vector3f translation = position.Sub(oldPosition);
		oldPosition= position;
		collider.Transform(translation);
		return collider;
	}
	
	public void removePhysicsObject(){
		if(collider.RemoveReference()){
			if(collider.GetReferenceCount() == 0){
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
	
	public void setCollider(Collider collider){
		this.collider = collider;
	}

//	public float getRadius() {
//		return radius;
//	}

}
