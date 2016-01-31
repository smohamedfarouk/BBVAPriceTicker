package com.bbva.ticker.client;


import com.bbva.ticker.model.Request;

import java.util.concurrent.ExecutorService;

public class RequestPublisher implements AutoCloseable {
    private SocketAdaptor m_socketAdaptor;
    private final ExecutorService m_executorService;

    public RequestPublisher(SocketAdaptor socketAdaptor,
                            ExecutorService executorService) {
        m_executorService = executorService;
        m_socketAdaptor = socketAdaptor;
    }

    public void close() throws Exception {
        m_executorService.shutdownNow();
        m_socketAdaptor.close();
    }

    public void publish(final Request request,final Client client) throws ClientException {
        m_executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (m_socketAdaptor == null) {
                    client.setException(new Exception("Unable to perform " + request.toString() + " No Socket Adaptor available, Kindly start pricing server first!!"));
                    return;
                } m_socketAdaptor.write(request);

            }
        });
    }
}
