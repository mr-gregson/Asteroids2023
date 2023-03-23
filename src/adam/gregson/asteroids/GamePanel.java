package adam.gregson.asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	// UI configuration
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1300;
	private static final int HEIGHT = 800;

	// Image loading and configuration
	private final static int NUMBER_OF_SHIP_IMAGES = 64;
	private final static int NUMBER_OF_ROCK_IMAGES = 32;
	private final static int NUMBER_OF_ROCK_TYPES = 3;

	// Image storage
	private static BufferedImage[] shipImages;
	private static Map<RockSize,BufferedImage[]> rockImages;
	private static BufferedImage missileImage;


	// Entities
	private AsteroidsGame game;
	private Ship ship;
	private List<Rock> rocks;

	// Timing
	private Timer timer;
	private final int FRAMES_PER_SECOND = 50;

	// deltaTime is the amount of time that changes between each frame.
	private final double deltaTime = 1 / ((double) FRAMES_PER_SECOND);

	static {
		shipImages = new BufferedImage[NUMBER_OF_SHIP_IMAGES];

		// Load images of the ships
		for (int i = 0; i < NUMBER_OF_SHIP_IMAGES; ++i){
			shipImages[i] = readImage(String.format("img/ship/ship%04d.png", i));
		}

		// Load all images of rocks and divide with rockImages map into into large, medium, and small types
		rockImages = new HashMap<RockSize, BufferedImage[]>();
		RockSize[] rockSizes = RockSize.values();
		
		for (int i = 0; i < NUMBER_OF_ROCK_TYPES; ++i){
			rockImages.put(rockSizes[i], new BufferedImage[NUMBER_OF_ROCK_IMAGES]);
			
			for (int j = 0; j < NUMBER_OF_ROCK_IMAGES; ++j){
				rockImages.get(rockSizes[i])[j] = readImage(String.format("img/rock%d/rock%d%04d.png",i+1,i+1,j));
			}
		}
	}
	
	// Instantiate game
	public GamePanel(AsteroidsGame game) {
		this.game = game;

		ship = new Ship(shipImages, WIDTH/2, HEIGHT/2, WIDTH, HEIGHT, deltaTime);
		rocks = new LinkedList<>();
		initGUI();
		timer.start();
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	// Instantiate gameloop
	public void initGUI() {
		setFocusable(true);
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				switch (key) {
					case KeyEvent.VK_A:
						ship.rotateLeft();
						break;
					case KeyEvent.VK_D:
						ship.rotateRight();
						break;
					case KeyEvent.VK_W:
						ship.accelerate(500);
				}
				repaint();
			}
		});

		timer = new Timer(1000 / FRAMES_PER_SECOND, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Refer to timedAction for main game loop
				timedAction();
			}
		});

		addRock(RockSize.LARGE, (int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), (int) (Math.random() * NUMBER_OF_ROCK_IMAGES ));
	}

	private void addRock(RockSize rockSize, int x, int y, int direction){
		Rock rock = new Rock(rockImages.get(rockSize), rockSize, x, y, WIDTH, HEIGHT, deltaTime);
		rock.setDirection(18);
		rock.setSpeed(35);

		rocks.add(rock);
	}
	
	// Paint all graphics
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(ship.getImage(),(int) ship.getX(),(int) ship.getY(),null);
		for (Rock rock: rocks){
			g.drawImage(rock.getImage(),(int) rock.getX(),(int) rock.getY(),null);
		}
	}

	// Main game loop
	private void timedAction() {
		ship.move();
		ship.decaySpeed();

		for (Rock rock : rocks){
			rock.move();
			rock.rotate();
		}
		repaint();
	}
	
	private static BufferedImage readImage(String filename){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new FileInputStream(filename));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return image;
	}

}
