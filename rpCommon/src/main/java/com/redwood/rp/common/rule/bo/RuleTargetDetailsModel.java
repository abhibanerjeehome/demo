package com.redwood.rp.common.rule.bo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.redwood.rp.core.util.BooleanUtil;



public class RuleTargetDetailsModel implements Serializable,Comparable<RuleTargetDetailsModel> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer ruleId;
	private Integer functionId;
	private String targetObject;
	private String targetValue;
	private Boolean evalRequired;
	private Boolean isField;
	
	public RuleTargetDetailsModel(){}
	
	public RuleTargetDetailsModel(Integer ruleId,Integer functionId, String targetObject,
			String targetValue, Boolean evalRequired, Boolean isField) {
		super();
		this.ruleId = ruleId;
		this.functionId = functionId;
		this.targetObject = targetObject;
		this.targetValue = targetValue;
		this.evalRequired = evalRequired;
		this.isField = BooleanUtil.toBoolean(isField);
	}	
	public RuleTargetDetailsModel(Integer ruleId,Integer functionId, String targetObject,
			String targetValue, Boolean evalRequired, Integer isField) {
		super();
		this.ruleId = ruleId;
		this.functionId = functionId;
		this.targetObject = targetObject;
		this.targetValue = targetValue;
		this.evalRequired = evalRequired;
		this.isField = (isField!=null ? BooleanUtil.toBooleanObject(isField.toString()) : null);
	}
	public RuleTargetDetailsModel(Object ruleId, Object description, Object service,
			Object orderOfExecution, Object anomalyRuleId, Object ruleConcat,
			Object chainedRuleId, Object isAnomaly, Object fieldName,
			Object logicalOperator, Object fieldValue, Object functionConcat,
			Object chainedFunctionId, Object functionId, Object targetObject,
			Object targetValue, Object evalRequired, Object isField) {
		super();
		this.ruleId = (ruleId!=null ? Integer.valueOf(ruleId.toString()) : null);
		this.functionId = (functionId!=null ? Integer.valueOf(functionId.toString()) : null);
		this.targetObject = (targetObject!=null ? targetObject.toString() : null);
		this.targetValue = (targetValue!=null ? targetValue.toString() : null);
		this.evalRequired = (evalRequired!=null ? BooleanUtil.toBooleanObject(evalRequired.toString()) : null);
		this.isField = (isField!=null ? BooleanUtil.toBooleanObject(isField.toString()) : null);
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
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

//	@Override
//	public int compare(RuleTargetDetailsModel ruleTargetDetailsModel1, RuleTargetDetailsModel ruleTargetDetailsModel2) {
//		if()
//		return 0;
//	}
	
	public boolean equals(Object object){
		
		RuleTargetDetailsModel ruleTargetDetailsModel = null;
		if(object != null && object instanceof RuleTargetDetailsModel){
			ruleTargetDetailsModel = (RuleTargetDetailsModel)object;
			return ((this.ruleId == ruleTargetDetailsModel.getRuleId()) 
					&& (this.functionId == ruleTargetDetailsModel.getFunctionId())
					&& (this.targetObject == ruleTargetDetailsModel.getTargetObject()));
		}
			
			
		return false;	
	}

		@Override
		public int compareTo(RuleTargetDetailsModel ruleTargetDetailsModel) {
			
			if(this.ruleId == ruleTargetDetailsModel.getRuleId()){
				
				if(this.functionId == ruleTargetDetailsModel.getFunctionId()){
					
					if(StringUtils.equals(this.targetObject, ruleTargetDetailsModel.getTargetObject())){
						return 0;
					}else{
						return this.targetObject.compareTo(ruleTargetDetailsModel.getTargetObject());
					}
					
				}else{
					return this.functionId - ruleTargetDetailsModel.getFunctionId();
				}
			}else{
				return this.ruleId - ruleTargetDetailsModel.getRuleId();
			}
		}
}
