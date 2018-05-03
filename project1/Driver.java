import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Driver
{
	// Data Members
	private static int k, count;
	private static int[] numsFromText;

	public static void main(String[] args) throws FileNotFoundException, IOException
	{	
		// Data Members
		int[][] arr;
		int[] mergedArray;

		if (args.length != 1)
		{
			System.out.println("usage: java Driver <filename>");
			System.exit(0);
		}
		else
		{
			String dir = args[0];

			// Create reference to MergeSort object
			MergeSort kwm = new MergeSort();

			// Call method to get numbers from text file
			getNumbers(dir);

			int chunkSize = numsFromText.length / k;

			// Divide numbers from text file into chunks
			arr = divideIntoChunks(kwm, chunkSize);

			mergedArray = kwm.mergeKWay(arr, numsFromText.length);

			System.out.println(Arrays.toString(numsFromText));

			writeToDisk(mergedArray, dir);
		}
	} 

	public static void getNumbers(String dir) throws FileNotFoundException, IOException
	{
		// Data Members
		FileReader fr = new FileReader(dir); 
		BufferedReader br = new BufferedReader(fr); 
		String line;
		
		numsFromText = new int[10];
		k = 0;
		
		int i = 0;

		// Find the number of elements in text file and K
		while((line = br.readLine()) != null)
		{
			// Trim whitespace from line
			line = line.trim();

			// Parse line into integer
			int tempNum = Integer.parseInt(line);

			// Get first value from text file (i.e. "k")
			if (i == 0)
			{
				if (tempNum >= 2)
				{
					k = tempNum;
				}
				// Display error if K < 2
				else
				{
					System.out.println("K must be >= 2.");
					System.exit(0);
				}
			}
			// Add next element from text file to array
			else if (i < numsFromText.length && i != 0)
			{
				numsFromText[i - 1] = tempNum;
			}
			// Resize array if full
			else if (i == numsFromText.length)
			{
				int size = numsFromText.length + 1;
				int[] newTempArray = new int[size];
				System.arraycopy(numsFromText, 0, newTempArray, 0, numsFromText.length);

				numsFromText = new int[size];
				System.arraycopy(newTempArray, 0, numsFromText, 0, size);

				numsFromText[i] = tempNum;
			}

			i++;
		}

		// Close scanner
		fr.close();
	}   

	public static int[][] divideIntoChunks(MergeSort kwm, int chunkSize)
	{
		int numArrays = (int) Math.ceil((double) numsFromText.length / chunkSize);
		int[][] arr = new int[numArrays][];

		// Split array of text file elements into chunks of equal size
		for (int i = 0; i < numArrays; i++) 
		{
			int start = i * chunkSize;
			int length = Math.min(numsFromText.length - start, chunkSize);

			int[] newTempArray = new int[length];
			System.arraycopy(numsFromText, start, newTempArray, 0, length);

			newTempArray = kwm.mergeSort(newTempArray);

			// Display sorted subarray
			System.out.println(Arrays.toString(newTempArray));

			arr[i] = newTempArray;
		}

		return arr;
	}

	public static void writeToDisk(int[] mergedArray, String dir) throws IOException
	{
		// Trim off file extension
		String newDir = dir.substring(0, dir.lastIndexOf('.'));

		// Write the sorted array into new text file
		FileWriter fw = new FileWriter(newDir + "_sorted.txt"); 
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < mergedArray.length; i++) 
		{
			bw.write("  " + mergedArray[i]);
			bw.newLine();
		}

		bw.flush();  
		bw.close();  
	}
}