package com.bbva.ticker.client;


import com.bbva.ticker.model.DataRequest;
import com.bbva.ticker.model.Request;

public class UnsubscribePriceDataRequestHandler implements RequestHandler {
    private final DataRequest dataRequest;

    public UnsubscribePriceDataRequestHandler(DataRequest dataRequest) {
        this.dataRequest = dataRequest;
    }

    public void handleRequest(Request.Builder requestBuilder) {
        requestBuilder.setMessageType(Request.MessageType.unsubscribePriceData);
        requestBuilder.setDataRequest(dataRequest);
    }
}
