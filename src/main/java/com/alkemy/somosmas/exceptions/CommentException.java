package com.alkemy.somosmas.exceptions;

public class CommentException extends SomosmasException{
	private static final long serialVersionUID = 1L;

	public CommentException(String error) {
		super("CommentException",error);
	}
}
