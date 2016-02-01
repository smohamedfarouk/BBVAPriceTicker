package com.bbva.ticker.DataSource;

import com.bbva.ticker.model.Instrument;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.PriceDataSourceType;

import java.util.Map;

/**
 * Created by moham on 29/01/2016.
 */
public class SourcePriceDataStore {
    private PriceDataSourceType m_priceDataSourceType;
    private Map<Instrument, PriceDataMap<PriceData>> m_instrumentPriceDataListMap;
    private int m_maxRecordsToKeep;

    public SourcePriceDataStore(PriceDataSourceType priceDataSourceType, Map<Instrument, PriceDataMap<PriceData>> instrumentPriceDataListMap, int maxRecordsToKeep) {
        m_priceDataSourceType = priceDataSourceType;
        m_instrumentPriceDataListMap = instrumentPriceDataListMap;
        m_maxRecordsToKeep = maxRecordsToKeep;
    }

    public PriceDataMap getPriceDataList(Instrument instrument) {
        PriceDataMap<PriceData> priceDataMap = m_instrumentPriceDataListMap.get(instrument);
        if (priceDataMap == null) {
            return new PriceDataMap(m_maxRecordsToKeep);
        }
        return m_instrumentPriceDataListMap.get(instrument);
    }

    public void addPriceDataList(Instrument instrument, PriceData priceData) {
        if (m_instrumentPriceDataListMap.get(instrument) == null) {
            PriceDataMap<PriceData> priceDatas = new PriceDataMap<PriceData>(m_maxRecordsToKeep);
            m_instrumentPriceDataListMap.put(instrument, priceDatas);
        }

        m_instrumentPriceDataListMap.get(instrument).addPriceData(priceData);
    }

}
