import java.util.*;
import java.io.*;

public class Driver 
{
    // Data Members
	private static int minRating;
	private static int userId;
	private static HashMap<Integer, ArrayList<Integer>> aList;

	public static void main(String[] args)
	{
		String filename = args[0];
		aList = new HashMap<>();

		Scanner reader = new Scanner(System.in);

		System.out.println("Enter a User ID: ");
		int userId = reader.nextInt();

		System.out.println("Enter Min Rating: ");
		int minRating = reader.nextInt();

		Driver d = new Driver(userId, minRating);
		d.hashMapBuilder(filename);

		search(userId);
	}

 	// Constructor
	public Driver(int userId, int minRating)
	{
		this.userId = userId;
		this.minRating = minRating;
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
				ArrayList<Integer> cList = new ArrayList<Integer>();

				String[] line = input.split(",");
				int start = Integer.parseInt(line[0]);
				int end = Integer.parseInt(line[1]);
				int userRating = Integer.parseInt(line[2]);

				if (userRating >= minRating)
				{	
					// If starting Node already contains the key then add the ending Node
					if (!aList.containsKey(start))
					{
						ArrayList<Integer> temp = aList.get(start);

						temp.add(end);
						aList.put(start, temp);

					}
					// If starting Node is not a key then add it
					else
					{
						cList.add(end);
						aList.put(start, cList);
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

	public static void search(int node) 
	{
        // Create an ArrayList to keep track of nodes already checked
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();

        // Create a new LinkedList to add nodes to queue
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(node);
        
        // Add the starting node to the ArrayList
		alreadyChecked.add(node);

		while (!q.isEmpty()) 
		{
            // Get the next node
			node = queue.remove();

            // Create temp ArrayList to store nodes
			ArrayList<Integer> tempList = aList.get(node);
			
			if (tempList != null) 
			{
				for (Integer next: tempList) 
				{
					if (!alreadyChecked.contains(next)) 
					{
						queue.add(next);
						alreadyChecked.add(next);
						System.out.println("Node: " + next);
					}
				}
			}
		}
	}
}