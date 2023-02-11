package adam.gregson.asteroids;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;


public class InfoPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int score;
    private int lives;
    private int level;

    private FlowLayout layout;
    private DigitalScorePanel scorePanel;
    private DigitalLevelPanel levelPanel;
    private DigitalLivesPanel livesPanel;
    private FuelGauge fuelGauge;
    

    public InfoPanel(AsteroidsGame game){
        score = 0;
        level = 0;
        lives = 2;
        layout = (FlowLayout) getLayout();
        layout.setHgap(5);

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        scorePanel = new DigitalScorePanel(score);
        add(scorePanel);
        levelPanel = new DigitalLevelPanel(level);
        add(levelPanel);
        livesPanel = new DigitalLivesPanel(lives);
        add(livesPanel);
        fuelGauge = new FuelGauge();
        add(fuelGauge);
        
    }
}