package KeyListenerAndTimer.Cau1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MyBall extends JPanel implements KeyListener, ActionListener {
    private int x = 0, y = 0;

    MyBall() {
        Timer timer = new Timer(0, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setLayout(null);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillOval(x, y, 50, 50);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }
    @Override
    public void keyPressed(KeyEvent e) {
    	int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP) {
            y -= 10;
            repaint();
        } else if (k == KeyEvent.VK_DOWN) {
            y += 10;
            repaint();
        } else if (k == KeyEvent.VK_LEFT) {
            x -= 10;
            repaint();
        } else if (k == KeyEvent.VK_RIGHT) {
            x += 10;
            repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }

	@Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(x<0){
            x=0;
    		repaint();
		}else if(x>1130){
            x=1130;
            repaint();
		}else if(y<0){
            y=0;
            repaint();
		}else if(y>710){
            y=710;
            repaint();
		}
    }

}
