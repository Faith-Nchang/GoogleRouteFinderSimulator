import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * holds the name of the town and a list of adjacent towns
 * Vertex in a graph
 * @author - Faith Fru Nchang
 */
public class Town implements Comparable<Town>
{
	private String townName;
	private ArrayList<Town> adjacentTowns;
	private Town previousTown;
	
	
	public Town(String name)
	{
		townName = name;
		adjacentTowns = new ArrayList<>();
		previousTown = null;
	}
	
	public Town(Town newTown)
	{
		this.townName = newTown.townName;
		this.adjacentTowns = newTown.adjacentTowns;
		previousTown = newTown.previousTown;
		
	}
	/**
	 * gets the town name
	 * @return - townName
	 */
	public String getName()
	{
		return this.townName;
	}
	
	/**
	 * updates the town name
	 * @param name - name of town
	 */
	public void setTownName(String name)
	{
		this.townName = name;
	}
	
	/**
	 * gets the neighboring towns
	 * @return adjacentTowns
	 */
	public ArrayList<Town> getAdjacentTowns()
	{
		return this.adjacentTowns;
	}
	
	/**
	 * updates the adjacent towns 
	 * @param towns - ajacentTowns
	 */
	public void setAjacentTowns(ArrayList<Town> towns)
	{
		this.adjacentTowns = towns;
	}

	/**
	 * compares two towns based on their name
	 * @param otherTown - the other town to be compared with
	 */
	@Override
	public int compareTo(Town otherTown) {
		if (this.townName.equals(otherTown.townName))
		{
			return 0;
		}
		else if (this.townName.compareTo(otherTown.townName) < 0)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * Town's attributes
	 * @return town name
	 */
	public String toString()
	{
		return townName;
	}
	/**
	 * checks if two towns are equal
	 * @param object
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		Town town = (Town) obj;
		return this.townName.compareTo(town.townName) ==0;
	}
	
	/**
	 * computes the hash code of the town based on the town name
	 * @return hashCode of town
	 */
	@Override
	public int hashCode()
	{
		return this.townName.hashCode();
	}
	
	
	public void addAjacentTown(Town town)
	{
		if (!containsTown(town) && town !=null)
		{
			adjacentTowns.add(town);
		}
	}

	public boolean containsTown(Town town)
	{
		for (Town t: adjacentTowns)
		{
			if (t.equals(town))
				return true;
		}
		return false;
	}
	
	
	public void setPreviousTown(Town newTown)
	{	
		if (newTown != null)
			this.previousTown = newTown;
	}
	
	
	public Town getPreviousTown()
	{
		return this.previousTown;
	}
	
	public void removeAdjacentTown(Town t)
	{
		adjacentTowns.remove(t);
	}
}