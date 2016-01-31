package com.bbva.ticker.service;

import com.bbva.ticker.client.ClientException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;

public class ClientSocketPublisher extends AbstractPublisher<Socket> {
	private final ServerSocketFactory m_ServerSocketFactory;

	public ClientSocketPublisher(ServerSocketFactory xerverSocketFactory) {
		this.m_ServerSocketFactory = xerverSocketFactory;
	}

	public void start() throws ClientException {
		ServerSocket serverSocket = m_ServerSocketFactory.create();
		System.out.println("Started listening for client connection on port"
				+ serverSocket.getLocalPort());
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("Accepted Client socket" + socket);
			} catch (IOException e) {
				new ServerException("Failed to accept client socket" + socket,
						e);
			} 
			publish(socket);
			Thread.yield();
		}
	}
}
