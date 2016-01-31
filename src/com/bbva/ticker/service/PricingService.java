package com.bbva.ticker.service;

import com.bbva.ticker.model.*;

/**
 * Created by moham on 25/01/2016.
 */
public interface PricingService {

    void subscribePriceData(Request request, Response.Builder responseBuilder);

    void unsubscribePriceData(Request request, Response.Builder responseBuilder);


}
