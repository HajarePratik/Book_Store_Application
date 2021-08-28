package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.repository.BookRepository;

@Service
public class BookService implements IBookService{

	@Autowired
	BookRepository bookRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ResponseDTO getAllBooks(String token) 
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			List<BookModel> bookList = bookRepository.findAll();
			return new ResponseDTO("List of all books ",bookList);
		}
		else 
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	@Override
	public ResponseDTO getBook(String token, int id)
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			Optional<BookModel> isUserPresent = bookRepository.findById(id);
			return new ResponseDTO("User of id:" + id, isUserPresent);
		}
		else 
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	@Override
	public ResponseDTO addBook(String token, BookDTO bookDTO) 
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			BookModel addBook = modelMapper.map(bookDTO, BookModel.class);
			bookRepository.save(addBook);
			return new ResponseDTO("Book Added Successfully",addBook);
		}
		else 
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	@Override
	public ResponseDTO updateBook(String token, int id, BookDTO bookDTO)
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			Optional<BookModel> isUserPresent = bookRepository.findById(id);
			if (isUserPresent.isPresent()) 
			{
				isUserPresent.get().setBookName(bookDTO.getBookName());
				isUserPresent.get().setBookAuthor(bookDTO.getBookAuthor());
				isUserPresent.get().setBookDescription(bookDTO.getBookDescription());
				isUserPresent.get().setBookName(bookDTO.getBookLogo());
				isUserPresent.get().setBookPrice(bookDTO.getBookPrice());
				isUserPresent.get().setBookQuantity(bookDTO.getBookQuantity());
				bookRepository.save(isUserPresent.get());
				return new ResponseDTO("Book Details Updated Successfully ",isUserPresent.get());
			}
			else
			{
				throw new BookException(400, "Book not Present");
			}
		}
		else 
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	@Override
	public ResponseDTO deleteBook(String token, int id) 
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			Optional<BookModel> isUserPresent = bookRepository.findById(id);
			if (isUserPresent.isPresent()) 
			{
				bookRepository.deleteById(id);
				return new ResponseDTO("Book Deleted Successfully",isUserPresent.get());
			}
			else 
			{
				throw new BookException(400, "Book not Present");
			}
		}
		else
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	@Override
	public ResponseDTO updateBookPrice(String token, int id, double price) 
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			Optional<BookModel> isUserPresent = bookRepository.findById(id);
			if (isUserPresent.isPresent()) 
			{
				isUserPresent.get().setBookPrice(price);
				bookRepository.save(isUserPresent.get());
				return new ResponseDTO("Book Price Updated Sucessfully",isUserPresent.get());
			}
			else 
			{
				throw new BookException(400, "Book not Present");
			}
		}
		else
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	@Override
	public ResponseDTO updateBookQuantity(String token, int id, long quantity) 
	{
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			Optional<BookModel> isUserPresent = bookRepository.findById(id);
			if (isUserPresent.isPresent()) 
			{
				isUserPresent.get().setBookQuantity(quantity);
				bookRepository.save(isUserPresent.get());
				return new ResponseDTO("Book Quantity Updated Sucessfully",isUserPresent.get());
			}
			else 
			{
				throw new BookException(400, "Book not Present");
			}
		}
		else
		{
			throw new BookException(400, "User not Login, Please Check");
		}
	}
	
	
}
