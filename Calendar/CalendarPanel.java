package calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class CalendarPanel extends JPanel
{
	static Calendar cal = Calendar.getInstance();
	
	private String today;
	private int year = cal.get(Calendar.YEAR);
	private int month;
	private int startDay = startDay();
	private int endDay = endDay();
	
	public CalendarPanel()
	{
		setSize(500, 500);
		setLayout(null);
		
		Object[] week1 = new Object[7];
		Object[] week2 = new Object[7];
		Object[] week3 = new Object[7];
		Object[] week4 = new Object[7];
		Object[] week5 = new Object[7];
		Object[] week6 = new Object[7];
		
		addCalendarToTable(week1, week2, week3, week4, week5, week6);
		// show calendar
		JTable table = new JTable();   
		DefaultTableModel defaultTableModel = new DefaultTableModel()
			{
				@Override
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
			};
		table.setModel(defaultTableModel);
		
		defaultTableModel.addColumn("SUN");
		defaultTableModel.addColumn("MON");
		defaultTableModel.addColumn("TUE");
		defaultTableModel.addColumn("WED");
		defaultTableModel.addColumn("THU");
		defaultTableModel.addColumn("FRI");
		defaultTableModel.addColumn("SAT");
		
		defaultTableModel.addRow(week1);
		defaultTableModel.addRow(week2);
		defaultTableModel.addRow(week3);
		defaultTableModel.addRow(week4);
		defaultTableModel.addRow(week5);
		defaultTableModel.addRow(week6);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(90, 60, 300, 120);
		add(scrollPane);
		
		
		// button here
		JButton backButton = new JButton("<");
		backButton.setBounds(90, 25, 50, 30);
		
		JButton nextButton = new JButton(">");
		nextButton.setBounds(340, 25, 50, 30);
		add(nextButton);
		add(backButton);
		
		// label here
		JLabel today = new JLabel("TODAY:");
		today.setBounds(160, 25, 80, 30);
		add(today);
		
		// textfield here
		JTextField monthTextField = new JTextField();
		monthTextField.setBounds(20, 60, 50, 30);
		monthTextField.setText(String.valueOf(month) + "/" + String.valueOf(year));
		
		JTextField todayTextField = new JTextField();
		todayTextField.setBounds(220, 25, 110, 30);
		SimpleDateFormat simpleDayFormat = new SimpleDateFormat("dd/MM/yyyy");
		todayTextField.setText(simpleDayFormat.format(cal.getTime()));
		
		add(monthTextField);
		add(todayTextField);
		
		// action
		ActionListener actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == backButton)
				{
					if(month == 1)
					{
						year = year - 1;
						cal.add(Calendar.MONTH,-1);
					}
					else
						cal.add(Calendar.MONTH,-1);
					startDay();
					endDay();
					
						
					monthTextField.setText(String.valueOf(month) + "/" + String.valueOf(year));
					
					defaultTableModel.setRowCount(0);
					
					
					addCalendarToTable(week1, week2, week3, week4, week5, week6);
					defaultTableModel.addRow(week1);
					defaultTableModel.addRow(week2);
					defaultTableModel.addRow(week3);
					defaultTableModel.addRow(week4);
					defaultTableModel.addRow(week5);
					defaultTableModel.addRow(week6);
				}
				else if(e.getSource() == nextButton)
				{
					if(month == 12)
					{
						year = year + 1;
						cal.add(Calendar.MONTH,1);
					}
					else
						cal.add(Calendar.MONTH,1);
					startDay();
					endDay();
					monthTextField.setText(String.valueOf(month) + "/" + String.valueOf(year));
					
					defaultTableModel.setRowCount(0);
					
					addCalendarToTable(week1, week2, week3, week4, week5, week6);
					defaultTableModel.addRow(week1);
					defaultTableModel.addRow(week2);
					defaultTableModel.addRow(week3);
					defaultTableModel.addRow(week4);
					defaultTableModel.addRow(week5);
					defaultTableModel.addRow(week6);
				}
			}
		};
		backButton.addActionListener(actionListener);
		nextButton.addActionListener(actionListener);
	}

	// find start day month
	public int startDay() 
	{
		today = cal.getTime().toString();
		String[] dateSplit = today.split(" "); 
		int dayOfMonth = Integer.parseInt(dateSplit[2]);
		String dayOfWeek = dateSplit[0];
		
		int dayOfWeekInIntergerType = 0;
		
		switch (dayOfWeek) 
		{
			case "Sun":
				dayOfWeekInIntergerType = 0;
				break;
			case "Mon":
				dayOfWeekInIntergerType = 1;
				break;
			case "Tue":
				dayOfWeekInIntergerType = 2;
				break;
			case "Wed":
				dayOfWeekInIntergerType = 3;
				break;
			case "Thu":
				dayOfWeekInIntergerType = 4;
				break;
			case "Fri":
				dayOfWeekInIntergerType = 5;
				break;
			case "Sat":
				dayOfWeekInIntergerType = 6;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + dayOfWeek);
		}
		while(dayOfMonth >= 1)
		{
			if(dayOfWeekInIntergerType < 0)
				dayOfWeekInIntergerType = 6;
			startDay = dayOfWeekInIntergerType;
			dayOfWeekInIntergerType--;
			dayOfMonth--;
		}
		
		return startDay;
	}
	
	// find end day of month
	public int endDay() 
	{
		month = cal.get(Calendar.MONTH) + 1;
		switch (month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			endDay = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			endDay = 30;
			break;
		case 2:
			if(year % 4 == 0)
				endDay = 29;
			else
				endDay = 28;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + month);
		}
		return endDay;
	}
	
	// add calendar to table
	public void addCalendarToTable(Object[] week1, Object[] week2, Object[] week3, Object[] week4, Object[] week5, Object[] week6)
	{
		int dayCount = 2;
		week1[startDay] = 1;
		for(int i = 0; i < week1.length; i++)
		{
			if(i < startDay)
				week1[i] = " ";
			if(i > startDay && i < week1.length)
			{
				week1[i] = dayCount;
				dayCount++;
			}
		}
		for(int i = 0; i < week2.length; i++)
		{
			week2[i] = dayCount;
			dayCount++;
		}
		for(int i = 0; i < week3.length; i++)
		{
			week3[i] = dayCount;
			dayCount++;
		}
		for(int i = 0; i < week4.length; i++)
		{
			week4[i] = dayCount;
			dayCount++;
		}
		for(int i = 0; i < week5.length; i++)
		{
			if(dayCount <= endDay)
				week5[i] = dayCount;
			else if(dayCount > endDay)
				week5[i] = " ";
			dayCount++;
		}
		for(int i = 0; i < week6.length; i++)
		{
			if(dayCount <= endDay)
				week6[i] = dayCount;
			else if(dayCount > endDay)
				week6[i] = " ";
			dayCount++;
		}
	}
	
}
