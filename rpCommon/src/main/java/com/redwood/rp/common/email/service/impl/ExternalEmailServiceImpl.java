
package com.redwood.rp.common.email.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;

import com.redwood.rp.rest.RestEasyService;
import com.redwood.rp.common.email.request.vo.EmailRequest;
import com.redwood.rp.common.email.service.ExternalEmailService;
import com.redwood.rp.core.constant.RestConst;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.CommonUtil;

/**
 * @author kjain
 */
@Named
public class ExternalEmailServiceImpl implements ExternalEmailService {

	@Inject
	private RestEasyService restEasyService;
	
	@Inject
	private CommonUtil commonUtil;

	@Value("${acpcommon.email.service.url}")
	private String sendEmailServiceUrl;

	/* (non-Javadoc)
	 * @see com.redwood.rp.common.external.service.ExternalService#sendEmail(com.redwood.rp.common.email.request.vo.EmailRequest)
	 */
	public boolean sendEmail(EmailRequest emailRequest) throws ServiceException {
		Map<String, String> authHeaders= commonUtil.retrieveAuthHeaders(null,null,null);
		boolean status = false;

		Response emailResponse = restEasyService.post(sendEmailServiceUrl, emailRequest, null, authHeaders, Response.class, null,null);
		
		if(RestConst.RES_STATUS_CODE_200 == emailResponse.getStatus()) {
			status=true;
		}
		
		return status;
	}
}
