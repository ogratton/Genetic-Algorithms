package RoomPicker;

import java.util.ArrayList;
import java.util.Collections;

import auxil.CSVReader;

/**
 * Collection of tenants
 * 
 * @author Oliver
 *
 */
public class House
{
	private Tenant[] tenants;
	private int num;

	// info we need to make the tenants

	public House(String filename)
	{
		// read data from csv
		CSVReader csv = new CSVReader();
		ArrayList<String[]> data = csv.readContents(filename);
		num = data.size();

		tenants = new Tenant[num];
		// for each line in the file
		for (int i = 0; i < data.size(); i++)
		{
			String name = data.get(i)[0];
			String prefs = data.get(i)[1];
			tenants[i] = new Tenant(name, parse(prefs));
		}
	}

	public Tenant[] getTenants()
	{
		return tenants;
	}

	/**
	 * parse the preference info from the csv into an int[]
	 * 
	 * @param p string array from csv
	 * @return int array of room preference in descending order
	 */
	private int[] parse(String p)
	{
		String[] ps = p.split(";");
		int[] prefs = new int[num];
		for (int i = 0; i < ps.length; i++)
		{
			prefs[i] = Integer.parseInt(ps[i]);
		}

		return prefs;
	}

	/**
	 * randomly assign rooms to each of the tenants
	 */
	public void assignRooms()
	{
		// randomise the numbers 1 to num
		ArrayList<Integer> r = new ArrayList<Integer>(num);
		for (int i = 1; i <= num; i++)
		{
			r.add(i);
		}
		Collections.shuffle(r);
		// assign rooms to tenants one by one
		for (int i = 0; i < tenants.length; i++)
		{
			tenants[i].setRoom(r.get(i));
		}
	}

	public int fitness()
	{
		int x = 0;
		for (int i = 0; i < tenants.length; i++)
		{
			x += tenants[i].getHappiness();
		}
		return x;
	}

	public String toString()
	{
		String s = "";
		for (int i = 0; i < tenants.length; i++)
		{
			s += tenants[i].getName() + " is in room " + tenants[i].getRoom() + ", happiness: " + tenants[i].getHappiness() + "\n";
		}

		return s;
	}

	public boolean equals(House that)
	{
		Tenant[] t = that.tenants;

		for (int i = 0; i < tenants.length; i++)
		{
			if (tenants[i].getRoom() == t[i].getRoom())
			{
				continue;
			}
			else
			{
				return false;
			}
		}

		return true;
	}

}
