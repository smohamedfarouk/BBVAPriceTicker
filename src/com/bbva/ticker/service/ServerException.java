package com.bbva.ticker.service;

public class ServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7712758486957117873L;

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}
}
