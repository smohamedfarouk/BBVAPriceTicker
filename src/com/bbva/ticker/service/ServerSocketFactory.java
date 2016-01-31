package com.bbva.ticker.service;

import com.bbva.ticker.client.ClientException;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketFactory {
	private final int m_port;

	public ServerSocketFactory(int port) {
		this.m_port = port;
	}

	public ServerSocket create() throws ClientException {
		try {
			return new ServerSocket(m_port);
		} catch (IOException e) {
			throw new ClientException("Failed to create a socket to host:"
					+ " on port: " + m_port, e);
		}
	}
}
