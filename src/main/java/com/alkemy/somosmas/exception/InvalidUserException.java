package com.alkemy.somosmas.exception;

public class InvalidUserException extends SomosmasException{

	private static final long serialVersionUID = 1L;

	public InvalidUserException(String description) {
		super("InvalidUserException", description);
	}
}
