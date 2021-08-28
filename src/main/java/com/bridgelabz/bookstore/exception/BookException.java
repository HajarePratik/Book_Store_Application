package com.bridgelabz.bookstore.exception;

import org.springframework.http.HttpStatus;

public class BookException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BookException(String message)
	{
		super(message);
	}
	public BookException(int statusCode, String statusmessage)
	{
		super(statusmessage);
	}
	public BookException(String string, HttpStatus ok, Object object, String string2) {
		// TODO Auto-generated constructor stub
	}
	

	

}
