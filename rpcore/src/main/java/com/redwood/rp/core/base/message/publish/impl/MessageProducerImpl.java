package com.redwood.rp.core.base.message.publish.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redwood.rp.core.base.message.publish.MessageProducer;
import com.redwood.rp.core.vo.generic.ACPRequest;

@Service
public class MessageProducerImpl implements MessageProducer {

	// =================================================
	// Class Variables
	// =================================================
	
	private static Logger logger = LoggerFactory.getLogger(MessageProducerImpl.class);

	// =================================================
	// Instance Variables
	// =================================================
	
	@Autowired(required=false)
	private AmqpTemplate messageTemplate;

	// =================================================
	// Instance Methods
	// =================================================

	/*
	 * (non-Javadoc)
	 * @see com.redwood.rp.core.base.message.publish.MessageProducer#sendMessage(com.redwood.rp.core.vo.generic.ACPRequest)
	 */
	public void sendMessage(final ACPRequest request) {
		logger.info("send message to exchange" + request);
		try {
			messageTemplate.convertAndSend(request);
		} catch (Exception exception) {
			logger.error("An error occurred when sending notification to exchange ", exception);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.redwood.rp.core.base.message.publish.MessageProducer#sendMessage(com.redwood.rp.core.vo.generic.ACPRequest, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMessage(final ACPRequest request, final String exchange, final String routingKey) {
		logger.info("send message to the exchange " + exchange + " and routing key " + routingKey);
		try {
			messageTemplate.send(exchange, routingKey, prepareMessageFromRequest(request));
		} catch (Exception exception) {
			logger.error("An error occurred when sending notification to exchange " + exchange + " with routing key " + routingKey, exception);
		}
	}
	
	/**
	 * Method to prepare message from request
	 * @param request
	 * @return Message
	 */
	private Message prepareMessageFromRequest(final ACPRequest request) {
		MessageProperties properties = new MessageProperties();
		properties.setContentType(MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT);
		Message message = new Message(SerializationUtils.serialize(request), properties);
		return message;
	}

}