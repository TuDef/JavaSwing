package Draw_2D;

import javax.swing.*;
import java.awt.*;

public class DrawStar extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        //Lam hinh ngoi sao khong bị lỗi cạnh nhìn xấu
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //ve hinh ngoi sao
        int[] x = {200,300,100,350,400,450,700,500,600,400};
        int[] y = {700,530,400,400,300,400,400,530,700,600};

        graphics2D.setColor(Color.YELLOW);
        graphics2D.fillPolygon(x , y, 10);

    }

}


