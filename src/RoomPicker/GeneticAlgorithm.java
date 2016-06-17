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
			// Select parents
			House parent1 = tournamentSelection(pop);
			House parent2 = tournamentSelection(pop);

			// prevent asexual reproduction
			while (parent1.equals(parent2))
			{
				parent2 = tournamentSelection(pop);
			}

			// Crossover parents
			House child = crossover(parent1, parent2);
			// Add child to new population
			newPopulation.saveHouse(i, child);
		}

		// Mutate the new population a bit to add some new genetic material
		for (int i = elitismOffset; i < newPopulation.size(); i++)
		{
			mutate(newPopulation.getHouse(i), false);
		}

		return newPopulation;
	}

	/**
	 * Somehow breed two houses together to create a 'legal' child
	 * 
	 * @param parent1
	 * @param parent2
	 * @return
	 */
	public static House crossover(House parent1, House parent2)
	{
		//House child = new House(filename);

		// TODO VERY TEMPORARY BREEDING PROGRAM JUST RETURNS ONE OF THE PARENTS
		// This currently causes the whole thing to fill because one parent fills the whole array and then the anti asexual loop gets stuck
		return parent1;
	}

	/**
	 * Spice things up by swapping two random tenants' rooms
	 * 
	 * @param house
	 */
	public static void mutate(House house, boolean force)
	{
		// Apply mutation rate
		if (Math.random() < mutationRate || force)
		{
			// pick two random tenants
			int pos1 = (int) (house.getTenants().length * Math.random());
			int pos2 = (int) (house.getTenants().length * Math.random());

			// ensure a swap actually happens
			while (pos2 == pos1)
			{
				pos2 = (int) ((house.getTenants().length) * Math.random());
			}

			// debugging printing
			if (force)
				System.out.println("Swapping tenants: " + pos1 + " and " + pos2);

			// Store the rooms of the two tenants
			int room1 = house.getTenants()[pos1].getRoom();
			int room2 = house.getTenants()[pos2].getRoom();

			// Swap them around
			house.getTenants()[pos1].setRoom(room2);
			house.getTenants()[pos2].setRoom(room1);
		}
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
