package com.redwood.rp.common.rule.bo;

import java.io.Serializable;
import java.util.Set;

public class RuleDetailsModel implements Serializable,Comparable<RuleDetailsModel> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer ruleId;
	private String fieldName;
	private String logicalOperator;
	private String fieldValue;
	private String functionConcat;
	private Integer chainedFunctionId;
	private Integer functionId;
	private Integer anomalyFunctionId;
	private Boolean enhancedRuleInvocationRequired;
	
	private Set<RuleTargetDetailsModel> ruleTargetDetailsBOs;

	public RuleDetailsModel(){}
	public RuleDetailsModel(Integer ruleId, String fieldName,
			String logicalOperator, String fieldValue, String functionConcat,
			Integer chainedFunctionId, Integer functionId,
			Integer anomalyFunctionId,Boolean enhancedRuleInvocationRequired, Set<RuleTargetDetailsModel> ruleTargetDetailsBOs) {
		
		super();
		this.ruleId = ruleId;
		this.fieldName = fieldName;
		this.logicalOperator = logicalOperator;
		this.fieldValue = fieldValue;
		this.functionConcat = functionConcat;
		this.chainedFunctionId = chainedFunctionId;
		this.functionId = functionId;
		this.anomalyFunctionId =anomalyFunctionId;
		this.ruleTargetDetailsBOs = ruleTargetDetailsBOs;
		this.enhancedRuleInvocationRequired=enhancedRuleInvocationRequired;
	}	
	
	public RuleDetailsModel(Object ruleId,  Object fieldName,
			Object logicalOperator, Object fieldValue, Object functionConcat,
			Object chainedFunctionId, Object functionId,Object anomalyFunctionId
			,Boolean enhancedRuleInvocationRequired) {
		super();
		this.ruleId = (ruleId!=null ? Integer.valueOf(ruleId.toString()) : null);
		this.fieldName = (fieldName!=null ? fieldName.toString() : null);
		this.logicalOperator = (logicalOperator!=null ? logicalOperator.toString() : null);
		this.fieldValue = (fieldValue!=null ? fieldValue.toString() : null);
		this.functionConcat = (functionConcat!=null ? functionConcat.toString() : null);
		this.chainedFunctionId = (chainedFunctionId!=null ? Integer.valueOf(chainedFunctionId.toString()) : null);
		this.functionId = (functionId!=null ? Integer.valueOf(functionId.toString()) : null);
		this.anomalyFunctionId = (anomalyFunctionId!=null ? Integer.valueOf(anomalyFunctionId.toString()) : null);
		this.enhancedRuleInvocationRequired=enhancedRuleInvocationRequired;
	}
	
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
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
	public Set<RuleTargetDetailsModel> getRuleTargetDetailsBOs() {
		return ruleTargetDetailsBOs;
	}
	public void setRuleTargetDetailsBOs(
			Set<RuleTargetDetailsModel> ruleTargetDetailsBOs) {
		this.ruleTargetDetailsBOs = ruleTargetDetailsBOs;
	}
	
	
	
	
	public Integer getAnomalyFunctionId() {
		return anomalyFunctionId;
	}
	public void setAnomalyFunctionId(Integer anomalyFunctionId) {
		this.anomalyFunctionId = anomalyFunctionId;
	}
	
	public Boolean getEnhancedRuleInvocationRequired() {
		return enhancedRuleInvocationRequired;
	}
	public void setEnhancedRuleInvocationRequired(
			Boolean enhancedRuleInvocationRequired) {
		this.enhancedRuleInvocationRequired = enhancedRuleInvocationRequired;
	}
	public boolean equals(Object object){
		
		RuleDetailsModel ruleDetailsModel = null;
		if(object != null && object instanceof RuleTargetDetailsModel){
			ruleDetailsModel = (RuleDetailsModel)object;
//			System.out.println("rule detail compare  "+this.ruleId +" "+ruleDetailsModel.getRuleId()
//					+" ---- "+this.functionId+" "+ruleDetailsModel.getFunctionId() + String.valueOf((this.ruleId == ruleDetailsModel.getRuleId()) 
//					&& (this.functionId == ruleDetailsModel.getFunctionId())));
			return ((this.ruleId == ruleDetailsModel.getRuleId()) 
					&& (this.functionId == ruleDetailsModel.getFunctionId()));
		}
			
			
		return false;	
	}

	@Override
	public int compareTo(RuleDetailsModel ruleDetailsModel) {
//		System.out.println("rule detail compare  "+this.ruleId +" "+ruleDetailsModel.getRuleId()
//				+" ---- "+this.functionId+" "+ruleDetailsModel.getFunctionId()
//				+" rule compare "+(this.ruleId == ruleDetailsModel.getRuleId())
//				+" function compare "+(this.functionId == ruleDetailsModel.getFunctionId()));
		if(this.ruleId.equals(ruleDetailsModel.getRuleId())){
			
			if(this.functionId.equals(ruleDetailsModel.getFunctionId())){
					return 0;
			}else{
				return this.functionId - ruleDetailsModel.getFunctionId();
			}
		}else{
			return this.ruleId - ruleDetailsModel.getRuleId();
		}
	}
}
