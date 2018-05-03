import java.util.AbstractList;
import java.util.Arrays;

/**
 * TODO: Implement your own array based list. For full credit,
 *       do not import ArrayList.
 *
 */
public class ArrayBasedList<Product> extends AbstractList<Product> 
{
	private int initialCapacity = 100;
	private int numElements;
	private Product[] data;
		
	// Constructor
	public ArrayBasedList() 
	{
		data = (Product[]) new Object[initialCapacity];
		numElements = 0;
	}

	// Constructor
	public ArrayBasedList(int size)
	{
		data = (Product[]) new Object[size];
		numElements = 0;
	} 
	
	@Override
	public Product get(int index) 
	{
		if (index < numElements)
		{
			return data[index];
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public int size() 
	{
		return numElements;
	}
	
	@Override
	public boolean add(Product element) 
	{
		if ((data.length - numElements) <= 5)
		{
			createNewSizedArray();
		}

		data[numElements++] = element;

		return true;
	}
	
	@Override
	public void add(int index, Product element) 
	{
		if ((data.length - numElements) <= 5)
		{
			createNewSizedArray();
		}

		System.arraycopy(data, index, data, index + 1, numElements - index);
		data[index] = element;
		numElements++;
	}
	
	@Override
	public Product set(int index, Product element) 
	{
		if (index >= data.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		else
		{
			data[index] = element;
			return element;
		}
	}
	
	@Override
	public Product remove(int index) 
	{
		if (index < numElements)
		{
			Product element = (Product) data[index];
			data[index] = null;
			int temp = index;

			while (temp < numElements)
			{
				data[temp] = data[temp + 1];
				data[temp + 1] = null;
				temp++;
			}

			numElements--;

			return element;
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void createNewSizedArray()
	{
		data = Arrays.copyOf(data, data.length * 2);
	}
}
