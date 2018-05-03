public class Product 
{
	private String asin;
	private int[] ratings;
		
	// Constructor
	public Product(String asin, int[] ratings) 
	{
		this.asin = asin;
		this.ratings = ratings;
	}

	public String getAsin()
	{
		return asin;
	}
	
	public double getAvgRating()
	{
		int total = 0;
		double avgRating = 0.0;

		for (int i = 0; i < ratings.length; i++)
		{
			total += ratings[i];
		}

		avgRating = (double) (total / ratings.length);

		return avgRating;
	}
}
