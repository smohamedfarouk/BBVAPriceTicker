package com.bbva.ticker.service;

public interface Listener<T> {
	void handleRequest(T object);
}
