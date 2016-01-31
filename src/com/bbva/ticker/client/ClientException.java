package com.bbva.ticker.client;

public class ClientException extends RuntimeException {

	private static final long serialVersionUID = 1713858308679829587L;

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}
}