package com.kraqqen.core.physics;

import com.kraqqen.util.math.Vector3f;
import com.kraqqen.util.timing.ReferenceCounter;

public abstract class Collider extends ReferenceCounter {
	
	private int type;
	
	public enum ColliderTypes {
	    TYPE_SPHERE, 
	    TYPE_AABB, 
	    TYPE_PLANE,
	    
	    TYPE_SIZE
	}

	public Collider(int type) {
		new ReferenceCounter();
		this.type = type;
	}
	
	IntersectData Intersect(Collider other){
		
		/*TODO add support for:
		 *  AABB to BoundingSphere
		 *  AABB to Plane
		 *  BoundingSphere to AABB
		 *  Plane to AABB
		 */
		
		if(type == ColliderTypes.TYPE_SPHERE.ordinal() && other.getType() == ColliderTypes.TYPE_SPHERE.ordinal()){
			BoundingSphere self = (BoundingSphere)this;
			return self.IntersectBoundingSphere((BoundingSphere)other);
		}else if(type == ColliderTypes.TYPE_AABB.ordinal() && other.getType() == ColliderTypes.TYPE_AABB.ordinal()){
			AABB self = (AABB)this;
			return self.IntersectAABB((AABB)other);
		}else if(type == ColliderTypes.TYPE_PLANE.ordinal() && other.getType() == ColliderTypes.TYPE_SPHERE.ordinal()){
			Plane self = (Plane)this;
			return self.IntersectSphere((BoundingSphere)other);
		}else if(type == ColliderTypes.TYPE_SPHERE.ordinal() && other.getType() == ColliderTypes.TYPE_PLANE.ordinal()){
			BoundingSphere self = (BoundingSphere)this;
			return ((Plane) other).IntersectSphere(self);
		}else{
			System.out.println("Error: Collision not implemented between specified " + "colliders.");
			//System.exit(1);
			return new IntersectData(false, new Vector3f(0, 0, 0));
		}
	}
	
	abstract void Transform(Vector3f translation);
	
	abstract Vector3f getCenter();

	public int getType() {
		return type;
	}
	
}
