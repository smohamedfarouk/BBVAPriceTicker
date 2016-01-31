package com.bbva.ticker.service;

import java.util.LinkedHashSet;
import java.util.Set;

public class AbstractPublisher<T> {
	private final Set<Listener<T>> m_listeners;

	protected AbstractPublisher() {
		m_listeners = new LinkedHashSet<Listener<T>>();
	}

	public void addListener(Listener<T> listener) {
		m_listeners.add(listener);
	}

	protected void publish(T object) {
		for (Listener<T> listener : m_listeners) {
			listener.handleRequest(object);
 		}
	}
}
