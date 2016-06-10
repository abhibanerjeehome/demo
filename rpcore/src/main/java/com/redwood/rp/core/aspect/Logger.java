package com.redwood.rp.core.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.redwood.rp.core.annotation.Loggable;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.generic.ACPRequest;

@Aspect
public class Logger {

	/****************************************************************************
	 * 
	 * Static Variables
	 ****************************************************************************/

	private static String formatter = "yyyy-MM-dd HH:mm:ss.SSS";

	/****************************************************************************
	 * 
	 * Instance Variables
	 ****************************************************************************/

	@Value("${acpcore.time.tracker.logger.appender.name}")
	private String appender;

	/****************************************************************************
	 * 
	 * Instance Methods
	 ****************************************************************************/
	/**
	 * Method to log transactions.
	 * @param proceedingJoinPoint
	 * @param loggable
	 * @return Object
	 * @throws Throwable
	 */
	@Around("@annotation(loggable)")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint, final Loggable loggable) throws Throwable {
		Object proceedingObject = null;
		Signature signature = proceedingJoinPoint.getSignature();
		org.slf4j.Logger targetLogger = LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass());
		org.slf4j.Logger logger = LoggerFactory.getLogger(appender);
		StringBuilder log = new StringBuilder();
		StopWatch timer = new StopWatch();

		Date date = new Date();
		String startTime = new SimpleDateFormat(formatter).format(date);
		log.append("startTime=").append(startTime);
		timer.start();
		try {
			proceedingObject = proceedingJoinPoint.proceed();
			timer.stop();
			date = new Date();
			String endTime = new SimpleDateFormat(formatter).format(date);
			log.append("&endTime=").append(endTime);
			log.append(logTxnSuccess(timer));
			Object clientInformation = logClientInformation(proceedingJoinPoint);
			if (clientInformation != null) {
				log.append(clientInformation.toString());
			}
			if (targetLogger.isDebugEnabled()) {
				log.append(logMethodArguments(proceedingJoinPoint));
			}
			logger.info(MessageFormattor.infoFormat(signature.getDeclaringType().getSimpleName(), signature.getName(), log.toString()));
		} catch (Exception exception) {
			timer.stop();
			logger.error(MessageFormattor.errorFormat(signature.getDeclaringType().getSimpleName(), signature.getName(), ExceptionType.EXCEPTION_APPLICATION, log.append(logErrorValues(timer, exception)).append(logMethodArguments(proceedingJoinPoint)).toString()), exception);
			throw exception;
		}
		return proceedingObject;
	}

	/**
	 * Method to log client information. This will occur wherever the request to the method is a ACPRequest.
	 * This is because ProceedingJoinPoint will have only the parameters which are in the arguments of the method being advised.
	 * @return Object
	 */
	private Object logClientInformation(final ProceedingJoinPoint proceedingJoinPoint) {
		String returnValue = null;
		Object[] listOfArguments = proceedingJoinPoint.getArgs();
		if (listOfArguments != null && listOfArguments.length > 0) {
			for (Object argument : listOfArguments) {
				if (argument instanceof ACPRequest) {
					ACPRequest request = (ACPRequest) argument;
					if (request != null && request.getUserRequest() != null) {
						StringBuffer clientInformation = new StringBuffer().append("&clientIP=").append(request.getUserRequest().getClientIP());
						clientInformation.append("&clientName=").append(request.getUserRequest().getClientName());
						clientInformation.append("&userName=").append(request.getUserRequest().getUsername());
						clientInformation.append("&userId=").append(request.getUserRequest().getUserId());
						clientInformation.append("&userEmail=").append(request.getUserRequest().getEmail());
						clientInformation.append("&firstName=").append(request.getUserRequest().getFirstName());
						clientInformation.append("&lastName=").append(request.getUserRequest().getLastName());
						clientInformation.append("&tokenType=").append(request.getUserRequest().getTokenType());
						clientInformation.append("&tokenValue=").append(request.getUserRequest().getTokenValue());
						clientInformation.append("&grantType=").append(request.getUserRequest().getGrantType());
						clientInformation.append("&anonymous=").append(request.getUserRequest().isAnonymousToken());
						returnValue = clientInformation.toString();
					}
					break;
				}
			}
		}
		return returnValue;
	}


	/**
	 * Method to log transaction success
	 * @param timer
	 * @return Object
	 */
	private Object logTxnSuccess(final StopWatch timer) {
		StringBuilder aReturnValue = new StringBuilder();
		aReturnValue.append("&processingTime").append("=").append(timer.getTime());
		aReturnValue.append("&status").append("=").append("SUCCESS");
		return aReturnValue.toString();
	}


	/**
	 * Method to log errors
	 * @param timer
	 * @param exception
	 * @return String
	 */
	private String logErrorValues(final StopWatch timer, final Exception exception) {
		StringBuilder returnValue = new StringBuilder();
		returnValue.append("&processingTime").append("=").append(timer.getTime());
		returnValue.append("&status").append("=").append("FAILURE");
		returnValue.append("&exception").append("=").append(exception.getMessage());
		return returnValue.toString();
	}


	/**
	 * Method to log arguments
	 * @param proceedingJoinPoint
	 * @return String
	 * @throws JSONException 
	 */
	private String logMethodArguments(final ProceedingJoinPoint proceedingJoinPoint) throws JSONException {
		StringBuffer returnValue = new StringBuffer();
		Object[] listOfArguments = proceedingJoinPoint.getArgs();
		Signature signature = proceedingJoinPoint.getSignature();
		JSONObject methodParamNameValues = null;
		String[] parameterNames = null;
		MethodSignature methodSignature = null;
		if (signature instanceof MethodSignature) {
			methodSignature = (MethodSignature) signature;
			parameterNames = methodSignature.getParameterNames();
		}
		if (listOfArguments != null && parameterNames != null) {
			methodParamNameValues = new JSONObject();
			for (int iCounter = 0; iCounter < listOfArguments.length; iCounter++) {
				if (listOfArguments[iCounter] != null) {
					methodParamNameValues.put(parameterNames[iCounter], listOfArguments[iCounter]);
				} else {
					methodParamNameValues.put(parameterNames[iCounter], "NULL");
				}
			}
		}
		if (methodParamNameValues != null) {
			returnValue.append("&parameters").append("=").append(methodParamNameValues.toString());
		}
		return returnValue.toString();
	}

}