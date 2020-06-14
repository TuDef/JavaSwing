package cau2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Object extends JPanel 
{
	private int x;
	private int y;
	private int moveX = 10;
	private int moveY = 0;
	
	public Object()
	{	
		setSize(1200, 800);
		
		setFocusable(true);
		
		ActionListener actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				autoRun();
			}
		};
		new Timer(100, actionListener).start();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		Rectangle rectangle = new Rectangle(x , y, 30, 30);
		graphics.draw(rectangle);
	}
	
	public void autoRun()
	{
		repaint();
		
		x = x + moveX;
		y = y + moveY;
		
		if(x == 1150 && y < 720)
		{
			moveX = 0;
			moveY = 10;
		}
		else if(y == 720 && x > 0)
		{
			moveY = 0;
			moveX = -10;
		}
		else if(x == 0 && y > 0)
		{
			moveX = 0;
			moveY = -10;
		}
		else if(y == 0)
		{
			moveX = 10;
			moveY = 0;
		}
	}
}

