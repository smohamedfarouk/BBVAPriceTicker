package com.bbva.ticker.client;


import com.bbva.ticker.model.Request;

import java.util.concurrent.ExecutorService;

public class RequestPublisher implements AutoCloseable {
    private  SocketAdaptor m_socketAdaptor;
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

    public void publish(final Request request) {
        m_executorService.execute(new Runnable() {
            @Override
            public void run() {
                m_socketAdaptor.write(request);
            }
        });
    }
}
