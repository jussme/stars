package base;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Galaxy extends JFrame{
	static final short WIDTH = 400;
	static final short HEIGHT = 400;
	static final short NAUGHT_TO_CORNER = 
			(short) sqrt(pow((WIDTH/2), 2) + pow((HEIGHT/2), 2));
	
	public static void main(String[] args) {
		new Galaxy();
	}
	
	Galaxy(){
		setSize(WIDTH, HEIGHT);
		setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		class GalaxyView extends JPanel{
			private final static short STAR_COUNT = 10;
			final Star[] STARS = new Star[STAR_COUNT];
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.WHITE);
				g2.translate(Galaxy.WIDTH/2, Galaxy.HEIGHT/2);
				g2.setStroke(new BasicStroke(1));
				for(int it = 0; it < STAR_COUNT; ++it) {
					STARS[it].paintStar_Trail(g2);
				}
			}
			
			GalaxyView(){
				for ( int it = 0; it < STAR_COUNT; ++it)
					STARS[it] = new Star();
				
				setBackground(Color.BLACK);
				
				new Thread(() -> {
					while (true) {
						for ( int it = 0; it < STAR_COUNT; ++it) {
							STARS[it].update();
							
							try {
								Thread.sleep(8);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}this.repaint();
					}
				}).start();
			}
		}
		
		add(new GalaxyView());
		
		setVisible(true);
	}
}
