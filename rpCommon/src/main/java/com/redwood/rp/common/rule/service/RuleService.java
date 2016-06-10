package com.redwood.rp.common.rule.service;

import java.util.List;
import java.util.Map;

import com.redwood.rp.common.rule.bo.RuleDetailsBO;
import com.redwood.rp.common.rule.bo.RuleMasterModel;
import com.redwood.rp.core.exception.ServiceException;

public interface RuleService {

	public <T> void updateObjectWithRules(Object[] bo, T resObject) throws ServiceException;
	
	public <T> void updateObjectWithRules(Object bo, T resObject) throws ServiceException;
	
	public void updateObjectWithRules(Object bo) throws ServiceException;
	
	public List<RuleMasterModel> formRuleMasterModel(Map<String, List<RuleDetailsBO>> ruleMap);
	
	public Map<String, List<RuleDetailsBO>> getRuleMap();
	
	public void getAllRules();
}
