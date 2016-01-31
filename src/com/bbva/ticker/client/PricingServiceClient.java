package com.bbva.ticker.client;

import com.bbva.ticker.model.*;

import java.util.List;
import java.util.Random;

/**
 * Created by moham on 25/01/2016.
 */
public class PricingServiceClient {
    PricingServiceProxy proxy;

    public PricingServiceClient() throws ClientException {
        proxy = new PricingServiceProxy();
    }


    public static void main(String[] args) {
        PricingServiceClient client = new PricingServiceClient();
        Callback subScribeCallback = new Callback<PriceData, String>() {

            @Override
            public void success(PriceData success) {
                System.out.println("Successfully subscribePriceData PriceData"
                        + success.toString());
            }

            @Override
            public void failure(String failure) {
                System.out.println("Failure subscribePriceData"
                        + failure.toString());
            }


        };
         Callback unsubScribeCallback = new Callback<String, String>() {

            @Override
            public void success(String success) {
                System.out.println("Successfully unsubscribePriceData PriceData"
                        + success.toString());
            }

            @Override
            public void failure(String failure) {
                System.out.println("Failure unsubscribePriceData"
                        + failure.toString());
            }
        };

        DataRequest.Builder audUsdBuilder = createDataRequestForSubscription(1, "AUDUSD", PriceDataSourceType.SOURCE1);
        DataRequest.Builder audjpyBuilder = createDataRequestForSubscription(2, "AUDJPY", PriceDataSourceType.SOURCE1);
        DataRequest.Builder audnzdBuilder = createDataRequestForSubscription(3, "AUDNZD", PriceDataSourceType.SOURCE1);
        DataRequest.Builder cadjpyBuilder = createDataRequestForSubscription(4, "CADJPY", PriceDataSourceType.SOURCE1);
        DataRequest.Builder eurchfBuilder = createDataRequestForSubscription(5, "EURCHF", PriceDataSourceType.SOURCE1);
        DataRequest.Builder eurGpbBuilder = createDataRequestForSubscription(6, "EURGBP", PriceDataSourceType.SOURCE1);
        DataRequest.Builder eurJpyBuilder = createDataRequestForSubscription(7, "EURJPY", PriceDataSourceType.SOURCE1);
        DataRequest.Builder eurUsdBuilder = createDataRequestForSubscription(8, "EURUSD", PriceDataSourceType.SOURCE1);
        DataRequest.Builder gbpJpydBuilder = createDataRequestForSubscription(9, "GBPJPY", PriceDataSourceType.SOURCE1);
        DataRequest.Builder gbpUsdBuilder = createDataRequestForSubscription(10, "GBPUSD", PriceDataSourceType.SOURCE1);

        String audUsdRequest = getRequestIdentifier();
        String audJpyRequest = getRequestIdentifier();
        String audnzdRequest = getRequestIdentifier();
        String cadjpyRequest = getRequestIdentifier();
        String eurchfRequest = getRequestIdentifier();
        String eurGpbRequest = getRequestIdentifier();
        String eurJpyRequest = getRequestIdentifier();
        String eurUsdRequest = getRequestIdentifier();
        String gbpjpyRequest = getRequestIdentifier();
        String gbpusdRequest = getRequestIdentifier();
        client.subcribeInstrument(audUsdRequest, audUsdBuilder, subScribeCallback);
        client.subcribeInstrument(audJpyRequest, audjpyBuilder, subScribeCallback);
        client.subcribeInstrument(audnzdRequest, audnzdBuilder, subScribeCallback);
        client.subcribeInstrument(eurchfRequest, cadjpyBuilder, subScribeCallback);
        client.subcribeInstrument(cadjpyRequest, eurchfBuilder, subScribeCallback);
        client.subcribeInstrument(eurGpbRequest, eurGpbBuilder, subScribeCallback);
        client.subcribeInstrument(eurJpyRequest, eurJpyBuilder, subScribeCallback);
        client.subcribeInstrument(eurUsdRequest, eurUsdBuilder, subScribeCallback);
        client.subcribeInstrument(gbpjpyRequest, gbpJpydBuilder, subScribeCallback);
        client.subcribeInstrument(gbpusdRequest, gbpUsdBuilder, subScribeCallback);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.unsubcribeInstrument(audUsdRequest, audUsdBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(audJpyRequest, audnzdBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(audnzdRequest, cadjpyBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(eurchfRequest, eurchfBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(cadjpyRequest, audjpyBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(eurGpbRequest, eurGpbBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(eurJpyRequest, eurJpyBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(eurUsdRequest, eurUsdBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(gbpjpyRequest, gbpJpydBuilder, unsubScribeCallback);
        client.unsubcribeInstrument(gbpusdRequest, gbpUsdBuilder, unsubScribeCallback);


    }

    public static DataRequest.Builder createDataRequestForSubscription(int id, String instrumentName, PriceDataSourceType source) {
        Instrument instrument1 = Instrument.newBuilder().setInstrumentId(id).setName(instrumentName).build();
        DataRequest.Builder dataRequestBuilder = DataRequest.newBuilder().setId(randomIntValue());
        dataRequestBuilder.setInstrument(instrument1);
        dataRequestBuilder.setPriceDataSourceType(source);
        PricingType type = PricingType.TICK;
        dataRequestBuilder.setPricingType(type);
        return dataRequestBuilder;

    }

    public static DataHistoryRequest.Builder createHistoryValues(int id, String instrumentName, PriceDataSourceType source, int count) {
        Instrument instrument1 = Instrument.newBuilder().setInstrumentId(id).setName(instrumentName).build();
        DataHistoryRequest.Builder dataHistoryRequestBuilder = DataHistoryRequest.newBuilder().setId(randomIntValue());
        dataHistoryRequestBuilder.setInstrument(instrument1);
        dataHistoryRequestBuilder.setPriceDataSourceType(source);
        PricingType type = PricingType.TICK;
        dataHistoryRequestBuilder.setPricingType(type);
        dataHistoryRequestBuilder.setCount(count);
        return dataHistoryRequestBuilder;

    }


    public void subcribeInstrument(String identifier, DataRequest.Builder dataRequestBuilder, Callback<PriceData, String> subScribeCallback) {
        proxy.subscribePriceData(identifier, createDataRequestForSubscription(1, dataRequestBuilder.getInstrument().getName(), dataRequestBuilder.getPriceDataSourceType()).build(), subScribeCallback);
    }

    public void unsubcribeInstrument(String identifier, DataRequest.Builder dataRequestBuilder, Callback<String, String> unsubScribeCallback) {
        proxy.unsubscribePriceData(identifier, dataRequestBuilder.build(), unsubScribeCallback);
    }

    public void getPriceDataHistory(String identifier, DataHistoryRequest.Builder dataHistoryRequestBuilder, Callback<List<PriceData>, String> priceDataHistoryCallBack) {
        proxy.getPriceDataHistory(identifier, dataHistoryRequestBuilder.build(), priceDataHistoryCallBack);
    }

    public static String getRequestIdentifier() {
        String identifier = new UUIDIdentifierFactory().newIdentifier()+1;
        return identifier;

    }

    private static int randomIntValue() {
        Random rand = new Random();
        int posRandInt = rand.nextInt(Integer.MAX_VALUE) + 1;
        return posRandInt;
    }
}
