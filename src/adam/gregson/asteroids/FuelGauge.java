package adam.gregson.asteroids;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FuelGauge extends JPanel{

    private static final int MAX_FUEL = 5000;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 20;
    private static final int NUM_BARS = 25;
    
    private int fuel;

    public FuelGauge(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        fuel = MAX_FUEL;
    }

    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(0,0, 100, HEIGHT);
        g.setColor(Color.YELLOW);
        g.fillRect(100, 0, 100, HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(200,0,200,HEIGHT);
    }

    
}
