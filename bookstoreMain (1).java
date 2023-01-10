/* 
 * PROBLEM STATEMENT : Implement a Book Store Management System using Binary Search Tree, File Organization, and Hash-Maps
 *                     taking into consideration admin perspective and implement ADT of Binary Search Tree.
 *                     
 * NAME              : 1. 404 - Bhairavi Bhuyar
 *                     2. 419 - Bhakti Girase
 *                     3. 421 - Isha Godse
 *
 * DSA LAB           :   MINI PROJECT
 */

//All required packages declared

package bookStoreManagement;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;

class Node // class node
{
	String key; // Data Members of class Node
	Node left, right;

	Node(String item) // Node Parameterized Constructor
	{
		key = item;
		left = null;
		right = null;
	}
}

class Book { // class book
	String bname;
	int bquantity;
	int bprice;
}

class Customer // Customer class
{
	String name; // Data members of customer class
	static int bill_no = 1001;
	long phoneno;
	int book_no;

	Customer(String name, long phoneno, int id_no) // Parameterized Constructor
	{
		this.name = name;
		this.phoneno = phoneno;
	}

}

class BookstoreManagement { // BookstoreManagement class
	Node root;
	Scanner sc = new Scanner(System.in);

	BookstoreManagement() // Default constructor
	{

		root = null;
	}

	void insert(String key) // Function to insert a key
	{

		root = insertRec(root, key);
	}

	Node insertRec(Node root, String key) // Recursive Insert function
	{
		if (root == null) {
			root = new Node(key);
			return root;
		}

		if (key.compareTo(root.key) < 0) // Placed as left child
			root.left = insertRec(root.left, key);
		else if (key.compareTo(root.key) > 0) // Placed as right child
			root.right = insertRec(root.right, key);
		else
			System.out.println("Error!");

		return root;
	}

	public boolean containsNode(String value) // To check if book is present
	{

		return containsNodeRecursive(root, value);
	}

	private boolean containsNodeRecursive(Node current, String key) {
		if (current == null) {
			return false;
		}
		if (key.equalsIgnoreCase(current.key)) {
			return true;
		}
		return key.compareTo(current.key) < 0 ? containsNodeRecursive(current.left, key)
				: containsNodeRecursive(current.right, key);
	}

	void printTree() // To Print Tree
	{

		root = printTreeRec(root, 0);
	}

	Node printTreeRec(Node t, int space) // Recursive Function that returns root of Tree
	{
		if (t == null) {
			return root;
		}
		space += 10; // For space between nodes
		printTreeRec(t.right, space);
		System.out.println();

		for (int i = 5; i < space; i++)
			System.out.print(" ");
		System.out.print("[" + t.key + "]");

		return printTreeRec(t.left, space); // Recursive call of function to Print Tree
	}

	void deleteKey(String key) // Key to be deleted is passed
	{

		root = deleteRec(root, key);
	}

	Node deleteRec(Node root, String key) // Recursive delete function
	{
		if (root == null) {
			return null;
		}

		if (key.compareTo(root.key) < 0)
			root.left = deleteRec(root.left, key);
		else if (key.compareTo(root.key) > 0)
			root.right = deleteRec(root.right, key);
		else {
			if (root.left == null) // node with only one child or no child
				return root.right;
			else if (root.right == null)
				return root.left;

			root.key = minValue(root.right); // Get inorder successor
			root.right = deleteRec(root.right, root.key);
		}
		return root;
	}

	String minValue(Node root) // Function to find inorder successor
	{
		String minv = root.key;
		while (root.left != null) {
			minv = root.left.key;
			root = root.left;
		}
		return minv;
	}

	void printInorder(Node node) // Recursive Inorder Function
	{
		if (node == null)
			return;

		printInorder(node.left);
		System.out.println(node.key);
		printInorder(node.right);
	}

	void printInorder() // To Print Inorder Traversal
	{
		System.out.println();
		printInorder(root);
	}
}

public class bookstoreMain {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		BookstoreManagement tree = new BookstoreManagement();

		HashMap<String, Integer> hashmapping = new HashMap<>();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss"); // Date and Time generator
		Date date = new Date();

		// book name
		FileReader file = new FileReader("C:\\Users\\bhair\\Desktop\\bname.txt");
		BufferedReader reader = new BufferedReader(file);

