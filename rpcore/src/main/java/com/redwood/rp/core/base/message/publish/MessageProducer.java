package com.redwood.rp.core.base.message.publish;

import com.redwood.rp.core.vo.generic.ACPRequest;

public interface MessageProducer {
	
	/**
	 * Method to send notification to the exchange
	 * @param request
	 */
	public void sendMessage(final ACPRequest request);
	
	/**
	 * Method to send notification to the exchange
	 * @param request
	 * @param exchange
	 * @param routingKey
	 */
	public void sendMessage(final ACPRequest request, final String exchange, final String routingKey);
	
}
