import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;

public class Driver 
{
	public static void main(String[] args) 
	{
		if (args.length != 1)
		{
			System.out.println("usage: java Driver <filename>");
			System.exit(0);
		}
		else
		{
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
		
			plist = buildPList(args[0]);

			try 
			{
				InsertionSort is = new InsertionSort();
				plist = is.sort(plist);

				// This will test the certain edge case and accuracy of your sorts.
				// This is NOT comprehensive and your own tests need to done in 
				// addition to this test.
				//
				// TODO: To look the timing of your sorts, uncomment printTiming in the 
				// Test.java file. Large samples will take a long time to complete though.
				Test.test(plist, args);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | 
				IllegalArgumentException | InvocationTargetException | IOException e) 
			{
				System.out.println("Test could not run/finish.");
			}

			//TODO: Write to a new .csv file named whatever the file being read is with "_sorted"
			//      added to it. So if the file being read is called ratings_Stuff.csv, your file
			//      will be called ratings_Stuff_sorted.csv. For this part, use any of your sorts.
			//      The file will have one ASIN per line.
			try
			{
				writeToDisk(plist, args[0]);
			}
			catch (IOException e)
			{

			}

			try 
			{
				// Requires both result and sorted files to be in current directory
				Test.testFile(args);
			} 
			catch (IOException e1) 
			{
				System.out.println("----------------------------------------");
				System.out.println("!! File Test: Could not read file. Check file naming.");
			} 
			catch (IndexOutOfBoundsException e2) 
			{
				System.out.println("----------------------------------------");
				System.out.println("!! File Test: Missing command line argument");
			}
		}
	}

	public static ArrayBasedList<Product> buildPList(String filename)
	{
		// Creates a new MovieList object
		ArrayBasedList l1 = new ArrayBasedList();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) 
		{
			String input;
			String split[];
			
			while ((input = br.readLine()) != null) 
			{
				split = input.split(",\"");
				
				String[] ratingsInput = split[1].replaceAll("[^0-9,]+", "").split(",");

				int[] ratings = new int[ratingsInput.length];
				
				for (int i = 0; i < ratings.length; i++) 
				{
					ratings[i] = Integer.parseInt(ratingsInput[i]);
				}

				// add the new product to the plist
				l1.add(new Product(split[0], ratings));
			}
		}
		catch (IOException e)
		{

		}

		return l1;
	}

	public static int[] getRatingsArray(String ratingString)
	{
		int[] ratings = new int[1];
		ratingString = ratingString.substring(2, ratingString.length() - 2);

		String[] splitArray = ratingString.split(",");

		for (int i = 0; i < splitArray.length; i++)
		{
			if (i >= ratings.length)
			{
				ratings = Arrays.copyOf(ratings, ratings.length + 1);
			}

			ratings[i] = Integer.parseInt(splitArray[i]);
		}

		return ratings;
	}

	public static void writeToDisk(ArrayBasedList<Product> plist, String dir) throws IOException
	{
		// Trim off file extension
		String newDir = dir.substring(0, dir.lastIndexOf('.'));

		// Write the sorted array into new text file
		FileWriter fw = new FileWriter(newDir + "_sorted.csv"); 
		fw.append("\n");

		for (Product product : plist)
		{
			fw.append(String.valueOf(product.getAsin()));
			fw.append("\n");
		}

		fw.flush();  
		fw.close();  
	}
}