		// price
		FileReader file2 = new FileReader("C:\\Users\\bhair\\Desktop\\bprice.txt");
		BufferedReader reader2 = new BufferedReader(file2);

		// quantity
		FileReader file3 = new FileReader("C:\\Users\\bhair\\Desktop\\bquantity.txt");
		BufferedReader reader3 = new BufferedReader(file3);

		int i = 0;
		int[][] arr = new int[100][4];
		boolean e1 = false;
		String id1 = "flourish";
		String pwd1 = "alohomora";

		do {
			System.out.println("--------- LOGIN DETAILS ---------");
			System.out.print("\nENTER USERNAME: ");
			String id2 = input.next();
			System.out.print("\nENTER PASSWORD: ");
			String pwd2 = input.next();

			if (id2.equals(id1) && pwd2.equals(pwd1)) {
				System.out.println("\n----- LOGGED IN SUCCESSFULLY -----");
				boolean e2 = false;

				String line;
				while ((line = reader.readLine()) != null) { // To read data line by line by readLine() method

					tree.insert(line);
					hashmapping.put(line, i); // To insert elements in a hash map
					i++;
				}
				int j = i;
				int o = 0;
				String number;
				while ((number = reader2.readLine()) != null) { // Price-reader
					try {
						int result = Integer.parseInt(number);
						if (o != j)
							arr[o][0] = result; // Storing price of book into array
						o++;
					} catch (NumberFormatException ex) {
						// Exception is caught
					}
				}
				int pq = 0;
				String number1;
				while ((number1 = reader3.readLine()) != null) { // Quantity-reader
					try {
						int result1 = Integer.parseInt(number1);
						if (pq != j)
							arr[pq][1] = result1; // Storing quantity of book into array
						pq++;
					} catch (NumberFormatException ex) {
						// Exception is caught
					}
				}

				while (!e2) {

					System.out.print("\n........................................................");
					System.out.println("........................................................");

					System.out.println("\n********** FLOURISH & BLOTTS **********");
					System.out.println("1. GENERATE BILL");
					System.out.println("2. ADD NEW BOOK");
					System.out.println("3. DELETE BOOK");
					System.out.println("4. UPDATE BOOK STOCK");
					System.out.println("5. CHECK AVAILABILITY OF BOOKS");
					System.out.println("6. PRINT INORDER");
					System.out.println("7. PRINT TREE");
					System.out.println("8. LOG-OUT!!");

					System.out.print("\n........................................................");
					System.out.println("........................................................");

					System.out.print("\nENTER YOUR CHOICE: ");
					int ch2 = input.nextInt();
					input.nextLine();

					switch (ch2) {

					case 1: // Generate bill method
						int flag = 0;
						double total = 0;
						double discount = 0.1;
						int c;
						System.out.print("\nENTER CUSTOMER NAME: "); // Accepting customer details
						String custName = input.nextLine();
						System.out.print("ENTER CUSTOMER MOBILE NUMBER: ");
						long mob_no = input.nextLong();
						input.nextLine();

						System.out.print("\nENTER NO. OF BOOKS TO BUY: ");
						int no = input.nextInt();
						input.nextLine();

						Book buybook[] = new Book[no]; // Accepting purchase details
						for (c = 0; c < no; c++) {
							buybook[c] = new Book();
							System.out.print("\nENTER NAME OF BOOK: ");
							buybook[c].bname = input.nextLine();

							System.out.print("ENTER THE QUANTITY OF THE BOOK: ");
							buybook[c].bquantity = input.nextInt();
							input.nextLine();

							boolean bb = tree.containsNode(buybook[c].bname); // Retrieving data from hashmap
							if (bb) {
								int bk = hashmapping.get(buybook[c].bname);
								arr[bk][1] -= buybook[c].bquantity;
								buybook[c].bprice = arr[bk][0];
								total += buybook[c].bprice * buybook[c].bquantity; // Calculating invoice
							}
							if (!bb) {
								System.out.println("\nBOOK NOT AVAILABLE!!");
							}
						}
						if (no >= 3) {
							flag = 1;
							discount = discount * total;
							total = total - discount;
						}

						System.out.println("\n======================== INVOICE ==========================");
						System.out.println("-------------------- FLOURISH & BLOTTS --------------------");
						System.out.println("                           PUNE\n");
						System.out.println(
								"Invoice no. " + Customer.bill_no + "                       " + formatter.format(date));
						Customer.bill_no++;
						System.out.println();
						System.out.println("Name: " + custName);
						System.out.println("Mobile no: " + mob_no);

						System.out.println("\n------------------------------------------------------------");
						System.out.format("%-24s %14s %12s", "      Books", "Quantity", "Price");
						for (int d = 0; d < no; d++) {
							System.out.format("\n%-30s %5s %16s", buybook[d].bname, buybook[d].bquantity,
									buybook[d].bprice + "/-");
						}
						System.out.println("\n------------------------------------------------------------");
						if (flag == 1) {
							System.out.println("\n             YOU HAVE AVAILED 10% DISCOUNT!!");
						}
						System.out.println();
						System.out.format("%-40s %14s", "Total Bill:", total + "/-");
						System.out.println("\n============================================================");
						break;

					case 2: // To add a book
						System.out.print("\nENTER NAME OF BOOK WANT TO ADD: ");
						String name = input.nextLine();
						boolean z1 = tree.containsNode(name);
						if (z1) {
							System.out.println("\nBOOK ALREADY EXISTS !!");
						} else {
							System.out.print("\nENTER THE QUANTITY OF BOOK: ");
							int quantity = input.nextInt();
							System.out.print("\nENTER THE PRICE OF BOOK: ");
							int price = input.nextInt();

							tree.insert(name);
							hashmapping.put(name, i);
							hashmapping.get(name);
							arr[i][1] += quantity;
							arr[i][0] = price;
							i++;
						}
						System.out.println("\nBOOK ADDED SUCCESSFULLY!!");
						break;

					case 3: // To delete a book
						System.out.print("\nENTER THE NAME OF BOOK TO BE DELETED: ");
						String b1 = input.nextLine();
						boolean x = tree.containsNode(b1);
						if (x) {
							tree.deleteKey(b1);
							hashmapping.remove(b1);
							System.out.println("\nBOOK IS DELETED!");
						} else {
							System.out.println("BOOK IS NOT AVAILABLE!!  ");
						}
						break;

					case 4: // To update any book
						System.out.print("\nENTER THE BOOK NAME TO UPDATE: ");
						String b2 = input.nextLine();
						boolean z = tree.containsNode(b2);
						if (z) {
							int a = hashmapping.get(b2);
							System.out.print("\nENTER QUANTITY OF BOOK TO UPDATE: ");
							int q = input.nextInt();
							arr[a][1] += q;
							System.out.println("\nSTOCK UPDATED SUCCESSFULLY!!");
						} else {
							System.out.println("BOOK NOT AVAILABLE!");
						}
						break;

					case 5: // Print Books Details
						Set<Entry<String, Integer>> setOfEntries = hashmapping.entrySet();
						for (Entry<String, Integer> entry : setOfEntries) {
							int r = entry.getValue();
							System.out.println("********************************************************");
							System.out.println("NAME OF BOOK           : " + entry.getKey());
							System.out.println("PRICE OF BOOK          : " + arr[r][0] + "/-");
							System.out.println("TOTAL QUANTITY OF BOOK : " + arr[r][1]);
							System.out.println("********************************************************");
						}
						break;

					case 6:
						tree.printInorder(); // To print inorder traversal
						break;

					case 7:
						tree.printTree(); // To print binary search tree
						break;

					case 8: // Exit condition
						e2 = true;
						e1 = true;
						System.out.println("\nYOU'VE BEEN LOGGED OUT!!");
						break;
					}
				}
			} else if (!id2.equals(id1) && pwd2.equals(pwd1))
				System.out.println("INVALID USERNAME.\n");
			else
				System.out.println("INVALID PASSWORD.\n");

		} while (!e1);

