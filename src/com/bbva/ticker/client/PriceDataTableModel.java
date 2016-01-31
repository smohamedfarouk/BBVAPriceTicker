package com.bbva.ticker.client;

import com.bbva.ticker.model.PriceData;

import javax.swing.table.AbstractTableModel;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by moham on 29/01/2016.
 */
class PriceDataTableModel extends AbstractTableModel {

    List<PriceData> tableData = new ArrayList<>();

    Object[] columnNames = {"Instrument", "TimeStamp", "Bid", "Offer", "Source"};

    public PriceDataTableModel(List<PriceData> data) {

        tableData = data;
    }

    public List<PriceData> getTableData() {
        return tableData;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class getColumnClass(int column) {
        Class returnValue;
        if ((column >= 0) && (column < getColumnCount())) {
            returnValue = getValueAt(0, column).getClass();
        } else {
            returnValue = Object.class;
        }
        return returnValue;
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PriceData data = tableData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return data.getInstrument().getName();
            case 1:
                Date date = new Date(data.getDateTime());
                Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss:SSSSSSS");
                return format.format(date);
            case 2:
                return data.getRateBid();
            case 3:
                return data.getRateOffer();
            case 4:
                return data.getPriceDataSourceType();

            //    return convertTimeWithTimeZome(data.getDateTime());
            default:
                return null;
        }
    }
    public String convertTimeWithTimeZome(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " "
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE));

    }
}


