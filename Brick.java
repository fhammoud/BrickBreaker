import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Brick extends GameObject{
	
	private int red, green, blue;
	
	public Brick(int x, int y, int w, int h){
		super(x, y, w, h);
		Random gen = new Random();
		red = gen.nextInt(255);
		green = gen.nextInt(255);
		blue = gen.nextInt(255);
	}

	public void paint(Graphics page)
	{
		page.setColor(new Color(red, green, blue));
		page.fillRect(x, y, width, height);
	}
}