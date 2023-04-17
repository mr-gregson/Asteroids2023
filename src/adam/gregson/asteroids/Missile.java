package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public class Missile extends Sprite {
    private final double TRAVELLING_SPEED = 1000;
    // In seconds
    private final double LIFE_TIME = 0.8;
    private double timer = 0;

    public boolean isAlive = false;

    // angleOfMovement should be in radians
    public Missile(BufferedImage[] images, double x, double y, int maxX, int maxY, double deltaTime, double angleOfMovement) {
		super(images, x, y, maxX, maxY, deltaTime);

        // Set velocity
        dx = Math.cos(angleOfMovement) * TRAVELLING_SPEED;
        dy = Math.sin(angleOfMovement) * TRAVELLING_SPEED;

        isAlive = true;
	}

    
    @Override
    public void move() {
        // Make sure the bullet doesn't live too long
        timer += dt;

        if (timer > LIFE_TIME) {
            isAlive = false;
        }

        super.move();
    }
}
