package RoomPicker;

public class RoomPickerMain
{

	private static House random()
	{
		House house = new House("prefs.csv");
		house.assignRooms();
		return house;
	}
	
	private static void printRandom()
	{
		House house = random();
		System.out.println("House happiness: "+house.fitness() + "/36");
		System.out.println(house);
	}

	private static void genetic()
	{
		int ATTEMPTS = 1; 	// how many times we run the GA
		int GENS = 50; 		// how many generations we evolve each attempt
		int POPSIZE = 50; 	// how many houses in each generation
		String filename = "prefs.csv";
		int happiest = 0;
		House happiestHouse = null;

		Pop pop = new Pop(POPSIZE, filename, true);

		for (int i = 0; i < ATTEMPTS; i++)
		{
			// evolve the population 'GENS' times
			for (int j = 0; j < GENS; j++)
			{
				pop = GeneticAlgorithm.evolvePopulation(pop);
				System.out.println("evolved " + (j + 1) + " time(s)");
				System.out.println("House happiness: "+pop.getFittest().fitness() + "/36");
				System.out.println(pop.getFittest());
			}
			int currHap = pop.getFittest().fitness();
			if (currHap > happiest)
			{
				happiestHouse = pop.getFittest();
				happiest = currHap;
				System.out.println("New best: " + happiest);
			}
		}
		System.out.println("Best solution found: \n" + happiestHouse);
	}
	
	@SuppressWarnings("unused")
	private static void testMutation()
	{
		House house = random();
		System.out.println("House happiness: " + house.fitness() + "/36");
		System.out.println(house);
		
		GeneticAlgorithm.mutate(house, true);
		
		System.out.println("House happiness: " + house.fitness() + "/36");
		System.out.println(house);

	}

	public static void main(String[] args)
	{
		printRandom();
		
		//genetic();
		
		//testMutation();

	}

}
