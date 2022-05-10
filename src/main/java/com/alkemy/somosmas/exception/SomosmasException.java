package com.alkemy.somosmas.exception;

public class SomosmasException extends RuntimeException {
	private static final long serialVersionUID = 4286989780117077446L;

	public SomosmasException(String error) {
		super(error);
	}
}
