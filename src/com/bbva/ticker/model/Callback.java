package com.bbva.ticker.model;

/**
 * Created by moham on 25/01/2016.
 */


public interface Callback<SuccessType, FailureType> {
    void success(SuccessType success);

    void failure(FailureType failure);
}
