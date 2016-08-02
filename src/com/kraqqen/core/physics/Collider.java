package com.kraqqen.core.physics;

import com.kraqqen.util.math.Vector3f;
import com.kraqqen.util.timing.ReferenceCounter;
import com.sun.glass.ui.Pixels.Format;

public abstract class Collider extends ReferenceCounter {
	
	private int type;
	
	public enum ColliderTypes {
	    TYPE_SPHERE, 
	    TYPE_AABB, 
	    TYPE_PLANE,
	    TYPE_CYLINDER,
	    
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
		
		//self on self
		if(type == ColliderTypes.TYPE_SPHERE.ordinal() && other.getType() == ColliderTypes.TYPE_SPHERE.ordinal()){
			BoundingSphere self = (BoundingSphere)this;
			return self.IntersectBoundingSphere((BoundingSphere)other);
		}else if(type == ColliderTypes.TYPE_AABB.ordinal() && other.getType() == ColliderTypes.TYPE_AABB.ordinal()){
			AABB self = (AABB)this;
			return self.IntersectAABB((AABB)other);
		}
		
		//
		else if(type == ColliderTypes.TYPE_PLANE.ordinal() && other.getType() == ColliderTypes.TYPE_SPHERE.ordinal()){
			Plane self = (Plane)this;
			return self.IntersectSphere((BoundingSphere)other);
		}else if(type == ColliderTypes.TYPE_SPHERE.ordinal() && other.getType() == ColliderTypes.TYPE_PLANE.ordinal()){
			BoundingSphere self = (BoundingSphere)this;
			return ((Plane) other).IntersectSphere(self);
		}else if(type == ColliderTypes.TYPE_AABB.ordinal() && other.getType() == ColliderTypes.TYPE_SPHERE.ordinal()){
			AABB self = (AABB)this;
			return self.IntersectBoundingSphere((BoundingSphere)other);
		}/*else if(type == ColliderTypes.TYPE_AABB.ordinal() && other.getType() == ColliderTypes.TYPE_PLANE.ordinal()){
			return null;
		}*/else if(type == ColliderTypes.TYPE_SPHERE.ordinal() && other.getType() == ColliderTypes.TYPE_AABB.ordinal()){
			BoundingSphere self = (BoundingSphere)this;
			return ((AABB) other).IntersectBoundingSphere(self);
		}/*else if(type == ColliderTypes.TYPE_PLANE.ordinal() && other.getType() == ColliderTypes.TYPE_AABB.ordinal()){
			return null;
		}*/else{
			System.out.println("Error: Collision not implemented between " + format(this) + " and " + format(other) + " colliders.");
			//System.exit(1);
			return new IntersectData(false, new Vector3f(0, 0, 0));
		}
	}
	
	private static String format(Collider collider) {
		return collider.toString().replaceAll("com.kraqqen.core.physics.", "").replaceAll("@.*", "");
	}
	
	abstract void Transform(Vector3f translation);
	
	abstract Vector3f getCenter();

	public int getType() {
		return type;
	}
	
}
