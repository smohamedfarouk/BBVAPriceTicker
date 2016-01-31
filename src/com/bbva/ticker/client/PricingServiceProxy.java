package com.bbva.ticker.client;

import com.bbva.ticker.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class PricingServiceProxy {

    private Session m_session;

    public PricingServiceProxy() {
        connect();
    }

    public void subscribePriceData(String identifier, DataRequest dataRequest, Callback<PriceData, String> callback) {
        ResponseHandler responseHandler = new SubscribePriceDataResponseHandler(
                callback, Request.MessageType.subscribePriceData);
        m_session.execute(identifier, new SubscribePriceDataRequestHandler(dataRequest), responseHandler,
                2000);
    }

    public void unsubscribePriceData(String identifier, DataRequest dataRequest, Callback<String, String> callback) {
        ResponseHandler responseHandler = new UnsubscribePriceDataResponseHandler(
                callback, Request.MessageType.unsubscribePriceData);
        m_session.execute(identifier, new UnsubscribePriceDataRequestHandler(dataRequest), responseHandler,
                2000);
    }
    public void getPriceDataHistory(String identifier, DataHistoryRequest dataHistoryRequest, Callback<List<PriceData>, String> callback) {
        ResponseHandler responseHandler = new PriceDataHistoryResponseHandler(
                callback, Request.MessageType.priceDataHistory);
        m_session.execute(identifier, new PriceDataHistoryRequestHandler(dataHistoryRequest), responseHandler,
                2000);
    }
    Map<String, ResponseHandler> m_responseHandlers = new HashMap<>();

    public void connect() {
        ClientSocketFactory clientSocketFactory = new ClientSocketFactory(
                "localhost", 9006);

        SocketAdaptor socketAdaptor = null;
        try {
            socketAdaptor = new SimpleSocketAdaptor(
                    clientSocketFactory.create());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseListener responseListener = new ResponseListener(
                socketAdaptor, Executors.newFixedThreadPool(20), m_responseHandlers);
        RequestPublisher requestPublisher = new RequestPublisher(
                socketAdaptor, Executors.newFixedThreadPool(20));
        final Session session = new Session(new UUIDIdentifierFactory(),
                requestPublisher, responseListener);
        this.m_session = session;
    }
}
