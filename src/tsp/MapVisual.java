package tsp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class MapVisual
{
	private final int STROKE = 5;
	private final double CITY_RADIUS = 7;
	
	private int fw;
	private int fh;
	private int max_x;
	private int max_y;
	private double scale;
	private Tour fittest;
	
	public MapVisual(int fw, int fh, int max_x, int max_y, Tour fittest)
	{
		this.fw = fw;
		this.fh = fh;
		this.max_x = max_x;
		this.max_y = max_y;
		this.scale = 0.05;
		this.fittest = fittest;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		for (int i = (int)(fw*scale); i <= fw; i += (fw*scale))
		{
			g.draw(new Line2D.Double(i, 0, i, fh));
		}
		for (int i = (int)(fh*scale); i <= fh; i += fh*scale)
		{
			g.draw(new Line2D.Double(0, i, fw, i));
		}
		
		String tour = fittest.toString();
		// best found so far (863)
//		String tour = "200,40;180,60;180,100;200,160;180,200;140,180;140,140;100,120;100,160;80,180;60,200;20,160;40,120;60,80;20,40;20,20;60,20;100,40;120,80;160,20;";
		// this route costs 1000;
//		String tour = "20,20;20,40;60,80;100,120;140,140;140,180;180,200;200,160;180,100;180,60;200,40;160,20;120,80;40,120;20,160;60,200;80,180;100,160;100,40;60,20;";
		String[] points = tour.split(";");
		Point[] pts = new Point[points.length];
		for (int i = 0; i < points.length; i++)
		{
			String[] coords = points[i].split(",");
			pts[i] = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
		}
		
		double prevX = 0;
		double prevY = 0;
		double firstX = 0;
		double firstY = 0;
		for (int i = 0; i < pts.length; i++)
		{
			Point p = pts[i];
			double radius = CITY_RADIUS;
			double x = (p.getX()/max_x)*fw - radius;
			double y = (p.getY()/max_y)*fh - radius;
			Ellipse2D.Double city = new Ellipse2D.Double(x,y,radius*2,radius*2);
			g.draw(city);
			g.setColor(Color.RED);
			g.fill(city);
			
			g.setColor(Color.BLUE);
			
			Stroke origStroke = g.getStroke();

			if (i != 0)
			{
				Line2D.Double path = new Line2D.Double(prevX, prevY, x+radius, y+radius);
				g.setStroke(new BasicStroke(STROKE));
				g.draw(path);
				g.setStroke(origStroke);
			}
			else
			{
				firstX = x+radius;
				firstY = y+radius;
			}
			if (i == pts.length-1)
			{
				Line2D.Double path = new Line2D.Double(firstX, firstY, x+radius, y+radius);
				g.setStroke(new BasicStroke(STROKE));
				g.draw(path);
				g.setStroke(origStroke);
			}
			
			prevX = x+radius;
			prevY = y+radius;
			g.setColor(Color.BLACK);
		}
		
	}

}
