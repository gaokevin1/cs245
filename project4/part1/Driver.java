public class Driver
{
	public static void main(String[] args) 
	{
		HashTable hashTable = new HashTable();
		
		// Put some key values.
		for(int i = 0; i < 30; i++) 
		{
			final String key = String.valueOf(i);
			hashTable.put(key, key);
		}

		// Print the HashTable structure
		System.out.println("HashTable");
		System.out.println(hashTable.toString());
		System.out.println("\nValue for key(20) : " + hashTable.get("20"));
	}
}