/*
* TourManager.java
* Holds the cities of a tour
*/

package tsp;

import java.util.ArrayList;

public class TourManager
{

	// Holds our cities
	private static ArrayList<City> destinationCities = new ArrayList<City>();

	/**
	 * Adds a destination city
	 * @param city
	 */
	public static void addCity(City city)
	{
		destinationCities.add(city);
	}

	/**
	 * Get a city
	 * @param index
	 * @return
	 */
	public static City getCity(int index)
	{
		return destinationCities.get(index);
	}

	/**
	 * Get the number of destination cities
	 * @return
	 */
	public static int numberOfCities()
	{
		return destinationCities.size();
	}
}