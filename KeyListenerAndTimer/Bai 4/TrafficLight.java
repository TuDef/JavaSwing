package cau4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TrafficLight extends JPanel
{
	private Color red = Color.gray;
	private Color yellow = Color.gray;
	private Color green = Color.green;
	private String light = "green";
	
	public TrafficLight() 
	{
		JLabel label = new JLabel("TRAFFIC LIGHT");
		label.setBounds(100, 10, 80, 30);
		add(label);
		
		TimerTask timerTask = new TimerTask() 
		{
			
			@Override
			public void run() 
			{
				switchLight();
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(timerTask, 5000, 5000);
	} 
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(red);
		g.fillOval(200, 50, 50, 50);
		g.setColor(Color.gray);
		g.drawOval(200, 50, 50, 50);
		
		g.setColor(yellow);
		g.fillOval(150, 50, 50, 50);
		g.setColor(Color.gray);
		g.drawOval(150, 50, 50, 50);
		
		g.setColor(green);
		g.fillOval(100, 50, 50, 50);
		g.setColor(Color.gray);
		g.drawOval(100, 50, 50, 50);
		
	}
	
	private void switchLight()
	{
		if(light.equals("green") == true )
		{
			light = "yellow";
			green = Color.gray;
			yellow = Color.yellow;
		}
		else if(light.equals("yellow") == true)
		{
			light = "red";
			yellow = Color.gray;
			red = Color.red;
		}
		else if(light.equals("red") == true)
		{
			light = "green";
			red = Color.gray;
			green = Color.green;
		}
		
		repaint();
	}
	
}
