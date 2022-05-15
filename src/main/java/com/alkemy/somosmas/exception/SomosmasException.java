package com.alkemy.somosmas.exception;

import com.alkemy.somosmas.dtos.ErrorDTO;

public class SomosmasException extends Exception {
	private static final long serialVersionUID = 4286989780117077446L;
	private ErrorDTO error;
	public SomosmasException(String name, String description) {
		super(name);
		this.error = new ErrorDTO(name, description);
	}

	public ErrorDTO getError() {
		return error;
	}
}
