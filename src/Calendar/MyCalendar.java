package Calendar;

import javax.swing.JFrame;


public class MyCalendar extends JFrame {

	public static void main(String[] args) {
		MyCalendar calendar = new MyCalendar();
		calendar.setVisible(true);

	}
	private MyCalendar() {
		setTitle("MY CALENDAR");
		setSize(500, 300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		add(new CalendarSwing());
	}

}
