import java.awt.Color;
import java.awt.Graphics;
//import java.util.Random;

public class Ball extends GameObject{
	
	public Ball(int x, int y, int d)
	{
		super(x, y, d, d);
	}
	
	public void paint(Graphics page)
	{
//		Random gen = new Random();
//		int r = gen.nextInt(255);
//		int g = gen.nextInt(255);
//		int b = gen.nextInt(255);
//		
//		page.setColor(new Color(r, g, b));
		page.setColor(Color.DARK_GRAY);
		page.fillOval(x, y, width, height);
	}
}