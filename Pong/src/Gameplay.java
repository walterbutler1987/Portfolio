import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private boolean Play = false;
	private int Score = 0;
	private Timer timer;
	private int delay = 8;
	private int playerY = 310;
	private int enemyY = 310;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXDir = 2 * randomNum(-1,1);
	private int ballYDir = 2 * randomNum(-2,2);
	

	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	private int randomNum(int min, int max) {
		if(min > max) {
			throw new IllegalArgumentException("Invalid Range");
		}
		
		double rand = Math.random();
		return (int)(rand * ((max-min)+1))+min;
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.BLACK);
		g.fillRect(1,1,692,592);
		
		//borders
		g.setColor(Color.YELLOW);
		g.fillRect(0,0,692,3);
		g.fillRect(0,570,692,3);		
		
		//player
		g.setColor(Color.GREEN);
		g.fillRect(15, playerY, 8, 100);
		
		//enemy
		g.setColor(Color.RED);
		g.fillRect(675, enemyY, 8, 100);
		
		//ball
		g.setColor(Color.WHITE);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		//out of bounds loss
		if(ballPosX < 5) {
			Play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over! Score: " + Score, 200, 300);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Enter to Restart", 200, 350);
		}
		
		//out of bounds win
		if(ballPosX > 690) {
			Play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Win! Score: " + Score, 200, 300);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Enter to Restart", 200, 350);
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {//ball movement and reactions
		timer.start();
		if(Play) {
			ballPosX += ballXDir;
			ballPosY += ballYDir;
			
			if(ballPosY < 0) {
				ballYDir = -ballYDir;
			}
			if(ballPosY > 550) {
				ballYDir = -ballYDir;
			}
			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(15, playerY, 8, 100))) {
				ballXDir = -ballXDir;
				Score += 5;
			}
			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(15, enemyY, 8, 100))) {
				ballXDir = -ballXDir;
			}
			
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(playerY >= 590) {
				playerY = 590;
			} else {
				moveUp();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(playerY >= 590) {
				playerY = 590;
			} else {
				moveUp();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(playerY <= 10) {
				playerY = 10;
			} else {
				moveDown();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			if(playerY <= 10) {
				playerY = 10;
			} else {
				moveDown();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!Play) {
				Play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXDir = randomNum(-1,1);
				ballYDir = randomNum(-2,2);
				playerY = 310;
				Score = 0;
				repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveUp() {
		Play = true;
		playerY -= 30;
	}
	
	public void moveDown() {
		Play = true;
		playerY += 30;
	}
	
}
