package com.bbva.ticker.service;

import com.bbva.ticker.DataSource.PriceSourceAdapter;
import com.bbva.ticker.DataSource.PriceSourceDataAdapterImpl;
import com.bbva.ticker.model.*;

import java.util.List;
import java.util.StringTokenizer;


public class GetPriceDataConnectionEventHandler extends
        AbstractClientConnectionEventHandler<PricingService> {
    PriceSourceAdapter m_priceSourceDataAdapterImpl;

    public GetPriceDataConnectionEventHandler(Request.MessageType messageType,
                                              PricingService service,
                                              PriceSourceAdapter priceSourceAdapter) {
        super(messageType, service);
        this.m_priceSourceDataAdapterImpl = priceSourceAdapter;
    }


    @Override
    protected void doHandle(ClientEvent clientConnectionEvent,
                            Response.Builder responseBuilder) {
        Request request = clientConnectionEvent.getRequest();
        if (request.getMessageType().equals(Request.MessageType.subscribePriceData)) {
            handleSubscription(request, responseBuilder);
        }
        if (request.getMessageType().equals(Request.MessageType.unsubscribePriceData)) {
            handleUnsubscription(request, responseBuilder);
        }
        if (request.getMessageType().equals(Request.MessageType.priceDataHistory)) {
            handlePriceHistory(request, responseBuilder);
        }
    }

    private void handlePriceHistory(Request request, Response.Builder responseBuilder) {
        DataHistoryRequest dataRequest = request.getDataHistoryRequest();
        Instrument instrument = dataRequest.getInstrument();
        PriceDataSourceType source = dataRequest.getPriceDataSourceType();
        int count = dataRequest.getCount();


        try {
            List<PriceData> prices = m_priceSourceDataAdapterImpl.getPriceDataHistory(source, instrument, count);
            responseBuilder.addAllPriceDataHistory(prices);
        } catch (Exception e) {
            responseBuilder.setException("Failed to execute handleSubscription:"
                    + request);
        }
    }

    private void handleSubscription(Request request,
                                    Response.Builder responseBuilder) {
        DataRequest dataRequest = request.getDataRequest();
        Instrument instrument = dataRequest.getInstrument();
        PricingType pricingType = dataRequest.getPricingType();
        PriceDataSourceType source = dataRequest.getPriceDataSourceType();
        try {
            responseBuilder.setPriceData(m_priceSourceDataAdapterImpl.getPriceData(source, instrument, pricingType));
        } catch (Exception e) {
            responseBuilder.setException("Failed to execute handleSubscription:"
                    + request);
        }
    }

    private void handleUnsubscription(Request request,
                                      Response.Builder responseBuilder) {
        try {
            responseBuilder.setMessage(request.getDataRequest().getInstrument().getName());
            PriceData.Builder priceData = PriceData.newBuilder();
            priceData.setPriceDataSourceType(request.getDataRequest().getPriceDataSourceType());
            priceData.setId(0);
            Instrument instrument = Instrument.newBuilder().setInstrumentId(request.getDataRequest().getInstrument().getInstrumentId()).
                    setName(request.getDataRequest().getInstrument().getName()).build();
            priceData.setInstrument(instrument);
            priceData.setRateBid("0");
            priceData.setRateOffer("0");
            priceData.setDateTime(0);
            responseBuilder.setPriceData(priceData);

        } catch (Exception e) {
            responseBuilder.setException("Failed to execute handleUnsubscription:"
                    + request);
        }
    }
}
