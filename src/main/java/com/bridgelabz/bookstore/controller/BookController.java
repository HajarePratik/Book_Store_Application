package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.service.IBookService;

@RestController
public class BookController 
{

	@Autowired(required = true)
	private IBookService bookService;
	
	@GetMapping("/getallbooks/{token}")
	public ResponseEntity<ResponseDTO> getAllBooks(@PathVariable String token) {
		ResponseDTO respDTO = bookService.getAllBooks(token);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/getbook/{token}/{id}")
	public ResponseEntity<ResponseDTO> getBook(@PathVariable String token,@PathVariable int id)
	{
		ResponseDTO respDTO = bookService.getBook(token,id);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PostMapping("/addbook/{token}")
	public ResponseEntity<ResponseDTO> addBook(@PathVariable String token,@RequestBody BookDTO userDTO)
	{
		ResponseDTO userData = bookService.addBook(token,userDTO);
		ResponseDTO resDTO = new ResponseDTO("Create User Details Sucessfully :"+userData, userDTO);
		return new ResponseEntity<ResponseDTO>(resDTO,HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{token}/{id}")
	public ResponseEntity<ResponseDTO> updateBook(@PathVariable String token,@PathVariable int id, @RequestBody BookDTO userDTO)
	{
		ResponseDTO respDTO = bookService.updateBook(token,id, userDTO);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebook/{token}/{id}")
	public ResponseEntity<ResponseDTO> deleteBook(@PathVariable String token, @PathVariable int id) {
		ResponseDTO respDTO = bookService.deleteBook(token, id);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatebookprice/{token}/{id}")
	public ResponseEntity<ResponseDTO> updateBookPrice(@PathVariable String token,@PathVariable int id,@RequestParam double price)
	{
		ResponseDTO respDTO = bookService.updateBookPrice(token,id,price);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@PutMapping("/updatebookquantity/{token}/{id}")
	public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable String token,@PathVariable int id,@RequestParam long quantity)
	{
		ResponseDTO respDTO = bookService.updateBookQuantity(token,id,quantity);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@GetMapping("/verifyBook/{token}/{id}")
	public Boolean verifyBook(@PathVariable String token,@PathVariable int id) 
	{
		return bookService.verifyBook(token,id);
	}
	
}
