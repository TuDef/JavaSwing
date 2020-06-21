package KeyListenerAndTimer.Cau3;
import javax.swing.*;

public class Main extends JFrame {
	private Main() {
		setTitle("CAU 3");
		setSize(300, 300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Time time = new Time();
		add(time);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setVisible(true);
	}

}
