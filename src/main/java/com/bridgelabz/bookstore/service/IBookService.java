package com.bridgelabz.bookstore.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;

@Service
public interface IBookService 
{
	
	ResponseDTO getAllBooks(String token);

	ResponseDTO getBook(String token,int bookid);
	
	ResponseDTO addBook(String token, BookDTO bookDTO);

	ResponseDTO updateBook(String token, int bookid, BookDTO bookDTO);

	ResponseDTO deleteBook(String token, int bookid);

	ResponseDTO updateBookPrice(String token, int bookid, double price);

	ResponseDTO updateBookQuantity(String token, int bookid, long quantity);
	
	Boolean verifyBook(String token,int bookid);
	
}
