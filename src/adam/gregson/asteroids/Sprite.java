package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public abstract class Sprite {
	
	private BufferedImage[] images;
	private int maxX;
	private int maxY;

	private int currentImage;
	protected double angle;

	
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	// Will always be constant
	protected double dt;
	
	/**
	 * @param images
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public Sprite(BufferedImage[] images, double x, double y, int maxX, int maxY, double deltaTime) {
		this.images = images;
		this.currentImage = 0;	
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.maxY = maxY;
		this.dt = deltaTime;

		this.angle = 2*Math.PI / images.length;
	}
	

	public void rotateLeft(){
		currentImage--;
		if (currentImage < 0) {
			currentImage = Math.floorMod(currentImage, images.length);
		}
	}

	public void rotateRight(){
		currentImage++;
		if (currentImage >= images.length) {
			currentImage = Math.floorMod(currentImage, images.length);
		}
	}

	// Change speed [dx, dy] by acceleration
	public void accelerate(double acceleration) {
		dx += acceleration * Math.cos(currentImage * angle) * dt;
		dy += acceleration * Math.sin(currentImage * angle) * dt;
	}

	public void move() {
		x += dx * dt;
		y += dy * dt;

		// Loop the position of the asteroid
		if (x < 0) x += maxX;
		if (y < 0) y += maxY;
		if (x > maxX) x -= maxX;
		if (y > maxY) y -= maxY;
	}

	public BufferedImage getImage() {
		return images[currentImage];
	}

	public double getX() {
		return x;
	}	

	public double getY() {
		return y;
	}	

	/**
	 * @return integer 0 <=  n < images.length representing the number of 
	 * increments in the counterclockwise direction.
	 */
	public int getDirection(){
		return currentImage;
	}

	public void setDirection(int increments){
		currentImage = Math.floorMod(increments, images.length);
	}

	public double getSpeed(){
		return Math.sqrt(dx*dx + dy*dy);
	}

	public void setSpeed(double speed){
		dx = speed * Math.cos(currentImage * angle);
		dy = speed * Math.sin(currentImage * angle);

	}
}
