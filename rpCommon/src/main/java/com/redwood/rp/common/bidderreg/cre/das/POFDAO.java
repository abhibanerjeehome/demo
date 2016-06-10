package com.redwood.rp.common.bidderreg.cre.das;

import java.util.List;
import java.util.Map;

import com.redwood.rp.core.exception.DaoException;

public interface POFDAO {
	public List<Map<String, Object>> getPOFDetails(Long userId) throws DaoException;
}
