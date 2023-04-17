package adam.gregson.asteroids;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AsteroidsGame extends JFrame{

	private static final long serialVersionUID = 1L;
	private InfoPanel infoPanel;
	private GamePanel gamePanel;
	
	public AsteroidsGame() {
		// Configure window settings
		setTitle("Gasteroids");
		initGUI();
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private void initGUI(){
		// infoPanel
		infoPanel = new InfoPanel(this);
		add(infoPanel,BorderLayout.PAGE_START);

		// gamePanel
		gamePanel = new GamePanel(this);
		add(gamePanel);
		//add(gamePanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}

		// Start game
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AsteroidsGame();
			}
		});
	}

}
