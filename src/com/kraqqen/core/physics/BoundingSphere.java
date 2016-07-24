package com.kraqqen.core.physics;

import com.kraqqen.util.math.Vector3f;
import com.kraqqen.core.physics.IntersectData;
import com.kraqqen.core.physics.Collider;

public class BoundingSphere extends Collider{
	
	private Vector3f center;
	private float  radius;
	
	public BoundingSphere(Vector3f center, float radius) {
		super(ColliderTypes.TYPE_SPHERE.ordinal());
		this.center = center;
		this.radius = radius;
	}
	
	public IntersectData IntersectBoundingSphere(BoundingSphere other){
		
		float radiusDistance = radius + other.radius;
		Vector3f direction = (other.getCenter().Sub(center));
		float centerDistance = direction.Length();
		direction = direction.Div(centerDistance);
		float distance = centerDistance - radiusDistance;
		
		return new IntersectData(centerDistance < radiusDistance, direction.Mul(distance));
	}
	
	@Override
	void Transform(Vector3f translation) {
		center = center.Add(translation);
		
	}
	
	public static void test(){
		
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f, 0.0f, 0.0f), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
		BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0.0f, 0.0f, 2.0f), 1.0f);
		BoundingSphere sphere4 = new BoundingSphere(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);
		
		IntersectData sphere1IntersectSphere2 = sphere1.IntersectBoundingSphere(sphere2);
		IntersectData sphere1IntersectSphere3 = sphere1.IntersectBoundingSphere(sphere3);
		IntersectData sphere1IntersectSphere4 = sphere1.IntersectBoundingSphere(sphere4);
		
		assert(sphere1IntersectSphere2.isDoesIntersect() == false);
		assert(sphere1IntersectSphere2.getDistance()     == 1.0f);
		
		assert(sphere1IntersectSphere3.isDoesIntersect() == false);
		assert(sphere1IntersectSphere3.getDistance()     == 0.0f);
		
		assert(sphere1IntersectSphere4.isDoesIntersect() == true);
		assert(sphere1IntersectSphere4.getDistance()     == 1.0f);
		
//		System.out.println("Sphere1 intersect Sphere2: " + sphere1IntersectSphere2.isDoesIntersect());
//		System.out.println("Distance: " + sphere1IntersectSphere2.getDistance());
//		System.out.println("Sphere1 intersect Sphere3: " + sphere1IntersectSphere3.isDoesIntersect());
//		System.out.println("Distance: " + sphere1IntersectSphere3.getDistance());
//		System.out.println("Sphere1 intersect Sphere4: " + sphere1IntersectSphere4.isDoesIntersect());
//		System.out.println("Distance: " + sphere1IntersectSphere4.getDistance());
	}
	
	@Override
	public Vector3f getCenter() {
		return center;
	}
	public float getRadius() {
		return radius;
	}

}
