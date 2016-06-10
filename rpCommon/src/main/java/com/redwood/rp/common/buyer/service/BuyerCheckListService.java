package com.redwood.rp.common.buyer.service;

import java.lang.reflect.InvocationTargetException;

import com.redwood.rp.buyer.vo.BuyerCheckListVO;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.property.dto.BuyerCheckListDTO;

public interface BuyerCheckListService {

	
	public BuyerCheckListVO getBuyerCheckListDetails(BuyerCheckListDTO buyerCheckListDTO, String auctionType) throws ServiceException,InvocationTargetException;

	
}
