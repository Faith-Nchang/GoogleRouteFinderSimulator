import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;


/**
 * Data Manager class
 * Populates the Graph data structure
 * @author - Faith Fru Nchang
 */
public class TownGraphManager implements TownGraphManagerInterface
{	
	private Graph townGraph = new Graph();

	
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName)
	{
		if (town1 == null || town2 == null)
			return false;
		
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		try
		{
			townGraph.addEdge(t1, t2, weight, roadName);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
		
	
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2)
	{
		if (town1 == null || town2 == null)
			return null;
		
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		Road road = townGraph.getEdge(t1, t2);
		return road.getName();
	}
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v)
	{
		if (v == null)
			return false;
		
		Town town = new Town(v);
		return townGraph.addVertex(town);
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name)
	{
		if (name == null)
			return null;
		
		
		Set<Town> towns = townGraph.vertexSet();
		
		for (Town t: towns)
		{
			if (t.getName().equals(name))
			{
				return t;
			}
		}
		
		return null;
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v)
	{
		if (v == null)
			return false; 
		
		Town t = new Town(v);
		return  townGraph.containsVertex(t);
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2)
	{
		if (town1 == null || town2 == null)
			return false;
		
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		return townGraph.containsEdge(t1, t2);
	}
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads()
	{
		Set<Road> roadSet = townGraph.edgeSet();
		ArrayList<String> roads = new ArrayList<>();
		
		for (Road road: roadSet)
		{
			roads.add(road.getName());
		}
		
		Collections.sort(roads); 
		
		return roads;
		
	}
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road)
	{
		if (town1 == null || town2 == null || road == null)
			return false;
		
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		Road otherRoad = null;
		for (Road r: townGraph.edgeSet())
		{
			String edge = getRoad(town1, town2);
			if (edge.equals(r.getName()))
			{
				otherRoad = r;
				
			}
		}
		
		Road removeRoad = townGraph.removeEdge(t1, t2, otherRoad.getWeight(), road);
		if (removeRoad == null)
			return false;
		
		return true;
	}
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v)
	{
		if (v == null)
			return false;
		
		Town town = new Town(v);
		
		return townGraph.removeVertex(town);
	}
	
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns()
	{
		Set<Town> townSet = townGraph.vertexSet();
		ArrayList<String> towns = new ArrayList<>();
		for (Town t: townSet)
		{
			towns.add(t.getName());
		}
		
		Collections.sort(towns);
		return towns;
	}
	
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2)
	{
		return townGraph.shortestPath(new Town(town1), new Town(town2));
	}

	
	
	/**
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException
	{
		// road-name,miles;town-name;town-name
		Scanner inputFile = new Scanner(file);
		
		String[] towns;
		
		while (inputFile.hasNext())
		{
			towns = inputFile.next().strip().split(",|;");
			
			String roadName = towns[0];
			int miles = Integer.parseInt(towns[1]);
			
			Town town1 = new Town(towns[2]);
			Town town2 = new Town(towns[3]);
			
			townGraph.addVertex(town1);
			townGraph.addVertex(town2);
			townGraph.addEdge(town1, town2, miles, roadName);
			
		}
		
		inputFile.close();
	    
	}
}
