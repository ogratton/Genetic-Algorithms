package RoomPicker;

/**
 * A collection of Houses
 * 
 * @author Oliver
 *
 */
public class Pop
{
	private House[] pop;
	private int size;

	/**
	 * Make a new array of houses
	 * 
	 * @param size how many houses desired
	 * @param filename source of preferences
	 * @param initialise true if houses and rooms should be generated or kept as a blank array
	 */
	public Pop(int size, String filename, boolean initialise)
	{
		pop = new House[size];
		this.size = size;

		// if necessary, make brand new houses with random rooms assigned
		if (initialise)
		{
			for (int i = 0; i < pop.length; i++)
			{
				pop[i] = new House(filename);
				pop[i].assignRooms();
			}
		}
	}

	public House getHouse(int index)
	{
		return pop[index];
	}

	public void saveHouse(int index, House house)
	{
		pop[index] = house;
	}

	/**
	 * @return The happiest house in the population
	 */
	public House getFittest()
	{
		House fittest = pop[0];
		// Loop through individuals to find fittest
		for (int i = 1; i < size; i++)
		{
			if (fittest.fitness() <= getHouse(i).fitness())
			{
				fittest = getHouse(i);
			}
		}
		return fittest;
	}
	
	public int size()
	{
		return size;
	}
}
