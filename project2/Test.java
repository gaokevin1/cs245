import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * This is used to test Project 2. This is only preliminary
 * and does not cover all cases.
 *
 */
public class Test 
{
	/**
	 * Tests all sorts. This is NOT a comprehensive test and further tests should be
	 * done to ensure validity. 
	 * 
	 * @param args command line arguments
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void test(ArrayBasedList<Product> plist, String[] args) throws IOException, NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (testExceptions()) 
		{
			//TODO: If using a relatively small sample try uncommenting printTiming
			//      to look at the difference in time between sorts. However, due to
			//      the nature of this method, big samples will take a very long
			//      time.
//			printTiming(plist);
			testAccuracy();
		}
	}
	
	/**
	 * Prints the timing of all sorts in nanoseconds. Does not count the overhead
	 * of rewriting the unsorted list each time.
	 * 
	 * @param orig
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private static void printTiming(ArrayBasedList<Product> orig) throws 
	NoSuchMethodException, SecurityException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException {
		System.out.println("----------------------------------------");
		System.out.println("Timing (Does NOT check for accuracy): ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
			for (Product p: orig) {
				plist.add(p);
			}
			long start = System.nanoTime();
			method.invoke(sortName,plist);
			System.out.println(sortName.getClass().getName()+": "+(System.nanoTime()-start)+" nano seconds");
		}
	}

	/**
	 * Set up for small accuracy test
	 * 
	 * @return filled list
	 */
	private static ArrayBasedList<Product> setUp1() {
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
		plist.add(new Product("123",new int[] {1,2,2}));
		plist.add(new Product("345",new int[] {3,2,3,3,3}));
		plist.add(new Product("4",new int[] {3,3}));
		plist.add(new Product("2AC",new int[] {1,1}));
		plist.add(new Product("BC",new int[] {4,4}));
		plist.add(new Product("CD",new int[] {5,5,5,5}));
		return plist;
	}
	
