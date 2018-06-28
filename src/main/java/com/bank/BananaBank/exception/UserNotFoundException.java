package com.bank.BananaBank.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final Integer id;

	  public UserNotFoundException(final Integer id) {
	    super("User could not be found with id: " + id);
	    this.id = id;
	  }

	}