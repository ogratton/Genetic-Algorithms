package RoomPicker;

public class GeneticAlgorithm
{
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;
	private static final String filename = "prefs.csv";

	/**
	 * Evolve a population by one generation
	 * 
	 * @param pop population to be evolved
	 * @return
	 */
	public static Pop evolvePopulation(Pop pop)
	{
		// new blank pop
		Pop newPopulation = new Pop(pop.size(), filename, false);

		// keep best individual if elitism enabled
		int elitismOffset = 0;
		if (elitism)
		{
			newPopulation.saveHouse(0, pop.getFittest());
			elitismOffset = 1;
		}

		for (int i = elitismOffset; i < newPopulation.size(); i++)
		{
			// Select parent
			House parent = tournamentSelection(pop);

			// Mutate parent
			House child = mutate(parent);
			// Add child to new population
			newPopulation.saveHouse(i, child);
		}

		// Regenerate any shit Houses
		for (int i = elitismOffset; i < newPopulation.size(); i++)
		{
			newPopulation.saveHouse(i, regen(newPopulation.getHouse(i)));
		}

		return newPopulation;
	}

	/**
	 * Crappy asexual reproduction
	 * 
	 * @param house
	 * @return
	 */
	public static House mutate(House house)
	{
		if (Math.random() < mutationRate)
		{
			// pick two random tenants
			// TODO pos1 should be the least happy tenant
			int pos1 = (int) (house.getTenants().length * Math.random());
			int pos2 = (int) (house.getTenants().length * Math.random());
			// ensure a swap actually happens
			while (pos2 == pos1)
			{
				pos2 = (int) ((house.getTenants().length) * Math.random());
			}
			// Store the rooms of the two tenants
			int room1 = house.getTenants()[pos1].getRoom();
			int room2 = house.getTenants()[pos2].getRoom();
			// Swap them around
			house.getTenants()[pos1].setRoom(room2);
			house.getTenants()[pos2].setRoom(room1);
		}
		return house;
	}
	
	private static House regen(House house)
	{
		if (house.fitness() < (Math.pow(house.getTenants().length, 2)) / 2.0)
		{
			house.assignRooms();
		}
		
		return house;
	}

	private static House tournamentSelection(Pop pop)
	{
		// Create a blank tournament population
		Pop tournament = new Pop(tournamentSize, filename, false);
		// Fill tournament with random candidates
		for (int i = 0; i < tournamentSize; i++)
		{
			int randomId = (int) (Math.random() * pop.size());
			tournament.saveHouse(i, pop.getHouse(randomId));
		}
		// Get the fittest tour in the tournament
		House fittest = tournament.getFittest();
		return fittest;
	}
}
