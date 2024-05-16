import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * Graph class that implements the GraphInterface given you.  For Graph<V,E>,  V is the vertex type (a Town), E is the edge type (a Road).
 * @author Faith Fru Nchang
 */
public class Graph implements GraphInterface<Town, Road>
{
	private HashSet<Town> vertices;
	HashSet<Road> edges;
	
	// Keeps track of the towns and road distance to their neighboring towns
	private Map<Town, Integer> distances ;
	private Map<Town, Town> previousVertices;
	
	public Graph()
	{
		vertices = new HashSet<>();
		edges = new HashSet<>();
		distances = new HashMap<Town, Integer>();
		previousVertices = new HashMap<Town, Town>();
	}
	 
	
	
	
	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
    public Road getEdge(Town sourceVertex, Town destinationVertex)
    {
		if(sourceVertex == null || destinationVertex == null) {
			
			return null;
		}
		
		else {
			for(Road road : edges) {
				if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
					return road;
				}
			}	
		}
		
		return null;
    }
	
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws NullPointerException, IllegalArgumentException
    {
    	if (sourceVertex == null || destinationVertex == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	//  checks if either the source or destination vertex is not in the graph
    	if (!(containsVertex(sourceVertex) || containsVertex(destinationVertex)))
    	{
    		throw new IllegalArgumentException();
        	
    	}
    		
    	Road road1 = new Road(sourceVertex, destinationVertex, weight, description);

    	edges.add(road1);
    	
    	for (Town town: vertices)
    	{
    		if (town.equals(sourceVertex))
    		{
    			town.addAjacentTown(destinationVertex);
    		}
    		else if (town.equals(destinationVertex))
    		{
    			town.addAjacentTown(sourceVertex);
    		}
    	}
    	
    	

    	return road1;
    }
	
	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    public boolean containsEdge(Town sourceVertex, Town destinationVertex)
    {
    	if(sourceVertex == null || destinationVertex == null) {
    		return false;
    	}
    	
    	if (!(vertices.contains(sourceVertex) || vertices.contains(destinationVertex))) {
    		return false;
    	}
		
		for(Road road : edges) {
			if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
    }
    
    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    public boolean containsVertex(Town v)
    {
    	if (v== null)
    		return false;
    	
    	
    	for (Town town: vertices)
    	{
    		if (town.equals(v))
    			return true;
    	}
    	return false;
    }
    
    
    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    public Set<Road> edgeSet()
    {
    	if (edges == null)
    		return null;
    	
    	return edges;
    }
    
    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    public Set<Road> edgesOf(Town vertex) throws IllegalArgumentException, NullPointerException
    {
    	Set<Road> neighbors = new HashSet<Road>();
    	if (vertex == null)
    	{
    		throw new NullPointerException();
    	}
    	if (!containsVertex(vertex))
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	for (Road r: edges)
    	{
    		if (r.contains(vertex))
    		{
    			neighbors.add(r);
    		}
    	}
    	
    	return neighbors;
    }
    
    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {
    	if (sourceVertex==null || destinationVertex == null)
    	{
    		return null;
    	}
    	
    	Road road = new Road(sourceVertex, destinationVertex, weight, description);
    	if (!this.containsEdge(sourceVertex, destinationVertex) || weight<0 || description == null)
    		return null;
    	
    	for (Town town: vertices)
    	{
    		if (town.containsTown(sourceVertex))
    		{
    			town.removeAdjacentTown(destinationVertex);    			
    		}
    		else if (town.containsTown(destinationVertex))
    		{
    			town.removeAdjacentTown(sourceVertex);
    		}
    	}
    	
    	for (Road r: edges)
    	{
    		if (road.equals(r))
    		{
    			edges.remove(r);
    			
    			
    			return r;
    		}
    	}
    	
    	return null;
    	
    }
    
    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    public boolean removeVertex(Town v)
    {
    	if (v==null)
    	{
    		return false;
    	}
    	
    	
    	for (Town t: vertices)
    	{
    		if (t.containsTown(v))
    		{
    			t.removeAdjacentTown(v);
    		}
    		
    		if (t.equals(v))
    		{
    			vertices.remove(t);
    			
    			return true;
    		}
    		
    		
    	}
    	
    
    	
    	return false;
    }
    
    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    public Set<Town> vertexSet()
    {
    	return vertices;
    }
    
    
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
    {
    	
    	ArrayList<String> path = new ArrayList<String>();
		boolean containsDestination = false;
		
		for (Road road : edges) {
			if (road.contains(destinationVertex)) {
				containsDestination = true;
			}
		}
		// checks if the destination is reachable
		if(!containsDestination) {
			return path;
		}
		// computes the shortest path to all vertices
		dijkstraShortestPath(sourceVertex);
		
		
		// Loop until the current destination vertex is equal to the sourceVertex
		while (!sourceVertex.equals(destinationVertex))
		{
		    // Iterate through each road in the roadSet
		    for (Road road : edges)
		    {
		        // Check if the road connects the current destination vertex and its previous vertex
		        if (road.contains(destinationVertex) && road.contains(previousVertices.get(destinationVertex))) {
		            
		        	String name = previousVertices.get(destinationVertex).getName();
		        	String roadN = road.getName();
		        	String dest = destinationVertex.getName();
		        	int distance = road.getWeight() ;
		        	
		            // Add the corresponding path information to the shortest path list
		            path.add(0, name + " via " + roadN + " to " + dest + " " + distance + " mi");  
		        }
		    }
		 	destinationVertex = previousVertices.get(destinationVertex);
		}
		return path;


   }
    
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
    public void dijkstraShortestPath(Town sourceVertex)
    {
    	Set<Town > closestPath = new HashSet<Town>();
    	
    	// initializes each vertex to infinity
   		for(Town town : vertices) {
   			distances.put(town, Integer.MAX_VALUE);
    	}
		
    	// adds all the vertices to the closestPath
		for(Town town: vertices) {
			closestPath.add(town);
		}
		
		
		// loops to process each town
		while(!closestPath.isEmpty()) {
			
			int minimum = 100000;
			
			for (Map.Entry<Town, Integer> entry : distances.entrySet()) {
			    Town town = entry.getKey();
			    int distance = entry.getValue();
			    if (closestPath.contains(town)&& minimum > distance ) {
			        minimum = distance;
			        sourceVertex = town;
			    }
			}

			// Update the weights for neighboring towns through sourceVertex
			for (Road road : edges) {
			    
			    Town destination = road.getDestination();
			    if (destination.equals(sourceVertex) || !closestPath.contains(destination)) 
			    {
			        continue;
			    }
			    if (!road.contains(sourceVertex)) {
			        continue;
			    }
			    
			    
			    int sourceWeight = distances.get(sourceVertex);
			    int destinationWeight = distances.get(destination);
			    int newWeight = sourceWeight + road.getWeight();
			    
			    if (newWeight < destinationWeight) {
			        previousVertices.put(destination, sourceVertex);
			        distances.put(destination, newWeight);
			    }
			}

			// Remove the sourceVertex from closestPath as it is processed
			for (Road road : edges) {
			    
			    Town source = road.getSource();
			    if (source.equals(sourceVertex) || !closestPath.contains(source)) {
			        continue;
			    }
			    if (!road.contains(sourceVertex)) {
			        continue;
			    }
			    
			    
			    int sourceWeight = distances.get(sourceVertex);
			    int sourceToRoadWeight = road.getWeight();
			    int currentRoadWeight = distances.get(source);
			    int newWeight = sourceWeight + sourceToRoadWeight;
			    
			    if (newWeight < currentRoadWeight) {
			        previousVertices.put(source, sourceVertex);
			        distances.put(source, newWeight);
			    }
			}
			// removes the currentVertex from the closest path after processing
			closestPath.remove(sourceVertex);

		}
	}
   
    
    
    
    
    
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    public boolean addVertex(Town v) throws NullPointerException
    {
    	if (v == null)
    		throw new NullPointerException();
    	
    	if (containsVertex(v))
    		return false;
    	vertices.add(v);
    	
    	return true;
    }
    
    
}
   
