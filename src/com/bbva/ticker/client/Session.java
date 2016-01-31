package com.bbva.ticker.client;

import com.bbva.ticker.model.Request;

import java.util.HashMap;
import java.util.Map;

public class Session implements AutoCloseable {

	private final IdentifierFactory m_counter;
	private final RequestPublisher m_requestPublisher;
	private final ResponseListener m_responseListener;
	private Map<String,ResponseHandler> m_responseHandlers = new HashMap<>();

	public Session(IdentifierFactory counter,
				   RequestPublisher requestPublisher, ResponseListener responseListener) {
		this.m_counter = counter;
		this.m_requestPublisher = requestPublisher;
		this.m_responseListener = responseListener;
	}

	public void execute(String identifier, RequestHandler requestHandler,
						ResponseHandler responseHandler, long period)  {


			m_responseListener.addResponseHandler(identifier, responseHandler,
					period);

			Request.Builder requestBuilder = Request.newBuilder();
			requestBuilder.setIdentifier(identifier);
			requestHandler.handleRequest(requestBuilder);
			Request request = requestBuilder.build();
			m_requestPublisher.publish(request);


	}

	public void close() throws Exception {
		m_requestPublisher.close();
		m_responseListener.close();
	}
	public IdentifierFactory getIdentifierFactory(){
		return m_counter;
	}
}
