import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends GameObject{
	
	public Paddle(int x, int y, int w, int h){
		super(x, y, w, h);
	}

	public void paint(Graphics page)
	{
		page.setColor(Color.DARK_GRAY);
		page.fillRect(x, y, width, height);
	}
}