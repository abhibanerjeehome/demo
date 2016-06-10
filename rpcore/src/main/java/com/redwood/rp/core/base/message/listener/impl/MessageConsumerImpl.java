package com.redwood.rp.core.base.message.listener.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redwood.rp.core.base.message.listener.MessageConsumer;
import com.redwood.rp.core.base.pattern.delegate.BusinessDelegate;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.security.service.OauthService;
import com.redwood.rp.security.vo.UserRequestVO;

@Service
public class MessageConsumerImpl implements MessageListener, MessageConsumer {

	// =================================================
	// Class Variables
	// =================================================
	private static Logger logger = LoggerFactory.getLogger(MessageConsumerImpl.class);

	// =================================================
	// Instance variables
	// =================================================

	@Autowired
	private BusinessDelegate businessDelegate;

	// =================================================
	// Instance methods
	// =================================================

	@Override
	public void onMessage(Message message) {
		if (logger.isInfoEnabled()) {
			logger.info("received message from queue" + message);
		}
		try {
			businessDelegate.execute(prepareRequestFromMessage(message));
		}
		catch (Exception exception) {
			logger.error("exception occurred when listening to messages from queue", exception);
			// throw a run time exception.
			throw new RuntimeException("Error occurred when consuming and processing the message " + exception.getMessage());
		}
	}

	/**
	 * Method to prepare request from message
	 * @param message
	 * @return ACPRequest
	 */
	private ACPRequest prepareRequestFromMessage(final Message message)  {
		ACPRequest request = null;
		Object payloadFromMessage = deserialize(message.getBody());
		if (payloadFromMessage instanceof ACPRequest) {
			request = (ACPRequest) payloadFromMessage;
			if (request != null) {
				UserRequestVO userRequest = request.getUserRequest();
				OauthService.setUserRequest(userRequest);
				if (logger.isInfoEnabled()) {
					if (userRequest == null) {
						logger.info("Thread ID: "+Thread.currentThread().getId() + " userRequest is null onMessage");
					}
					else {
						logger.info("Thread ID: "+Thread.currentThread().getId() + " userid: "+userRequest.getUserId() + " onMessage");
					}
				}
			}
		}
		return request;
	}
	
    private static Object deserialize(byte[] objectData) {
        if (objectData == null) {
            throw null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        ObjectInputStream in = null;
        try {
            // stream closed in the finally
            in = new ObjectInputStream(bais);
            return in.readObject();
            
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

}
