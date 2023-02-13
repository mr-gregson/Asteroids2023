package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public abstract class Sprite {
	
	private BufferedImage[] images;
	private double x;
	private double y;

	private double dx;
	private double dy;
	private int maxX;
	private int maxY;
	private int currentImage;
	private double angle;

	private static final int maxA = 2;
	
	
	/**
	 * @param images
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public Sprite(BufferedImage[] images, double x, double y, int maxX, int maxY) {
		this.images = images;
		this.dx = 0;
		this.dy = 0;
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
		double tempX = Math.cos(angle)*dx - Math.sin(angle)*dy;
		double tempY = Math.sin(angle)*dx + Math.cos(angle)*dy;
		dx = tempX;
		dy = tempY;
	}

	public void rotateRight(){
		currentImage++;
		if (currentImage >= images.length) {
			currentImage = 0;
		}
		double tempX = Math.cos(-angle)*dx - Math.sin(-angle)*dy;
		double tempY = Math.sin(-angle)*dx + Math.cos(-angle)*dy;
		dx = tempX;
		dy = tempY;
	}

	public void accelerate(double acceleration) {
		if (Math.sqrt(dx*dx+dy+dy) < maxA){
			dx += acceleration * Math.cos(currentImage * angle);
			dy += acceleration * Math.sin(currentImage * angle);
		}
	}

	public void move() {
		x += dx;
		y += dy;
		if (x >= maxX) x -= maxX;
		if (y >= maxY) y -= maxY;
		if (x < 0) x += maxX;
		if (y < 0) y += maxY;
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
		if (y < maxY) this.y = y;
	}

}
