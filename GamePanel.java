import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
	private int ballX, ballY;
	private int diffX, diffY;
	private boolean right;
	private boolean up;
	private boolean stickToPaddle;
	
	//brick variables
	private ArrayList<Brick> bricks;
	private int brickHeight;
	private int brickWidth;
	private int brickX, brickY;
	
	//GamePanel constructor
	public GamePanel(JLabel sl)
	{
		this.setBackground(Color.WHITE);
		scoreLabel = sl;
		score = 0;
		
		//add listeners
		addKeyListener(this);
		addMouseListener(this);
		timer = new Timer(DELAY, this);
		setFocusable(true);
	}
	
	//start game
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
		paddleY = panelHeight - 50;
		paddleWidth = 100;
		paddleHeight = 20;
		paddle = new Paddle(paddleX, paddleY, paddleWidth, paddleHeight);
		paddleSpeed = 10;
		
		//bricks
		brickX = 0;
		brickY = 40;
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
				bricks.add(new Brick(brickX, brickY, brickWidth, brickHeight));
				brickX += (brickWidth + 1);
			}
			brickX = 0;
			brickY += (brickHeight + 1);
		}
		
		//ball
		ballSpeed = 10;
		ballDiameter = 20;
		ballRadius = ballDiameter / 2;
		ballX = paddleX + paddleWidth / 2 - ballRadius;
		ballY = paddleY - ballDiameter;
		ball = new Ball(ballX, ballY, ballDiameter);
		stickToPaddle = true;
		right = true;
		up = true;
		diffX = 0;
		diffY = 0;
		
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
		
		Point mouseClick = event.getPoint();
	    int clickX = (int)mouseClick.getX();
	    int clickY = (int)mouseClick.getY();
	    
//	    System.out.println(clickX + ", " + ballX);
	    
	    if (clickX < ballX){
	    	right = false;
	    }
	    
	    int xP = clickX - ballX;
	    int yP = ballY - clickY;
	    diffX = Math.abs((int) (ballSpeed * (xP/Math.sqrt(Math.pow(xP, 2) + Math.pow(yP, 2)))));
	    diffY = Math.abs((int) (ballSpeed * (yP/Math.sqrt(Math.pow(xP, 2) + Math.pow(yP, 2)))));
	    
//	    System.out.println(diffX + ", " + diffY);
//	    System.out.println(clickX + ", " + clickY);
	    
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
		
		//move the paddle
		if (pressed){
			switch(keyCode)
			{
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					if (paddleX < (panelWidth - paddleWidth))
					{
						paddleX += paddleSpeed;
						paddle.setPos(paddleX, paddleY);
					}
					break;
				
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					if (paddleX > 0)
					{
						paddleX -= paddleSpeed;
						paddle.setPos(paddleX, paddleY);
					}
					break;
				default:
			}
		}
		
		//border collision logic
		if (ballX >= (panelWidth - ballDiameter)){
			right = false;
		}
		else if (ballX <= 0){
			right = true;
		}
		
		if (ballY <= 0){
			up = false;
		}
		else if (ballY > (panelHeight - ballDiameter)){
			up = true;
			timer.stop();
			JOptionPane.showMessageDialog(null, "You lose!");
		}
		
		//get new ball position
		if (!stickToPaddle)
		{
			if (right){
				ballX += diffX;
			}
			else if (!right){
				ballX -= diffX;
			}
			
			if (up){
				ballY -= diffY;
			}
			else if (!up){
				ballY += diffY;
			}
			
//			if (right){
//				ballX += ballSpeed;
//			}
//			else if (!right){
//				ballX -= ballSpeed;
//			}
//			
//			if (up){
//				ballY -= ballSpeed;
//			}
//			else if (!up){
//				ballY += ballSpeed;
//			}
		}
		else
			ballX = paddleX + paddleWidth / 2 - ballRadius;
		
		//update ball position
		ball.setPos(ballX, ballY);
		
		//ball and paddle collision logic
		int ballTouchPoint = ballX + ballRadius;
		int ballSideTouchPoint = ballY + ballRadius;
		//double ballIncrement = 1.25;
		
		if ((ballY + ballDiameter) == paddleY)
		{
			if ((ballTouchPoint >= paddleX) && (ballTouchPoint <= (paddleX + paddleWidth)))
			{
				up = true;
				//ballSpeed ++;
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
		
		//brick collision logic
		//bottom of brick
		for (int i = 0; i < bricks.size(); i++)
		{
			brickX = (int) bricks.get(i).getPos().getX();
			brickY = (int) bricks.get(i).getPos().getY();
			if (ballY <= brickY + brickHeight){
				if (ballTouchPoint >= brickX && ballTouchPoint <= brickX + brickWidth){
					up = false;
					bricks.remove(i);
					score++;
					scoreLabel.setText("Score: " + score);
				}
			}
		}
		
		//Check win condition
		if (bricks.size() == 0){
			timer.stop();
			JOptionPane.showMessageDialog(null, "You WIN!");
		}
		
		//paint new information
		repaint();
	}
}
