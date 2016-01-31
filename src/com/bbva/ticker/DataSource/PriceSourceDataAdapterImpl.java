package com.bbva.ticker.DataSource;

import com.bbva.ticker.model.Instrument;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.PriceDataSourceType;
import com.bbva.ticker.model.PricingType;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by moham on 25/01/2016.
 */
public class PriceSourceDataAdapterImpl implements PriceSourceAdapter {

    private final static String delimeter = ",";

    private Map<PriceDataSourceType, SourcePriceDataStore> sourcePriceDataStores = new ConcurrentHashMap<>();

    public PriceSourceDataAdapterImpl() {

    }

    public void addPriceData(PriceDataSourceType priceDataSourceType, Instrument instrument, PriceData priceData) {
        if (sourcePriceDataStores.get(priceDataSourceType) == null) {
            Map<Instrument, PriceDataMap<PriceData>> instrumentPriceDataListMap = new ConcurrentHashMap<>();
            sourcePriceDataStores.put(priceDataSourceType, new SourcePriceDataStore(priceDataSourceType, instrumentPriceDataListMap));
        }
        SourcePriceDataStore sourcePriceDataStore = sourcePriceDataStores.get(priceDataSourceType);
        sourcePriceDataStore.addPriceDataList(instrument, priceData);
    }

    public List<PriceData> getPriceData(PriceDataSourceType source, Instrument instrument, int count) {
        SourcePriceDataStore sourcePriceDataStore = sourcePriceDataStores.get(source);
        PriceDataMap<PriceData> prices = sourcePriceDataStore.getPriceDataList(instrument);
        return prices.getPriceDataList();
    }

    @Override
    public PriceData convert(String priceLine) {
        PriceData.Builder priceData = PriceData.newBuilder();

        //if (source.equals(PriceDataSourceType.SOURCE2)) {
        Instrument instrument = null;
        StringTokenizer tokens = new StringTokenizer(priceLine, ",");
        PriceDataSourceType source = null;
        while (tokens.hasMoreTokens()) {
            source = PriceDataSourceType.valueOf(tokens.nextToken());
            priceData.setPriceDataSourceType(source);
            priceData.setId(Integer.parseInt(tokens.nextToken()));
            instrument = Instrument.newBuilder().setInstrumentId(Integer.parseInt(tokens.nextToken())).setName(tokens.nextToken()).build();
            priceData.setInstrument(instrument);
            priceData.setRateBid(tokens.nextToken());
            priceData.setRateOffer(tokens.nextToken());
            priceData.setDateTime(Long.parseLong(tokens.nextToken()));
        }
        //  }
        PriceData price = priceData.build();
        if (instrument != null) {
            addPriceData(source, instrument, price);
        }
        return price;
    }


    @Override
    public PriceData getPriceData(PriceDataSourceType source, Instrument instrument, PricingType type) {
        String tickLine = new String(source + delimeter + randomIntValue() + delimeter + instrument.getInstrumentId() + delimeter + instrument.getName() +
                delimeter + randomDoubleValue() + delimeter + randomDoubleValue() + delimeter + System.currentTimeMillis());

        return convert(tickLine);
    }

    @Override
    public List<PriceData> getPriceDataHistory(PriceDataSourceType source, Instrument instrument, int count) {
        return getPriceData(source, instrument, count);
    }

    private String randomDoubleValue() {
        double min = 0;
        double max = 1;
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return df.format(randomValue);
    }

    private static int randomIntValue() {
        Random rand = new Random();
        int posRandInt = rand.nextInt(Integer.MAX_VALUE) + 1;
        return posRandInt;
    }
}
