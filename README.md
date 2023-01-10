# Bookstore Management System
Implement a Book Store Management System using Binary Search Tree, File Organization,  Hash-Maps, and Arrays considering the admin perspective, and implement ADT of Binary Search Tree.

BRIEF ABOUT THE PROJECT:

A bookstore management system for the business owner to perform the following functions:
     Generation of bill
     Adding new book titles
     Deleting book titles 
     Updating the quantity of books
     Checking the availability of books

We have taken input through 3 file reader and buffered reader functions 
In the main method, admin login then the files are read and input in hashmap
A menu is displayed to perform the above functions and print functions

To generate the bill, we have taken customer details and stored them in an array and then we take input of books and the bill is displayed and the quantity from the stock is reduced. The purchase details and the total amount to be paid is then displayed on the bill.

For adding a book in the stock, the book name and its respective price is entered by the user and then with the help of the tree insert function and hashmap, the new book data is added to the book list. Simultaneously, the array index for the stock array  is incremented to keep a track of the number of new books added.

For deleting a particular book, The book name is entered by the user. If the book name entered matches the book data present in the tree, then the book is directly deleted by using the tree.removeKey function. and the book data is removed from the hashmap as well.

For updating the current stock of books; the book name is taken as user input, if the book is present in the tree, then the user is asked to enter the required quantity of books. Then the stock is updated by adding the new book in the array

All the book detail entries are stored in the hashmap by using a set of entries. Then the values are printed using the entry.getValue function of hashmap by running a for loop.


DATA STRUCTURE USED ARE:

   Binary Search Tree
        To store the books in a hierarchical way and sorted order
        Make traversal, insertion and deletion faster
        No size limit, can store as many nodes as possible
        Code is comparatively simple

  Hash-Map
        It stores the book names and index in (Key, Value) pairs
        Can access values of a type (e.g. Integer) by an index of another type (e.g. String).
        One object is used as a key (index) to another object (value).  
        Faster access of elements
        Restricts duplicate keys
        Adding and removing elements from a HashMap based on a key takes constant-time.

  Array
        An array is used to store quantity and price of the books and another array to access book details for generation of bill
        Can store multiple data of similar types
        Most familiar data structure

  Other: File Organization
        File reader class in java to read data from text files and return in byte format
        Buffered reader class in java to read data line by line from text files


