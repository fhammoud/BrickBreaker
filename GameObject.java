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
	
	public void setVelocity(int x, int y){
		diffX = x;
		diffY = y;
	}
	
	public Point getVelocity(){
		Point v = new Point(diffX, diffY);
		return v;
	}
	
	public Point getCenter(){
		Point center = new Point(x + width / 2, y + height / 2);
		return center;
	}
	
	public boolean overlap(GameObject other){
		boolean isOverlap = false;
		
		Point otherTopLeft = other.getPos();
		int otherLeftX = otherTopLeft.x;
		int otherTopY = otherTopLeft.y;
		int otherRightX = otherLeftX + other.width;
		int otherBottomY = otherTopY + other.height;

		//top left corner of this
		int thisLeftX = this.x;
		int thisTopY = this.y;
		int thisRightX = this.x + this.width;
		int thisBottomY = this.y + this.height;
		
		if (thisLeftX >= otherLeftX && thisLeftX <= otherRightX &&
			thisTopY >= otherTopY && thisTopY <= otherBottomY ||
			
			thisRightX >= otherLeftX && thisRightX <= otherRightX &&
			thisTopY >= otherTopY&& thisTopY <= otherBottomY ||
			
			thisRightX >= otherLeftX && thisRightX <= otherRightX &&
			thisBottomY >= otherTopY && thisBottomY <= otherBottomY ||
			
			thisLeftX >= otherLeftX && thisLeftX <= otherRightX &&
			thisBottomY >= otherTopY && thisBottomY <= otherBottomY)
			{
				isOverlap = true;
			}
		
		return isOverlap;
	}
	
	public abstract void paint(Graphics page);
}
