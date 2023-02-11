package adam.gregson.asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1300;
	private static final int HEIGHT = 800;
	
	private AsteroidsGame game;
	
	public GamePanel(AsteroidsGame game) {
		this.game = game;
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	

}
