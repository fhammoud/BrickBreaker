import javax.swing.JFrame;


public class Pong {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Test");
		JFrame frame = new JFrame("Pong");
		int width = 410;
		int height = 600;
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		PongPanel game = new PongPanel(width, height);
		frame.add(game);
		frame.setVisible(true);
		System.out.println(frame.getWidth());
	}
}
