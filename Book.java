/*
 * Author:		Roy Mendez
 * Date:		5/8/2017
 * File name: 	Book.java
 * Course:		Computer Science 2 PGCC	
 */

public class Book {
	private String title, author, genre, ISBN, aisle, shelf;
	private int stock;
	
	Book(){
		title = null;
		author = null;
		genre = null;
		stock = 0;
		ISBN = null;
	}
	
	Book(String t, String a, String g, String isbn, int s, String ais, String sh){
		title = t;
		author = a;
		genre = g;
		stock = s;
		ISBN = isbn;
		aisle = ais;
		shelf = sh;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setAuthor(String a){
		author = a;
	}
	
	public void setGenre(String g){
		genre = g;
	}
	
	public void setStock(int s){
		stock = s;
	}
	
	public void setISBN(String isbn){
		ISBN = isbn;
	}
	
	public void setAisle(String as){
		aisle = as;
	}
	
	public void setShelf(String sh){
		shelf = sh;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public int getStock(){
		return stock;
	}
	
	public String getISBN(){
		return ISBN;
	}
	
	public String getAisle(){
		return aisle;
	}
	
	public String getShelf(){
		return shelf;
	}
	
	public boolean equals(Book b){
		if(ISBN.equals(b.getISBN()) || title.equals(b.getTitle()) && author.equals(b.getAuthor()) && 
				genre.equals(b.getGenre()))
			return true;
		else
			return false;
	}
	
	public String toString(){
		return "Title: " + title + ", Author: " + author + ", Genre: " + genre + 
				", In Stock: " + ", ISBN:" + ISBN + ", Aisle: " + aisle + ", Shelf: " + shelf;
	}
	
	public int CompareTo(Book b){
		return this.toString().compareTo(b.toString());
	}
}
