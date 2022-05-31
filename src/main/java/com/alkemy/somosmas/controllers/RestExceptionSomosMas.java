package com.alkemy.somosmas.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alkemy.somosmas.exceptions.CommentException;
import com.alkemy.somosmas.exceptions.InvalidUserException;

@ControllerAdvice
public class RestExceptionSomosMas extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {InvalidUserException.class})
	protected ResponseEntity<Object> handleParamNotFound(InvalidUserException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getError(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}

	@ExceptionHandler(value = {CommentException.class})
	protected ResponseEntity<Object> handleParamNotFound(CommentException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getError(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
