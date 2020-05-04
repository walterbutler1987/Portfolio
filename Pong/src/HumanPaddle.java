import java.awt.Graphics;
import java.awt.Color;

public class HumanPaddle extends Paddle{
	double y, yVel;
	final double GRAVITY = .98;
	int player,x;
	boolean upAccel, downAccel;
	
	public HumanPaddle(int player) {
		upAccel = false; downAccel = false;
		y = 250; yVel = 0;
		if(player == 1)
			x = 20;
		else
			x = 660;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, (int) y, 20, 80);
	}
	
	public void move() {
		if(upAccel) {
			yVel -= 2;
		} else if(downAccel) {
			yVel += 2;
		} else if(!upAccel && !downAccel) {
			yVel *= GRAVITY;
		}
				
		if(yVel >= 5)
			yVel = 5;
		else if(yVel <= 5)
			yVel = -5;
		
		if(y < 0)
			y = 0;
		if(y > 420)
			y = 420;
		
		y += yVel;
	}
	
	public void setUpAccel(boolean input) {
		upAccel = input;
	}
	
	public void setDownAccel(boolean input) {
		downAccel = input;
	}
	
	public int getY() {
		return (int) y;		
	}
}
