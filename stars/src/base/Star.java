package base;

import java.awt.Graphics2D;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.random;

/*public class Star {
	private final Galaxy parentGalaxy;
	private final static double OFFSET_MIN_VALUE  = 0.5;
	private final static int MULTIPLIER = 2;
	private final double halfDiagonalMultiplied;
	private double sx;
	private double sy;
	private double x;
	private double y;
	private double z;
	
	Star(Galaxy galaxy){
		parentGalaxy = galaxy;
		halfDiagonalMultiplied = parentGalaxy.NAUGHT_TO_CORNER * MULTIPLIER;
		recycle();
		z = sqrt(pow(x, 2) + pow(y, 2))/halfDiagonalMultiplied + OFFSET_MIN_VALUE;
	}
	
	public void paintStar_Trail(Graphics2D g) {
		//short radius = (short) (12 * z);
		//g.fillOval((int)(x-radius/2), (int)(y-radius/2), radius, radius);
		g.drawLine((int)x, (int)y, (int)sx, (int)sy);
	}
	
	void update() {
		x *= 1 + z/16;
		y *= 1 + z/16;
		z = (sqrt(pow(x, 2) + pow(y, 2))/halfDiagonalMultiplied) + OFFSET_MIN_VALUE;
		if(z >= 1)
			recycle();
	}
	
	void recycle() {
		double width = parentGalaxy.WIDTH , height = parentGalaxy.HEIGHT ;
		sx = (short) (random() * width - width/2);
		sy = (short) (random() * width - height/2);
		x = sx;
		y = sy;
	}
}*/
public class Star {
	private final Galaxy parentGalaxy;
	private double sx;
	private double sy;
	private double z;
	
	Star(Galaxy galaxy){
		parentGalaxy = galaxy;
		
		recycle();
	}
	
	public void paintStar_Trail(Graphics2D g) {
		float galaxyWidthByDepth = (float) (parentGalaxy.HEIGHT/z);
		int x = (int) (galaxyWidthByDepth * sx);
		int y = (int) (galaxyWidthByDepth * sy);
		short radius = (short) (3 * galaxyWidthByDepth);
		g.fillOval((int)(x-radius/2), (int)(y-radius/2), radius, radius);
		//g.drawLine( x, y, (int) sx, (int) sy);
	}
	
	void update() {
		if(--z < 0)
			recycle();
	}
	
	void recycle() {
		sx = (short) (random() * parentGalaxy.WIDTH - parentGalaxy.WIDTH/2);
		sy = (short) (random() * parentGalaxy.HEIGHT - parentGalaxy.HEIGHT/2);
		z = random() * (parentGalaxy.HEIGHT - 1) + 1;
	}
}
