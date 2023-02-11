package adam.gregson.asteroids;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class DigitalLevelPanel extends JPanel{
    private static final String levelFormat = "%02d";
    private int level;
    private JLabel textLabel;
    private JLabel levelLabel;
    
    
    public DigitalLevelPanel(int level){
        this.level = level;
        setBackground(Color.BLACK);
        textLabel = new DigitalLabel("Level: ");
        add(textLabel);
        levelLabel = new DigitalLabel(String.format(levelFormat,this.level));
        add(levelLabel);
    }

    public int getLevel(){
        return level;
    }
    public void nextLevel(){
        levelLabel.setText(String.format(levelFormat,++level));
    }

}
