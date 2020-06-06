package SlideShow;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {
    public static void main(String[] args)  {
        Main main = new Main();
    }
    private Main()  {
        setTitle("Slide Show");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        MyPanel myPanel = new MyPanel();
        add(myPanel);
    }

}
