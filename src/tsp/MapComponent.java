package tsp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class MapComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	private MapVisual map;

	public MapComponent(int fw, int fh, int max_x, int max_y, Tour fittest)
	{
		map = new MapVisual(fw,fh,max_x, max_y, fittest);
	}

	/**
	 * paint component on graphics object
	 * 
	 * @param g the graphics object
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		map.draw(g2);
	}
}
