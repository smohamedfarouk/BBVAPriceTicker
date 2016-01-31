package com.bbva.ticker.client;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketFactory {
	private final String m_host;
	private final int m_port;

	public ClientSocketFactory(String host, int port) {
		this.m_host = host;
		this.m_port = port;
	}

	public Socket create() throws  ClientException{
		try {
			return new Socket(m_host, m_port);
		} catch (IOException e) {
			throw new ClientException("Failed to create a socket to host:"
					+ m_host + " on port: " + m_port, e);
		}
	}
}
