/*
 * Author:		Roy Mendez
 * Date:		5/8/2017
 * File name: 	Borrower.java
 * Course:		Computer Science 2 PGCC	
 */

import java.util.ArrayList;

public class Borrower {

	private String name, address, city, state, DOB;
	private int zip, fees, ID;
	private ArrayList <Book> booksBorrowed;
	
	Borrower(){
		name = null;
		address = null;
		city = null;
		state = null;
		DOB = null;
		zip = 0;
		fees = 0;
		ID = 0;
		booksBorrowed = new ArrayList();
	}
	
	Borrower(String n, String a, String c, String s, int z, String birth, int f, int IDnum){
		name = n;
		address = a;
		city = c;
		state = s;
		DOB = birth;
		zip = z;
		fees = f;
		booksBorrowed = new ArrayList();
		ID = IDnum;
	}
	
	public void borrowBook(Book b){
		b.setStock(b.getStock()-1);
		booksBorrowed.add(b);
	}
	
	public void returnBook(Book b){
		b.setStock(b.getStock()+1);
		booksBorrowed.remove(b);
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setAddress(String a){
		address = a;
	}
	
	public void setCity(String c){
		city = c;
	}
	
	public void setState(String s){
		state = s;
	}
	
	public void setDOB(String birth){
		DOB = birth;
	}
	
	public void setZip(int z){
		zip = z;
	}
	
	public void setFees(int f){
		fees = f;
	}
	
	public void setID(int IDnum){
		ID = IDnum;
	}
	
	public void setBooksBorrowed(ArrayList<Book> bb){
		booksBorrowed.addAll(bb);
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public String getDOB(){
		return DOB;
	}
	
	public int getZip(){
		return zip;
	}
	
	public int getFees(){
		return fees;
	}
	
	public int getID(){
		return ID;
	}
	
	public ArrayList<Book> getBooksBorrowed(){
		return booksBorrowed;
	}
	
	public String toString(){
		return "Name: " + name + ", DOB: " + DOB + ", Fees: " + fees + 
				"Address: " + address + ", " + city + ", " + state + ", " + zip;
	}
	
	public int compareTo(Borrower b){
		return this.toString().compareTo(b.toString());
	}
	
	public boolean equals(Borrower b){
		return this.toString().equals(b.toString());
	}
}
