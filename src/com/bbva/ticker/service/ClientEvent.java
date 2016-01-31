package com.bbva.ticker.service;

import com.bbva.ticker.model.Request;
import com.bbva.ticker.model.Response;

/**
 * Created by moham on 28/01/2016.
 */
public interface ClientEvent {
    Request getRequest();

    void respond(Response response);
}
