import java.util.*;
import java.io.*;

public class Driver 
{
// Data Members
	private static int sourceId;
	private static int targetId;
	private static HashMap<Integer, ArrayList<NodeImage>> aList = new HashMap<>();
	private static ArrayList<Double> avgArr = new ArrayList<Double>();

	public static void main(String[] args)
	{
		String filename = args[0];

		Scanner reader = new Scanner(System.in);

		System.out.println("Enter a Source ID: ");
		sourceId = reader.nextInt();

		System.out.println("Enter Target ID: ");
		targetId = reader.nextInt();

		Driver d = new Driver(sourceId, targetId);
		d.hashMapBuilder(filename);

		DFS(sourceId, targetId);

		if (avgArr.size() > 0)
		{
			System.out.println("(" + sourceId + "," + targetId + ")'s average score: " + computeTotalAverage());
		}
		else
		{
			System.out.println("Error - End Node Not Found");
		}
	}

	// Constructor
	public Driver(int userId, int targetId)
	{
		this.sourceId = sourceId;
		this.targetId = targetId;
	}

	public void hashMapBuilder(String filename) 
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String input;

			// Read in from file
			while((input = br.readLine()) != null)
			{
				// Create list of values for hashmap
				ArrayList<NodeImage> list = new ArrayList<NodeImage>();

				String[] line = input.split(",");
				int start = Integer.parseInt(line[0]);
				int end = Integer.parseInt(line[1]);
				int userRating = Integer.parseInt(line[2]);

				NodeImage c = new NodeImage(start, end, userRating);

				boolean shouldIterate = true;

				while (shouldIterate)
				{
					// If starting Node already contains the key then add the ending Node
					if (aList.containsKey(start))
					{
						ArrayList<NodeImage> temp = aList.get(c.getStartNode());

						temp.add(c);
						aList.put(start, temp);
						shouldIterate = false;
					}
					// If starting Node is not a key then add it
					else
					{
						list.add(c);
						aList.put(start, list);
						shouldIterate = false;
					}
				}
			}
				
			br.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e);
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}

	public static void DFS(int start, int end)
	{
		// Create an ArrayList to keep track of nodes already checked
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();

		// Create an ArrayList to keep track of the list of paths found
		ArrayList<NodeImage> paths = new ArrayList<NodeImage>();

    	// Recursively call the DFS search method
		search(start, end, alreadyChecked, paths);
	}

	public static void search(int node, int endNode, ArrayList<Integer> alreadyChecked, ArrayList<NodeImage> paths) 
	{
		// Add source to the alreadyChecked arrayList
		alreadyChecked.add(node);

		if (aList.containsKey(node))
		{
			for (NodeImage c : aList.get(node))
			{
				if ((c.getNode() != endNode) && (!alreadyChecked.contains(c.getNode())))
				{
					// Store the current node to the list of paths
					paths.add(c);

					// Recursive call
					search(c.getNode(), endNode, alreadyChecked, paths);

					// Remove the current element from the path and alreadyChecked ArrayList
					paths.remove(c);
					alreadyChecked.remove(new Integer(c.getNode()));
				}
				else if (c.getNode() == endNode)
				{
					// Add the end node to the list of paths
					paths.add(c);
					avgArr.add(computeTempAverage(paths));

					paths.remove(c);
				}
			}
		}
	}

	// Computes the average for the temporary paths that are checked
	public static double computeTempAverage(ArrayList<NodeImage> paths)
	{
		double sum = 0;
		double length = paths.size();

		for (NodeImage node : paths)
		{
			sum += node.getRating();
		}

		double avg = sum / length;
		return avg;
	}


	// Computes the total average from an array of temporary averages
	public static double computeTotalAverage()
	{
		double sum = 0;
		double length = avgArr.size();

		for(double node : avgArr)
		{
			sum += node;
		}

		double avg = sum / length;
		return avg;
	}
}