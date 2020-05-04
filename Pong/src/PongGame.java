import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends Applet implements KeyListener, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440660688873053245L;
	final int WIDTH = 700, HEIGHT = 500;
	boolean gameStarted;
	Thread thread;
	HumanPaddle p1;
	AIPaddle p2;
	Ball b1;
	Graphics gfx;
	Image img;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);
		b1 = new Ball();
		p2 = new AIPaddle(2,b1);		
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		gfx.setColor(Color.BLACK);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.white);
			gfx.setFont(new Font("serif", Font.BOLD, 30));
			gfx.drawString("Game Over!", WIDTH/2, HEIGHT/2);
		} else {
			p1.draw(gfx);
			p2.draw(gfx);
			b1.draw(gfx);			
		}
		
		if(!gameStarted) {
			gfx.setColor(Color.WHITE);
			gfx.drawString("Pong", 340, 100);
			gfx.drawString("Press Enter to start", 310, 130);
		}
		
		g.drawImage(img, 0,0,this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void run() {
		for(;;) {
			if(gameStarted) {
				p1.move();
				b1.move();
				p2.move();				
				b1.checkPaddleCollision(p1, p2);
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
		
	}

}
