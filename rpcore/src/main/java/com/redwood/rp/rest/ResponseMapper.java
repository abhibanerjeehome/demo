package com.redwood.rp.rest;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class ResponseMapper {

	private static Logger logger = LoggerFactory.getLogger(ResponseMapper.class);

	@Inject
	private ObjectMapper mapper;

	/**
	 * Method to convert JSON string to object
	 * @param requiredType
	 * @param response
	 * @return <T>
	 */
	public <T> T getObjectFromJSON(final Class<T> requiredType, final String response) {
		try {
			return mapper.readValue(response, requiredType);
		} catch (Exception exception) {
			logger.error("An error occurred when retrieving the object from JSON ", exception);
		}
		return null;
	}
	
	/**
	 * Method to convert object to string
	 * @param response
	 * @return String
	 */
	public String getStringFromObject(final Object response) {
		try {
			return mapper.writeValueAsString(response);
		} catch (Exception exception) {
			logger.error("An error occurred when retrieving the string from object ", exception);
		}
		return null;
	}

}