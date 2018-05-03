public class NodeImage
{
	// Data Members
	private int start;
	private int end;
	private int ranking;

	// Constructor
	public NodeImage(int start, int end, int ranking)
	{
		this.start = start;
		this.end = end;
		this.ranking = ranking;
	}

	// Return startNode
	public int getStartNode()
	{
		return start;
	}

	// Return endNode
	public int getNode()
	{
		return end;
	}

	// Return ranking
	public int getRating()
	{
		return ranking;
	}
}