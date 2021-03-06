package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 3D Object: Plane.
 *
 */
public class Plane extends Geometry {
	private Point3D point;
	private Vector normal;
	
	/**
	 *"Description	Resource	Path	Location	Type
	 *Implicit super constructor Plane() is undefined. Must explicitly invoke another constructor	Triangle.java	/IME5778-E1-1913-5687/src/geometries	line 16	Java Problem"
	 *
	 *In order to avoid the above error a default constructor had had to be built
	 */
	public Plane() {
		
	}
	
	public Plane(Point3D p,Vector v, Color color){
		super(color);
		point = new Point3D(p);
		normal = new Vector(v);
	}
	public Plane(Plane obj) {
		super(obj.getEmmission());
		point = new Point3D(obj.getPoint());
		normal = new Vector(obj.getNormal());
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @param p3
	 * The constructor takes three points and calculates the plane
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3, Color color) {
		super(color);
		Vector p1_2 = new Vector(p1.subtract(p2));
		Vector p1_3 = new Vector(p2.subtract(p3));
		Vector n = p1_2.crossProduct(p1_3);
		normal = n.normalize();
		point = new Point3D(p1);
	}
	
	// ***************** Getters/Setters ********************** //
	public Point3D getPoint() {return point;}
	
	public Vector getNormal() {return normal;}


	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Plane))
			return false;
		if(this==obj)
			return true;
		Plane other = new Plane((Plane)obj);
		return this.point.equals(other.point) && this.normal.equals(other.normal) && this.getEmmission().equals(other.getEmmission());
	}
	
	@Override
	/**
	 * @param ray
	 * Calculates and returns the points where the given ray cross the plane
	 */
	public HashMap<Geometry,ArrayList<Point3D>> findIntersection(Ray ray) {
		
		HashMap<Geometry,ArrayList<Point3D>> result = new HashMap<Geometry,ArrayList<Point3D>>();
		ray = new Ray(ray.normalize(),ray.getLocation());
		double t = (normal.dotProduct(point.vectorSubstraction(ray.getLocation())))
					/(normal.dotProduct(ray.getDirection()));
		if(t>0) {
			ArrayList<Point3D> arr = new ArrayList<Point3D>();
			arr.add(ray.getLocation().add(ray.getDirection().scalarMuliplication(t).getPoint3D()));
			result.put(this, arr);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Point: "+point.toString()+", Vector: "+normal.toString();
		}
	
	//Operators
	@Override
	public Vector getNormal(Point3D p) {
		return normal.normalize();
	}

}
