// Insertion sort iterates through the list by consuming one input element at 
	// each repetition, and growing a sorted output list.It repeats until 
		// no input elements remain.

public class InsertionSort
{
	public static ArrayBasedList<Product> sort(ArrayBasedList<Product> plist) 
	{
		ArrayBasedList<Product> sortedArray = new ArrayBasedList<Product>(plist.size());
		int i;

		for (int j = 1; j < plist.size(); j++)
		{
			// Remove temp Element
			Product temp = plist.get(j);
			i = j;
			
			// Start iterating through array until larger element is found
			while (j > 0 && (plist.get(j - 1).getAvgRating() < temp.getAvgRating()))
			{
				// Shift item to the right
				plist.set(j, plist.get(j - 1));

				// Go left one position
				j--;
			}

			// Insert temp element
			plist.set(j, temp);
		}

		return plist;
    }
}
