package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;

import java.util.concurrent.ExecutorService;

public class ClientConnectionEventPublisher extends
		AbstractPublisher<ClientConnectionEvent> implements
		Listener<ClientConnection> {
	private final ExecutorService m_executorService;

	public ClientConnectionEventPublisher(ExecutorService executorService) {
		this.m_executorService = executorService;
	}

	@Override
	public void handleRequest(final ClientConnection clientConnection) {
		m_executorService.execute(new Runnable() {

			@Override
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						Request request;
						request = clientConnection.read();
						System.out.println(this.getClass()
                                .getName()
                                + " Server Received Request " + request);
						publish(new ClientConnectionEvent(request,
								clientConnection));
						Thread.yield();
					}
				} finally {
					clientConnection.close();
					System.out.println("Connection closed" + clientConnection);
				}
			}
		});
	}
}
