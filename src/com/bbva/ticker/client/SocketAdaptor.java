package com.bbva.ticker.client;


import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

public interface SocketAdaptor extends AutoCloseable {
    void write(Request request);

    Response read();
}
