package com.bbva.ticker.client;

import com.bbva.ticker.model.Request;

public interface RequestHandler {
    public void handleRequest(Request.Builder builder);
}
