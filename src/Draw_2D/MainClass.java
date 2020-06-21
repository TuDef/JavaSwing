package Draw_2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;

public class MainClass extends JFrame {
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
    }


    public MainClass() {
        JFrame app = new JFrame();
        app.setSize(800, 600);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);

        //Muon su dung lop nao thi add lop do vao
        //app.add(new DrawStar());//Ve hinh ngoi sao
        app.add(new DrawRoundRectangle());//Ve hinh chu nhat co bo goc
        //app.add(new DrawCircle());//ve hinh tron


    }

}





























