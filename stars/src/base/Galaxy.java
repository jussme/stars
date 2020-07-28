package base;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Galaxy extends JFrame{
	private static final long serialVersionUID = 1L;
	static final short REFRESH_DELAY = 2;
	final short WIDTH;
	final short HEIGHT;
	final short NAUGHT_TO_CORNER;
	
	public static void main(String[] args) {
		new Galaxy();
	}
	
	Galaxy(){
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		
		WIDTH = (short) screenSize.width;
		HEIGHT = (short) screenSize.height;
		NAUGHT_TO_CORNER = (short) (sqrt(pow((WIDTH/2), 2) + pow((HEIGHT/2), 2)));

		setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		class GalaxyWindow extends JPanel{
			private static final long serialVersionUID = 1L;
			private final static short STAR_COUNT = 1;
			final Star[] STARS = new Star[STAR_COUNT];
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.WHITE);
				g2.translate(Galaxy.this.WIDTH/2, Galaxy.this.HEIGHT/2);
				g2.setStroke(new BasicStroke(0.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				for(int it = 0; it < STAR_COUNT; ++it) {
					STARS[it].paintStar_Trail(g2);
				}
				g2.drawOval(0, 0, 3, 3);
			}
			
			GalaxyWindow(){
				addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						for(Star star : STARS)
							star.recycle();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				for ( int it = 0; it < STAR_COUNT; ++it)
					STARS[it] = new Star(Galaxy.this);
				
				setBackground(Color.BLACK);
				
				new Thread(() -> {
					while (true) {
						for ( int it = 0; it < STAR_COUNT; ++it)
						{
							STARS[it].update();
						}
						
						try {
							Thread.sleep(REFRESH_DELAY);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						this.repaint();
					}
				}).start();
			}
		}
		
		add(new GalaxyWindow());
		
		setVisible(true);
	}
}
