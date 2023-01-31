// Name: Jewel Rahman
// Class: CS211
// Date: 6/2/22
// Instructor: Craig Niiyama
// Description: This is the main class that contains the test methods for the 4 methods in LinkedIntList.java
public class Assignment9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testLastIndexOf();
		testCountDuplicates();
		testHasTwoConsecutive();
		testDeleteBack();
	}
	
	public static void testLastIndexOf() {
		//creating a test list of ints
		int test_list[] = {1, 18, 2, 7, 18, 39, 18, 40};
		
		// making it a linked list object
		LinkedIntList list = new LinkedIntList(test_list);
		
		//calling the method upon the linked list (test case 1)
		System.out.println(LinkedIntList.lastIndexOf(test_list,18));
		
		//calling the method upon the linked list (test case 2)
		System.out.println(LinkedIntList.lastIndexOf(test_list,3));
		
	}
	
	public static void testCountDuplicates() {
		//creating the test list of ints
		int test_list[] = {1, 1, 1, 3, 3, 6, 9, 15, 15, 23, 23, 23, 40, 40};
		
		// making it a linked list object
		LinkedIntList list = new LinkedIntList(test_list);	
		
		//calling the method upon the linked list 
		System.out.println(list.countDuplicates());
	}
	
	public static void testHasTwoConsecutive() {
		//creating the test list of ints (for true condition)
		int test_listT[] = {1, 18, 2, 7, 8, 39, 18, 40};
		
		// making it a linked list object
		LinkedIntList listT = new LinkedIntList(test_listT);
		
		//calling the method upon the linked list and displaying it (true condition)
		System.out.println(listT.hasTwoConsecutive());
		
		//creating the test list of ints (for false condition)
		int test_listF[] = {1, 18, 17, 2, 7, 39, 18, 40, 8};
		
		// making it a linked list object
		LinkedIntList listF = new LinkedIntList(test_listF);
		
		//calling the method upon the linked list and displaying it (false condition)
		System.out.println(listF.hasTwoConsecutive());
	}
	
	public static void testDeleteBack() {
		//creating the test list of ints
		int test_list[] = {1, 18, 2, 7, 8, 39, 18, 40};
		
		// making it a linked list object
		LinkedIntList list = new LinkedIntList(test_list);
		
		//calling the method upon the linked list 
		System.out.println(list.deleteBack());
		System.out.println(list);
		
		//creating an empty list test case to make sure the exception is thrown
		int empty[] = {};
		LinkedIntList emptyList = new LinkedIntList(empty);
		//System.out.println(emptyList.deleteBack());
		
	}
}
