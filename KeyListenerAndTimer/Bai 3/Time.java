package cau3;

import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Time extends JPanel 
{
	
	public Time()
	{
		setSize(300, 300);
		setLayout(null);
		
		JLabel label = new JLabel("ROLEX | SWISS WATCH");
		label.setForeground(Color.blue);
		label.setBounds(70, 60, 150, 30);
		add(label);
		
		JTextField textField = new JTextField();
		textField.setBounds(45, 100, 200, 30);
		textField.setEditable(false);
		
		TimerTask task = new TimerTask()
		{
			
			@Override
			public void run() 
			{
				textField.setText(new Date().toString());
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 0, 1000);
		add(textField);
	}
}
