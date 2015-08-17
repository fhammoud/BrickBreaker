import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
//import java.util.Random;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
	
	//game variables
	private int panelWidth;
	private int panelHeight;
	private JLabel scoreLabel;
	private boolean pause;
	private boolean pressed;
	private int keyCode;
	private Timer timer;
	private final int DELAY = 16;
	private int score;
	
	//paddle variables
	private Paddle paddle;
	private int paddleWidth;
	private int paddleHeight;
	private int paddleSpeed;
	private int paddleX;
	private int paddleY;
	
	//ball variables
	private Ball ball;
	private int ballRadius;
	private int ballDiameter;
	private int ballSpeed;
	private int x, y;
	//private int diffX, diffY;
	private boolean right;
	private boolean up;
	private boolean stickToPaddle;
	
	//brick variables
	private ArrayList<Brick> bricks;
	private int brickHeight;
	private int brickWidth;
	
	//GamePanel constructor
	public GamePanel(JLabel sl)
	{
		this.setBackground(Color.WHITE);
		scoreLabel = sl;
		
		//add listeners
		addKeyListener(this);
		addMouseListener(this);
		timer = new Timer(DELAY, this);
		setFocusable(true);
	}
	
	public void startGame(){
		
		//game
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();
		System.out.println("Width: " + panelWidth);
		System.out.println("Height: " + panelHeight);
		pause = false;
		pressed = false;
		
		//paddle
		paddleX = 0;
		paddleY = panelHeight - 80;
		paddleWidth = 100;
		paddleHeight = 20;
		paddle = new Paddle(paddleX, paddleY, paddleWidth, paddleHeight);
		paddleSpeed = 10;
		
		//bricks
		x = 0;
		y = 40;
		brickWidth = 40;
		brickHeight = 20;
		bricks = new ArrayList<Brick>();
		int bricksCount = panelWidth / brickWidth;
		int rows = 3;
		
		//create bricks
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < bricksCount; j++)
			{
				bricks.add(new Brick(x, y, brickWidth, brickHeight));
				x += (brickWidth + 1);
			}
			x = 0;
			y += (brickHeight + 1);
		}
		
		//ball
		x = 0;
		y = panelHeight - 100;
		ballDiameter = 20;
		ball = new Ball(x, y, ballDiameter);
		ballSpeed = 10;
		ballRadius = ballDiameter/2;
		score = 0;
		stickToPaddle = true;
		right = true;
		up = true;
