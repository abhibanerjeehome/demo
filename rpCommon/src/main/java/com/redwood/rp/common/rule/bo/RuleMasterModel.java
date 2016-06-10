package com.redwood.rp.common.rule.bo;

import java.io.Serializable;
import java.util.Set;

import com.redwood.rp.core.util.BooleanUtil;

public class RuleMasterModel implements Serializable,Comparable<RuleMasterModel> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer ruleId;
	private String description;
	private String service;
	private String orderOfExecution;
	private String anomalyRuleId;
	private String ruleConcat;
	private String chainedRuleId;
	private Boolean isAnomaly;
	private Set<RuleDetailsModel> ruleDetailsModels;
	
	public RuleMasterModel(){}
	public RuleMasterModel(Integer ruleId, String description, String service,
			String orderOfExecution, String anomalyRuleId, String ruleConcat,
			String chainedRuleId, Boolean isAnomaly,Set<RuleDetailsModel> ruleDetailsModels) {
		super();
		this.ruleId = ruleId;
		this.description = description;
		this.service = service;
		this.orderOfExecution = orderOfExecution;
		this.anomalyRuleId = anomalyRuleId;
		this.ruleConcat = ruleConcat;
		this.chainedRuleId = chainedRuleId;
		this.isAnomaly = isAnomaly;
		this.ruleDetailsModels = ruleDetailsModels;
	}	
	public RuleMasterModel(Object ruleId, Object description, Object service,
			Object orderOfExecution, Object anomalyRuleId, Object ruleConcat,
			Object chainedRuleId, Object isAnomaly,Set<RuleDetailsModel> ruleDetailsModels) {
		super();
		this.ruleId = (ruleId!=null ? Integer.valueOf(ruleId.toString()) : null);
		this.description = (description!=null ? description.toString() : null);
		this.service = (service!=null ? service.toString() : null);
		this.orderOfExecution = (orderOfExecution!=null ? orderOfExecution.toString() : null);
		this.anomalyRuleId = (anomalyRuleId!=null ? anomalyRuleId.toString() : null);
		this.ruleConcat = (ruleConcat!=null ? ruleConcat.toString() : null);
		this.chainedRuleId = (chainedRuleId!=null ? chainedRuleId.toString() : null);
		this.isAnomaly = (isAnomaly!=null ? BooleanUtil.toBooleanObject(isAnomaly.toString()) : null);
		this.ruleDetailsModels = ruleDetailsModels;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getOrderOfExecution() {
		return orderOfExecution;
	}
	public void setOrderOfExecution(String orderOfExecution) {
		this.orderOfExecution = orderOfExecution;
	}
	public String getAnomalyRuleId() {
		return anomalyRuleId;
	}
	public void setAnomalyRuleId(String anomalyRuleId) {
		this.anomalyRuleId = anomalyRuleId;
	}
	public String getRuleConcat() {
		return ruleConcat;
	}
	public void setRuleConcat(String ruleConcat) {
		this.ruleConcat = ruleConcat;
	}
	public String getChainedRuleId() {
		return chainedRuleId;
	}
	public void setChainedRuleId(String chainedRuleId) {
		this.chainedRuleId = chainedRuleId;
	}
	public Boolean getIsAnomaly() {
		return isAnomaly;
	}
	public void setIsAnomaly(Boolean isAnomaly) {
		this.isAnomaly = isAnomaly;
	}
	public Set<RuleDetailsModel> getRuleDetailsModels() {
		return ruleDetailsModels;
	}
	public void setRuleDetailsModels(Set<RuleDetailsModel> ruleDetailsModels) {
		this.ruleDetailsModels = ruleDetailsModels;
	}
	
	
	public boolean equals(Object object){
		
		RuleMasterModel ruleMasterModel = null;
		if(object != null && object instanceof RuleMasterModel){
			ruleMasterModel = (RuleMasterModel)object;
			return this.ruleId == ruleMasterModel.getRuleId();
		}
			
			
		return false;	
	}

	@Override
	public int compareTo(RuleMasterModel ruleMasterModel) {
		return this.ruleId - ruleMasterModel.getRuleId();
	}
}
