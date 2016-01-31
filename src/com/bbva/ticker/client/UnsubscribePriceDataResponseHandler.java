package com.bbva.ticker.client;

import com.bbva.ticker.model.Callback;
import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

public class UnsubscribePriceDataResponseHandler extends
		AbstractCallbackResponseHandler<String, String> {
	public UnsubscribePriceDataResponseHandler(
            Callback<String, String> callback, Request.MessageType messageType) {
		super(callback, messageType);
	}

	@Override
	protected String failureResult(Response response) {
//		System.out.println(this.getClass().getName() + response);
		return response.getException();
	}

	@Override
	protected String successResult(Response response) {
	//	System.out.println(this.getClass().getName() + response);
		return response.getPriceData().getInstrument().getName();
	}
}