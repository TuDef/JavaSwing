package StudentManagement;

import javax.swing.*;

import java.awt.*;


public class Control extends JFrame {
    public static void main(String[] args) {
        Control c = new Control();
        c.setVisible(true);

    }

    public Control() {
        setTitle("Student Management");
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JLabel label = new JLabel();
        label.setText("STUDENT MANAGEMENT");
        label.setBounds(200, 10, 300, 23);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        add(label);

        JLabel label4 = new JLabel();
        label4.setText("ADD NEW STUDENT");
        label4.setForeground(Color.GREEN);
        label4.setBounds(200, 370, 300, 23);
        label4.setFont(new Font("Serif", Font.PLAIN, 24));
        add(label4);

        addWindowListener(new MyPanel());

        MyPanel panel = new MyPanel();
        add(panel);




    }
}

