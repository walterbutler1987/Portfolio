import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private boolean Play = false;
	private int Score = 0;
	private Timer timer;
	private int delay = 8;
	private int playerX = 310;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXDir = -1;
	private int ballYDir = -2;

	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(1,1,692,592);
		
		g.setColor(Color.YELLOW);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + Score, 590, 30);
		
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.WHITE);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		if(ballPosY > 570) {
			Play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over! Score: " + Score, 200, 300);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Enter to Restart", 200, 350);
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(Play) {
			ballPosX += ballXDir;
			ballPosY += ballYDir;
			if(ballPosX < 0) {
				ballXDir = -ballXDir;
			}
			if(ballPosY < 0) {
				ballYDir = -ballYDir;
			}
			if(ballPosX > 670) {
				ballXDir = -ballXDir;
			}
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYDir = -ballYDir;
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
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveUp();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_U) {
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveUp();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(playerX <= 10) {
				playerX = 10;
			} else {
				moveDown();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			if(playerX <= 10) {
				playerX = 10;
			} else {
				moveDown();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!Play) {
				Play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXDir = -1;
				ballYDir = -2;
				playerX = 310;
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
		playerY -= 20;
	}
	
	public void moveDown() {
		Play = true;
		playerY += 20;
	}
	
}
