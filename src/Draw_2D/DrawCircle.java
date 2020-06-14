package Draw_2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Double;

public class DrawCircle extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Co 2 cach lam : 1. sử dụng fillOval , 2.sử dung fillRoundRect

        RoundRectangle2D rectangle2D = new
                RoundRectangle2D.Double(100,100 ,100 ,100 ,100 ,100 );

        graphics2D.setColor(Color.BLACK);
        graphics2D.scale(1.5, 1.5);
        graphics2D.draw(rectangle2D);


    }
}
