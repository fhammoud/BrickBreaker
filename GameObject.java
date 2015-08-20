import java.awt.Graphics;
import java.awt.Point;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected int diffX;
	protected int diffY;

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
	
	public Point getCenter(){
		Point center = new Point(x + width / 2, y + height / 2);
		return center;
	}
	
	public boolean overlap(GameObject other){
		boolean isOverlap = false;
		
		Point otherTopLeft = other.getPos();
		Point otherTopRight = new Point((int)otherTopLeft.x + other.getWidth(), (int)otherTopLeft.y);
		Point otherBottomLeft = new Point((int)otherTopLeft.x, (int)otherTopLeft.y + other.getHeight());
		Point otherBottomRight = new Point((int)otherTopLeft.x + other.getWidth(), (int)otherTopLeft.y + other.getHeight());
		
		//top left corner of this
		int thisTopLeftX = this.x;
		int thisTopLeftY = this.y;
		int thisTopRightX = this.x + this.width;
		int thisTopRightY = this.y;
		int thisBottomLeftX = this.x;
		int thisBottomLeftY = this.y + this.height;
		int thisBottomRightX = this.x + this.width;
		int thisBottomRightY = this.y + this.height;
		
		if (thisTopLeftX >= otherTopLeft.x && thisTopLeftX <= otherTopRight.x &&
			thisTopLeftY >= otherTopRight.y && thisTopLeftY <= otherBottomRight.y ||
			
			thisTopRightX >= otherTopLeft.x && thisTopRightX <= otherTopRight.x &&
			thisTopRightY >= otherTopRight.y && thisTopRightY <= otherBottomRight.y ||
			
			thisBottomRightX >= otherTopLeft.x && thisBottomRightX <= otherTopRight.x &&
			thisBottomRightY >= otherTopRight.y && thisBottomRightY <= otherBottomRight.y ||
			
			thisBottomLeftX >= otherTopLeft.x && thisBottomLeftX <= otherTopRight.x &&
			thisBottomLeftY >= otherTopRight.y && thisBottomLeftY <= otherBottomRight.y)
			{
				isOverlap = true;
			}
		
		
		if (isOverlap && this instanceof Ball)
		{
			double distance;
			
			//collision with another ball
			if (other instanceof Ball)
			{
				
			}
			else //collision with rectangle
			{
				distance = Math.min(Math.sqrt(Math.pow((otherBottomRight.x - this.getCenter().x), 2) + Math.pow((otherBottomRight.y - this.getCenter().y), 2)),Math.min(Math.sqrt(Math.pow((otherBottomLeft.x - this.getCenter().x), 2) + Math.pow((otherBottomLeft.y - this.getCenter().y), 2)), Math.min(Math.sqrt(Math.pow((otherTopLeft.x - this.getCenter().x), 2) + Math.pow((otherTopLeft.y - this.getCenter().y), 2)), Math.sqrt(Math.pow((otherTopRight.x - this.getCenter().x), 2) + Math.pow((otherTopRight.y - this.getCenter().y), 2)))));
				System.out.println(distance);
				if (distance > this.width / 2)
					isOverlap = false;
			}
		}
		
		return isOverlap;
	}
	
	public abstract void paint(Graphics page);
}
