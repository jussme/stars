package base;

import java.awt.Graphics2D;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.random;
public class Star {
	private final static char[] STAR = {'*'};
	private final static short SPEED = 10;
	private final static short STAR_SIZE = 5;
	private short sx;
	private short sy;
	private short x;
	private short y;
	private double z;
	
	Star(){
		sx = (short) (random() * Galaxy.WIDTH - Galaxy.WIDTH/2);
		sy = (short) (random() * Galaxy.HEIGHT - Galaxy.HEIGHT/2);
		x = sx;
		y = sy;
		z = sqrt(pow(x, 2) + pow(y, 2))/Galaxy.NAUGHT_TO_CORNER;
		}
	
	public void paintStar_Trail(Graphics2D g) {
		short radius = (short) (STAR_SIZE * z);
		g.fillOval(x-radius/2, y-radius/2, radius, radius);
		g.drawLine(x, y, sx, sy);
	}
	
	void update() {
		x *= 1 + z/4;
		y *= 1 + z/4;
		z = sqrt(pow(x, 2) + pow(y, 2))/Galaxy.NAUGHT_TO_CORNER;
		if(z >= 1)
			recycle();
	}
	
	void recycle() {
		sx = (short) (random() * Galaxy.WIDTH - Galaxy.WIDTH/2);
		sy = (short) (random() * Galaxy.HEIGHT - Galaxy.HEIGHT/2);
		x = sx;
		y = sy;
	}
}
