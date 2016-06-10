package com.redwood.rp.common.rule.bo;

import java.io.Serializable;

import com.redwood.rp.core.util.BooleanUtil;

public class RuleDetailsBO implements Serializable, Comparable<RuleDetailsBO> {

	private static final long serialVersionUID = 1L;

	private Integer ruleId;
	private String description;
	private String service;
	private String orderOfExecution;
	private String anomalyRuleId;
	private String ruleConcat;
	private String chainedRuleId;
	private Boolean isAnomaly;
	private String fieldName;
	private String logicalOperator;
	private String fieldValue;
	private String functionConcat;
	private Integer chainedFunctionId;
	private Integer functionId;
	private String targetObject;
	private String targetValue;
	private Boolean evalRequired;
	private Boolean isField;
	private String anomalyFunctionId;
	private Boolean enhancedRuleInvocationRequired;

	public RuleDetailsBO() {
	}

	public RuleDetailsBO(Integer ruleId, String description, String service,
			String orderOfExecution, String anomalyRuleId, String ruleConcat,
			String chainedRuleId, Boolean isAnomaly, String fieldName,
			String logicalOperator, String fieldValue, String functionConcat,
			Integer chainedFunctionId, Integer functionId, String targetObject,
			String targetValue, Boolean evalRequired, Boolean isField, String anomalyFuctionId) {
		super();
		this.ruleId = ruleId;
		this.description = description;
		this.service = service;
		this.orderOfExecution = orderOfExecution;
		this.anomalyRuleId = anomalyRuleId;
		this.ruleConcat = ruleConcat;
		this.chainedRuleId = chainedRuleId;
		this.isAnomaly = isAnomaly;
		this.fieldName = fieldName;
		this.logicalOperator = logicalOperator;
		this.fieldValue = fieldValue;
		this.functionConcat = functionConcat;
		this.chainedFunctionId = chainedFunctionId;
		this.functionId = functionId;
		this.targetObject = targetObject;
		this.targetValue = targetValue;
		this.evalRequired = evalRequired;
		this.isField = isField;
		this.anomalyFunctionId = anomalyFuctionId;
	}

	public RuleDetailsBO(Object ruleId, Object description, Object service,
			Object orderOfExecution, Object anomalyRuleId, Object ruleConcat,
			Object chainedRuleId, Object isAnomaly, Object fieldName,
			Object logicalOperator, Object fieldValue, Object functionConcat,
			Object chainedFunctionId, Object functionId, Object targetObject,
			Object targetValue, Object evalRequired, Object isField, Object anomalyFuctionId) {
		super();
		this.ruleId = (ruleId != null ? Integer.valueOf(ruleId.toString())
				: null);
		this.description = (description != null ? description.toString() : null);
		this.service = (service != null ? service.toString() : null);
		this.orderOfExecution = (orderOfExecution != null ? orderOfExecution
				.toString() : null);
		this.anomalyRuleId = (anomalyRuleId != null ? anomalyRuleId.toString()
				: null);
		this.ruleConcat = (ruleConcat != null ? ruleConcat.toString() : null);
		this.chainedRuleId = (chainedRuleId != null ? chainedRuleId.toString()
				: null);
		this.isAnomaly = (isAnomaly != null ? BooleanUtil
				.toBooleanObject(isAnomaly.toString()) : null);
		this.fieldName = (fieldName != null ? fieldName.toString() : null);
		this.logicalOperator = (logicalOperator != null ? logicalOperator
				.toString() : null);
		this.fieldValue = (fieldValue != null ? fieldValue.toString() : null);
		this.functionConcat = (functionConcat != null ? functionConcat
				.toString() : null);
		this.chainedFunctionId = (chainedFunctionId != null ? Integer
				.valueOf(chainedFunctionId.toString()) : null);
		this.functionId = (functionId != null ? Integer.valueOf(functionId
				.toString()) : null);
		this.targetObject = (targetObject != null ? targetObject.toString()
				: null);
		this.targetValue = (targetValue != null ? targetValue.toString() : null);
		this.evalRequired = (evalRequired != null ? BooleanUtil
				.toBooleanObject(evalRequired.toString()) : null);
		this.isField = (isField != null ? BooleanUtil.toBooleanObject(isField
				.toString()) : null);
		this.anomalyFunctionId = (anomalyFuctionId != null ? anomalyFuctionId
				.toString() : null);
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

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(String logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
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

	public String getFunctionConcat() {
		return functionConcat;
	}

	public void setFunctionConcat(String functionConcat) {
		this.functionConcat = functionConcat;
	}

	public Integer getChainedFunctionId() {
		return chainedFunctionId;
	}

	public void setChainedFunctionId(Integer chainedFunctionId) {
		this.chainedFunctionId = chainedFunctionId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public Boolean getEvalRequired() {
		return evalRequired;
	}

	public void setEvalRequired(Boolean evalRequired) {
		this.evalRequired = evalRequired;
	}

	public Boolean getIsField() {
		return isField;
	}

	public void setIsField(Boolean isField) {
		this.isField = isField;
	}

	@Override
	public String toString() {
		return "RuleDetailsBO [ruleId=" + ruleId + ", description="
				+ description + ", service=" + service + ", orderOfExecution="
				+ orderOfExecution + ", anomalyRuleId=" + anomalyRuleId
				+ ", ruleConcat=" + ruleConcat + ", chainedRuleId="
				+ chainedRuleId + ", isAnomaly=" + isAnomaly + ", fieldName="
				+ fieldName + ", logicalOperator=" + logicalOperator
				+ ", fieldValue=" + fieldValue + ", functionConcat="
				+ functionConcat + ", chainedFunctionId=" + chainedFunctionId
				+ ", functionId=" + functionId + ", targetObject="
				+ targetObject + ", targetValue=" + targetValue
				+ ", evalRequired=" + evalRequired + ", isField=" + isField
				 + ", anomalyFunctionId=" + anomalyFunctionId
				+ "]";
	}

	// public boolean equals(Object otherObject)
	// {
	// if (this == otherObject) return true;
	// if (otherObject == null) return false;
	// if (getClass() != otherObject.getClass()) return false;
	// RuleDetailsBO other = (RuleDetailsBO) otherObject;
	// return description.equals(other.description)
	// && partNumber == other.partNumber;
	// }

	public void setAnomalyFunctionId(String anomalyFunctionId) {
		this.anomalyFunctionId = anomalyFunctionId;
	}

	@Override
	public int compareTo(RuleDetailsBO ruleDetailsBO) {
		return this.ruleId - ruleDetailsBO.getRuleId();
	}
	
	@Override
	public boolean equals(Object object) {
		RuleDetailsBO ruleDetailsBO = null;
		if (object != null && object instanceof RuleDetailsBO) {
			ruleDetailsBO = (RuleDetailsBO) object;
			return this.ruleId == ruleDetailsBO.getRuleId();
		}
		return false;
	}

	public String getAnomalyFunctionId() {
		return anomalyFunctionId;
	}

	public Boolean getEnhancedRuleInvocationRequired() {
		return enhancedRuleInvocationRequired;
	}

	public void setEnhancedRuleInvocationRequired(Boolean enhancedRuleInvocationRequired) {
		this.enhancedRuleInvocationRequired = enhancedRuleInvocationRequired;
	}

	public void setEnhancedRuleInvocationRequired(Object enhancedRuleInvocationRequired) {
		if (enhancedRuleInvocationRequired != null)
			this.enhancedRuleInvocationRequired = BooleanUtil.toBooleanObject(enhancedRuleInvocationRequired.toString());
		else
			this.enhancedRuleInvocationRequired = false;
	}

}
