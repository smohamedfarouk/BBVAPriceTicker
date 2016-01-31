package com.bbva.ticker.client;

import com.bbva.ticker.model.Callback;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

import java.util.List;
import java.util.Set;

public class PriceDataHistoryResponseHandler extends
        AbstractCallbackResponseHandler<List<PriceData>, String> {
    public PriceDataHistoryResponseHandler(
            Callback<List<PriceData>, String> callback, Request.MessageType messageType) {
        super(callback, messageType);
    }

    @Override
    protected String failureResult(Response response) {
//		System.out.println(this.getClass().getName() + response);
        return response.getException();
    }

    @Override
    protected List<PriceData> successResult(Response response) {
        //	System.out.println(this.getClass().getName() + response);
        return response.getPriceDataHistoryList();
    }
}