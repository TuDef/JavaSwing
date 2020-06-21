package KeyListenerAndTimer.Cau1;

import javax.swing.JFrame;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame cau1 = new JFrame("CAU 1");
        cau1.setSize(1200, 800);
        cau1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cau1.setLocationRelativeTo(null);
        cau1.setResizable(false);

        MyBall myball = new MyBall();
        cau1.add(myball);

        cau1.setVisible(true);
    }


}
