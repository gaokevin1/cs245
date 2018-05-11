public class HashEntry
{
	String key;
	String value;

	// Linked list of same hash entries.
	HashEntry next;

	public HashEntry(String key, String value) 
	{
		this.key = key;
		this.value = value;
		this.next = null;
	}

	@Override
	public String toString() 
	{
		return "[" + key + ", " + value + "]";
	}
}