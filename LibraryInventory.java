/*
 * Author:		Roy Mendez
 * Date:		5/8/2017
 * File name: 	LibraryInventory.java
 * Course:		Computer Science 2 PGCC	
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryInventory extends JFrame implements ActionListener{
	
	//SAVE FILE ADDRESS	
	private String fileAddress = "C:\\Users\\royme\\OneDrive\\Documents\\PGCC\\Spring2017\\CS2\\libInventory";
	
	
	//instance variables
	private JPanel menu, newBookForm, newBorrowerForm, findBookForm, findBorrowerForm, borrowBookForm, returnBookForm, saveOrOpen;
	private JButton addBook, addBorrower, findBook, findBorrower, borrowBook, returnBook, save, open, showBok, showBor;
	
	private JTextField titleText, authorText, genreText, ISBNText, stockText, aisleText, shelfText;	//newBook stuff
	private JTextField nameText, addressText, cityText, stateText, zipText, DOBText, feesText;		//newBorrower stuff
	private JTextField titleText2, authorText2, genreText2, ISBNText2;								//findBook stuff
	private JTextField IDText, nameText2, DOBText2;													//findBorrower stuff
	private JTextField IDText2, DOBText3, titleText3, authorText3, ISBNText3;						//borrowBook stuff
	private JTextField IDText3, DOBText4, titleText4, authorText4, ISBNText4;						//returnBook stuff
	private JLabel fieldError;
	
	private JPanel newBookResults, newBorrowerResults, findBookResults, findBorrowerResults, borrowBookResults, returnBookResults;
	private JPanel displayBooks, displayBorrowers;
	
	private ArrayList<Book> library = new ArrayList<Book>();
	private ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
	private int IDnum = 10100;
	
	public LibraryInventory(){
		super("Library Inventory");
		
		//
		//WINDOW PROPERTIES
		//
		
		setSize(1000,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//
		//MENU STUFF
		//
		
		menu = new JPanel(new GridLayout(6,1));
		
		addBook = new JButton("Add New Book");
		addBook.addActionListener(this);
		addBorrower = new JButton("Add New Borrower");
		addBorrower.addActionListener(this);
		findBook = new JButton("Find Book");
		findBook.addActionListener(this);
		findBorrower = new JButton("Find Borrower");
		findBorrower.addActionListener(this);
		borrowBook = new JButton("Borrow Book");
		borrowBook.addActionListener(this);
		returnBook = new JButton("Return Book");
		returnBook.addActionListener(this);
		
		menu.add(addBook);
		menu.add(addBorrower);
		menu.add(findBook);
		menu.add(findBorrower);
		menu.add(borrowBook);
		menu.add(returnBook);
		
		add(menu, BorderLayout.WEST);
		
		//
		//Top Menu
		//
		saveOrOpen = new JPanel();
		
		save = new JButton("Save");
		save.addActionListener(this);
		open = new JButton("Open");
		open.addActionListener(this);
		showBok = new JButton("Display All Books");
		showBok.addActionListener(this);
		showBor = new JButton("Display All Borrowers");
		showBor.addActionListener(this);
		
		saveOrOpen.add(save);
		saveOrOpen.add(open);
		saveOrOpen.add(showBok);
		saveOrOpen.add(showBor);
		
		add(saveOrOpen, BorderLayout.NORTH);
		
		//
		//NEW BOOK FORM STUFF
		//
		
		newBookForm = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel title = new JLabel("Title: ");
		titleText = new JTextField("", 20);
		JLabel author = new JLabel("Author: ");
		authorText = new JTextField("", 20);
		JLabel genre = new JLabel("Genre: ");
		genreText = new JTextField("", 20);
		JLabel ISBN = new JLabel("ISBN:");
		ISBNText = new JTextField("", 20);
		JLabel stock = new JLabel("Stock: ");
		stockText = new JTextField("", 20); 
		JLabel loc = new JLabel("Location: ");
		JLabel aisle = new JLabel("Aisle: ");
		aisleText = new JTextField("", 20);
		JLabel shelf = new JLabel("Shelf: ");
		shelfText = new JTextField("", 20);
		JButton addBook = new JButton("Add the Book");
		addBook.addActionListener(this);
		
		newBookForm.add(title, c);
		c.gridy++;
		newBookForm.add(author, c);
		c.gridy++;
		newBookForm.add(genre, c);
		c.gridy++;
		newBookForm.add(ISBN, c);
		c.gridy++;
		newBookForm.add(stock, c);
		c.gridy++;
		newBookForm.add(new JLabel(" "), c);
		c.gridy++;
		newBookForm.add(loc, c);
		c.gridy++;
		newBookForm.add(aisle, c);
		c.gridy++;
		newBookForm.add(shelf,c);
		
		c.gridy = 0; 
		c.gridx++;
		newBookForm.add(new JLabel("        "), c);
		c.gridx++;
		
		newBookForm.add(titleText, c);
		c.gridy++;
		newBookForm.add(authorText, c);
		c.gridy++;
		newBookForm.add(genreText, c);
		c.gridy++;
		newBookForm.add(ISBNText, c);
		c.gridy++;
		newBookForm.add(stockText, c);
		c.gridy+=3;
		newBookForm.add(aisleText, c);
		c.gridy++;
		newBookForm.add(shelfText, c);
		c.gridy++;
		newBookForm.add(addBook, c);

		//
		//NEW BORROWER FORM STUFF
		//
		
		newBorrowerForm = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel name = new JLabel("Name: ");
		nameText = new JTextField("", 20);
		JLabel address = new JLabel("Address: ");
		addressText = new JTextField("", 20);
		JLabel city = new JLabel("City: ");
		cityText = new JTextField("", 20);
		JLabel state = new JLabel("State: ");
		stateText = new JTextField("", 20);
		JLabel zip = new JLabel("Zipcode: ");
		zipText = new JTextField("", 20);
		JLabel DOB = new JLabel("Date of birth (Enter as MM/DD/YYYY): ");
		DOBText = new JTextField("", 20);
		JLabel fees = new JLabel("Fees: ");
		feesText = new JTextField("", 20);
		JButton addBorrower = new JButton("Add the Borrower");
		addBorrower.addActionListener(this);
		
		newBorrowerForm.add(name, c);
		c.gridy++;
		newBorrowerForm.add(address, c);
		c.gridy++;
		newBorrowerForm.add(city, c);
		c.gridy++;
		newBorrowerForm.add(state, c);
		c.gridy++;
		newBorrowerForm.add(zip, c);
		c.gridy++;
		newBorrowerForm.add(DOB, c);
		c.gridy++;
		newBorrowerForm.add(fees, c);
		
		c.gridy = 0;
		c.gridx++;
		
		newBorrowerForm.add(nameText, c);
		c.gridy++;
		newBorrowerForm.add(addressText, c);
		c.gridy++;
		newBorrowerForm.add(cityText, c);
		c.gridy++;
		newBorrowerForm.add(stateText, c);
		c.gridy++;
		newBorrowerForm.add(zipText, c);
		c.gridy++;
		newBorrowerForm.add(DOBText, c);
		c.gridy++;
		newBorrowerForm.add(feesText, c);
		c.gridy++;
		newBorrowerForm.add(addBorrower, c);
		
		//
		//FIND BOOK STUFF
		//
		
		findBookForm = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel title2 = new JLabel("Title: ");
		titleText2 = new JTextField("", 20);
		JLabel author2 = new JLabel("Author: ");
		authorText2 = new JTextField("", 20);
		JLabel genre2 = new JLabel("Genre: ");
		genreText2 = new JTextField("", 20);
		JLabel or = new JLabel("Or");
		JLabel ISBN2 = new JLabel("ISBN:");
		ISBNText2 = new JTextField("", 20);
		JButton findBook = new JButton("Find the Book");
		findBook.addActionListener(this);
		
		findBookForm.add(title2, c);
		c.gridy++;
		findBookForm.add(author2, c);
		c.gridy++;
		findBookForm.add(genre2, c);
		c.gridy++;
		findBookForm.add(or, c);
		c.gridy++;
		findBookForm.add(ISBN2, c);
		
		c.gridx++;
		c.gridy = 0;
		
		findBookForm.add(titleText2, c);
		c.gridy++;
		findBookForm.add(authorText2, c);
		c.gridy++;
		findBookForm.add(genreText2, c);
		c.gridy+=2;
		findBookForm.add(ISBNText2, c);
		c.gridy++;
		findBookForm.add(findBook, c);
		
		//
		//FIND BORROWER
		//
		
		findBorrowerForm = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel ID = new JLabel("Enter ID: ");
		IDText = new JTextField("", 20);
		JLabel or2 = new JLabel("Or");
		JLabel name2 = new JLabel("Name: ");
		nameText2 = new JTextField("", 20);
		JLabel DOB2 = new JLabel("Date of birth (Enter as MM/DD/YYYY): ");
		DOBText2 = new JTextField("", 20);
		JButton findBorrower = new JButton("Find the Borrower");
		findBorrower.addActionListener(this);
		
		findBorrowerForm.add(ID, c);
		c.gridy++;
		findBorrowerForm.add(or2, c);
		c.gridy++;
		findBorrowerForm.add(name2, c);
		c.gridy++;
		findBorrowerForm.add(DOB2, c);
		
		c.gridy = 0;
		c.gridx++;
		findBorrowerForm.add(IDText, c);
		c.gridy+=2;
		findBorrowerForm.add(nameText2, c);
		c.gridy++;
		findBorrowerForm.add(DOBText2, c);
		c.gridy++;
		findBorrowerForm.add(findBorrower, c);
		
		//
		//BORROW BOOK STUFF
		//
		
		borrowBookForm = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel h1 = new JLabel("Borrower Information");
		JLabel ID2 = new JLabel("Enter ID: ");
		IDText2 = new JTextField("", 20);
		JLabel DOB3 = new JLabel("Date of birth (Enter as MM/DD/YYYY): ");
		DOBText3 = new JTextField("", 20);
		JLabel h2 = new JLabel("Book Information");
		JLabel title3 = new JLabel("Title: ");
		titleText3 = new JTextField("", 20);
		JLabel author3 = new JLabel("Author: ");
		authorText3 = new JTextField("", 20);
		JLabel or3 = new JLabel("Or");
		JLabel ISBN3 = new JLabel("ISBN:");
		ISBNText3 = new JTextField("", 20);
		JButton borrowBook = new JButton("Borrow the Book");
		borrowBook.addActionListener(this);
		
		borrowBookForm.add(h1, c);
		c.gridy++;
		borrowBookForm.add(ID2, c);
		c.gridy++;
		borrowBookForm.add(DOB3, c);
		c.gridy++;
		borrowBookForm.add(h2, c);
		c.gridy+=2;
		borrowBookForm.add(title3, c);
		c.gridy++;
		borrowBookForm.add(author3, c);
		c.gridy++;
		borrowBookForm.add(or3, c);
		c.gridy++;
		borrowBookForm.add(ISBN3, c);
		
		c.gridy = 1;
		c.gridx++;
		
		borrowBookForm.add(IDText2, c);
		c.gridy++;
		borrowBookForm.add(DOBText3, c);
		c.gridy+=3;
		borrowBookForm.add(titleText3, c);
		c.gridy++;
		borrowBookForm.add(authorText3, c);
		c.gridy+=2;
		borrowBookForm.add(ISBNText3, c);
		c.gridy++;
		borrowBookForm.add(borrowBook, c);
		
		//
		//RETURN BOOK STUFF
		//
		
		returnBookForm = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel h3 = new JLabel("Borrower Information");
		JLabel ID3 = new JLabel("Enter ID: ");
		IDText3 = new JTextField("", 20);
		JLabel DOB4 = new JLabel("Date of birth (Enter as MM/DD/YYYY): ");
		DOBText4 = new JTextField("", 20);
		JLabel h4 = new JLabel("Book Information");
		JLabel title4 = new JLabel("Title: ");
		titleText4 = new JTextField("", 20);
		JLabel author4 = new JLabel("Author: ");
		authorText4 = new JTextField("", 20);
		JLabel or4 = new JLabel("Or"); 
		JLabel ISBN4 = new JLabel("ISBN:");
		ISBNText4 = new JTextField("", 20);
		JButton returnBook = new JButton("Return the Book");
		returnBook.addActionListener(this);
		
		returnBookForm.add(h3, c);
		c.gridy++;
		returnBookForm.add(ID3, c);
		c.gridy++;
		returnBookForm.add(DOB4, c);
		c.gridy++;
		returnBookForm.add(Box.createHorizontalGlue(), c);
		c.gridy++;
		returnBookForm.add(h4, c);
		c.gridy++;
		returnBookForm.add(title4, c);
		c.gridy++;
		returnBookForm.add(author4, c);
		c.gridy++;
		returnBookForm.add(or4, c);
		c.gridy++;
		returnBookForm.add(ISBN4, c);
		
		c.gridy = 1;
		c.gridx++;
		
		returnBookForm.add(IDText3, c);
		c.gridy++;
		returnBookForm.add(DOBText4, c);
		c.gridy+=3;
		returnBookForm.add(titleText4, c);
		c.gridy++;
		returnBookForm.add(authorText4, c);
		c.gridy+=2;
		returnBookForm.add(ISBNText4, c);
		c.gridy++;
		returnBookForm.add(returnBook, c);
		
		//
		//Initialize Results
		//
		
		newBookResults = new JPanel(new GridBagLayout());
		newBorrowerResults = new JPanel(new GridBagLayout());
		findBookResults = new JPanel(new GridBagLayout());
		findBorrowerResults = new JPanel(new GridBagLayout());
		borrowBookResults = new JPanel(new GridBagLayout());
		returnBookResults = new JPanel(new GridBagLayout());
		
		//
		//Initialize Displays
		//
		displayBooks = new JPanel(new GridBagLayout());
		displayBorrowers = new JPanel(new GridBagLayout());
		
		//TextFieldError label
		
		fieldError = new JLabel("You must fill in all fields unless specified.");
		
	}
	
	public static void main(String[] args){
		LibraryInventory lib = new LibraryInventory();
		lib.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		String actionCmd = e.getActionCommand();
		System.out.println(actionCmd + " was pressed.");
		
		if(actionCmd.equals("Add New Book")){
			clearCenterGUI();
			add(newBookForm, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Add New Borrower")){
			clearCenterGUI();
			add(newBorrowerForm, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Find Book")){
			clearCenterGUI();
			add(findBookForm, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Find Borrower")){
			clearCenterGUI();
			add(findBorrowerForm, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Borrow Book")){
			clearCenterGUI();
			add(borrowBookForm, BorderLayout.CENTER);
			
		}
		
		if(actionCmd.equals("Return Book")){
			clearCenterGUI();
			add(returnBookForm, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Add the Book")){
			if(titleText.getText().equals("") || authorText.getText().equals("") || genreText.getText().equals("") || 
				ISBNText.getText().equals("") || stockText.getText().equals("") || aisleText.getText().equals("") || 
				shelfText.getText().equals(""))
					add(fieldError, BorderLayout.SOUTH);
			else{
				int stock = Integer.parseInt(stockText.getText());
			
				Book b = new Book(titleText.getText(), authorText.getText(), genreText.getText(), 
							ISBNText.getText(), stock, aisleText.getText(), shelfText.getText());
				library.add(b);
				makeNewBookResults(b);
				clearCenterGUI();
				add(newBookResults, BorderLayout.CENTER);
			}
		}
		
		if(actionCmd.equals("Add the Borrower")){
			if(nameText.getText().equals("") || addressText.getText().equals("") || cityText.getText().equals("") || 
					stateText.getText().equals("") || zipText.getText().equals("") || DOBText.getText().equals("") || 
					feesText.getText().equals(""))
						add(fieldError, BorderLayout.SOUTH);
			
			else{
				int zip = Integer.parseInt(zipText.getText());
				int fees = Integer.parseInt(feesText.getText());
				IDnum++;
				
				Borrower b = new Borrower(nameText.getText(), addressText.getText(), cityText.getText(), 
						stateText.getText(), zip, DOBText.getText(), fees, IDnum);
				borrowers.add(b);
				makeNewBorrowerResults(b);
				clearCenterGUI();
				add(newBorrowerResults, BorderLayout.CENTER);
			}
		}
		
		if(actionCmd.equals("Find the Book")){
			Book target = null;
			if(!ISBNText2.getText().equals("")){
				for(int i = 0; i < library.size(); i++){
					if(library.get(i).getISBN().equals(ISBNText2.getText()))
						target = library.get(i);
				}
			}
			else if(!(titleText2.getText().equals("") && authorText2.getText().equals("") && genreText2.getText().equals(""))){
				for(int i = 0; i < library.size(); i++){
					if(library.get(i).getTitle().equals(titleText2.getText()) && 
							library.get(i).getAuthor().equals(authorText2.getText()) && 
							library.get(i).getGenre().equals(genreText2.getText()))
						target = library.get(i);
				}
			}
			else
				add(fieldError, BorderLayout.SOUTH);
			
			makeFindBookResults(target);
			clearCenterGUI();
			add(findBookResults, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Find the Borrower")){
			Borrower target = null;
			if(!IDText.getText().equals("")){
				int id = Integer.parseInt(IDText.getText());
				for(int i = 0; i < borrowers.size(); i++){
					if(borrowers.get(i).getID() == id)
						target = borrowers.get(i);
				}
			}
			else if(!(nameText2.getText().equals("") && DOBText2.getText().equals(""))){
				for(int i = 0; i < borrowers.size(); i++){
					if(borrowers.get(i).getName().equals(nameText2.getText()) && 
							borrowers.get(i).getDOB().equals(DOBText2.getText()))
						target = borrowers.get(i);
				}
			}
			else
				add(fieldError, BorderLayout.SOUTH);
			
			makeFindBorrowerResults(target);
			clearCenterGUI();
			add(findBorrowerResults, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Borrow the Book")){
			Borrower p = null;
			Book s = null;
			
			if(!(IDText2.getText().equals("") && DOBText3.getText().equals(""))){
				int id = Integer.parseInt(IDText2.getText());
				
				for(int i = 0; i < borrowers.size(); i++){
					if(borrowers.get(i).getID() == id && borrowers.get(i).getDOB().equals(DOBText3.getText()))
						p = borrowers.get(i);
				}
			}
			else
				add(fieldError, BorderLayout.NORTH);
			
			if(!ISBNText3.getText().equals("")){
				for(int i = 0; i < library.size(); i++){
					if(library.get(i).getISBN().equals(ISBNText3.getText()))
						s = library.get(i);
				}
			}
			else if(!(titleText3.getText().equals("") && authorText3.getText().equals(""))){
				for(int i = 0; i < library.size(); i++){
					if(library.get(i).getTitle().equals(titleText3.getText()) && 
							library.get(i).getAuthor().equals(authorText3.getText()))
						s = library.get(i);
				}
			}
			else
				add(fieldError, BorderLayout.SOUTH);
			
			makeBorrowBookResults(p, s);
			clearCenterGUI();
			add(borrowBookResults, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Return the Book")){
			Borrower p = null;
			Book s = null;
			
			if(!(IDText3.getText().equals("") && DOBText4.getText().equals(""))){
				int id = Integer.parseInt(IDText3.getText());

				for(int i = 0; i < borrowers.size(); i++){
					if(borrowers.get(i).getID() == id && borrowers.get(i).getDOB().equals(DOBText3.getText()))
						p = borrowers.get(i);
				}
			}
			else
				add(fieldError, BorderLayout.SOUTH);
			
			if(!ISBNText4.getText().equals("")){
				for(int i = 0; i < library.size(); i++){
					if(library.get(i).getISBN().equals(ISBNText4.getText()))
						s = library.get(i);
				}
			}
			else if(!(titleText4.getText().equals("") && authorText4.getText().equals(""))){
				for(int i = 0; i < library.size(); i++){
					if(library.get(i).getTitle().equals(titleText4.getText()) && 
							library.get(i).getAuthor().equals(authorText4.getText()))
						s = library.get(i);
				}
			}
			else
				add(fieldError, BorderLayout.SOUTH);
			
			makeReturnBookResults(p, s);
			clearCenterGUI();
			add(returnBookResults, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Save")){
			PrintWriter outputStream = null;
			ArrayList<Book> borrowedBooks;
			try{
				outputStream = new PrintWriter(new FileOutputStream(fileAddress));
			}
			
			catch (FileNotFoundException error){
				System.out.println("Error file could not be opened or outputed");
				System.exit(0);
			}
			outputStream.println(library.size());
			outputStream.println(borrowers.size());
			
			for(int i = 0; i < library.size(); i++){
				outputStream.println(library.get(i).getTitle());
				outputStream.println(library.get(i).getAuthor());
				outputStream.println(library.get(i).getGenre());
				outputStream.println(library.get(i).getISBN());
				outputStream.println(library.get(i).getStock());
				outputStream.println(library.get(i).getAisle());
				outputStream.println(library.get(i).getShelf());
				System.out.println(library.get(i).getTitle() + " was saved.");
			}
			
			for(int i = 0; i < borrowers.size(); i++){
				outputStream.println(borrowers.get(i).getName());
				outputStream.println(borrowers.get(i).getAddress());
				outputStream.println(borrowers.get(i).getCity());
				outputStream.println(borrowers.get(i).getState());
				outputStream.println(borrowers.get(i).getZip());
				outputStream.println(borrowers.get(i).getDOB());
				outputStream.println(borrowers.get(i).getFees());
				outputStream.println(borrowers.get(i).getID());
				borrowedBooks = borrowers.get(i).getBooksBorrowed();
				outputStream.println(borrowedBooks.size());
				for(int j = 0; j < borrowedBooks.size(); j++){
					outputStream.println(borrowedBooks.get(i).getTitle());
					outputStream.println(borrowedBooks.get(i).getAuthor());
					outputStream.println(borrowedBooks.get(i).getGenre());
					outputStream.println(borrowedBooks.get(i).getISBN());
					outputStream.println(borrowedBooks.get(i).getStock());
					outputStream.println(borrowedBooks.get(i).getAisle());
					outputStream.println(borrowedBooks.get(i).getShelf());
				}
				System.out.println(borrowers.get(i).getName() + " was saved.");
			}
			outputStream.close();
			
		}
		
		if(actionCmd.equals("Open")){
			Scanner inputStream = null;
			int libSize, borSize, bbSize;
			ArrayList<Book> borrowedBooks = new ArrayList();
			borrowers.clear();
			library.clear();
			
			try{
				inputStream = new Scanner(new FileInputStream(fileAddress));
			}
			catch(FileNotFoundException error){
				System.out.println("File not found or could not be opened");
				System.exit(0);
			}
			
			libSize = Integer.parseInt(inputStream.nextLine());
			borSize = Integer.parseInt(inputStream.nextLine());
			
			for(int i = 0; i < libSize; i++){
				Book b = new Book();
				b.setTitle(inputStream.nextLine());
				b.setAuthor(inputStream.nextLine());
				b.setGenre(inputStream.nextLine());
				b.setISBN(inputStream.nextLine());
				b.setStock(Integer.parseInt(inputStream.nextLine()));
				b.setAisle(inputStream.nextLine());
				b.setShelf(inputStream.nextLine());
				library.add(b);
				System.out.println(b.getTitle() + " was retrieved.");
			}
			
			for(int i = 0; i < borSize; i++){
				Borrower b = new Borrower();
				b.setName(inputStream.nextLine());
				b.setAddress(inputStream.nextLine());
				b.setCity(inputStream.nextLine());
				b.setState(inputStream.nextLine());
				b.setZip(Integer.parseInt(inputStream.nextLine()));
				b.setDOB(inputStream.nextLine());
				b.setFees(Integer.parseInt(inputStream.nextLine()));
				b.setID(Integer.parseInt(inputStream.nextLine()));
				bbSize = Integer.parseInt(inputStream.nextLine());
				for(int j = 0; j < bbSize; j++){
					Book bok = new Book();
					bok.setTitle(inputStream.nextLine());
					bok.setAuthor(inputStream.nextLine());
					bok.setGenre(inputStream.nextLine());
					bok.setISBN(inputStream.nextLine());
					bok.setStock(Integer.parseInt(inputStream.nextLine()));
					bok.setAisle(inputStream.nextLine());
					bok.setShelf(inputStream.nextLine());
					borrowedBooks.add(bok);
				}
				b.setBooksBorrowed(borrowedBooks);
				borrowers.add(b);
				System.out.println(b.getName() + " was retrieved.");
			}
			inputStream.close();
		}
		
		if(actionCmd.equals("Display All Books")){
			displayAllBooks();
			clearCenterGUI();
			add(displayBooks, BorderLayout.CENTER);
		}
		
		if(actionCmd.equals("Display All Borrowers")){
			displayAllBorrowers();
			clearCenterGUI();
			add(displayBorrowers, BorderLayout.CENTER);
		}
		
		validate();
		repaint();
		
	}
	
	
	public void clearCenterGUI(){
		remove(newBookForm);
		remove(newBorrowerForm);
		remove(findBookForm);
		remove(findBorrowerForm);
		remove(borrowBookForm);
		remove(returnBookForm);
		remove(newBookResults);
		remove(newBorrowerResults);
		remove(findBookResults);
		remove(findBorrowerResults);
		remove(borrowBookResults);
		remove(returnBookResults);
		remove(fieldError);
		remove(displayBooks);
		remove(displayBorrowers);
	}
	
	public void makeNewBookResults(Book b){
		newBookResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel suc = new JLabel("A new book was successfully added with the following information: ");
		JLabel t = new JLabel("Title: " + b.getTitle());
		JLabel a = new JLabel("Author: " + b.getAuthor());
		JLabel g = new JLabel("Genre: " + b.getGenre());
		JLabel s = new JLabel("Stock: " + b.getStock());
		JLabel isbn = new JLabel("ISBN: " + b.getISBN());
		JLabel aisle = new JLabel("Aisle: " + b.getAisle());
		JLabel shelf = new JLabel("Shelf: " + b.getShelf());
		
		newBookResults.add(suc, c);
		c.gridy++;
		newBookResults.add(t, c);
		c.gridy++;
		newBookResults.add(a, c);
		c.gridy++;
		newBookResults.add(g, c);
		c.gridy++;
		newBookResults.add(s, c);
		c.gridy++;
		newBookResults.add(isbn, c);
		c.gridy++;
		newBookResults.add(aisle, c);
		c.gridy++;
		newBookResults.add(shelf, c);
		
		titleText.setText("");
		authorText.setText("");
		genreText.setText("");
		stockText.setText("");
		ISBNText.setText("");
		aisleText.setText("");
		shelfText.setText("");
	}
	
	public void makeNewBorrowerResults(Borrower b){
		newBorrowerResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel suc = new JLabel("A new borrower was successfully added with the following information: ");
		JLabel n = new JLabel("Name: " + b.getName());
		JLabel a = new JLabel("Address: " + b.getAddress());
		JLabel city = new JLabel("City: " + b.getCity());
		JLabel s = new JLabel("State: " + b.getState());
		JLabel z = new JLabel("Zip code: " + b.getZip());
		JLabel birth = new JLabel("Date of Birth: " + b.getDOB());
		JLabel f = new JLabel("Fees: " + b.getFees());
		JLabel id = new JLabel("ID#: " + b.getID());
		
		newBorrowerResults.add(suc, c);
		c.gridy++;
		newBorrowerResults.add(n, c);
		c.gridy++;
		newBorrowerResults.add(a, c);;
		c.gridy++;
		newBorrowerResults.add(city,  c);
		c.gridy++;
		newBorrowerResults.add(s, c);
		c.gridy++;
		newBorrowerResults.add(z, c);
		c.gridy++;
		newBorrowerResults.add(birth, c);
		c.gridy++;
		newBorrowerResults.add(f, c);
		c.gridy++;
		newBorrowerResults.add(id, c);
		
		nameText.setText("");
		addressText.setText("");
		cityText.setText("");
		stateText.setText("");
		zipText.setText("");
		DOBText.setText("");
		feesText.setText("");
	}
	
	public void makeFindBookResults(Book b){
		findBookResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		if(b == null){
			JLabel notFound = new JLabel("Book could not be found.");
			findBookResults.add(notFound, c);
		}
		else{
			JLabel suc = new JLabel("Book information: ");
			JLabel t = new JLabel("Title: " + b.getTitle());
			JLabel a = new JLabel("Author: " + b.getAuthor());
			JLabel g = new JLabel("Genre: " + b.getGenre());
			JLabel s = new JLabel("Stock: " + b.getStock());
			JLabel isbn = new JLabel("ISBN: " + b.getISBN());
			JLabel aisle = new JLabel("Aisle: " + b.getAisle());
			JLabel shelf = new JLabel("Shelf: " + b.getShelf());
			
			findBookResults.add(suc, c);
			c.gridy++;
			findBookResults.add(t, c);
			c.gridy++;
			findBookResults.add(a, c);
			c.gridy++;
			findBookResults.add(g, c);
			c.gridy++;
			findBookResults.add(s, c);
			c.gridy++;
			findBookResults.add(isbn, c);
			c.gridy++;
			findBookResults.add(aisle, c);
			c.gridy++;
			findBookResults.add(shelf, c);
			
			titleText2.setText("");
			authorText2.setText("");
			genreText2.setText("");
			ISBNText2.setText("");
		}
	}
	
	public void makeFindBorrowerResults(Borrower b){
		findBorrowerResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridx = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		if(b == null){
			JLabel notFound = new JLabel("Borrower could not be found.");
			findBorrowerResults.add(notFound, c);
		}
		
		else{
			JLabel suc = new JLabel("Borrower information: ");
			JLabel n = new JLabel("Name: " + b.getName());
			JLabel a = new JLabel("Address: " + b.getAddress());
			JLabel city = new JLabel("City: " + b.getCity());
			JLabel s = new JLabel("State: " + b.getState());
			JLabel z = new JLabel("Zip code: " + b.getZip());
			JLabel birth = new JLabel("Date of Birth: " + b.getDOB());
			JLabel f = new JLabel("Fees: " + b.getFees());
			JLabel id = new JLabel("ID#: " + b.getID());
			JLabel bb = new JLabel("Borrowed Books:");
			
			findBorrowerResults.add(suc, c);
			c.gridy++;
			findBorrowerResults.add(n, c);
			c.gridy++;
			findBorrowerResults.add(a, c);;
			c.gridy++;
			findBorrowerResults.add(city,  c);
			c.gridy++;
			findBorrowerResults.add(s, c);
			c.gridy++;
			findBorrowerResults.add(z, c);
			c.gridy++;
			findBorrowerResults.add(birth, c);
			c.gridy++;
			findBorrowerResults.add(f, c);
			c.gridy++;
			findBorrowerResults.add(id, c);
			c.gridy++;
			findBorrowerResults.add(bb, c);
			c.gridy++;
			
			JLabel ht, hauthor, hisbn, t, author, isbn;
			ht = new JLabel("Title:");
			ht.setPreferredSize(new Dimension(100, 25));
			hauthor = new JLabel("Author:");
			hauthor.setPreferredSize(new Dimension(100, 25));
			hisbn = new JLabel("ISBN:");
			hisbn.setPreferredSize(new Dimension(100, 25));
			
			findBorrowerResults.add(ht, c);
			c.gridx++;
			findBorrowerResults.add(hauthor, c);
			c.gridx++;
			findBorrowerResults.add(hisbn, c);
			c.gridy++;
			
			for(int i = 0; i < b.getBooksBorrowed().size(); i++){
				c.gridx = 0;
				
				t = new JLabel(b.getBooksBorrowed().get(i).getTitle());
				author = new JLabel(b.getBooksBorrowed().get(i).getAuthor());
				isbn = new JLabel(b.getBooksBorrowed().get(i).getISBN());
				
				findBorrowerResults.add(t, c);
				c.gridx++;
				findBorrowerResults.add(author, c);
				c.gridx++;
				findBorrowerResults.add(isbn, c);
				c.gridy++;
			}
			
			IDText.setText("");
			nameText2.setText("");
			DOBText2.setText("");
		}
	}
	
	public void makeBorrowBookResults(Borrower bor, Book bok){
		borrowBookResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		if(bor == null && bok == null){
			JLabel issue = new JLabel("Borrower and book could not be found.");
			borrowBookResults.add(issue, c);
		}
		else if(bor == null){
			JLabel issue = new JLabel("Borrower could not be found.");
			borrowBookResults.add(issue, c);
		}
		else if(bok == null){
			JLabel issue = new JLabel("Book could not be found.");
			borrowBookResults.add(issue, c);
		}
		else if(bok.getStock() <= 0){
			JLabel issue = new JLabel("This book is no longer in Stock.");
			borrowBookResults.add(issue, c);
		}
		else{
			JLabel suc = new JLabel("Borrower " + bor.getName() + "has successfully borrowed " + bok.getTitle() + ".");
			JLabel h = new JLabel("Borrower information: ");
			JLabel n = new JLabel("Name: " + bor.getName());
			JLabel a = new JLabel("Address: " + bor.getAddress());
			JLabel city = new JLabel("City: " + bor.getCity());
			JLabel s = new JLabel("State: " + bor.getState());
			JLabel z = new JLabel("Zip code: " + bor.getZip());
			JLabel birth = new JLabel("Date of Birth: " + bor.getDOB());
			JLabel f = new JLabel("Fees: " + bor.getFees());
			JLabel id = new JLabel("ID#: " + bor.getID());
			JLabel h2 = new JLabel("Book information: ");
			JLabel t = new JLabel("Title: " + bok.getTitle());
			JLabel auth = new JLabel("Author: " + bok.getAuthor());
			JLabel g = new JLabel("Genre: " + bok.getGenre());
			JLabel stk = new JLabel("Stock: " + bok.getStock());
			JLabel isbn = new JLabel("ISBN: " + bok.getISBN());
			JLabel aisle = new JLabel("Aisle: " + bok.getAisle());
			JLabel shelf = new JLabel("Shelf: " + bok.getShelf());
			
			borrowBookResults.add(suc);
			c.gridy++;
			borrowBookResults.add(h, c);
			c.gridy++;
			borrowBookResults.add(n, c);
			c.gridy++;
			borrowBookResults.add(a, c);;
			c.gridy++;
			borrowBookResults.add(city,  c);
			c.gridy++;
			borrowBookResults.add(s, c);
			c.gridy++;
			borrowBookResults.add(z, c);
			c.gridy++;
			borrowBookResults.add(birth, c);
			c.gridy++;
			borrowBookResults.add(f, c);
			c.gridy++;
			borrowBookResults.add(id, c);
			
			c.gridx++;
			c.gridy = 1;
			
			borrowBookResults.add(h2, c);
			c.gridy++;
			borrowBookResults.add(t, c);
			c.gridy++;
			borrowBookResults.add(auth, c);;
			c.gridy++;
			borrowBookResults.add(g,  c);
			c.gridy++;
			borrowBookResults.add(stk, c);
			c.gridy++;
			borrowBookResults.add(isbn, c);
			c.gridy++;
			borrowBookResults.add(aisle, c);
			c.gridy++;
			borrowBookResults.add(shelf, c);
			
			bor.borrowBook(bok);
			
			IDText2.setText("");
			DOBText3.setText("");
			titleText3.setText("");
			authorText3.setText(""); 
			ISBNText3.setText("");
		}
	}
	
	public void makeReturnBookResults(Borrower bor, Book bok){
		returnBookResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		if(bor == null && bok == null){
			JLabel issue = new JLabel("Borrower and book could not be found.");
			returnBookResults.add(issue, c);
		}
		else if(bor == null){
			JLabel issue = new JLabel("Borrower could not be found.");
			returnBookResults.add(issue, c);
		}
		else if(bok == null){
			JLabel issue = new JLabel("Book could not be found.");
			returnBookResults.add(issue, c);
		}
		else{
			JLabel suc = new JLabel("Borrower " + bor.getName() + "has successfully returned " + bok.getTitle() + ".");
			JLabel h = new JLabel("Borrower information: ");
			JLabel n = new JLabel("Name: " + bor.getName());
			JLabel a = new JLabel("Address: " + bor.getAddress());
			JLabel city = new JLabel("City: " + bor.getCity());
			JLabel s = new JLabel("State: " + bor.getState());
			JLabel z = new JLabel("Zip code: " + bor.getZip());
			JLabel birth = new JLabel("Date of Birth: " + bor.getDOB());
			JLabel f = new JLabel("Fees: " + bor.getFees());
			JLabel id = new JLabel("ID#: " + bor.getID());
			JLabel h2 = new JLabel("Book information: ");
			JLabel t = new JLabel("Title: " + bok.getTitle());
			JLabel auth = new JLabel("Author: " + bok.getAuthor());
			JLabel g = new JLabel("Genre: " + bok.getGenre());
			JLabel stk = new JLabel("Stock: " + bok.getStock());
			JLabel isbn = new JLabel("ISBN: " + bok.getISBN());
			JLabel aisle = new JLabel("Aisle: " + bok.getAisle());
			JLabel shelf = new JLabel("Shelf: " + bok.getShelf());
			
			returnBookResults.add(suc);
			c.gridy++;
			returnBookResults.add(h, c);
			c.gridy++;
			returnBookResults.add(n, c);
			c.gridy++;
			returnBookResults.add(a, c);;
			c.gridy++;
			returnBookResults.add(city,  c);
			c.gridy++;
			returnBookResults.add(s, c);
			c.gridy++;
			returnBookResults.add(z, c);
			c.gridy++;
			returnBookResults.add(birth, c);
			c.gridy++;
			returnBookResults.add(f, c);
			c.gridy++;
			returnBookResults.add(id, c);
			
			c.gridx++;
			c.gridy = 1;
			
			returnBookResults.add(h2, c);
			c.gridy++;
			returnBookResults.add(t, c);
			c.gridy++;
			returnBookResults.add(auth, c);;
			c.gridy++;
			returnBookResults.add(g,  c);
			c.gridy++;
			returnBookResults.add(stk, c);
			c.gridy++;
			returnBookResults.add(isbn, c);
			c.gridy++;
			returnBookResults.add(aisle, c);
			c.gridy++;
			returnBookResults.add(shelf, c);
			
			bor.returnBook(bok);
			
			IDText3.setText("");
			DOBText4.setText("");
			titleText4.setText("");
			authorText4.setText(""); 
			ISBNText4.setText("");
		}
	}
	
	public void displayAllBooks(){
		displayBooks.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel t, a, g, isbn, s, aisle, shelf;
		
		JLabel ht = new JLabel("Title:");
		ht.setPreferredSize(new Dimension(100, 25));
		JLabel ha = new JLabel("Author:");
		ha.setPreferredSize(new Dimension(100, 25));
		JLabel hg = new JLabel("Genre:");
		hg.setPreferredSize(new Dimension(100, 25));
		JLabel hisbn = new JLabel("ISBN:");
		hisbn.setPreferredSize(new Dimension(120, 25));
		JLabel hs = new JLabel("Stock:");
		hs.setPreferredSize(new Dimension(100, 25));
		JLabel haisle = new JLabel("Aisle:");
		haisle.setPreferredSize(new Dimension(100, 25));
		JLabel hshelf = new JLabel("Stock:");
		hshelf.setPreferredSize(new Dimension(100, 25));
		
		displayBooks.add(ht, c);
		c.gridx++;
		displayBooks.add(ha, c);
		c.gridx++;
		displayBooks.add(hg, c);
		c.gridx++;
		displayBooks.add(hisbn, c);
		c.gridx++;
		displayBooks.add(hs, c);
		c.gridx++;
		displayBooks.add(haisle, c);
		c.gridx++;
		displayBooks.add(hshelf, c);
		c.gridy++;
		
		for(int i = 0; i < library.size(); i++){
			c.gridx = 0;
			t = new JLabel(library.get(i).getTitle());
			a = new JLabel(library.get(i).getAuthor());
			g = new JLabel(library.get(i).getGenre());
			isbn = new JLabel(library.get(i).getISBN());
			s = new JLabel(library.get(i).getStock() + "");
			aisle = new JLabel(library.get(i).getAisle());
			shelf = new JLabel(library.get(i).getShelf());
			displayBooks.add(t, c);
			c.gridx++;
			displayBooks.add(a, c);
			c.gridx++;
			displayBooks.add(g, c);
			c.gridx++;
			displayBooks.add(isbn, c);
			c.gridx++;
			displayBooks.add(s, c);
			c.gridx++;
			displayBooks.add(aisle, c);
			c.gridx++;
			displayBooks.add(shelf, c);
			c.gridy++;
		}
	}
	
	public void displayAllBorrowers(){
		displayBorrowers.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel n, a, city, s, z, b, f, ID;
		
		JLabel hn = new JLabel("Name:");
		hn.setPreferredSize(new Dimension(100, 25));
		JLabel ha = new JLabel("Address:");
		ha.setPreferredSize(new Dimension(100, 25));
		JLabel hcity = new JLabel("City:");
		hcity.setPreferredSize(new Dimension(100, 25));
		JLabel hs = new JLabel("State:");
		hs.setPreferredSize(new Dimension(100, 25));
		JLabel hz = new JLabel("Zip code:");
		hz.setPreferredSize(new Dimension(100, 25));
		JLabel hb = new JLabel("Date of birth:");
		hb.setPreferredSize(new Dimension(100, 25));
		JLabel hf = new JLabel("Fees:");
		hf.setPreferredSize(new Dimension(100, 25));
		JLabel hID = new JLabel("ID #:");
		hID.setPreferredSize(new Dimension(100, 25));
		
		displayBorrowers.add(hn, c);
		c.gridx++;
		displayBorrowers.add(ha, c);
		c.gridx++;
		displayBorrowers.add(hcity, c);
		c.gridx++;
		displayBorrowers.add(hs, c);
		c.gridx++;
		displayBorrowers.add(hz, c);
		c.gridx++;
		displayBorrowers.add(hb, c);
		c.gridx++;
		displayBorrowers.add(hf, c);
		c.gridx++;
		displayBorrowers.add(hID, c);
		c.gridy++;
		
		for(int i = 0; i < borrowers.size(); i++){
			c.gridx = 0;
			n = new JLabel(borrowers.get(i).getName());
			a = new JLabel(borrowers.get(i).getAddress());
			city = new JLabel(borrowers.get(i).getCity());
			s = new JLabel(borrowers.get(i).getState());
			z = new JLabel(""+borrowers.get(i).getZip());
			b = new JLabel(borrowers.get(i).getDOB());
			f = new JLabel(borrowers.get(i).getFees() + "");
			ID = new JLabel(borrowers.get(i).getID() + "");
			displayBorrowers.add(n, c);
			c.gridx++;
			displayBorrowers.add(a, c);
			c.gridx++;
			displayBorrowers.add(city, c);
			c.gridx++;
			displayBorrowers.add(s, c);
			c.gridx++;
			displayBorrowers.add(z, c);
			c.gridx++;
			displayBorrowers.add(b, c);
			c.gridx++;
			displayBorrowers.add(f, c);
			c.gridx++;
			displayBorrowers.add(ID, c);
			c.gridy++;
		}
	}
}