//		diffX = 0;
//		diffY = 0;
		
		repaint();
		timer.start();
	}
	
	//paint game objects
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		//paint paddle and ball
		paddle.paint(page);
		ball.paint(page);
		
		//paint all remaining bricks
		for (int i = 0; i < bricks.size(); i++)
			bricks.get(i).paint(page);
		
	}
	
	
	/******************************************************************************
	 * Key events
	 ******************************************************************************/
	@Override
	public void keyPressed(KeyEvent e) {
		pressed = true;
		keyCode = e.getKeyCode();
		
		//pause
		if (keyCode == KeyEvent.VK_P)
		{
			if (pause){
				pause = false;
				timer.start();
			}
			else{
				pause = true;
				timer.stop();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		pressed = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	/******************************************************************************
	 * Mouse events
	 ******************************************************************************/
	@Override
	public void mouseClicked(MouseEvent event) {
		
//			Point mouseClick;
//			
//			mouseClick = event.getPoint();
//		    int clickX = (int)mouseClick.getX();
//		    int clickY = (int)mouseClick.getY();
//		    
//		    int xP = clickX - x;
//		    int yP = y - clickY;
//		    diffX = (int) (ballSpeed * (xP/Math.sqrt(Math.pow(xP, 2) + Math.pow(yP, 2))));
//		    diffY = (int) (ballSpeed * (yP/Math.sqrt(Math.pow(xP, 2) + Math.pow(yP, 2))));
//		    
//		    System.out.println(diffX + ", " + diffY);
//		    System.out.println(clickX + ", " + clickY);
	    stickToPaddle = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	
	/******************************************************************************
	 * Timer events
	 ******************************************************************************/
	@Override
	public void actionPerformed (ActionEvent event) {
		if (pressed){
			switch(keyCode)
			{
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					if (paddleX < (panelWidth - paddleWidth) && pressed)
					{
						paddleX += paddleSpeed;
						paddle.setPos(paddleX, paddleY);
					}
					break;
				
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					if (paddleX > 0 && pressed)
					{
						paddleX -= paddleSpeed;
						paddle.setPos(paddleX, paddleY);
					}
					break;
				default:
			}
		}
			
			
//		Random gen = new Random();
//		int r = gen.nextInt(255);
//		int g = gen.nextInt(255);
//		int b = gen.nextInt(255);
//		
//		gamePanel.setBackground((new Color(r, g, b)));
		
		//get ball poistion
		int ballX = (int) ball.getPos().getX();
		int ballY = (int) ball.getPos().getY();
		int ballTouchPoint = ballX + ballRadius;
		int ballSideTouchPoint = ballY + ballRadius;
		//double ballIncrement = 1.25;
		
		//ball and paddle collision logic
		if ((ballY + ballDiameter) == paddleY)
		{
			if ((ballTouchPoint >= paddleX) && (ballTouchPoint <= (paddleX + paddleWidth)))
			{
				up = true;
				//ballSpeed ++;
			}
		}
		
		//brick collision logic
		//bottom of brick
		for (int i = 0; i < bricks.size(); i++)
		{
			int brickX = (int) bricks.get(i).getPos().getX();
			int brickY = (int) bricks.get(i).getPos().getY();
			if (ballY <= brickY + brickHeight){
				if (ballTouchPoint >= brickX && ballTouchPoint <= brickX + brickWidth){
					up = false;
					bricks.remove(i);
					score++;
					scoreLabel.setText("Score: " + score);
				}
			}
		}
		
		//border collision logic
		if (x >= (panelWidth - ballDiameter)){
			right = false;
		}
		
		else if (x <= 0){
			right = true;
		}
		
		//get new ball position
		if (!stickToPaddle)
		{
			if (right){
				//x += diffX;
				x += ballSpeed;
			}
			else if (!right){
				//x -= diffX;
				x -= ballSpeed;
			}
			
			if (up){
				//y -= diffY;
				y -= ballSpeed;
			}
			else if (!up){
				//y += diffY;
				y += ballSpeed;
			}
		}
		else
			x = paddleX + paddleWidth / 2 - ballRadius;
		
		//update ball position
		ball.setPos(x, y);
		
		if (y <= 0){
			up = false;
		}
		else if (y > (panelHeight - ballDiameter)){
			up = true;
			timer.stop();
			JOptionPane.showMessageDialog(null, "You lose!");
		}
		
		//corner of paddle
		if((ballY + ballDiameter) == paddleY)
		{
			if((ballX + ballDiameter) == paddleX)
			{
				up = true;
				right = false;
			}
			
			if((ballX) == (paddleX + paddleWidth))
			{
				up = true;
				right = true;
			}
		}
		
		//ball and sides of paddle
		if ((ballSideTouchPoint) > paddleY && (ballSideTouchPoint) < (paddleY + paddleHeight))
		{
			if ((ballX + ballDiameter >= paddleX) && (ballX + ballDiameter <= paddleX + paddleWidth))
			{
				right = false;
			}
			
			else if ((ballX <= paddleX + paddleWidth) && (ballX + ballDiameter >= paddleX + paddleWidth))
			{
				right = true;
			}
		}
		
		repaint();
		
		if (bricks.size() == 0){
			timer.stop();
			JOptionPane.showMessageDialog(null, "You WIN!");
		}
	}
}
