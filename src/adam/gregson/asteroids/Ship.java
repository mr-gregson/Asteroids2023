package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public class Ship extends Sprite{

	private static final int ROTATIONS = 64;
	
	
	
	public Ship(BufferedImage[] images,double x, double y, int maxX, int maxY) {
		super(images, x, y, maxX, maxY);
	}
}
