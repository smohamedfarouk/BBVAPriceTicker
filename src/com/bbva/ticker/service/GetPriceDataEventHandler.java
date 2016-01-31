package com.bbva.ticker.service;

import com.bbva.ticker.DataSource.PriceSourceAdapter;
import com.bbva.ticker.DataSource.PriceSourceDataAdapterImpl;
import com.bbva.ticker.model.*;

import java.util.List;

public class GetPriceDataEventHandler extends
        AbstractClientConnectionEventHandler<PricingService> {

    public GetPriceDataEventHandler(Request.MessageType messageType,
                                    PricingService service) {
        super(messageType, service);
    }

    @Override
    protected void doHandle(ClientEvent clientConnectionEvent, Response.Builder responseBuilder) {
        Request request =clientConnectionEvent.getRequest();
        if (request.getMessageType().equals(Request.MessageType.subscribePriceData)) {
            handleSubscription(request, responseBuilder);
        }
        if (request.getMessageType().equals(Request.MessageType.unsubscribePriceData)) {
            handleUnsubscription(request, responseBuilder);
        }
    }


    /*protected void doHandle(Request request,
                            Response.Builder responseBuilder) {

    }
*/
    private void handleSubscription(Request request,
                                    Response.Builder responseBuilder) {
        DataRequest dataRequest = request.getDataRequest();
        Instrument instrument = dataRequest.getInstrument();
        PricingType pricingType = dataRequest.getPricingType();
        PriceDataSourceType source = dataRequest.getPriceDataSourceType();
        PriceSourceAdapter priceSourceAdapter = new PriceSourceDataAdapterImpl();
        try {
            responseBuilder.setPriceData(priceSourceAdapter.getPriceData(source, instrument, pricingType));
        } catch (Exception e) {
            responseBuilder.setException("Failed to execute:"
                    + request);
        }

    }

    private void handleUnsubscription(Request request,
                                      Response.Builder responseBuilder) {
        responseBuilder.setMessage("UnSubscribed Succeefully");
    }
}
