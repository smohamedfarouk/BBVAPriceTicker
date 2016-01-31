package com.bbva.ticker.service;

public class ShutDownHookImpl implements ShutDownHook {

	private ShutDownHook m_shutDownHook = null;

	public ShutDownHookImpl() {

	}

	public ShutDownHookImpl(ShutDownHook shutDownHook) {
		this.m_shutDownHook = shutDownHook;
	}

	@Override
	public void shutDownProcess() {
		System.out.println("Doing nohing whithin shutdown processing");
	}

	public void add() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (m_shutDownHook != null) {
					m_shutDownHook.shutDownProcess();
				} else {
					shutDownProcess();
				}
			}
		});
	}
}
