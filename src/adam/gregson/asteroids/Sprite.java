package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

import javax.lang.model.type.NullType;

import java.awt.Shape;
import java.awt.geom.Area;

public abstract class Sprite {
	
	private BufferedImage[] images;

	private int currentImage;
	protected double angle;
	
	protected int maxX;
	protected int maxY;

	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	protected Shape collisionBounds;

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
	
	public void setCollisionBounds(Shape bounds) {
		collisionBounds = bounds;

		var a = new Area(bounds);
		
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

		// Loop the position of the asteroid around the map
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

	// Returns the x-component of the center of the image sprite
	public double getVisualCenterX() {
		return x + images[currentImage].getWidth() / 2;
	}

	// Returns the y-component of the center of the image sprite
	public double getVisualCenterY() {
		return y + images[currentImage].getHeight() / 2;
	}

	/**
	 * @return integer 0 <=  n < images.length representing the number of 
	 * increments in the counterclockwise direction.
	 */
	public int getDirection(){
		return currentImage;
	}

	// In radians
	public double getAngleFacing() {
		return currentImage * Math.PI / 32.0; // (2pi / 64);
	}

	// Will return a value in radians
	public double getAngleOfDirectionOfMovement() {
		return Math.atan2(dy, dx);
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
