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
	private int ScoreP1 = 0;
	private int ScoreP2 = 0;
	private Timer timer;
	private int delay = 8;
	private int player1Y = 250;
	private int player2Y = 250;
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
		g.fillRect(15, player1Y, 8, 100);
		
		//enemy
		g.setColor(Color.RED);
		g.fillRect(675, player2Y, 8, 100);
		
		//ball
		g.setColor(Color.WHITE);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		if(ballPosX > 690 || ballPosX < 5) {
			Play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over!", 250, 200);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("P1 Score: " + ScoreP1 + " P2 Score: " + ScoreP2, 200, 250);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Enter to Restart", 200, 300);
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
			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(15, player1Y, 8, 100))) {
				ballXDir = -ballXDir;
				ScoreP1 += 5;
			}
			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(15, player2Y, 8, 100))) {
				ballXDir = -ballXDir;
				ScoreP2 += 5;
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
			if(player2Y >= 590) {
				player2Y = 590;
			} else {
				moveUpP2();
			}
		}			
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(player2Y <= 10) {
				player2Y = 10;
			} else {
				moveDownP2();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(player1Y >= 590) {
				player1Y = 590;
			} else {
				moveUpP1();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			if(player1Y <= 10) {
				player1Y = 10;
			} else {
				moveDownP1();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!Play) {
				Play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXDir = randomNum(-1,1);
				ballYDir = randomNum(-2,2);
				player1Y = 250;
				player2Y = 250;
				ScoreP1 = 0;
				ScoreP2 = 0;
				repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveUpP1() {
		Play = true;
		player1Y -= 30;
	}
	
	public void moveDownP1() {
		Play = true;
		player1Y += 30;
	}
	
	public void moveUpP2() {
		Play = true;
		player2Y -= 30;
	}
	
	public void moveDownP2() {
		Play = true;
		player2Y += 30;
	}
	
}
