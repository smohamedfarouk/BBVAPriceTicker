package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class PriceSubscriptionEventPublisher extends
        AbstractPublisher<ClientEvent> {
    private final ExecutorService m_executorService;
    private Map<String, Request> m_requests;
    private Map<String, ClientConnection> m_clientConnections;

    public PriceSubscriptionEventPublisher(ExecutorService executorService, Map<String, Request> requests, Map<String, ClientConnection> clientConnections) {
        this.m_executorService = executorService;
        this.m_requests = requests;
        this.m_clientConnections = clientConnections;
    }

    public void start() {
        m_executorService.execute(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    while (m_requests.size() > 0) {
                        for (Map.Entry<String, Request> entry : m_requests.entrySet()) {
                            try {
                                Request request = entry.getValue();
                                String identifier = entry.getKey();
                                ClientConnection clientConnection = m_clientConnections.get(identifier);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                publish(new PriceSubscriptionEvent(request, clientConnection));

                            } catch (Exception e) {
                                m_requests.remove(entry.getValue().getIdentifier());
                                e.printStackTrace();

                            }
                        }
                    }
                }
            }
        });
    }
}
