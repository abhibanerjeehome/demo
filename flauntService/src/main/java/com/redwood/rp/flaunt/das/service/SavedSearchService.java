package com.redwood.rp.flaunt.das.service;

import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.flaunt.bo.SaveSearch;
import com.redwood.rp.flaunt.bo.SaveSearchResponse;

public interface SavedSearchService {
	
	/**
	 * @param saveSearch
	 * @return
	 * @throws ServiceException
	 */
	public Long insertSavedSearchInfo(SaveSearch saveSearch) throws ServiceException;
	
	/**
	 * @param saveSearch
	 * @return
	 * @throws ServiceException
	 */
	public Integer updateSavedSearchInfoById(SaveSearch saveSearch) throws ServiceException;
	
	/**
	 * @param saveSearchId
	 * @return
	 * @throws ServiceException
	 */
	public Integer deleteSavedSearchInfoById(String saveSearchIds) throws ServiceException;
	
	/**
	 * @param requestSourceId
	 * @param filter
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ServiceException
	 */
	public SaveSearchResponse fetchSaveSearchListByUserId(Integer requestSourceId, String filter, Integer offset, Integer limit) throws ServiceException;
	
	/**
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ServiceException
	 */
	public SaveSearchResponse fetchAllSaveSearchList(Integer offset, Integer limit) throws ServiceException;
	
	/**
	 * @param saveSearchId
	 * @return
	 * @throws ServiceException
	 */
	public SaveSearch fetchSaveSearchBySaveSearchId(Long saveSearchId) throws ServiceException;
	
}
