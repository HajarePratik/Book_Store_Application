package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstore.dto.BookDTO;

import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class BookModel 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookId;
	
	private String bookName;
	private String bookAuthor;
	private String bookDescription;
	private String bookLogo;
	private double bookPrice;
	private long bookQuantity;
	
	public BookModel() {
	
	}
	
	public BookModel(int bookId,BookDTO bookDTO) 
	{
		
		this.bookName = bookDTO.getBookName();
		this.bookAuthor = bookDTO.getBookAuthor();
		this.bookDescription = bookDTO.getBookDescription();
		this.bookLogo = bookDTO.getBookLogo();
		this.bookPrice = bookDTO.getBookPrice();
		this.bookQuantity = bookDTO.getBookQuantity();

	}

	
	
}
