import java.awt.Graphics;
import java.awt.Point;


public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public GameObject(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int w){
		width = w;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public Point getPos(){
		Point point = new Point(x, y);
		return point;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void paint(Graphics page);
}
