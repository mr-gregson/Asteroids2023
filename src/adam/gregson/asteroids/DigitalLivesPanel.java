package adam.gregson.asteroids;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class DigitalLivesPanel extends JPanel {
    
    private static final String livesFormat = "%02d";
    private int lives;
    private JLabel textLabel;
    private JLabel livesLabel;
    
    
    public DigitalLivesPanel(int lives){
        this.lives = lives;
        setBackground(Color.BLACK);
        textLabel = new DigitalLabel("Lives: ");
        add(textLabel);
        livesLabel = new DigitalLabel(String.format(livesFormat,this.lives));
        add(livesLabel);
    }

   
    public int addLives(int n){
        lives += n;
        livesLabel.setText(String.format(livesFormat,lives));
        return lives;
    }

    public int subtractLife(){
        livesLabel.setText(String.format(livesFormat, --lives));
        return lives;
    }

}
