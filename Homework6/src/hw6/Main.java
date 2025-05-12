package hw6;
import java.util.ArrayList;
import java.util.Scanner;

//Book class
class Book {
	private String title;
	private String author;
	private String isbn;
	private boolean isAvailable;
	
	//book constructor class
	public Book(String title, String author, String isbn, boolean isAvailable) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.isAvailable = true; //Setting a default value for every new Book object.
		
	}
	//Getters
	public String getTitle() {
		return title; 
		}
	public String getAuthor() {
		return author;
	}
	public String getISBN() {
		return isbn;
	}
	public boolean getAvailibility() {
		return isAvailable;
	}
	
	//Method to change an individual book's check out/check in status
	public void checkOutBook() {
		isAvailable = false;
		}
	public void returnBook() {
		isAvailable = true;
	}
	
	//toString() method for displayAllBooks() method below
	public String toString() {
		return "Title: " + title + ". Author: " + author +". ISBN: " + isbn + ". Available: " + isAvailable + ".";
	}
	}

//Library class
class Library {
	//Initializing an ArrayList that will hold all created book objects
	private ArrayList<Book> books = new ArrayList<>();
	
	//ADDING A BOOK TO THE LIBRARY COLLECTION
	public void addBook(Book book) {
		//Print object attribute to confirm arguments are being passed correctly
	    System.out.println("Attempting to add book: " + book.getTitle());
	    
	    for (Book b : books) {
	        if (b.getISBN().equals(book.getISBN())) { 
	        	//Error handling message if book ISBN already exists
	            System.out.println("ERROR: Book already exists in the catalog!");
	            return; //Breaks out of method's loop back to the main loop
	        }
	    }
	    books.add(book);
	    System.out.println("Book successfully added.");
	    //Print library size to confirm object is being added to the collection
	    System.out.println("Current library size: " + books.size()); 
	}

	
	
	//method to check a book out the library collection
	public String checkOutBook(String isbn) {
	    for (Book book : books) {
	        if (book.getISBN().equals(isbn)) {
	            if (book.getAvailibility()) {  // Check if book is available
	                book.checkOutBook();  // Modify book's status
	                System.out.println("Book successfully checked out.");
	                return book.toString(); //Exit method when the book is found
	            } else {
	            	//Error handling statement if book is not found
	                return "ERROR: Book is already checked out!";
	            }	
	        }
	    }
	    //Error statement if book is not in library
	    return "ERROR: Book not found in library.";
	    }

	//Return the book to the library collection by using its ISBN
	public void returnBook(String isbn) {
		boolean found = false; //Initializing a boolean statement to help with error handling if book doesn't exist in library
		
	    for (Book book : books) {
	        if (book.getISBN().equals(isbn)) {
	            if (!book.getAvailibility()) {  // Check if book is available
	                book.returnBook();  // Modify book's availability status
	                System.out.println("Book successfully returned.");
	                found = true; //Change found to true once the book is found
	                return; //Exit method when the book is returned
	            } else {
	            	//Error handling statement if book is not returnable
	                System.out.println("ERROR: Book is already returned!");
	            }	
	        }
	    //Error statement if book is not in library    
	        if (!found) {
	        	System.out.println("ERROR: Book not found in library.");
	        }
	}
	}

	
	//Remove the book from the library collection by using its ISBN
	public void removeBook(String isbn) {
	    boolean removed = books.removeIf(book -> book.getISBN().equals(isbn)); //Boolean statement that identifies if ISBN is in library collection or not
	    
	    if (removed) {
	        System.out.println("Book removed successfully.");
	    } else {
		    //Error statement if book is not in library 
	        System.out.println("ERROR: Book does not exist in the library!");
	    }
	}


	
	//Display all books in the library collection
	public void displayAllBooks() {
		//Error handling statement if no objects are in the ArrayList/library
		if (books.isEmpty()) {
			System.out.println("ERROR: Library is empty!");
		} else {
		System.out.println(books);
	}
	}
	
