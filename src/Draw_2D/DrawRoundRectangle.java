package Draw_2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DrawRoundRectangle extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        RoundRectangle2D rectangle2D = new
                RoundRectangle2D.Double(100,100 ,180 ,80 ,55 ,55 );

        graphics2D.setColor(Color.red);
        graphics2D.scale(1.5, 1.5);
        graphics2D.draw(rectangle2D);
    }
}
