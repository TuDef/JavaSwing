package KeyListenerAndTimer.Cau2;

import javax.swing.JFrame;

public class Main extends JFrame {
	public static void main(String[] args) {
		JFrame cau2 = new JFrame("CAU 2");
		cau2.setSize(1200, 800);
		cau2.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cau2.setLocationRelativeTo(null);
		cau2.setResizable(false);
		
		Object object = new Object();
		cau2.add(object);
		
		cau2.setVisible(true);
	}

	
}
