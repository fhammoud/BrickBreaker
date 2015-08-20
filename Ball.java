import java.awt.Color;
import java.awt.Graphics;
//import java.util.Random;
import java.awt.Point;

public class Ball extends GameObject{
	
	public Ball(int x, int y, int d)
	{
		super(x, y, d, d);
	}
	
	public void bounce(GameObject other, Point contactPoint)
	{
		Point[] otherPoints = new Point[4];
		otherPoints[0] = new Point(other.getPos().x, other.getPos().y);
		otherPoints[1] = new Point(other.getPos().x + other.width, other.getPos().y);
		otherPoints[2] = new Point(other.getPos().x, other.getPos().y + other.height);
		otherPoints[3] = new Point(other.getPos().x + width, other.getPos().y + other.height);
		
		Point corner = null;
		
		for(int i = 0; i < 4; i++)
		{
			if (contactPoint.equals(otherPoints[i]))
			{
				corner = otherPoints[i];
				break;
			}
		}
		
		if (corner == null)	//ball hit a side
		{
			
		}
		else	//ball hit a corner
		{
			System.out.println("Hit a corner");
		}
	}
	
	public Point overlapPoint(GameObject other)
	{
		Point overlapPoint = null;
		
		Point otherTopLeft = other.getPos();
		int otherLeft = otherTopLeft.x;
		int otherTop = otherTopLeft.y;
		int otherRight = otherLeft + other.width;
		int otherBottom = otherTop + other.height;
		
			
		//collision with another ball
		if (other instanceof Ball)
		{
			
		}
		else //collision with rectangle
		{
			Point thisCenter = this.getCenter();
			double radius = this.getWidth() / 2;
			// Find the closest point to the circle within the rectangle
			int closestX = (int) clamp(thisCenter.x, otherLeft, otherRight);
			int closestY = (int) clamp(thisCenter.y, otherTop, otherBottom);

			// Distances in x and y between the circle's center and this closest point
			double distanceX = thisCenter.x - closestX;
			double distanceY = thisCenter.y - closestY;

			/* Compare distance squared to closestPoint and radius squared
			Using squares where possible is usually faster than Math.sqrt*/
			double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
			
			if (distanceSquared <= (radius * radius)){
				overlapPoint = new Point(closestX, closestY);
			}
		}
		
		return overlapPoint;
	}
	
	private double clamp(double value, double min, double max){
		return Math.max(min, Math.min(max, value));
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
