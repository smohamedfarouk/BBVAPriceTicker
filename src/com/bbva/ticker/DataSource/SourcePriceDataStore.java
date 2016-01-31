package com.bbva.ticker.DataSource;

import com.bbva.ticker.model.Instrument;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.PriceDataSourceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by moham on 29/01/2016.
 */
public class SourcePriceDataStore {
    private PriceDataSourceType m_priceDataSourceType;
    private Map<Instrument, PriceDataMap<PriceData>> m_instrumentPriceDataListMap;
    public SourcePriceDataStore(PriceDataSourceType priceDataSourceType,Map<Instrument, PriceDataMap<PriceData>> instrumentPriceDataListMap ) {
        m_priceDataSourceType = priceDataSourceType;
        m_instrumentPriceDataListMap=instrumentPriceDataListMap;
    }

    public PriceDataMap getPriceDataList(Instrument instrument) {
        return m_instrumentPriceDataListMap.get(instrument);
    }

    public void addPriceDataList(Instrument instrument, PriceData priceData) {
        if (m_instrumentPriceDataListMap.get(instrument)==null){
           PriceDataMap<PriceData> priceDatas= new PriceDataMap<PriceData>(50);
            m_instrumentPriceDataListMap.put(instrument,priceDatas);
        }

        m_instrumentPriceDataListMap.get(instrument).addPriceData(priceData);
    }

}
