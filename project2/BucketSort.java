// Bucket sort is a stable sorting algorithm based on partitioning the input array 
	// into several parts and using some other sorting algorithm

public class BucketSort 
{
	public static ArrayBasedList<Product> sort(ArrayBasedList<Product> plist) 
	{
		int maxValue = maxValue(plist);

		plist = bucketSort(plist);

		return plist;
    }

    public static int maxValue(ArrayBasedList<Product> plist)
    {
    	int maxValue = 0;

        for (int i = 0; i < plist.size(); i++)
        {
            if (plist.get(i).getAvgRating() > maxValue)
            {
                maxValue = plist.get(i).getAvgRating();
            }
        }
        return maxValue;
    }
	
	public static ArrayBasedList<Product> bucketSort(ArrayBasedList<Product> plist)
	{
		ArrayBasedList<Product> bucket = new ArrayBasedList<Product>(maxValue + 1);

        ArrayBasedList<Product> sortedList = new int[plist.length];
 
        for (int i = 0; i < plist.length; i++)
        {
        	bucket.plist[i]]++;
        }
 
        int outPos = 0;

        for (int i = 0; i < bucket.length; i++)
        {
            for (int j = 0; j < bucket.get(i).getAvgRating(); j++)
            {
                sortedList.set(outPos++, i);
            }
        }
 
        return sortedList;
	}
}