	//Search the library collection for a book by using its title
	public String searchByTitle(String title) {
		boolean found = false; //Initializing a boolean statement to help with error handling if book doesn't exist in library
		int counter = 0; //Intializing a counter variable for the number of results found
	    //Since books can have the same title, string concatenation being used to fulfill that what-if
		String searchResult = ""; // Initialize an empty string
		
		for (Book book : books) {
			//Make the user input case-insensitive in order to find their query easier
			if (book.getTitle().equalsIgnoreCase(title)) {
				searchResult += book.toString() + "\n"; // Append book details
	            counter++; //Increment counter as more results are found
	            found = true; //Change found to true once the book is found
	        }
		}	
		//Error handling statement if no books are found by searching the title
		if (!found) {
			return "ERROR: Title " + title + " not found!";
		} else {
		return searchResult + counter + " result(s) found.";
		}
	}
	
	//Search the library collection for a book by using its author
	public String searchByAuthor(String author) {
		boolean found = false; //Initializing a boolean statement to help with error handling if book doesn't exist in library
	    int counter = 0; //Intializing a counter variable for the number of results found
	    //Since authors can publish multiple books, string concatenation being used to fulfill that what-if
	    String result = ""; // Initialize an empty string

	    for (Book book : books) {
			//Make the user input case-insensitive in order to find their query easier
	        if (book.getAuthor().equalsIgnoreCase(author)) {
	            counter++; //Increment counter as more results are found
	            found = true; //Change found to true once the book is found
	            result += book.toString() + "\n"; // Append book details
	        }
	    }
		//Error handling statement if no books are found by searching the title
	    if (!found) {
	        return "ERROR: Author \"" + author + "\" not found!";
	    } else {
	    	//Print results
	        return result + counter + " result(s) found.";
	    }
	}

public class LibraryManagementSystem {
	
//Main class
public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	//Create library collection to handle book objects
	Library library = new Library(); 
		boolean run = true; //Initializing the variable that will control the menu system
		
		//User menu for the Library Management System
		while (run) {
			System.out.println("\n----Library Management System----");
			System.out.println("1. Add Book\n2. Remove Book\n3. Display All Books");
			System.out.println("4. Search By Title\n5. Search by Author");
			System.out.println("6. Check Out Book\n7. Return Book\n8. Exit");
			System.out.print("Select and option: ");
			
			//Accepting and processing user's menu input
			 int choice = scan.nextInt(); //Initializing a variable that will accept the user's menu input
	            scan.nextLine(); //Accepts user input
	            
	            switch (choice) {
	            	//Add Book
	                case 1:
	                    System.out.print("Enter title: ");
	                    String title = scan.nextLine();
	                    System.out.print("Enter author: ");
	                    String author = scan.nextLine();
	                    System.out.print("Enter ISBN: ");
	                    String isbn = scan.nextLine();
	                    library.addBook(new Book(title, author, isbn, true));
	                    break;
	                //Remove Book    
	                case 2:
	                    System.out.print("Enter ISBN to remove: ");
	                    isbn = scan.nextLine();
	                    library.removeBook(isbn);
	                    break;
	                //Display All Books    
	                case 3:
	                    library.displayAllBooks();
	                    break;
	                //Search By Title    
	                case 4:
	                    System.out.print("Enter title to search: ");
	                    title = scan.nextLine();
	                    System.out.println(library.searchByTitle(title));
	                    break;
	                //Search By Author    
	                case 5:
	                    System.out.print("Enter author to search: ");
	                    author = scan.nextLine();
	                    System.out.println(library.searchByAuthor(author));
	                    break;
	                //Check Out Book    
	                case 6:
	                    System.out.print("Enter ISBN to check out: ");
	                    isbn = scan.nextLine();
	                    System.out.println(library.checkOutBook(isbn));
	                    break;
	                //Return Book    
	                case 7:
	                    System.out.print("Enter ISBN to return: ");
	                    isbn = scan.nextLine();
	                    library.returnBook(isbn);
	                    break;
	                //Exit    
	                case 8:
	                    System.out.println("Thank you for using the Library Management System. Goodbye.");
	                    scan.close();
	                    run = false; //Stops the main method's while loop
	                    return;
	                //Error handling if user makes an invalid input    
	                default:
	                    System.out.println("ERROR: Invalid choice. Returning to main menu...");
	            }

		}
	}
	}
}

