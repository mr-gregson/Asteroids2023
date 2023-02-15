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

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1300;
	private static final int HEIGHT = 800;
	
	private AsteroidsGame game;
	private Ship ship;
	private List<Rock> rocks;
	private Timer timer;

	private static BufferedImage[] shipImages;
	private static Map<RockSize,BufferedImage[]> rockImages;
	private static BufferedImage missileImage;

	

	private final static int NUMBER_OF_SHIP_IMAGES = 64;
	private final static int NUMBER_OF_ROCK_IMAGES = 32;
	private final static int NUMBER_OF_ROCK_TYPES = 3;

	static{
		shipImages = new BufferedImage[NUMBER_OF_SHIP_IMAGES];
		for (int i = 0; i < NUMBER_OF_SHIP_IMAGES; ++i){
			shipImages[i] = readImage(String.format("img/ship/ship%04d.png",i));
		}
		rockImages = new HashMap<>();
		RockSize[] rockSizes = RockSize.values();
		for (int i = 0; i < NUMBER_OF_ROCK_TYPES; ++i){
			rockImages.put(rockSizes[i], new BufferedImage[NUMBER_OF_ROCK_IMAGES]);
			for (int j = 0; j < NUMBER_OF_ROCK_IMAGES; ++j){
				rockImages.get(rockSizes[i])[j] = readImage(String.format("img/rock%d/rock%d%04d.png",i+1,i+1,j));
			}
		}
	}
	
	public GamePanel(AsteroidsGame game) {
		this.game = game;
		ship = new Ship(shipImages, WIDTH/2,HEIGHT/2,WIDTH, HEIGHT);
		rocks = new LinkedList<>();
		initGUI();
		timer.start();
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public void initGUI(){
		setFocusable(true);
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				switch (key){
					case KeyEvent.VK_A:
						ship.rotateLeft();
						break;
					case KeyEvent.VK_D:
						ship.rotateRight();
						break;
					case KeyEvent.VK_W:
						ship.accelerate(0.1);
				}
				repaint();
			}
		});

		timer = new Timer(30,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timedAction();
			}
		});

		addRock(RockSize.LARGE, (int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), (int) (Math.random() * NUMBER_OF_ROCK_IMAGES ));
	}

	private void addRock(RockSize rockSize, int x, int y, int direction){
		Rock rock = new Rock(rockImages.get(rockSize),rockSize, x,y,WIDTH,HEIGHT);
		rock.setDirection(18);
		rock.setSpeed(1);

		rocks.add(rock);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(ship.getImage(),(int) ship.getX(),(int) ship.getY(),null);
		for (Rock rock: rocks){
			g.drawImage(rock.getImage(),(int) rock.getX(),(int) rock.getY(),null);
		}
	}

	private void timedAction() {
		ship.move();
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
