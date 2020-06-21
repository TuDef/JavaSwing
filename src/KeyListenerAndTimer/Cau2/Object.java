package KeyListenerAndTimer.Cau2;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Object extends JPanel {
	private int x = 0, y = 0;
	private int angleX = 1, angleY = 0;
	
	Object() {
		setSize(1200, 800);
		setFocusable(true);
		ActionListener actionListener = e -> {
			if(x == 1130 && y < 710) {
				angleX = 0;
				angleY = 1;
			}
			else if(y == 710 && x > 0) {
				angleY = 0;
				angleX = -1;
			}
			else if(x == 0 && y > 0) {
				angleX = 0;
				angleY = -1;
			}
			else if(y == 0) {
				angleX = 1;
				angleY = 0;
			}
			x += angleX;
			y += angleY;
			Object.this.repaint();
		};
			new Timer(0, actionListener).start();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		setLayout(null);
		Graphics2D graphics2D = (Graphics2D) g;

		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setColor(Color.BLACK);
		graphics2D.fillOval(x, y, 50, 50);
	}

}

