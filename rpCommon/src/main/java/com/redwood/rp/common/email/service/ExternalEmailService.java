package com.redwood.rp.common.email.service;

import com.redwood.rp.common.email.request.vo.EmailRequest;
import com.redwood.rp.core.exception.ServiceException;

/**
 * @author kjain
 */
public interface ExternalEmailService {

	/**
	 * @param emailRequest
	 * @return
	 * @throws ServiceException
	 */
	public boolean sendEmail(EmailRequest emailRequest)  throws ServiceException;
}
