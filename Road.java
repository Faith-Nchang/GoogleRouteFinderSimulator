/**
 * represent the edges of a Graph of Towns
 * @author - Faith Fru Nchang
 */
public class Road implements Comparable<Road>
{
	private Town source;
	private Town destination;
	private int roadDistance;
	private String roadName;
	private final int DEFAULT_DISTANCE = 1;
	
	
	
	/**
	 * creates a Road object from the provided attributes
	 * @param source - beginning path
	 * @param destination - ending path
	 * @param distance - distance between townnA and townB
	 * @param name - roadName
	 */
	public Road(Town source, Town destination, int distance, String name)
	{
		this.source = source;
		this.destination = destination;
		this.roadDistance = distance;
		this.roadName = name;
	}
	
	/**
	 * Constructor with weight preset at 1
	 * source - One town on the road
	 * destination - Another town on the road
	 * name - Name of the road	 
	 **/
	public Road(Town source, Town destination, String name)
	{
		this.source = source;
		this.destination = destination;
		this.roadDistance = DEFAULT_DISTANCE;
		this.roadName = name;
	}
	
	/**
	 * gets source
	 * @return - source
	 */
	public Town getSource()
	{
		return this.source;
	}
	
	/**
	 * updates source
	 * @param town - new source
	 */
	public void setSource(Town town)
	{
		this.source = town;
	}
	/**
	 * gets destination
	 * @return - destination
	 */
	public Town getDestination()
	{
		return this.destination;
	}
	
	/**
	 * updates townB
	 * @param town - new townB
	 */
	public void setDestination(Town town)
	{
		this.destination = town;
	}
	
	/**
	 * gets distance between townA and townB
	 * @return townDistance;
	 */
	public int getWeight()
	{
		return this.roadDistance;
	}
	
	/**
	 * gets the road name
	 * @return road name
	 */
	public String getName()
	{
		return this.roadName;
	}
	
	/**
	 * updates the road name
	 * @param name - road name
	 */
	public void setName(String name)
	{
		roadName = name;
	}

	/**
	 * compares two roads
	 * @param other - the other road
	 * @return 0 if the same, -1 if current road is less than the other, 1 otherwise
	 */
	@Override
	public int compareTo(Road other) 
	{
		return this.roadDistance - other.getWeight();
			
	}

	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town - town - a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town)
	{
		return (source.equals(town) || destination.equals(town));
	}
	
	/**
	 * returns the roads attributes
	 * @return road's attributes
	 */
	public String toString()
	{
		return source + " " + destination + " " + roadDistance + " " + roadName;
	}
	
	/**
	 *  checks if one Road is equal to another
	 * @param road - road to be compared with
	 * @return true if the roads are the same, false other wise
	 */
	@Override
	public boolean equals(Object obj)
	{
		Road road=  (Road) obj;
		
		if ((this.source.equals(road.source) && (this.destination.equals(road.destination))) || (this.source.equals(road.destination) && this.destination.equals(road.source)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * computers the hashCode
	 * @return hashCode
	 */
	@Override
	public int hashCode()
	{
		return roadName.hashCode();
	}
}