package com.bbva.ticker.client;

import com.bbva.ticker.model.Request.MessageType;
import com.bbva.ticker.model.Response;

public interface ResponseHandler {
	public void handleResponse(Response response);

	MessageType getRequestMessageType();
}
