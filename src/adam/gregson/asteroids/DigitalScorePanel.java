package adam.gregson.asteroids;

import javax.swing.JPanel;

import java.awt.Color;


public class DigitalScorePanel extends JPanel {

   
	private static final long serialVersionUID = 1L;
    private static String scoreTemplate = "%07d"; 
    private DigitalLabel textLabel;
    private DigitalLabel scoreLabel;
    private int score;
    

    public DigitalScorePanel(int _score){
        score = _score;

        textLabel = new DigitalLabel("Score: ");

        scoreLabel = new DigitalLabel(String.format(scoreTemplate, score));
        updateScoreLabel();

        setBackground(Color.BLACK);
        add(textLabel);
        add(scoreLabel);
       
    }

    public int getCurrentScore(){
        return score;
    }

    public void setScore(int _score){
        score = _score;
        updateScoreLabel();
    }

    private void updateScoreLabel() {
        scoreLabel.setText(String.format(scoreTemplate,score));
    }

    
}
