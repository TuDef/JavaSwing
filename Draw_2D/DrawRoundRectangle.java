package Draw_2D;

import javax.swing.*;
import java.awt.*;

public class DrawRoundRectangle extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.scale(1.5, 1.5);
        graphics2D.setColor(Color.red);
        graphics2D.fillRoundRect(100, 100, 180, 80, 55, 55);
    }
}