		reader.close();
		reader2.close();
		reader3.close();
		input.close();
	}
}

/*
 * TIME COMPLEXITY :
 * 
 * 1.INSERT FUNCTION : BEST CASE : O(logn) WORST CASE : O(n) 
 * 2.DELETE FUNCTION : BEST CASE : O(logn) WORST CASE : O(n) 
 * 3.DISPLAY INORDER : BEST CASE : O(n)    WORST CASE : O(n) 
 * 4.DISPLAY DETAILS : BEST CASE : O(n)    WORST CASE : O(n)
 * 5.DISPLAY TREE    : BEST CASE : O(n)    WORST CASE : O(n)
 */


/*
 * OUTPUT-
 * 
 * --------- LOGIN DETAILS ---------

ENTER USERNAME: flourish

ENTER PASSWORD: alohomora

----- LOGGED IN SUCCESSFULLY -----

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 1

ENTER CUSTOMER NAME: Ron
ENTER CUSTOMER MOBILE NUMBER: 9854367200

ENTER NO. OF BOOKS TO BUY: 4

ENTER NAME OF BOOK: Harry Potter Book Set
ENTER THE QUANTITY OF THE BOOK: 1

ENTER NAME OF BOOK: Diary of a wimpy kid
ENTER THE QUANTITY OF THE BOOK: 1

ENTER NAME OF BOOK: Ikigai
ENTER THE QUANTITY OF THE BOOK: 2

ENTER NAME OF BOOK: The Monk who sold his Ferrari
ENTER THE QUANTITY OF THE BOOK: 3

======================== INVOICE ==========================
-------------------- FLOURISH & BLOTTS --------------------
                           PUNE

Invoice no. 1001                       13/12/2022  01:39:25

Name: Ron
Mobile no: 9854367200

------------------------------------------------------------
      Books                    Quantity        Price
Harry Potter Book Set              1           2808/-
Diary of a wimpy kid               1            251/-
Ikigai                             2            149/-
The Monk who sold his Ferrari      3            179/-
------------------------------------------------------------

             YOU HAVE AVAILED 10% DISCOUNT!!

Total Bill:                                    3504.6/-
============================================================

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 2

ENTER NAME OF BOOK WANT TO ADD: Midnight Children

ENTER THE QUANTITY OF BOOK: 77

ENTER THE PRICE OF BOOK: 499

BOOK ADDED SUCCESSFULLY!!

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 3

ENTER THE NAME OF BOOK TO BE DELETED: The 5 AM Club

BOOK IS DELETED!

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 4

ENTER THE BOOK NAME TO UPDATE: It Ends with us

ENTER QUANTITY OF BOOK TO UPDATE: 1000

STOCK UPDATED SUCCESSFULLY!!

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 5
********************************************************
NAME OF BOOK           : The Hunger Games
PRICE OF BOOK          : 999/-
TOTAL QUANTITY OF BOOK : 99
********************************************************
********************************************************
NAME OF BOOK           : The Monk who sold his Ferrari
PRICE OF BOOK          : 179/-
TOTAL QUANTITY OF BOOK : 76
********************************************************
********************************************************
NAME OF BOOK           : Diary of a wimpy kid
PRICE OF BOOK          : 251/-
TOTAL QUANTITY OF BOOK : 50
********************************************************
********************************************************
NAME OF BOOK           : The Lord of the Rings
PRICE OF BOOK          : 1059/-
TOTAL QUANTITY OF BOOK : 109
********************************************************
********************************************************
NAME OF BOOK           : The Catcher in the Rye
PRICE OF BOOK          : 439/-
TOTAL QUANTITY OF BOOK : 39
********************************************************
********************************************************
NAME OF BOOK           : The Immortals of Meluha
PRICE OF BOOK          : 649/-
TOTAL QUANTITY OF BOOK : 49
********************************************************
********************************************************
NAME OF BOOK           : Divergent 
PRICE OF BOOK          : 1495/-
TOTAL QUANTITY OF BOOK : 495
********************************************************
********************************************************
NAME OF BOOK           : The Maze Runner
PRICE OF BOOK          : 3216/-
TOTAL QUANTITY OF BOOK : 316
********************************************************
********************************************************
NAME OF BOOK           : Crazy Rich Asians
PRICE OF BOOK          : 278/-
TOTAL QUANTITY OF BOOK : 78
********************************************************
********************************************************
NAME OF BOOK           : It Ends with us
PRICE OF BOOK          : 134/-
TOTAL QUANTITY OF BOOK : 1034
********************************************************
********************************************************
NAME OF BOOK           : Harry Potter Book Set
PRICE OF BOOK          : 2808/-
TOTAL QUANTITY OF BOOK : 279
********************************************************
********************************************************
NAME OF BOOK           : A Good Girls Guide to Murder
PRICE OF BOOK          : 275/-
TOTAL QUANTITY OF BOOK : 75
********************************************************
********************************************************
NAME OF BOOK           : The Alchemist
PRICE OF BOOK          : 169/-
TOTAL QUANTITY OF BOOK : 69
********************************************************
********************************************************
NAME OF BOOK           : Wings of Fire
PRICE OF BOOK          : 140/-
TOTAL QUANTITY OF BOOK : 40
********************************************************
********************************************************
NAME OF BOOK           : Five Feet Apart
PRICE OF BOOK          : 260/-
TOTAL QUANTITY OF BOOK : 60
********************************************************
********************************************************
NAME OF BOOK           : The Secret
PRICE OF BOOK          : 187/-
TOTAL QUANTITY OF BOOK : 87
********************************************************
********************************************************
NAME OF BOOK           : Rich Dad Poor Dad
PRICE OF BOOK          : 139/-
TOTAL QUANTITY OF BOOK : 39
********************************************************
********************************************************
NAME OF BOOK           : Me before You
PRICE OF BOOK          : 297/-
TOTAL QUANTITY OF BOOK : 97
********************************************************
********************************************************
NAME OF BOOK           : Midnight Children
PRICE OF BOOK          : 499/-
TOTAL QUANTITY OF BOOK : 77
********************************************************
********************************************************
NAME OF BOOK           : It Starts with us
PRICE OF BOOK          : 198/-
TOTAL QUANTITY OF BOOK : 98
********************************************************
********************************************************
NAME OF BOOK           : Ikigai
PRICE OF BOOK          : 149/-
TOTAL QUANTITY OF BOOK : 47
********************************************************
********************************************************
NAME OF BOOK           : Pride and Prejudice
PRICE OF BOOK          : 399/-
TOTAL QUANTITY OF BOOK : 49
********************************************************
********************************************************
NAME OF BOOK           : Grandmas Bag of Stories
PRICE OF BOOK          : 200/-
TOTAL QUANTITY OF BOOK : 20
********************************************************
********************************************************
NAME OF BOOK           : The Great Gatsby
PRICE OF BOOK          : 279/-
TOTAL QUANTITY OF BOOK : 79
********************************************************
********************************************************
NAME OF BOOK           : Theory of Everything
PRICE OF BOOK          : 180/-
TOTAL QUANTITY OF BOOK : 18
********************************************************

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 6

A Good Girls Guide to Murder
Crazy Rich Asians
Diary of a wimpy kid
Divergent 
Five Feet Apart
Grandmas Bag of Stories
Harry Potter Book Set
Ikigai
It Ends with us
It Starts with us
Me before You
Midnight Children
Pride and Prejudice
Rich Dad Poor Dad
The Alchemist
The Catcher in the Rye
The Great Gatsby
The Hunger Games
The Immortals of Meluha
The Lord of the Rings
The Maze Runner
The Monk who sold his Ferrari
The Secret
Theory of Everything
Wings of Fire

................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 7

                                             [Wings of Fire]
                                                       [Theory of Everything]
                                   [The Secret]
                                             [The Monk who sold his Ferrari]
                         [The Maze Runner]
               [The Lord of the Rings]
                                   [The Immortals of Meluha]
                         [The Hunger Games]
                                   [The Great Gatsby]
                                             [The Catcher in the Rye]
                                                                           [The Alchemist]
                                                                 [Rich Dad Poor Dad]
                                                       [Pride and Prejudice]
                                                                                               [Midnight Children]
                                                                                     [Me before You]
                                                                           [It Starts with us]
                                                                 [It Ends with us]
                                                                           [Ikigai]
     [Harry Potter Book Set]
                                   [Grandmas Bag of Stories]
                         [Five Feet Apart]
               [Divergent ]
                                   [Diary of a wimpy kid]
                         [Crazy Rich Asians]
                                   [A Good Girls Guide to Murder]
................................................................................................................

********** FLOURISH & BLOTTS **********
1. GENERATE BILL
2. ADD NEW BOOK
3. DELETE BOOK
4. UPDATE BOOK STOCK
5. CHECK AVAILABILITY OF BOOKS
6. PRINT INORDER
7. PRINT TREE
8. LOG-OUT!!

................................................................................................................

ENTER YOUR CHOICE: 8

YOU'VE BEEN LOGGED OUT!!
*/
