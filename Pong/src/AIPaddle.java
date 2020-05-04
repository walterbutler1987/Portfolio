import java.awt.Graphics;
import java.awt.Color;

public class AIPaddle extends Paddle{
	double y, yVel;
	final double GRAVITY = .98;
	int player,x;
	boolean upAccel, downAccel;
	Ball b1;
	
	public AIPaddle(int player, Ball b) {
		upAccel = false; downAccel = false;
		b1 = b;
		y = 250; yVel = 0;
		if(player == 1)
			x = 20;
		else
			x = 660;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, (int) y, 20, 80);
	}
	
	public void move() {
		y = (b1.getY() - 40);
		
		if( y < 0)
			y = 0;
		if(y > 420)
			y = 420;
	}
	
	
	
	public int getY() {
		return (int) y;		
	}
}
