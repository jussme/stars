package base;

import java.awt.Graphics2D;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.random;
public class Star {
	static Galaxy GALAXY;
	private final static short STAR_SIZE = 10;
	private final static double OFFSET_Z_CALC  = 0.4;
	private final static double OFFSET_COORD_INCREASE = 0.1;
	private short sx;
	private short sy;
	private double x;
	private double y;
	private double z;
	
	static void setGalaxy(Galaxy galaxy){
		GALAXY = galaxy;
	}
	
	Star(){
		sx = (short) (random() * GALAXY.WIDTH - GALAXY.WIDTH/2);
		sy = (short) (random() * GALAXY.HEIGHT - GALAXY.HEIGHT/2);
		x = sx;
		y = sy;
		z = sqrt(pow(x, 2) + pow(y, 2))/GALAXY.NAUGHT_TO_CORNER + OFFSET_Z_CALC;
	}
	
	public void paintStar_Trail(Graphics2D g) {
		short radius = (short) (STAR_SIZE * z);
		g.fillOval((int)(x-radius/2), (int)(y-radius/2), radius, radius);
		g.drawLine((int)x, (int)y, sx, sy);
	}
	
	void update() {
		x *= 1 + z/8; //+ OFFSET_COORD_INCREASE;
		y *= 1 + z/8; //+ OFFSET_COORD_INCREASE;
		z = (sqrt(pow(x, 2) + pow(y, 2))/GALAXY.NAUGHT_TO_CORNER) + OFFSET_Z_CALC;
		if(z >= 1)
			recycle();
	}
	
	void recycle() {
		sx = (short) (random() * GALAXY.WIDTH - GALAXY.WIDTH/2);
		sy = (short) (random() * GALAXY.HEIGHT - GALAXY.HEIGHT/2);
		x = sx;
		y = sy;
	}
}
