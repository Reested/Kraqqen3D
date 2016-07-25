package com.kraqqen.core.physics;

import com.kraqqen.util.math.Scalar;
import com.kraqqen.util.math.Vector3f;

public class Plane extends Collider{
	
	private Vector3f normal;
	private float distance;
	
	public Plane(Vector3f normal, float distance) {
		super(ColliderTypes.TYPE_PLANE.ordinal());
		this.normal = normal;
		this.distance = distance;
	}
	
	public IntersectData IntersectSphere(BoundingSphere other){
		
		float distanceFromSphereCenter = Scalar.abs(normal.Dot(other.getCenter().Add(distance)));
		float distanceFromSphere = distanceFromSphereCenter - other.getRadius();
		
		return new IntersectData(distanceFromSphere < 0, normal.Mul(distanceFromSphere));
	}
	
	public Plane Normalized(){
		float magnitude = normal.Length();
		return new Plane(normal.Div(magnitude), distance/magnitude);
	}
	
	@Override
	void Transform(Vector3f translation) {}
	
	public static void test(boolean print){
		
		Plane plane1 = new Plane(new Vector3f(0.0f, 1.0f, 0.0f), 0.0f);
		
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f, 0.0f, 0.0f), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
		BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0.0f, 0.0f, 2.0f), 1.0f);
		BoundingSphere sphere4 = new BoundingSphere(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);
		
		IntersectData plane1IntersectSphere1 = plane1.IntersectSphere(sphere1);
		IntersectData plane1IntersectSphere2 = plane1.IntersectSphere(sphere2);
		IntersectData plane1IntersectSphere3 = plane1.IntersectSphere(sphere3);
		IntersectData plane1IntersectSphere4 = plane1.IntersectSphere(sphere4);
		
		assert(plane1IntersectSphere1.isDoesIntersect() == true);
		assert(plane1IntersectSphere1.getDistance()     == 1.0f);
		
		assert(plane1IntersectSphere2.isDoesIntersect() == false);
		assert(plane1IntersectSphere2.getDistance()     == 2.0f);
		
		assert(plane1IntersectSphere3.isDoesIntersect() == true);
		assert(plane1IntersectSphere3.getDistance()     == 1.0f);
		
		assert(plane1IntersectSphere4.isDoesIntersect() == true);
		assert(plane1IntersectSphere4.getDistance()     == 1.0f);
		
		if(print){
			System.out.println("");
			System.out.println("Plane1 intersect Sphere1: " + plane1IntersectSphere1.isDoesIntersect());
			System.out.println("Distance: " + plane1IntersectSphere1.getDistance());
			System.out.println("Plane1 intersect Sphere2: " + plane1IntersectSphere2.isDoesIntersect());
			System.out.println("Distance: " + plane1IntersectSphere2.getDistance());
			System.out.println("Plane1 intersect Sphere3: " + plane1IntersectSphere3.isDoesIntersect());
			System.out.println("Distance: " + plane1IntersectSphere3.getDistance());
			System.out.println("Plane1 intersect Sphere4: " + plane1IntersectSphere4.isDoesIntersect());
			System.out.println("Distance: " + plane1IntersectSphere4.getDistance());
			System.out.println("");
		}
	}

	public Vector3f getNormal() {
		return normal;
	}

	public float getDistance() {
		return distance;
	}
	
	@Override
	Vector3f getCenter() {
		return normal;
	}
	
}
