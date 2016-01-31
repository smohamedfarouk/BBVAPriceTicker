package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ClientConnectionEventExecutor extends
        AbstractPublisher<ClientEvent> implements
        Listener<ClientConnectionEvent> {
    private final ExecutorService m_executorService;
    private Map<String, Request> m_requests;
    private Map<String, ClientConnection> m_clientConnections;

    public ClientConnectionEventExecutor(ExecutorService executorService, Map<String, Request> m_requests, Map<String, ClientConnection> clientConnections) {
        this.m_executorService = executorService;
        this.m_requests = m_requests;
        this.m_clientConnections = clientConnections;
    }

    @Override
    public void handleRequest(final ClientConnectionEvent clientConnectionEvent) {
        Request request = clientConnectionEvent.getRequest();
        if (request.getMessageType().equals(Request.MessageType.subscribePriceData)) {
            m_requests.put(request.getIdentifier(), request);
            m_clientConnections.put(request.getIdentifier(), clientConnectionEvent.getClientConnection());
        }
        if (request.getMessageType().equals(Request.MessageType.unsubscribePriceData)) {
            m_requests.remove(request.getIdentifier());
            m_clientConnections.remove(request.getIdentifier());
            publish(clientConnectionEvent);
        }
        if (request.getMessageType().equals(Request.MessageType.priceDataHistory)) {
            publish(clientConnectionEvent);
        }

    }

}