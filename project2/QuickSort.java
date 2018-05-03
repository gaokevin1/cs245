// Like Merge Sort, QuickSort is a Divide and Conquer algorithm. 
	// It picks an element as pivot and partitions the given array around the picked pivot.

public class QuickSort 
{
	private static ArrayBasedList<Product> list;
	private static int size;

	public static ArrayBasedList<Product> arraySort(ArrayBasedList<Product> plist) 
	{
		list = plist;
		size = plist.size();
		
		quickSort(0, size - 1);

		return list;
	}

	public static void quickSort(int low, int high)
	{
		int i = low, j = high;
    	
    	// Get middle item in the list
		Product pivot = list.get(low + (high - low) / 2);

    	// Split the list in two
		while (i <= j) 
		{
    		// Select an element that is greater than the middle value
			while (list.get(i).getAvgRating() > pivot.getAvgRating()) 
			{
				i++;
			}
      	
      		// Select an element that is less than the middle value 
			while (list.get(j).getAvgRating() < pivot.getAvgRating())
			{
				j--;
			}

      		// If the element in the left list is less than or equal to the element in the right list 
			if (i <= j)
			{
				swap(i, j);

				i++;
				j--;
			}
		}
    	
    	// Recursion
		if (low < j)
		{
			quickSort(low, j);
		}

		if (i < high)
		{
			quickSort(i, high);
		}
	}

	public static void swap(int i, int j)
	{
		Product temp = list.get(j);

		list.set(i, list.get(j));
		list.set(j, temp);
	}
}

