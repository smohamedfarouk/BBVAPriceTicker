package com.bbva.ticker.client;

import com.bbva.ticker.model.DataHistoryRequest;
import com.bbva.ticker.model.Request;

/**
 * Created by moham on 29/01/2016.
 */
public class PriceDataHistoryRequestHandler implements RequestHandler {

    private final DataHistoryRequest dataRequest;

    public PriceDataHistoryRequestHandler(DataHistoryRequest dataRequest) {
        this.dataRequest = dataRequest;
    }

    public void handleRequest(Request.Builder requestBuilder) {
        requestBuilder.setMessageType(Request.MessageType.priceDataHistory);
        requestBuilder.setDataHistoryRequest(dataRequest);

    }
}
