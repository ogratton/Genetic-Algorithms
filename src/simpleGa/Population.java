package simpleGa;

/**
 * Manages all individuals of a population
 * 
 * @author Oliver
 *
 */
public class Population
{

	Individual[] individuals;

	/**
	 * Create a population
	 * 
	 * @param populationSize desired size of the population
	 * @param initialise true if generating completely new Individuals
	 */
	public Population(int populationSize, boolean initialise)
	{
		individuals = new Individual[populationSize];
		// Initialise population
		if (initialise)
		{
			// Loop and create individuals
			for (int i = 0; i < size(); i++)
			{
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	/**
	 * @param index position of individual in population array
	 * @return Individual object
	 */
	public Individual getIndividual(int index)
	{
		return individuals[index];
	}

	/**
	 * @return The fittest Individual in the population
	 */
	public Individual getFittest()
	{
		Individual fittest = individuals[0];
		// Loop through individuals to find fittest
		for (int i = 0; i < size(); i++)
		{
			if (fittest.getFitness() <= getIndividual(i).getFitness())
			{
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	/**
	 * Get population size
	 * 
	 * @return size of population
	 */
	public int size()
	{
		return individuals.length;
	}

	/**
	 * Save an Individual
	 * 
	 * @param index
	 * @param indiv
	 */
	public void saveIndividual(int index, Individual indiv)
	{
		individuals[index] = indiv;
	}
}