package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class PriceSubscriptionEventExecutor extends
        AbstractPublisher<ClientEvent> implements
        Listener<ClientEvent> {
    private final ExecutorService m_executorService;
    private Map<String, Request> m_requests;

    public PriceSubscriptionEventExecutor(ExecutorService executorService, Map<String, Request> requests) {
        this.m_executorService = executorService;
        this.m_requests = requests;
    }

    @Override
    public void handleRequest(final ClientEvent priceSubscriptionEvent) {
  //      System.out.println("priceSubscriptionEvent.toString()"+ priceSubscriptionEvent);
        m_executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //System.out.println("priceSubscriptionEvent.toString() inside"+ priceSubscriptionEvent);
                    publish(priceSubscriptionEvent);
                } catch (Exception e) {
                  //  System.out.println("priceSubscriptionEvent.toString() inside"+ e);
                    m_requests.remove(priceSubscriptionEvent.getRequest().getIdentifier());
                    e.printStackTrace();
                }
            }
        });
    }
}