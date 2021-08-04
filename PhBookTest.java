package HW6;
import java.util.Arrays;
import java.util.Random;
import HW6.PhBook.Person;

//Tests the running time of my HeapSort implementation to the java inbuilt sort() method for various input array sizes
public class PhBookTest {

	public static void main(String[] args) {
		try {

			//can change the input person array size here for testing
			int size = 100;
			PhBook phBook= new PhBook(size);

			Person[] personArrayForHeap = ArrayInsert(size, phBook);
			Person[] personArrayForJavaF= new Person[size];
			for(int i=0; i< personArrayForHeap.length; i++) {
				personArrayForJavaF[i] = personArrayForHeap[i];
			}

			PhBook phBookUsingJavaArray= new PhBook(size);
			PhBook phBookUsingHeap= new PhBook(size);
			phBookUsingJavaArray.persons = personArrayForHeap;
			phBookUsingHeap.persons = personArrayForJavaF;

			long startTimeUsingHeapSort = System.nanoTime();
			phBook.heapSort(phBookUsingHeap.persons,0,size -1);
			long endTimeUsingHeapSort = System.nanoTime();
			System.out.println("Running-Time using implemented HeapSort for PhBook of array size(" +size+ "): "
					+ (endTimeUsingHeapSort - startTimeUsingHeapSort) + "ms");
			System.out.println("==============================================");

			long startTimeUsingAaraySort = System.nanoTime();
			Arrays.sort(phBookUsingJavaArray.persons);
			long endTimeUsingArraySort = System.nanoTime();
			System.out.println("Running-Time using Java Array Sort for PhBook of array size(" +size+  "):"
					+ (endTimeUsingArraySort - startTimeUsingAaraySort) + "ms");  
			System.out.println("==============================================");

		} catch (Exception ex) {
			System.out.print("Please handle the error: " + ex.getMessage());
		}
	}

	public static Person[] ArrayInsert(int size, PhBook phBook) {
		phBook = new PhBook(size);
		long leftLimit = 1111111111L;
		long rightLimit = 9999999999L;
		int leftLimitForString = 97; // letter 'a'
		int rightLimitString = 122; // letter 'z'
		int targetStringLength1 = 10;
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			// Generate random long number and random string
			long generatedPhoneNo = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			String generatedName = random.ints(leftLimitForString, rightLimitString + 1).limit(targetStringLength1)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			phBook.PhBookInsert(generatedName, generatedPhoneNo);

		}
		return phBook.persons;
	}

}
