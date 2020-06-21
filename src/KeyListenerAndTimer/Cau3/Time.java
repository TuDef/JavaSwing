package KeyListenerAndTimer.Cau3;


import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class Time extends JPanel {

	Time() {
		setSize(300, 300);
		setLayout(null);

		JLabel label = new JLabel("CLOCK");
		label.setForeground(Color.blue);
		label.setBounds(70, 60, 150, 30);
		add(label);

		JTextField textField = new JTextField();
		textField.setBounds(45, 100, 200, 30);
		textField.setEditable(false);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				textField.setText(new Date().toString());
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 0, 1000);
		add(textField);
	}
}