	/**
	 * Set up medium accuracy test
	 * 
	 * @return filled list
	 */
	private static ArrayBasedList<Product> setUp2() {
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
		plist.add(new Product("F",new int[] {4,4,3,4,4,3,4,4,4,4,4,4,4,3,4}));
		plist.add(new Product("AL",new int[] {2,2,2,2,1,2,2,2}));
		plist.add(new Product("PI",new int[] {2,2,2,2,2,2,2,2}));
		plist.add(new Product("S",new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}));
		plist.add(new Product("A",new int[] {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}));
		plist.add(new Product("IS",new int[] {3,3,3,3,3,2,3,3,3,3,3,3,3,3,3,2,3,3,3,3,3,3,3,3}));
		plist.add(new Product("ID",new int[] {2,2,2,2,1,1,1,2}));
		plist.add(new Product("U",new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4}));
		plist.add(new Product("IL",new int[] {3,3,3,3,3,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}));
		plist.add(new Product("T",new int[] {3,3,3,3,3,3,3,3,2,3,3,3,3,3,3,3,2,3,3,2,3,3,3,3}));
		plist.add(new Product("OUS",new int[] {1,1,1}));
		plist.add(new Product("I",new int[] {4,4,4,3,4,4,4,4,4,4,4,4,4,3,4}));
		plist.add(new Product("P",new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4}));
		plist.add(new Product("CI",new int[] {2,2,1,1,1,1,1,2}));
		plist.add(new Product("EX",new int[] {3,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}));
		plist.add(new Product("C",new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4,4,4}));
		plist.add(new Product("O",new int[] {2,2,1,2,1,1,1,2}));
		plist.add(new Product("E",new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4}));
		plist.add(new Product("G",new int[] {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}));
		plist.add(new Product("R",new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4,4}));
		plist.add(new Product("IC",new int[] {3,3,3,3,3,3,3,3,2,3,3,3,2,3,3,3,3,2,3,3,2,3,3,3}));
		plist.add(new Product("L",new int[] {4,4,4,4,4,4,4,4,4,4,4,4,4,3,4}));
		plist.add(new Product("RA",new int[] {4,4,3,4,4,3,3,4,4,4,4,4,4,3,4}));
		return plist;
	}
	
	/**
	 * Test small and medium size for accuracy
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracy() throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringWriter sw = new StringWriter();
		sw.write("----------------------------------------\n");
		sw.write("Accuracy Test: \n");
		testAccuracyTwo(sw);
		testAccuracySmall(sw);
		testAccuracyMedium(sw);
		System.out.println(sw.toString());
	}
	
	/**
	 * Tests sample size of 2
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracyTwo(StringWriter sw) throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sw.write("-----------\n");
		sw.write("Two Test\n");
		sw.write("-----------\n");
		ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
		plist.add(new Product("CD345",new int[] {5,3,2}));
		plist.add(new Product("AB123",new int[] {5,4}));
		if (testAccuracyFor(plist,new String[] {"AB123","CD345"},sw)) {
			sw.write("Passes two sorting!\n");
		}
	}
	
	/**
	 * Tests small sample size
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracySmall(StringWriter sw) throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sw.write("-----------\n");
		sw.write("Small Test\n");
		sw.write("-----------\n");
		if (testAccuracyFor(setUp1(),new String[] {"CD","BC","4","345","123","2AC"},sw)) {
			sw.write("Passes small sorting!\n");
		}
	}
	
	/**
	 * Tests medium sample size
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void testAccuracyMedium(StringWriter sw) throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sw.write("-----------\n");
		sw.write("Medium Test\n");
		sw.write("-----------\n");
		if (testAccuracyFor(setUp2(),new String[] {"S","U","P","E","R","C","A","L","I","F","RA","G",
				"IL","IS","T","IC","EX","PI","AL","ID","O","CI","OUS"},sw)) {
			sw.write("Passed medium sorting!\n");
		} 
	}
	
	/**
	 * Helper for accuracy tests. Does not count overhead to rewrite the unsorted list
	 * for each sort.
	 * 
	 * @param orig
	 * @param answer
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static boolean testAccuracyFor(ArrayBasedList<Product> orig, String[] answer, StringWriter sw) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean success = true;
		Object[] sortNames = new Object[] { new InsertionSort(),
				new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
			for (Product p: orig) {
				plist.add(p);
			}
			long start = System.nanoTime();
			method.invoke(sortName,plist);
			sw.write(sortName.getClass().getName()+": "+(System.nanoTime()-start)+" nano seconds\n");
			for (int i=0; i<answer.length; i++) {
				try {
					if (!plist.get(i).toString().equals(answer[i])) {
						sw.write("!!! "+sortName.getClass().getName()+".sort does not work\n");
						success = false;
						break;
					}
				} catch (NullPointerException | IndexOutOfBoundsException e) {
					sw.write("!!! "+sortName.getClass().getName()+" test throws an error\n");
					success = false;
					break;
				}
			}
		}
		return success;
	}
	
	/**
	 * Check if sorted and results files are the same. This will 
	 * only work for stable sorts.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void testFile(String[] args) throws IOException {
		String f = args[0];
		System.out.println("----------------------------------------");
		System.out.println("File Test:");
		int index = f.lastIndexOf('.');
		Path result = Paths.get(f.substring(0, index)+"_result"+f.substring(index));
		Path answer = Paths.get(f.substring(0, index)+"_sorted"+f.substring(index));
		
		if (Arrays.equals(Files.readAllBytes(answer), Files.readAllBytes(result))) {
			System.out.println("Result and sorted files match!");
		} else {
			System.out.println("Result and sorted files do NOT match");
			System.out.println("Ignore if using unstable sort.");
		}
	}
	
	/**
	 * Check for Exceptions in specific edge cases
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testExceptions() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		System.out.println("----------------------------------------");
		System.out.println("Exception Test:");
		success &= testEmpty();
		success &= testEmptyRating();
		success &= testOne();
		return success;
	}
	
	/**
	 * Check when there is only one Product in the list
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testOne() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		StringWriter fail = new StringWriter();
		System.out.println("-------------");
		System.out.println("Single Test: ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
			plist.add(new Product("abc",new int[] {1,3}));
			try {
				method.invoke(sortName,plist);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				fail.write(sortName.getClass().getName()+", ");
				success = false;
			}
		}
		if (success) {
			System.out.println("Passes one element test!");
		} else {
			System.out.println("Fail: "+fail.toString());
		}
		return success;
	}
	
	/**
	 * Check when the list is empty
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testEmpty() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		StringWriter fail = new StringWriter();
		System.out.println("-------------");
		System.out.println("Empty Product list Test: ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
			try {
				method.invoke(sortName,plist);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				fail.write(sortName.getClass().getName()+", ");
				success = false;
			}
		}
		if (success) {
			System.out.println("Passes empty Product list test!");
		} else {
			System.out.println("Fail: "+fail.toString());
		}
		return success;
	}
	
	/**
	 * Check if there are Products in list but no ratings
	 * 
	 * @return true if success and false otherwise
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static boolean testEmptyRating() throws NoSuchMethodException, SecurityException {
		boolean success = true;
		StringWriter fail = new StringWriter();
		System.out.println("-------------");
		System.out.println("Empty ratings Test: ");
		Object[] sortNames = new Object[] { new InsertionSort(),
			new QuickSort(),new BucketSort()};
		for (Object sortName: sortNames) {
			Method method = sortName.getClass().getMethod("sort",ArrayBasedList.class);
			ArrayBasedList<Product> plist = new ArrayBasedList<Product>();
			plist.add(new Product("123",new int[] {}));
			plist.add(new Product("234",new int[] {}));
			plist.add(new Product("345",new int[] {}));
			try {
				method.invoke(sortName,plist);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				fail.write(sortName.getClass().getName()+", ");
				success = false;
			}
		}
		if (success) {
			System.out.println("Passes empty ratings test!");
		} else {
			System.out.println("Fail: "+fail.toString());
		}
		return success;
	}
	
}
