package tsp;

/**
 * Glorified Point class
 * @author Oliver
 *
 */
public class City
{
	int x;
	int y;

	/**
	 * Constructs a randomly placed city
	 */
	public City()
	{
		this.x = (int) (Math.random() * 200);
		this.y = (int) (Math.random() * 200);
	}

	/**
	 * Constructs a city at chosen x, y location
	 * @param x
	 * @param y
	 */
	public City(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets city's x coordinate
	 * @return
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Gets city's y coordinate
	 * @return
	 */
	public int getY()
	{
		return this.y;
	}

	/**
	 * Gets the distance to given city
	 * @param city
	 * @return
	 */
	public double distanceTo(City city)
	{
		int xDistance = Math.abs(this.getX() - city.getX());
		int yDistance = Math.abs(this.getY() - city.getY());
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

		return distance;
	}

	@Override
	public String toString()
	{
		return this.getX() + "," + this.getY();
	}
}