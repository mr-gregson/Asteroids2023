package adam.gregson.asteroids;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ship extends Sprite{

	private static final int ROTATIONS = 64;
	private static final BufferedImage[] images;

	static{
		images = new BufferedImage[ROTATIONS];
		for (int i = 0; i < ROTATIONS; i++)
		try{
			images[i] = ImageIO.read(new BufferedInputStream(new FileInputStream(String.format("img/ship/ship00%02d.png", i))));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	public Ship(double x, double y, int maxX, int maxY) {
		super(images, x, y, maxX, maxY);
	}

	public static void main(String[] args) {
		Ship ship = new Ship(0, 0, 0, 0);

	}
}
