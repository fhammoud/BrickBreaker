import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PongPanel extends JPanel{
	
	private JLabel scoreLabel;
	private GamePanel gamePanel;
	
	
	public PongPanel(int width, int height){
		
		//instantiate variables
		scoreLabel = new JLabel();
		gamePanel = new GamePanel(width, height, scoreLabel);
		
		//set layout and add elements to panel
		setLayout(new BorderLayout());
		add(scoreLabel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
	}
}
