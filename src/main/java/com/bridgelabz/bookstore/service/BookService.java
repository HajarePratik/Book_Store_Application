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
			Optional<BookModel> isBookPresent = bookRepository.findById(id);
			return new ResponseDTO("User of id:" + id, isBookPresent);
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
			Optional<BookModel> isBookPresent = bookRepository.findById(id);
			if (isBookPresent.isPresent()) 
			{
				isBookPresent.get().setBookName(bookDTO.getBookName());
				isBookPresent.get().setBookAuthor(bookDTO.getBookAuthor());
				isBookPresent.get().setBookDescription(bookDTO.getBookDescription());
				isBookPresent.get().setBookName(bookDTO.getBookLogo());
				isBookPresent.get().setBookPrice(bookDTO.getBookPrice());
				isBookPresent.get().setBookQuantity(bookDTO.getBookQuantity());
				bookRepository.save(isBookPresent.get());
				return new ResponseDTO("Book Details Updated Successfully ",isBookPresent.get());
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
			Optional<BookModel> isBookPresent = bookRepository.findById(id);
			if (isBookPresent.isPresent()) 
			{
				bookRepository.deleteById(id);
				return new ResponseDTO("Book Deleted Successfully",isBookPresent.get());
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
			Optional<BookModel> isBookPresent = bookRepository.findById(id);
			if (isBookPresent.isPresent()) 
			{
				isBookPresent.get().setBookPrice(price);
				bookRepository.save(isBookPresent.get());
				return new ResponseDTO("Book Price Updated Sucessfully",isBookPresent.get());
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
			Optional<BookModel> isBookPresent = bookRepository.findById(id);
			if (isBookPresent.isPresent()) 
			{
				isBookPresent.get().setBookQuantity(quantity);
				bookRepository.save(isBookPresent.get());
				return new ResponseDTO("Book Quantity Updated Sucessfully",isBookPresent.get());
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
	public Boolean verifyBook(String token,int id)
	{
		
		boolean verify = restTemplate.getForObject("http://userbook-store/verifyemail/" + token,Boolean.class);
		if (verify) 
		{
			Optional<BookModel> isBookPresent = bookRepository.findById(id);
			if(isBookPresent != null) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		return verify;
	}
	
	
}
