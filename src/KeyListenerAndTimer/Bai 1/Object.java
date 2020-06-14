package cau1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Object extends JComponent implements KeyListener, ActionListener
{
	Timer timer = new Timer(1, this);
	private int x = 12;
	private int y = 12;
	private int moveX = 0;
	private int moveY = 0;
	private int width = 30;
	private int height = 30;
	
	public Object()
	{
		setSize(1200, 800);
		
		addKeyListener(this);
		setFocusable(true);
		
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		
		Rectangle rectangle = new Rectangle(x, y, width, height);
		graphics.setColor(Color.BLUE);
		graphics.draw(rectangle);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		x = x + moveX;
		y = y + moveY;
		repaint();
	}
	
	private void moveUp() 
	{
		if(y < 30)
		{
			moveX = 0;
			moveY = 0;
		}
		else
		{
			moveX = 0;
			moveY = -10;
		}
	}
	
	private void moveDown() 
	{
		if(y > 690)
		{
			moveX = 0;
			moveY = 0;
		}
		else
		{
			moveX = 0;
			moveY = 10;
		}
	}
	
	private void moveLeft() 
	{
		if(x < 30)
		{
			moveX = 0;
			moveY = 0;
		}
		else
		{
			moveX = -10;
			moveY = 0;
		}
	}
	
	private void moveRight() 
	{
		if(x > 1120)
		{
			moveX = 0;
			moveY = 0;
		}
		else
		{
			moveX = +10;
			moveY = 0;
		}
	}
	
	private void resetMove()
	{
		moveX = 0;
		moveY = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP)
		{
			moveUp();
		}
		if(key == KeyEvent.VK_DOWN)
		{
			moveDown();
		}
		if(key == KeyEvent.VK_LEFT)
		{
			moveLeft();
		}
		if(key == KeyEvent.VK_RIGHT)
		{
			moveRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP)
			resetMove();
		if(key == KeyEvent.VK_DOWN)
			resetMove();
		if(key == KeyEvent.VK_LEFT)
			resetMove();
		if(key == KeyEvent.VK_RIGHT)
			resetMove();
	}
	
}
