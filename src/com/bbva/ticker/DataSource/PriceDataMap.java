package com.bbva.ticker.DataSource;

import com.bbva.ticker.model.PriceData;

import java.util.*;

/**
 * Created by moham on 30/01/2016.
 */
public class PriceDataMap<T> {
    private int m_maxHistoryCount;

    public PriceDataMap(int maxHistoryCount) {
        this.m_maxHistoryCount = maxHistoryCount;
    }

    private LinkedHashMap priceQueue = new LinkedHashMap<Integer, T>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, T> eldest) {
            return this.size() > m_maxHistoryCount;
        }
    };

    public List<PriceData> getPriceDataList() {
        List<PriceData> list = new ArrayList(priceQueue.values());
        Collections.sort(list, new Comparator<PriceData>() {
            @Override
            public int compare(PriceData o1, PriceData o2) {
                return ((Long) o1.getDateTime()).compareTo(o2.getDateTime());
            }
        });
        return list;
    }


    public void addPriceData(PriceData priceData) {
        priceQueue.put(priceData.getDateTime(), priceData);
    }
}
