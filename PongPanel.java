import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PongPanel extends JPanel{
	
	private GamePanel gamePanel;
	
	public PongPanel(){
		JLabel scoreLabel = new JLabel("Score: 0");
		gamePanel = new GamePanel(scoreLabel);
		
		this.setLayout(new BorderLayout());
		this.add(scoreLabel, BorderLayout.NORTH);
		this.add(gamePanel, BorderLayout.CENTER);
	}
	
	public GamePanel getGamePanel(){
		return gamePanel;
	}
}
