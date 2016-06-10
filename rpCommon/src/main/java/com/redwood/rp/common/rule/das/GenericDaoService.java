package com.redwood.rp.common.rule.das;

import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.core.exception.ServiceException;

public interface GenericDaoService {
	//public  <T extends Serializable> ACPResponse fetchDetailsAsMap(ACPRequest acpRequest, T boClazz) throws ServiceException, DaoException ;
	public ACPResponse fetchDetailsAsMap(ACPRequest acpRequest) throws ServiceException, DaoException ;
}
