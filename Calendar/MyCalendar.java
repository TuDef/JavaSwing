package calendar;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyCalendar extends JFrame
{
	public MyCalendar()
	{
		setTitle("CALENDAR");
		setSize(500, 500);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		CalendarPanel calendarPanel = new CalendarPanel();
		add(calendarPanel);
	}
	
	public static void main(String[] args) 
	{
		MyCalendar calendar = new MyCalendar();
		calendar.setVisible(true);
	}
	
}
