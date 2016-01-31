package com.bbva.ticker.model;

/**
 * Created by moham on 25/01/2016.
 */

public class CallbackWrapper<T> {
    private T val;
    private Callback<Boolean, Exception> callback;

    public CallbackWrapper(T val, Callback<Boolean, Exception> callback) {
        super();
        this.val = val;
        this.callback = callback;
    }

    public T getVal() {
        return val;
    }

    public Callback<Boolean, Exception> getCallback() {
        return callback;
    }


}
