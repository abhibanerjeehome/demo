package com.redwood.rp.common.document.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.rest.RestEasyService;
import com.redwood.rp.common.document.service.ExternalDocumentService;
import com.redwood.rp.common.document.vo.AllFilesVO;
import com.redwood.rp.common.document.vo.request.ListFileRequest;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.CommonUtil;

/**
 * @author kjain
 */
@Named
public class ExternalDocumentServiceImpl implements ExternalDocumentService {

	@Inject
	private RestEasyService restEasyService;

	@Inject
	private CommonUtil commonUtil;

	@Value("${acpcommon.document.service.url}")
	private String documenServiceUrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.redwood.rp.common.document.service.ExternalDocumentService#getDocuments
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public AllFilesVO getDocuments(ListFileRequest listFileRequest) throws ServiceException {
		AllFilesVO allfilesVO = new AllFilesVO();
		ErrorVO errorVO = null;
		try {
			Map<String, String> authHeaders = commonUtil.retrieveAuthHeaders(null, null, null);
			if (StringUtils.isNotBlank(listFileRequest.getAccessCode())) {
				authHeaders.put("accesscode", listFileRequest.getAccessCode());
			}
			allfilesVO = restEasyService.post(documenServiceUrl, listFileRequest, null, authHeaders, null, null, AllFilesVO.class, null, null);
		} catch (ServiceException serviceException) {
			serviceException.printStackTrace();
			errorVO = new ErrorVO(ErrorDescription.EXTERNAL_SERVICE_EXCEPTION, ErrorDescription.ERR_MSG_EXTERNAL_SERVICE, "Error while calling document service");
			throw new ServiceException(ExceptionType.EXCEPTION_SERVICE, errorVO);
		}
		return allfilesVO;
	}
}
