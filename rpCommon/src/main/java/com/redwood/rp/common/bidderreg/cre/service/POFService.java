package com.redwood.rp.common.bidderreg.cre.service;

import java.util.List;

import com.redwood.rp.common.bidderreg.cre.domain.Asset;
import com.redwood.rp.common.bidderreg.cre.vo.pof.POFVO;
import com.redwood.rp.core.exception.ServiceException;

/**
 *
 */
public interface POFService {
	
	/**
	 * @param List<Integer> globalPropIds
	 * @return
	 * @throws ServiceException
	 */
	public POFVO validatePOF(List<Asset> assetList)  throws ServiceException;
	


}
