package com.bbva.ticker.client;


import com.bbva.ticker.model.Callback;
import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

public abstract class AbstractCallbackResponseHandler<S, F> implements
		ResponseHandler {
	private final Callback<S, F> m_callback;
	private final Request.MessageType m_messageType;

	public AbstractCallbackResponseHandler(Callback<S, F> callback,
										   Request.MessageType messageType) {
		this.m_callback = callback;
		this.m_messageType = messageType;
	}

	@Override
	public void handleResponse(Response response) {
		System.out.println(this.getClass().getName() + "Handling Response " + response);
		boolean success =isSuccess(response);
		if ( success) {
			m_callback.success(successResult(response));
		} else {
			m_callback.failure(failureResult(response));
		}

	}

	protected abstract F failureResult(Response response);

	protected abstract S successResult(Response response);

	private boolean isSuccess(Response response) {
		return !response.hasException() ;
	}

	@Override
	public Request.MessageType getRequestMessageType() {
		return m_messageType;
	}
}
