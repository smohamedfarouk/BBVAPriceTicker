package com.bbva.ticker.client;

import com.bbva.ticker.model.Instrument;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.PriceDataSourceType;
import com.bbva.ticker.model.Response;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ResponseListener implements AutoCloseable {
    private final SocketAdaptor m_socketAdaptor;
    private final ExecutorService m_executorService;

    private Map<String,ResponseHandler> m_responseHandlers ;

    public ResponseListener(SocketAdaptor socketAdaptor,
                            ExecutorService executorService, Map<String,ResponseHandler> responseHandlers) {
        m_executorService = executorService;
        m_socketAdaptor = socketAdaptor;
        m_responseHandlers = responseHandlers;
        startToListen();
    }


    public void close() throws Exception {
        m_executorService.shutdownNow();
        m_socketAdaptor.close();
    }

    public void startToListen() {
        m_executorService.execute(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        readAndHandleResponse();
                    } catch (ClientException e) {
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }

            private void readAndHandleResponse() throws ClientException {

                if (m_socketAdaptor != null) {
                    final Response response = m_socketAdaptor.read();
                    m_responseHandlers.get(response.getIdentifier()).handleResponse(response);
                } else {
                    PriceData.Builder priceDataBuilder = PriceData.newBuilder().setInstrument(Instrument.newBuilder().setInstrumentId(0).setName("Invalid Instrument")).setRateOffer("0").setRateBid("0").setDateTime(0).setId(0).setPriceDataSourceType(PriceDataSourceType.SOURCE1);
                    Response response = Response.newBuilder().setException("Server Unavailable").setIdentifier("1").setPriceData(priceDataBuilder.build()).build();
                    if ( m_responseHandlers.get(response.getIdentifier()) !=null) {
                        m_responseHandlers.get(response.getIdentifier()).handleResponse(response);
                    }
                }
            }
        });
    }

    public void addResponseHandler(String identifier,
                                   ResponseHandler responseHandler, long period) {
      //  System.out.println("Added Response Handler");
        m_responseHandlers.put(identifier,responseHandler);


    }
}