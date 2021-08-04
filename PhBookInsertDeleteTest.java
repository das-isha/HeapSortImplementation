package HW6;
//This class was created to test insert, delete, and heapsort methods in the PhBook class
public class PhBookInsertDeleteTest {

	public static void main(String[] args) {
		try {
			//set the size of the phonebook
			PhBook phBook = new PhBook(8);
			System.out.println("To test Insert persons: ");
			phBook.PhBookInsert("Rosy", 111111346);
			phBook.PhBookInsert("Isha", 211111346);
			phBook.PhBookInsert("Sami", 457699999);
			phBook.PhBookInsert("Alie", 213456789);
			phBook.PhBookInsert("Dave", 999999999);
			phBook.PhBookInsert("Jane", 888888888);
			phBook.PhBookInsert("Zain", 666666666);
			phBook.PhBookInsert("Isha", 333222555);
			phBook.printPhBook(phBook.persons);
			
			System.out.println("To test delete a person Dave and ph no 999999999");
		
		    phBook.PhBookDelete("Dave", 999999999);	
			
			phBook.printPhBook(phBook.persons);
			System.out.println("Heap sort testing: ");
			phBook.heapSort(phBook.persons, 0, 6);
			System.out.println("=====================================================");
			phBook.printPhBook(phBook.persons);
			System.out.println("=====================================================");
			
			
		} catch (Exception ex) {
			System.out.print("Please handle the error: " + ex.getMessage());
		}
	}

}
