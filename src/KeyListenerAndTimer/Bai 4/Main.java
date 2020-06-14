package cau4;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame 
{
	public Main() 
	{
		setTitle("CAU 4");
		setSize(370, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		TrafficLight trafficLight = new TrafficLight();
		add(trafficLight);
		
	}
	
	public static void main(String[] args) 
	{
		Main display = new Main();
		display.setVisible(true);
	}

}
