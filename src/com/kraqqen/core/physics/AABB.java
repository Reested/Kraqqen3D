package com.kraqqen.core.physics;

import com.kraqqen.util.math.Vector3f;

public class AABB extends Collider{
	
	private Vector3f minExtents, maxExtents;

	public AABB(Vector3f minExtents, Vector3f maxExtents) {
		super(ColliderTypes.TYPE_AABB.ordinal());
		this.minExtents = minExtents;
		this.maxExtents = maxExtents;
	}
	
	public IntersectData IntersectAABB(AABB other){
		
		Vector3f distances1 = other.getMinExtents().Sub(maxExtents);
		Vector3f distances2 = minExtents.Sub(other.getMaxExtents());
		Vector3f distances = distances1.Max(distances2);
		
		float maxDistance = distances.Max();
		
		return new IntersectData(maxDistance < 0,distances);
	}
	
	public IntersectData IntersectBoundingSphere(BoundingSphere other){
		
		float dmin;
		Vector3f center, bmin, bmax;
		
		dmin = 0.0f;
		
	    center = other.getCenter();
	    bmin = this.getMinExtents();
	    bmax = this.getMaxExtents();

	    if (center.GetX() < bmin.GetX()) {
	        dmin += Math.pow(center.GetX() - bmin.GetX(), 2);
	    } else if (center.GetX() > bmax.GetX()) {
	        dmin += Math.pow(center.GetX() - bmax.GetX(), 2);
	    }

	    if (center.GetY() < bmin.GetY()) {
	        dmin += Math.pow(center.GetY() - bmin.GetY(), 2);
	    } else if (center.GetY() > bmax.GetY()) {
	        dmin += Math.pow(center.GetY() - bmax.GetY(), 2);
	    }

	    if (center.GetZ() < bmin.GetZ()) {
	        dmin += Math.pow(center.GetZ() - bmin.GetZ(), 2);
	    } else if (center.GetZ() > bmax.GetZ()) {
	        dmin += Math.pow(center.GetZ() - bmax.GetZ(), 2);
	    }

	    return new IntersectData(dmin <= Math.pow(other.getRadius(), 2), new Vector3f(0, 1, 0)); 
	}
	
	@Override
	void Transform(Vector3f translation) {
		minExtents.Add(translation);
		maxExtents.Add(translation);
	}
	
	public static void test(boolean print){
		
		AABB aabb1 = new AABB(new Vector3f(0.0f, 0.0f, 0.0f),  new Vector3f(1.0f, 1.0f, 1.0f));
		AABB aabb2 = new AABB(new Vector3f(1.0f, 1.0f, 1.0f),  new Vector3f(2.0f, 2.0f, 2.0f));
		AABB aabb3 = new AABB(new Vector3f(1.0f, 0.0f, 0.0f),  new Vector3f(2.0f, 1.0f, 1.0f));
		AABB aabb4 = new AABB(new Vector3f(0.0f, 0.0f, -2.0f), new Vector3f(1.0f, 1.0f, -1.0f));
		AABB aabb5 = new AABB(new Vector3f(0.0f, 0.5f, 0.0f),  new Vector3f(1.0f, 1.5f, 1.0f));
		AABB aabb6 = new AABB(new Vector3f(0.3f, 0.5f, 0.7f),  new Vector3f(1.3f, 1.5f, 1.7f));
		
		IntersectData aabb1Intersectaabb2 = aabb1.IntersectAABB(aabb2);
		IntersectData aabb1Intersectaabb3 = aabb1.IntersectAABB(aabb3);
		IntersectData aabb1Intersectaabb4 = aabb1.IntersectAABB(aabb4);
		IntersectData aabb1Intersectaabb5 = aabb1.IntersectAABB(aabb5);
		IntersectData aabb1Intersectaabb6 = aabb1.IntersectAABB(aabb6);
		
		assert(aabb1Intersectaabb2.isDoesIntersect() == false);
		assert(aabb1Intersectaabb2.getDistance()     == 0.0f);
		
		assert(aabb1Intersectaabb3.isDoesIntersect() == false);
		assert(aabb1Intersectaabb3.getDistance()     == 1.4142135f);
		
		assert(aabb1Intersectaabb4.isDoesIntersect() == false);
		assert(aabb1Intersectaabb4.getDistance()     == 1.7320508f);
		
		assert(aabb1Intersectaabb5.isDoesIntersect() == true);
		assert(aabb1Intersectaabb5.getDistance()     == 1.5f);
		
		assert(aabb1Intersectaabb6.isDoesIntersect() == true);
		assert(aabb1Intersectaabb6.getDistance()     == 0.9110434f);
		
		if(print){
			System.out.println("");
			System.out.println("AABB1 intersect AABB2: " + aabb1Intersectaabb2.isDoesIntersect());
			System.out.println("Distance: " + aabb1Intersectaabb2.getDistance());
			System.out.println("AABB1 intersect AABB3: " + aabb1Intersectaabb3.isDoesIntersect());
			System.out.println("Distance: " + aabb1Intersectaabb3.getDistance());
			System.out.println("AABB1 intersect AABB4: " + aabb1Intersectaabb4.isDoesIntersect());
			System.out.println("Distance: " + aabb1Intersectaabb4.getDistance());
			System.out.println("AABB1 intersect AABB5: " + aabb1Intersectaabb5.isDoesIntersect());
			System.out.println("Distance: " + aabb1Intersectaabb5.getDistance());
			System.out.println("AABB1 intersect AABB6: " + aabb1Intersectaabb6.isDoesIntersect());
			System.out.println("Distance: " + aabb1Intersectaabb6.getDistance());
			System.out.println("");
		}
	}

	public Vector3f getMinExtents() {
		return minExtents;
	}

	public Vector3f getMaxExtents() {
		return maxExtents;
	}

	@Override
	Vector3f getCenter() {
		return (minExtents.Add(maxExtents)).Div(2);
	}

}
