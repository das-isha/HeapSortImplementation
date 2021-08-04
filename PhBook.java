package HW6;

public class PhBook {

	Person[] persons;
	private int maxItems; // Capacity
	private int numItems; // Number of elements

	class Person implements Comparable<Person> {
		private String name;
		private long phoneNumber;

		public Person(String name, long phoneNumber) {
			this.name = name;
			this.phoneNumber = phoneNumber;
		}

		public String toString() {
			return "Name: " + this.name + ", Phone Number: " + this.phoneNumber;
		}

		// compareto method compares persons by their name first and, for persons with
		// the same name, by their phone number.
		@Override
		public int compareTo(Person person) {
			int nameComparision = this.name.compareTo(person.name);
			if (nameComparision != 0) {
				return nameComparision;
			} else if (this.phoneNumber < person.phoneNumber) {
				return -1;
			} else if (this.phoneNumber > person.phoneNumber) {
				return 1;
			} else
				return 0;
		}
	}

	// constructor of the PhBook class
	public PhBook(int maxItems) {
		this.maxItems = maxItems;
		this.numItems = 0;
		persons = new Person[maxItems];
	}

	// Function to return the index of the
	// parent node of a given node
	public int parent(int i) {
		// if i is already a root node
		if (i == 0) {
			return 0;
		}

		return (i - 1) / 2;
	}

	// Function to return the index of the
	// left child of the given node
	public int leftChild(int i) {
		return ((2 * i) + 1);
	}

	// Function to return the index of the
	// right child of the given node
	public int rightChild(int i) {
		return ((2 * i) + 2);
	}
	
	// this method sifts a node of index i down in the heap
	public void siftDown(int i) { 
		Person toSift = persons[i];
		int cursor = i;
		int child = 2 * i + 1; // Child to compare with & starts with left child
		while (child < numItems) {
			// If the right child is bigger than the left one, use the right child instead.
			if (child + 1 < numItems && persons[child].compareTo(persons[child + 1]) < 0) 
				child = child + 1; // take the right child
			if (toSift.compareTo(persons[child]) >= 0)
				break; 
			// Sift down one level in the tree.
			persons[cursor] = persons[child];
			persons[child] = toSift;
			cursor = child;
			child = 2 * cursor + 1;
		}
		persons[cursor] = toSift;
	}

	// Function to get value of
	// the current maximum element
	public Person getMax() {
		return persons[0];
	}

	public Person removeMax() {
		Person toRemove = persons[0];
		persons[0] = persons[numItems - 1];
		numItems--;
		siftDown(0);
		return toRemove;
	}

	// Function to shift up the node in order to maintain the heap property
	public void siftUp(int index) {

		int parentIdx = (index - 1) / 2;
		while (index > 0 && persons[parentIdx].compareTo(persons[index]) < 0) {
			swap(parentIdx, index);
			index = parentIdx;
		}
	}

	public void swap(int a, int b) {
		Person temp = persons[a];
		persons[a] = persons[b];
		persons[b] = temp;

	}

	//returns true if the new person has been added 
	//or false if the phone book was already at capacity 
	public boolean PhBookInsert(String name, long phoneNumber) {
		Person person = new Person(name, phoneNumber);
		if (numItems == maxItems) {
			return false;
		}
		persons[numItems] = person;
		numItems++;
		return true;
	}

	// removes one object with matching attributes from the phone book 
	// & if there is no object with these attributes then the phone book remains unmodified
	public void PhBookDelete(String name, long phoneNumber) {
		Person personToRemove = new Person(name, phoneNumber);
		numItems = persons.length;
		for (int i = 0; i < persons.length; i++) {
			if (personToRemove.compareTo(persons[i]) == 0) {
				swap(i, numItems - 1);
				persons[numItems - 1] = null;
				numItems--;
				return;
			}
		}

	}

	//helper method for the heapSort method
	public void buildHeap(Person[] a, int numItems) {
		int i = (numItems - 2) / 2;
		for (; i >= 0; i--) {
			siftDown(i);
		}
	}

	//heapSort method that has a worst, best, and average time complexity of O(nlogn) where n is the number of person nodes
	public void heapSort(Person[] arr, int startIndex, int endIndex) {
		numItems = startIndex + endIndex + 1;
		persons = arr;
		buildHeap(persons, numItems);
		int endUnsorted = numItems - 1;
		while (endUnsorted > 0) {
			Person largestRemaining = removeMax();
			arr[endUnsorted] = largestRemaining;
			endUnsorted--;
		}
	}

	// prints the phonebook
	public void printPhBook(Person[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (persons[i] != null) {
				System.out.println(persons[i].toString() + " ");
			}

		}
		System.out.println();
	}
}