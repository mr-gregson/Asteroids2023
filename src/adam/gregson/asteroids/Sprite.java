package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public abstract class Sprite {
	
	private BufferedImage[] images;
	private double x;
	private double y;
	private int maxX;
	private int maxY;

	private int currentImage;
	private double angle;
	private double dx;
	private double dy;

	
	
	/**
	 * @param images
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public Sprite(BufferedImage[] images, double x, double y, int maxX, int maxY) {
		this.images = images;
		this.currentImage = 0;	
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.maxY = maxY;

		this.angle = 2*Math.PI / images.length;
	}
	

	public void rotateLeft(){
		currentImage--;
		if (currentImage < 0) {
			currentImage = images.length - 1;
		}
	}

	public void rotateRight(){
		currentImage++;
		if (currentImage >= images.length) {
			currentImage = 0;
		}
	}

	public void accelerate(double acceleration) {
		dx += acceleration * Math.cos(currentImage * angle);
		dy += acceleration * Math.sin(currentImage * angle);
	}

	public void move() {
		x = Math.floorMod((int) (x+dx),maxX);
		y = Math.floorMod((int) (y+dy),maxY);
	}

	public BufferedImage getImage() {
		return images[currentImage];
	}

	public double getX() {
		return x;
	}	

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}	

	public void setY(double y) {
		this.y = y;
	}

	public double getDirection(){
		return currentImage * angle;
	}

	public double getSpeed(){
		return Math.sqrt(dx*dx+dy*dy);
	}

}
