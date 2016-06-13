package simpleGa;

public class FitnessCalc
{

	static byte[] solution = new byte[64];

	/**
	 * Set a candidate solution as a byte array
	 * @param newSolution
	 */
	public static void setSolution(byte[] newSolution)
	{
		solution = newSolution;
	}

	/**
	 * To make it easier we can use this method to set our candidate solution
	 * with string of 0s and 1s
	 * 
	 * @param newSolution
	 */
	static void setSolution(String newSolution)
	{
		solution = new byte[newSolution.length()];
		// Loop through each character of our string and save it in our byte
		// array
		for (int i = 0; i < newSolution.length(); i++)
		{
			String character = newSolution.substring(i, i + 1);
			if (character.contains("0") || character.contains("1"))
			{
				solution[i] = Byte.parseByte(character);
			}
			else
			{
				solution[i] = 0;
			}
		}
	}

	/**
	 * Calculate individuals fitness by comparing it to our candidate solution
	 * 
	 * @param individual
	 * @return
	 */
	static int getFitness(Individual individual)
	{
		int fitness = 0;
		// Loop through our individuals genes and compare them to our candidate's
		for (int i = 0; i < individual.size() && i < solution.length; i++)
		{
			if (individual.getGene(i) == solution[i])
			{
				fitness++;
			}
		}
		return fitness;
	}

	// Get optimum fitness
	static int getMaxFitness()
	{
		int maxFitness = solution.length;
		return maxFitness;
	}
}