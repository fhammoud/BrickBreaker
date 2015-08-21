import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	private int x, y;
	private JButton startGame, exit;
	private TitlePanel titlePanel;
	//private Title title;
	
	public Menu()
	{
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(2, 1));
		x = 50;
		y = 50;
		
		//title
		titlePanel = new TitlePanel();
		titlePanel.setBackground(Color.WHITE);
		this.add(titlePanel);
		
		//buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1));
		startGame = new JButton("Start Game");
		startGame.setBackground(Color.WHITE);
		exit = new JButton("Exit");
		exit.setBackground(Color.WHITE);
		buttons.add(startGame);
		buttons.add(exit);
		this.add(buttons);
		
		repaint();
	}
	
	private class TitlePanel extends JPanel{
		
		public TitlePanel(){
			//repaint();
		}
		
		public void paintComponent(Graphics page)
		{
			page.setColor(Color.DARK_GRAY);
			page.drawString("Brick Breaker", x, y);
		}
	}
}
