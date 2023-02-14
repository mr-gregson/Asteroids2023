package adam.gregson.asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1300;
	private static final int HEIGHT = 800;
	
	private AsteroidsGame game;
	private Ship ship;
	private Timer timer;
	
	public GamePanel(AsteroidsGame game) {
		this.game = game;
		ship = new Ship(WIDTH/2,HEIGHT/2,WIDTH, HEIGHT);
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

		timer = new Timer(40,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timedAction();
			}
		});

	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(ship.getImage(),(int) ship.getX(),(int) ship.getY(),null);
	}

	private void timedAction() {
		ship.move();
		repaint();
	}
	

}
