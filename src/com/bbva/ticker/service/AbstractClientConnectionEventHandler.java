package com.bbva.ticker.service;


import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

public abstract class AbstractClientConnectionEventHandler<Service> implements
        Listener<ClientEvent> {

    private final Request.MessageType m_messageType;
    private final Service m_service;

    public AbstractClientConnectionEventHandler(Request.MessageType messageType,
                                                Service service) {
        this.m_messageType = messageType;
        this.m_service = service;
    }

    @Override
    public void handleRequest(ClientEvent clientConnectionEvent) {
        if (m_messageType.equals(clientConnectionEvent.getRequest()
                .getMessageType())) {
            Response.Builder responseBuilder = createResponseBuilder(clientConnectionEvent);
            try {
                doHandle(clientConnectionEvent, responseBuilder);
            } catch (Exception exception) {
                responseBuilder.setException("Failed to execute: "
                        + clientConnectionEvent.getRequest());
                throw exception;
            }
            finishResponse(clientConnectionEvent, responseBuilder);
        }

    }

    public Request.MessageType getMessageType() {
        return m_messageType;
    }

    public Service getService() {
        return m_service;
    }

    private void finishResponse(ClientEvent clientConnectionEvent,
                                Response.Builder responseBuilder) {
        Response response = responseBuilder.build();
        try {
            System.out.println(this.getClass().getCanonicalName() + " Server Completed Response Sent " + response);
            clientConnectionEvent.respond(response);
        } catch (ServerException e) {
            System.out.println("Server Exception" + e);
            throw e;
        }
    }


    protected abstract void doHandle(
            ClientEvent clientConnectionEvent, Response.Builder responseBuilder);


    private Response.Builder createResponseBuilder(
            ClientEvent clientConnectionEvent) {
        Response.Builder responseBuilder = Response.newBuilder();
        responseBuilder.setIdentifier(clientConnectionEvent.getRequest()
                .getIdentifier());

        return responseBuilder;
    }


};
