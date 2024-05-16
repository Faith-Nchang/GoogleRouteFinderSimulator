# GoogleRouteFinderSimulator
An application to maintain a network of towns and the roads connecting them


# Data Element – Town (Vertex)
Create a Town class that holds the name of the town and a list of adjacent towns, and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.).  It will implement the Comparable interface.  This is the class header:
	public class Town implements Comparable<Town>
	Two towns will be considered the same if their name is the same.
# Data Element – Road (Edge)
	Create a class Road that can represent the edges of a Graph of Towns.  The class must implement Comparable.  The class stores references to the two vertices(Town endpoints), the distance between vertices, and a name, and the traditional methods (constructors, getters/setters, toString, etc.), and a compareTo, which compares two Road objects. Since this is a undirected graph, an edge from A to B is equal to an edge from B to A. This is the class header:
	public class Road implements Comparable<Road>

#  Data Structure – Graph, implements GraphInterface
Create a Graph class that implements the GraphInterface given you.  For Graph<V,E>,  V is the vertex type (a Town), E is the edge type (a Road).  You will need to decide how to store the graph, use an adjacent matrix or an adjacency list.  This is the class header:
	public class Graph implements GraphInterface<Town, Road>
Within the Graph interface is a method shortestPath, which finds the shortest path from a given source Town to a destination Town. Since there is a unique shortest path from every vertex to the source, there is a back-pointer to the previous vertex.  The method shortestPath calls dijkstraShortestPath which finds the shortest path from the source to every other vertex in the graph.  You will be coding the Dijkstra’s Shortest Path algorithm.  You will then be able to find the connections between two towns through the roads that connect them.
You may use the adjacency matrix approach found in the text book, or you may use a set of Towns and a set of Roads. The ShortestPath algorithm typically uses a weighted graph which means that the edges have a weight, and this is used to determine the shortest path.  For this implementation, each weight will be the distance of the road in miles.

# Data Manager – implements TownGraphManagerInterface
Your TownGraphManager will hold an object of your Graph. Implement the TownGraphManagerInterface. There are methods to populate the graph (reading from a text file), add a town (vertices), add a road (edge), list all towns and all roads, and list towns adjacent to a given town.   
Your solution will find the shortest path from a start town to a destination town.  It will account for the possibility of a disjoint graph (i.e., not all vertices can be reached from all other vertices.)
You may add any methods as needed for your design.

# concepts
* Aggregation * Dijkstra’s Shortest Path algorithm 

