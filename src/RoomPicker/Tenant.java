package RoomPicker;

/**
 * Inhabitant of House
 * @author Oliver
 *
 */
public class Tenant
{
	private int[] prefs;
	private int room;
	private String name;
	
	public Tenant(String name, int[] prefs)
	{
		this.name = name;
		this.prefs = prefs;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setPrefs(int[] prefs)
	{
		this.prefs = prefs;
	}
	
	public int[] getPrefs()
	{
		return prefs;
	}
	
	public void setRoom(int room)
	{
		this.room = room;
	}
	
	public int getRoom()
	{
		return room;
	}
	
	public int getHappiness()
	{
		// TODO work out where their room is in their preferences
		int happiness = prefs.length; // max happiness to start
		for (int i = 0; i < prefs.length; i++)
		{
			if (prefs[i] != room)
			{
				happiness--;
			}
			else
			{
				break;
			}
		}
		return happiness;
	}

}
