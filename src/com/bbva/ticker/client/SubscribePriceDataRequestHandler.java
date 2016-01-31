package com.bbva.ticker.client;


import com.bbva.ticker.model.DataRequest;
import com.bbva.ticker.model.Request;

public class SubscribePriceDataRequestHandler implements RequestHandler {
    private final DataRequest dataRequest;

    public SubscribePriceDataRequestHandler(DataRequest dataRequest) {
        this.dataRequest = dataRequest;
    }

    public void handleRequest(Request.Builder requestBuilder) {
        requestBuilder.setMessageType(Request.MessageType.subscribePriceData);
        requestBuilder.setDataRequest(dataRequest);

    }
}

