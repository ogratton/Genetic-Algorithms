/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

package tsp;

import javax.swing.JFrame;

/**
 * Generates an approximate solution to TSP
 * 200 by 200 grid
 * Be nice and use multiples of 20 only please thanks
 * 
 * @author Oliver
 *
 */
public class TSP_GA
{	
	
	public static void main(String[] args)
	{
		// CUSTOM VARIABLES:
		boolean random = false; //generate random cities
		int ATTEMPTS = 100; // how many times we run the GA
		int GENS = 50; // for how many generations we evolve each iteration
		
		
		if (random)
		{
			for (int i = 0; i < 50; i++)
			{
				TourManager.addCity(new City());
			}
		}
		
		else
		{
			City city = new City(60, 200);
			TourManager.addCity(city);
			City city2 = new City(180, 200);
			TourManager.addCity(city2);
			City city3 = new City(80, 180);
			TourManager.addCity(city3);
			City city4 = new City(140, 180);
			TourManager.addCity(city4);
			City city5 = new City(20, 160);
			TourManager.addCity(city5);
			City city6 = new City(100, 160);
			TourManager.addCity(city6);
			City city7 = new City(200, 160);
			TourManager.addCity(city7);
			City city8 = new City(140, 140);
			TourManager.addCity(city8);
			City city9 = new City(40, 120);
			TourManager.addCity(city9);
			City city10 = new City(100, 120);
			TourManager.addCity(city10);
			City city11 = new City(180, 100);
			TourManager.addCity(city11);
			City city12 = new City(60, 80);
			TourManager.addCity(city12);
			City city13 = new City(120, 80);
			TourManager.addCity(city13);
			City city14 = new City(180, 60);
			TourManager.addCity(city14);
			City city15 = new City(20, 40);
			TourManager.addCity(city15);
			City city16 = new City(100, 40);
			TourManager.addCity(city16);
			City city17 = new City(200, 40);
			TourManager.addCity(city17);
			City city18 = new City(20, 20);
			TourManager.addCity(city18);
			City city19 = new City(60, 20);
			TourManager.addCity(city19);
			City city20 = new City(160, 20);
			TourManager.addCity(city20);
		}

		
		// run GA 100 times to get a good route
		Integer bestDist = Integer.MAX_VALUE;
		Population bestPop = null;
		for (int i = 0; i < ATTEMPTS; i++)
		{
			if ((i+1) % 100 == 0)
			{
				System.out.println("Attempt number "+(i+1)+" of "+ATTEMPTS);
			}
			
			Population pop = new Population(50, true);
			// Evolve population for 100 generations
			pop = GA.evolvePopulation(pop);
			for (int j = 0; j < GENS; j++)
			{
				pop = GA.evolvePopulation(pop);
			}
			
			Integer currDist = pop.getFittest().getDistance();
			if (currDist < bestDist)
			{
				bestPop = pop;
				bestDist = currDist;
				System.out.println("New best: "+bestDist);
			}
		}
		
		// Print final results
		System.out.println("Final distance: " + bestPop.getFittest().getDistance());
		System.out.println("Solution:");
		System.out.println(bestPop.getFittest());

		// GUI stuff
		JFrame frame = new JFrame();
		int fw = 600;
		int fh = 600;
		frame.setSize(fw, fh);
		frame.setTitle("My Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MapComponent comp = new MapComponent((int) (fw * 0.9), (int) (fh * 0.9), 200, 200, bestPop.getFittest());
		frame.add(comp);
		frame.setVisible(true);
	}
}