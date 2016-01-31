package com.bbva.ticker.service;

import com.bbva.ticker.client.UUIDIdentifierFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionPublisher extends
		AbstractPublisher<ClientConnection> implements Listener<Socket> {

	private final UUIDIdentifierFactory m_IdentifierFactory;

	public ClientConnectionPublisher(UUIDIdentifierFactory identifierFactory) {
		this.m_IdentifierFactory = identifierFactory;
	}

	@Override
	public void handleRequest(Socket socket) {
		ClientConnection clientConnection;
		try {
			clientConnection = new ClientConnection(
					m_IdentifierFactory.newIdentifier(), socket,
					socket.getInputStream(), socket.getOutputStream());
		} catch (IOException e) {
			throw new com.bbva.ticker.service.ServerException(
					"Failed to create connection from client socket" + socket,
					e);
		}
		publish(clientConnection);
	}
}
