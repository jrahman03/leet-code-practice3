// Name: Jewel Rahman
// Class: CS211
// Date: 6/2/22
// Instructor: Craig Niiyama
// Description: This class contains lastIndexOf, countDuplicates, hasTwoConsecutive, and deleteBack
//lastIndexOf accepts an integer value as a parameter and that returns the index in the list of the last occurrence of that value, or -1 if the value is not found in the list
//countDuplicates returns the number of duplicates in a sorted list. 
//hasTwoConsecutive returns whether or not a list of integers has two adjacent numbers that are consecutive integers (true if such a pair exists and false otherwise). 
//deleteBack deletes the last value (the value at the back of the list) and returns the deleted value. 
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;
// A LinkedIntList object can be used to store a list of integers.
public class LinkedIntList {
    private ListNode front;   // node holding first value in list (null if empty)
    private String name = "front";   // string to print for front of list
    
    // Constructs an empty list.
    public LinkedIntList() {
        front = null;
    }
    
    // Constructs a list containing the given elements.
    // For quick initialization via Practice-It test cases.
    public LinkedIntList(int... elements) {
        this("front", elements);
    }
    
    public LinkedIntList(String name, int... elements) {
        this.name = name;
        if (elements.length > 0) {
            front = new ListNode(elements[0]);
            ListNode current = front;
            for (int i = 1; i < elements.length; i++) {
                current.next = new ListNode(elements[i]);
                current = current.next;
            }
        }
    }
    
    // Constructs a list containing the given front node.
    // For quick initialization via Practice-It ListNode test cases.
    private LinkedIntList(String name, ListNode front) {
        this.name  = name;
        this.front = front;
    }
    
    // Appends the given value to the end of the list.
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            } 
            current.next = new ListNode(value);
        }
    }
    
    // Inserts the given value at the given index in the list.
    // Precondition: 0 <= index <= size
    public void add(int index, int value) {
        if (index == 0) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            } 
            current.next = new ListNode(value, current.next);
        }
    }
    
    public boolean equals(Object o) {
        if (o instanceof LinkedIntList) {
            LinkedIntList other = (LinkedIntList) o;
            return toString().equals(other.toString());   // hackish
        } else {
            return false;
        }
    }
    
    // Returns the integer at the given index in the list.
    // Precondition: 0 <= index < size
    public int get(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    // Removes the value at the given index from the list.
    // Precondition: 0 <= index < size
    public void remove(int index) {
        if (index == 0) {
            front = front.next;
        } else {
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }
    
    // Returns the number of elements in the list.
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    // Returns a text representation of the list, giving
    // indications as to the nodes and link structure of the list.
    // Detects student bugs where the student has inserted a cycle
    // into the list.
    public String toFormattedString() {
        ListNode.clearCycleData();
        
        String result = this.name;
        
        ListNode current = front;
        boolean cycle = false;
        while (current != null) {
            result += " -> [" + current.data + "]";
            if (current.cycle) {
                result += " (cycle!)";
                cycle = true;
                break;
            }
            current = current.__gotoNext();
        }

        if (!cycle) {
            result += " /";
        }
        
        return result;
    }
    
    // Returns a text representation of the list.
    public String toString() {
        return toFormattedString();
    }
    
    // Returns a shorter, more "java.util.LinkedList"-like text representation of the list.
    public String toStringShort() {
        ListNode.clearCycleData();
        
        String result = "[";
        
        ListNode current = front;
        boolean cycle = false;
        while (current != null) {
            if (result.length() > 1) {
				result += ", ";
			}
            result += current.data;
            if (current.cycle) {
                result += " (cycle!)";
                cycle = true;
                break;
            }
            current = current.__gotoNext();
        }

        if (!cycle) {
            result += "]";
        }
        
        return result;
    }
    

    // ListNode is a class for storing a single node of a linked list.  This
    // node class is for a list of integer values.
    // Most of the icky code is related to the task of figuring out
    // if the student has accidentally created a cycle by pointing a later part of the list back to an earlier part.

    public static class ListNode {
        private static final List<ListNode> ALL_NODES = new ArrayList<ListNode>();
        
        public static void clearCycleData() {
            for (ListNode node : ALL_NODES) {
                node.visited = false;
                node.cycle = false;
            }
        }
        
        public int data;          // data stored in this node
        public ListNode next;     // link to next node in the list
        public boolean visited;   // has this node been seen yet?
        public boolean cycle;     // is there a cycle at this node?

        // post: constructs a node with data 0 and null link
        public ListNode() {
            this(0, null);
        }

        // post: constructs a node with given data and null link
        public ListNode(int data) {
            this(data, null);
        }

        // post: constructs a node with given data and given link
        public ListNode(int data, ListNode next) {
            ALL_NODES.add(this);
            this.data = data;
            this.next = next;
            this.visited = false;
            this.cycle = false;
        }
        
        public ListNode __gotoNext() {
            return __gotoNext(true);
        }
        
        public ListNode __gotoNext(boolean checkForCycle) {
            if (checkForCycle) {
                visited = true;
                
                if (next != null) {
                    if (next.visited) {
                        // throw new IllegalStateException("cycle detected in list");
                        next.cycle = true;
                    }
                    next.visited = true;
                }
            }
            return next;
        }
    }

// YOUR CODE GOES HERE
    //pre: int value > 0, testvals >  0
    //post: returns the index in the list of the last occurrence of that value, or -1 if the value is not found in the list
    public static int lastIndexOf(int[] testvals, int value) {
    	//for loop iterating through the test vals
        for (int i = testvals.length - 1; i >= 0; i--) {
        	//if the the given value at index i = desired value
            if (testvals[i] == value) {
            	//return i 
                return i;
            }
        }
        // or return -1 if value isnt found in the list
        return -1;
    }
    //pre: front != null
    //post: returns the number of duplicates in a sorted list. 
    public int countDuplicates() {
        int dup_total = 0;
        ListNode current = front;
        //returning 0 if the front of the list is empty
        if (front == null) {
            return 0;
        }
        //while not empty
        while (current.next != null) {
        	// if current = the next node
            if (current.data == current.next.data) {
            	//adding to the duplicate total
                dup_total++;
            }
            current = current.next;
        }
        //returning the duplicate total
        return dup_total;
    }
    //pre: current.next != null
    //post:returns whether or not a list of integers has two adjacent numbers that are consecutive integers (true if such a pair exists and false otherwise). 
    public boolean hasTwoConsecutive() {
        ListNode current = front;
        //if front is empty
        if (front == null) {
        	//return false
            return false;
        } else {
        	//while list isnt empty
            while (current.next != null) {
            	//if statement judging whether its consecutive (equals)
                if (current.data + 1 == current.next.data) {
                	//returning true if consecutive 
                    return true;
                }
                current = current.next;
            }
            //base case (return false)
            return false;
        }
    }
    //pre: current.next == null
    //post: deletes the last value (the value at the back of the list) and returns the deleted value. 
    public int deleteBack() {
        ListNode current = front;
        
        //if current is empty 
        if (current == null) {
        	//throwing NO Such Element Exception
            throw new NoSuchElementException("YOUR LIST IS EMPTY");
          //otherwise if the next node is empty
        } else if (current.next == null) {
            int removed_val = current.data;
            
            front = null;
            //returning the removed val
            return removed_val;
            
        } else {
        	//while the next nodes arent empty
            while (current.next.next != null) {
                current = current.next;
            }
            // putting the current value into a variable 
            int removed_val = current.next.data;
            //deleting the value from the list
            current.next = null;
            //returning the removed val
            return removed_val;
        }
    }
    

}
