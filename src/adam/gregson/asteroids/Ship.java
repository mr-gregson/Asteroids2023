package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public class Ship extends Sprite{

	private static final int ROTATIONS = 64;
	
	private final double speedDecayFactor = 0.75;
	private final double speedLimit = 750;

	public Ship(BufferedImage[] images,double x, double y, int maxX, int maxY, double deltaTime) {
		super(images, x, y, maxX, maxY, deltaTime);
	}
	public void decaySpeed() {
		dx -= dx*speedDecayFactor*dt;
		dy -= dy*speedDecayFactor*dt;

		if (getSpeed() > speedLimit) {
			setSpeed(speedLimit);
		}
	}
}
