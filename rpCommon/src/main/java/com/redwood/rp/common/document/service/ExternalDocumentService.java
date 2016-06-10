package com.redwood.rp.common.document.service;

import com.redwood.rp.common.document.vo.AllFilesVO;
import com.redwood.rp.common.document.vo.request.ListFileRequest;
import com.redwood.rp.core.exception.ServiceException;

/**
 * @author kjain
 */
public interface ExternalDocumentService {


	/**
	 * @param listFileRequest
	 * @return
	 * @throws ServiceException
	 */
	public AllFilesVO getDocuments(ListFileRequest listFileRequest) throws ServiceException;
}
