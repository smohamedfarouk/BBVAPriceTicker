package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

public class PriceSubscriptionEvent implements ClientEvent {
    private final Request m_request;
    private final ClientConnection m_clientConnection;

    public PriceSubscriptionEvent(Request request,
                                  ClientConnection clientConnection) {
        this.m_clientConnection = clientConnection;
        this.m_request = request;
    }

    public Request getRequest() {
        return m_request;
    }

    public ClientConnection getClientConnection() {
        return m_clientConnection;
    }

    public void respond(Response response) {
        if (m_clientConnection != null)
            m_clientConnection.write(response);
    }

    @Override
    public String toString() {
        return "PriceSubscriptionEvent [m_request=" + m_request
                + ", m_clientConnection=" + m_clientConnection + "]";
    }
}
