package unittests;

import primitives.*;

import elements.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class CameraTest {

	Camera testCamera;

	@Test
	public void testConstructRayThroughPixel() {
		
		final int WIDTH = 3;
		final int HEIGHT = 3;
		
		Point3D[][] screen = new Point3D [HEIGHT][WIDTH];
		
		Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
								   new Vector (0.0, 1.0, 0.0),
								   new Vector (0.0, 0.0, -1.0));
		
		System.out.println("Camera:\n" + camera);
		
		for (int i = 0; i < HEIGHT; i++){
			for (int j = 0; j < WIDTH; j++){
				
				Ray ray = camera.constructRayThroughPixel(WIDTH, HEIGHT, i, j, 1, 3 * WIDTH, 3 * HEIGHT);
				
				screen[i][j] = ray.getDirection();
				
				System.out.print(screen[i][j]);
				System.out.println(ray.getDirection());
				
				// checking z-coordinate
				assertTrue(Double.compare(screen[i][j].getZ().getNum(), -1.0) == 0);
				
				// Checking all options
				double x = screen[i][j].getX().getNum();
				double y = screen[i][j].getY().getNum();
				
				if (Double.compare(x, 3) == 0 ||
						Double.compare(x, 0) == 0 ||
						Double.compare(x, -3) == 0){
					
					if (Double.compare(y, 3) == 0 ||
							Double.compare(y, 0) == 0 ||
							Double.compare(y, -3) == 0){
						
						assertTrue(true);
					}
					else
						fail("Wrong y coordinate");
				} else
					fail("Wrong x coordinate");
			}
			System.out.println("---");
		}
	}

}
