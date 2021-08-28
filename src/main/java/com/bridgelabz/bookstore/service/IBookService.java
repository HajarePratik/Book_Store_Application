package com.bridgelabz.bookstore.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;

@Service
public interface IBookService 
{
	
	ResponseDTO getAllBooks(String token);

	ResponseDTO getBook(String token,int id);
	
	ResponseDTO addBook(String token, BookDTO bookDTO);

	ResponseDTO updateBook(String token, int id, BookDTO bookDTO);

	ResponseDTO deleteBook(String token, int id);

	ResponseDTO updateBookPrice(String token, int id, double price);

	ResponseDTO updateBookQuantity(String token, int id, long quantity);
	
}
