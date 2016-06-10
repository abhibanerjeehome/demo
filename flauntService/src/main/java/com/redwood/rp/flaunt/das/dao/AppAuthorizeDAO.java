package com.redwood.rp.flaunt.das.dao;

import com.redwood.rp.core.exception.DaoException;
import com.redwood.rp.flaunt.vo.json.request.AppAuthorizeVO;
import com.redwood.rp.flaunt.vo.json.response.UpdateUserResponseVO;

public interface AppAuthorizeDAO {

	/**
	 * @param appAuthorizeVO
	 * @return
	 * @throws DaoException
	 */
	public Integer insertUserPermission(AppAuthorizeVO appAuthorizeVO) throws DaoException;
}
