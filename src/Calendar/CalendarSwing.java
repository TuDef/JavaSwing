package Calendar;

import java.awt.*;
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


class CalendarSwing extends JPanel {
	private Calendar cal = Calendar.getInstance();
	private int year = cal.get(Calendar.YEAR);
	private int month= cal.get(Calendar.MONTH) + 1;

	CalendarSwing() {
		setSize(500, 500);
		setLayout(null);

		
		JButton backButton = new JButton("<=");
		backButton.setBounds(80, 25, 70, 30);

		JButton nextButton = new JButton("=>");
		nextButton.setBounds(340, 25, 70, 30);

		add(nextButton);
		add(backButton);

		
		JLabel label = new JLabel("TODAY:");
		label.setForeground(Color.BLUE);
		label.setBounds(160, 25, 80, 30);
		label.setFont(new Font("Serif", Font.PLAIN, 15));
		add(label);

		
		JTextField monthTextField = new JTextField();
		monthTextField.setBounds(220, 190, 50, 30);
		monthTextField.setRequestFocusEnabled(false);
		monthTextField.setBackground(Color.GRAY);
		monthTextField.setText(month + "/" + year);

		JTextField todayTextField = new JTextField();
		todayTextField.setBounds(220, 25, 110, 30);
		todayTextField.setRequestFocusEnabled(false);

		SimpleDateFormat simpleDayFormat = new SimpleDateFormat("dd/MM/yyyy");
		todayTextField.setText(simpleDayFormat.format(cal.getTime()));

		add(monthTextField);
		add(todayTextField);


		Object[] column1 = new Object[7];
		Object[] column2 = new Object[7];
		Object[] column3 = new Object[7];
		Object[] column4 = new Object[7];
		Object[] column5 = new Object[7];
		Object[] column6 = new Object[7];

		addCalendarToTable(column1, column2, column3, column4, column5, column6);
		
		
		JTable table = new JTable();
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(defaultTableModel);

		defaultTableModel.addColumn("SUNDAY");
		defaultTableModel.addColumn("MONDAY");
		defaultTableModel.addColumn("TUESDAY");
		defaultTableModel.addColumn("WEDNESDAY");
		defaultTableModel.addColumn("THURSDAY");
		defaultTableModel.addColumn("FRIDAY");
		defaultTableModel.addColumn("SATURDAY");

		defaultTableModel.addRow(column1);
		defaultTableModel.addRow(column2);
		defaultTableModel.addRow(column3);
		defaultTableModel.addRow(column4);
		defaultTableModel.addRow(column5);
		defaultTableModel.addRow(column6);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 60, 470, 120);
		add(scrollPane);

		// Action
		ActionListener actionListener = e -> {
			if(e.getSource() == backButton) {
				if(month == 1) {
					year--;
					cal.add(Calendar.MONTH,-1);
					month--;
					month = 12;
				}
				else {
					month--;
					cal.add(Calendar.MONTH,-1);
				}
				firstDay();
				lastDay();


				monthTextField.setText(month + "/" + year);

				defaultTableModel.setRowCount(0);


				addCalendarToTable(column1, column2, column3, column4, column5, column6);
				defaultTableModel.addRow(column1);
				defaultTableModel.addRow(column2);
				defaultTableModel.addRow(column3);
				defaultTableModel.addRow(column4);
				defaultTableModel.addRow(column5);
				defaultTableModel.addRow(column6);
			}
			else if(e.getSource() == nextButton) {
				if(month == 12) {
					year++;
					month++;
					month = 1;
					cal.add(Calendar.MONTH,1);
				}
				else {
					month++;
					cal.add(Calendar.MONTH,1);
				}
				firstDay();
				lastDay();
				monthTextField.setText(month + "/" + year);

				defaultTableModel.setRowCount(0);

				addCalendarToTable(column1, column2, column3, column4, column5, column6);
				defaultTableModel.addRow(column1);
				defaultTableModel.addRow(column2);
				defaultTableModel.addRow(column3);
				defaultTableModel.addRow(column4);
				defaultTableModel.addRow(column5);
				defaultTableModel.addRow(column6);
			}
		};
		backButton.addActionListener(actionListener);
		nextButton.addActionListener(actionListener);
	}

	private int firstDay() {
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		String firstDayOfTheMonth = cal.getTime().toString();

		int dayOfWeek = 0;

		if ("Sun".equals(firstDayOfTheMonth.substring(0, 3))) {
		} else if ("Mon".equals(firstDayOfTheMonth.substring(0, 3))) {
			dayOfWeek = 1;
		} else if ("Tue".equals(firstDayOfTheMonth.substring(0, 3))) {
			dayOfWeek = 2;
		} else if ("Wed".equals(firstDayOfTheMonth.substring(0, 3))) {
			dayOfWeek = 3;
		} else if ("Thu".equals(firstDayOfTheMonth.substring(0, 3))) {
			dayOfWeek = 4;
		} else if ("Fri".equals(firstDayOfTheMonth.substring(0, 3))) {
			dayOfWeek = 5;
		} else if ("Sat".equals(firstDayOfTheMonth.substring(0, 3))) {
			dayOfWeek = 6;
		} else {
			System.err.println("Loi ngay");
		}
		return dayOfWeek;
	}

	private int lastDay() {
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return Integer.parseInt(cal.getTime().toString().substring(8,10));
	}

	private void addCalendarToTable(Object[] week1, Object[] week2, Object[] week3, Object[] week4, Object[] week5, Object[] week6) {
		int count = 2;
		week1[firstDay()] = 1;
		for(int i = 0; i < week1.length; i++) {
			if(i < firstDay()) {
				week1[i] = " ";
			}
			if(i > firstDay()) {
				week1[i] = count;
				count++;
			}
		}
		for(int i = 0; i < week2.length; i++) {
			week2[i] = count;
			count++;
		}
		for(int i = 0; i < week3.length; i++) {
			week3[i] = count;
			count++;
		}
		for(int i = 0; i < week4.length; i++) {
			week4[i] = count;
			count++;
		}
		for(int i = 0; i < week5.length; i++) {
			if(count <= lastDay()) {
				week5[i] = count;
			} else if(count > lastDay()) {
				week5[i] = " ";
			}
			count++;
		}
		for(int i = 0; i < week6.length; i++) {
			if(count <= lastDay()) {
				week6[i] = count;
			} else if(count > lastDay()) {
				week6[i] = " ";
			}
			count++;
		}
	}

}
