package MouseListener;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;


public class MyPanel extends JPanel {
	private Point pointStart = null;
	private Point pointEnd   = null;

	MyPanel(){

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				pointStart = e.getPoint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				pointEnd = e.getPoint();
				repaint();
			}
		});
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (pointStart != null) {
			g.setColor(Color.BLUE);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
		}
	}
}
