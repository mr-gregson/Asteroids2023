package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

import adam.gregson.mycommonmethods.FileIO;


public class Ship {
	
	private static final String FILE_NAME = "./PNG/playerShip1_red.png";
	private static BufferedImage image;
	
	private static int WIDTH;
	private static int HEIGHT;

	private static double directionX = 0.0;
	private static double directionY = 1.0;

	private static double velocityX = 0.0;
	private static double velocityY = 0.0;

	
	public Ship() {
		image = FileIO.readImageFile(this, FILE_NAME);
		
		
	}
	

}
