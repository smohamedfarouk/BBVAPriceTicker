package com.bbva.ticker.client;

import com.bbva.ticker.model.Callback;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

public class SubscribePriceDataResponseHandler extends
		AbstractCallbackResponseHandler<PriceData, String> {
	public SubscribePriceDataResponseHandler(
            Callback<PriceData, String> callback, Request.MessageType messageType) {
		super(callback, messageType);
	}

	@Override
	protected String failureResult(Response response) {
//		System.out.println(this.getClass().getName() + response);
		return response.getException();
	}

	@Override
	protected PriceData successResult(Response response) {
	//	System.out.println(this.getClass().getName() + response);
		return response.getPriceData();
	}
}