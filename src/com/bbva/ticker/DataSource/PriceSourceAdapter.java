package com.bbva.ticker.DataSource;

import com.bbva.ticker.model.Instrument;
import com.bbva.ticker.model.PriceData;
import com.bbva.ticker.model.PriceDataSourceType;
import com.bbva.ticker.model.PricingType;

import java.util.List;

/**
 * Created by moham on 25/01/2016.
 */
public interface PriceSourceAdapter {
    PriceData convert(String priceLine);

    PriceData getPriceData(PriceDataSourceType source, Instrument instrument, PricingType type);

    List<PriceData> getPriceDataHistory(PriceDataSourceType source, Instrument instrument, int count);
}
