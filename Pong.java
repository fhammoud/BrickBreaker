import javax.swing.JFrame;

public class Pong {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Pong");
		int width = 410;
		int height = 600;
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		PongPanel pongPanel = new PongPanel();
		frame.add(pongPanel);
		frame.setVisible(true);
		pongPanel.getGamePanel().startGame();
	}
}
