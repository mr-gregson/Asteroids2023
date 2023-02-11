package adam.gregson.asteroids;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.File;

import javax.swing.JLabel;

public class DigitalLabel extends JLabel {
	
	
	private static final long serialVersionUID = 1L;

	
	public DigitalLabel(String text) {
	
		setBackground(Color.BLACK);
		setForeground(Color.GREEN);
		setText(text);
		registerFont();
	}

	private void registerFont(){
		try {
			GraphicsEnvironment
					.getLocalGraphicsEnvironment()
					.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("bin\\govodore-g64.ttf")));
					setFont(new Font("Govodore G64", Font.PLAIN, 24));
		} catch (IOException | FontFormatException e) {
			setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
		}
	}
}
