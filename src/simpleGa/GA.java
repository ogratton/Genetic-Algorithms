package simpleGa;

/**
 * Genetic Algorithm for guessing a 64 bit binary number
 * @author Oliver
 *
 */
public class GA
{

	public static void main(String[] args)
	{

		// Set a candidate solution
		// Must be no longer than 64 bytes as that number is hard-coded in the various classes 
		FitnessCalc.setSolution("1000100010110011110101001010101101010101010101011100011010101111");

		// Create an initial population
		Population myPop = new Population(50, true);

		// Evolve our population until we reach an optimum solution
		int generationCount = 0;
		while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness())
		{
			generationCount++;
			System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
			myPop = Algorithm.evolvePopulation(myPop);
		}
		System.out.println("Solution found!");
		System.out.println("Generation: " + generationCount);
		System.out.println("Genes:");
		System.out.println(myPop.getFittest());

	}
}