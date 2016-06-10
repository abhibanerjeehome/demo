package com.redwood.rp.common.rule.service.impl;

import static com.redwood.rp.rule.constant.RuleServiceConstant.GET_ALL_RULES;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redwood.rp.core.annotation.CacheAction;
import com.redwood.rp.core.annotation.CacheObject;
import com.redwood.rp.core.annotation.Loggable;
import com.redwood.rp.core.base.pattern.delegate.BusinessDelegate;
import com.redwood.rp.core.vo.generic.ACPRequest;
import com.redwood.rp.core.vo.generic.ACPResponse;
import com.redwood.rp.core.vo.json.ErrorVO;
import com.redwood.rp.genericquery.constant.QueryType;
import com.redwood.rp.genericquery.vo.generic.GenericQueryRequest;
import com.redwood.rp.common.rule.bo.RuleDetailsBO;
import com.redwood.rp.common.rule.bo.RuleDetailsModel;
import com.redwood.rp.common.rule.bo.RuleMasterModel;
import com.redwood.rp.common.rule.bo.RuleTargetDetailsModel;
import com.redwood.rp.common.rule.service.RuleService;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.constant.RequestType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.BooleanUtil;
import com.redwood.rp.property.constant.ServiceNameMapping;

@Named
public class RuleServiceImpl implements RuleService {
	private static Logger logger = LoggerFactory.getLogger(RuleServiceImpl.class.getName());

	public  Map<String, List<RuleDetailsBO>> ruleMap;
	
	public Map<Integer, RuleMasterModel> ruleDetailsBOs;

	private static List<String> typeToAdd = new ArrayList<String>();
	
	private boolean shallInvokeEnhancedRules;
	@Autowired
	private BusinessDelegate businessDelegate;

	@Override
	@Loggable
	public <T> void updateObjectWithRules(Object boArr[], T resObject) throws ServiceException {
		logger.info("RULE ENGINE CALLED ...");
		//System.out.println("RULE ENGINE CALLED ...");
		List<RuleMasterModel> ruleMasterModels = null;
		if(ruleMap!=null && ruleMap.keySet()!=null && ruleMap.keySet().size() >0){
		  ruleMasterModels = formRuleMasterModel(ruleMap);
		}
		if(ruleMap == null || ruleMap.size() == 0 || ruleMasterModels == null || ruleMasterModels.size() == 0){
			getAllRules();
			ruleMasterModels = formRuleMasterModel(ruleMap);
		}
		
		if (typeToAdd.size() == 0) {
				typeToAdd.add("java.lang.String");
				typeToAdd.add("long");
				typeToAdd.add("int");
				typeToAdd.add("short");
				typeToAdd.add("double");
				typeToAdd.add("float");
				typeToAdd.add("boolean");
				typeToAdd.add("java.lang.Boolean");
				typeToAdd.add("java.lang.Byte");
				typeToAdd.add("java.math.BigDecimal");
				typeToAdd.add("java.math.BigInteger");
				typeToAdd.add("java.lang.Double");
				typeToAdd.add("java.lang.Float");
				typeToAdd.add("java.lang.Long");
				typeToAdd.add("java.lang.Short");
				// turn on strong typing
				//ctx.setStrongTyping(true);
			}
			logger.info("rule map --- " + ruleMap.size());
			logger.info("rule map --- " + ruleMap.keySet());
			if (boArr != null && boArr.length > 0) {
				for (Object bo : boArr) {
					try{
						if (bo != null)
							getPropertyFields(bo, ruleDetailsBOs, resObject);
					} catch (Exception exception) {
						logger.error(
								"While executing Rule Engine throwing exception, for response object {}",
								resObject.getClass(), exception);
						ErrorVO errorVO = new ErrorVO("RE001",
								"While executing Rule Engine throwing exception, for DB object object "
										+ bo.getClass()+" and response object "+resObject.getClass(), exception.getMessage());
						throw new ServiceException(ExceptionType.EXCEPTION, errorVO);
					}
				}
			}
	}

	// private <T> Map<String, Object> getResponseFieldMap(T resObject) throws
	// IllegalAccessException
	// , InvocationTargetException, NoSuchMethodException{
	//
	// Field[] fields = resObject.getClass().getDeclaredFields();
	// if(typeToAdd.size() == 0){
	// typeToAdd.add("java.lang.String");
	// typeToAdd.add("long");
	// typeToAdd.add("int");
	// typeToAdd.add("short");
	// typeToAdd.add("double");
	// typeToAdd.add("float");
	// typeToAdd.add("boolean");
	// typeToAdd.add("java.lang.Boolean");
	// typeToAdd.add("java.lang.Byte");
	// typeToAdd.add("java.math.BigDecimal");
	// typeToAdd.add("java.math.BigInteger");
	// typeToAdd.add("java.lang.Double");
	// typeToAdd.add("java.lang.Float");
	// typeToAdd.add("java.lang.Long");
	// typeToAdd.add("java.lang.Short");
	// }
	// // for(Field field : fields){
	// // try{
	// // if(typeToAdd.contains(field.getType().getName())){
	// // if(!StringUtils.startsWith(field.getName(), "serialVersionUID")){
	// // System.out.println("field registered for rule... "+field.getName()+
	// " for "+resObject.getClass());
	// // setterMethodMap.put(field.getName(), (BeanUtils.getProperty(resObject,
	// field.getName())));
	// // }else{
	// // continue;
	// // }
	// // }else{
	// // getResponseFieldMap(PropertyUtils.getProperty(resObject,
	// field.getName()));
	// //
	// // }
	// // }catch(NullPointerException nullPointerException){
	// // //nullPointerException.printStackTrace();
	// // continue;
	// // }catch(NoSuchMethodException noSuchMethodException){
	// // //noSuchMethodException.printStackTrace();
	// // continue;
	// // }
	// // }
	// return setterMethodMap;
	//
	// }

	public <T> void copyProperties(T resObject, Map<String, Object> setterMethodMap) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Field[] fields = resObject.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				if (typeToAdd.contains(field.getType().getName())
						&& (setterMethodMap.containsKey(field.getName()))) {
					logger.info("Rule Engine copying ... "
							+ field.getName() + " in " + resObject.getClass()
							+ " value " + setterMethodMap.get(field.getName()));
					BeanUtils.copyProperty(resObject, field.getName(),
							setterMethodMap.get(field.getName()));
				}
				/*
				comment out for performance temporary fix
				 else if ((((Type)(field.getClass()) instanceof java.util.Collection) || (((Type)field
						.getClass()) instanceof java.util.Map))
				// || (((Type)field.getClass()) instanceof Array)
				) {
					// TODO for collections, map and array
					//System.out.println("Rule Engine collection");
					logger.info("Rule Engine collection");
					BeanUtils.copyProperty(resObject, field.getName(),
							setterMethodMap.get(field.getName()));
					// BeanUtils.copyProperty(resObject, field.getName(),
					// setterMethodMap.get(field.getName()));
				} else {
					copyProperties(PropertyUtils.getProperty(resObject,	field.getName()), setterMethodMap);
				}*/
			} catch (NullPointerException nullPointerException) {
				continue;
			}/*
				comment out for performance temporary fix
			 catch (NoSuchMethodException noSuchMethodException) {
				continue;
			}*/
		}
	}

	@Override
	public <T> void updateObjectWithRules(Object bo, T resObject) throws ServiceException {
		Object[] boArr = new Object[1];
		boArr[0] = bo;
		this.updateObjectWithRules(boArr, resObject);
	}

	@Override
	public void updateObjectWithRules(Object bo) throws ServiceException {

		Object boArr[] = new Object[1];
		boArr[0] = bo;

		this.updateObjectWithRules(boArr, bo);
	}

	public Map<Integer, RuleMasterModel> getRulesForService(List<RuleMasterModel> ruleMasterModels) {

		// TODO
		List<RuleDetailsBO> ruleDetailsBOList = new ArrayList<RuleDetailsBO>();
		for (String key : ruleMap.keySet()) {
			ruleDetailsBOList.addAll(ruleMap.get(key));
		}
		ruleDetailsBOs = new TreeMap();

		for (RuleMasterModel ruleMasterModel : ruleMasterModels) {
			for (RuleDetailsBO ruleDetailsBO : ruleDetailsBOList) {
				if (ruleMasterModel.getRuleId() == ruleDetailsBO.getRuleId()) {
					ruleDetailsBOs.put(ruleMasterModel.getRuleId(),
							ruleMasterModel);
					break;
				}
			}
		}
//		for (Integer ruleId : rulesQueueForService.keySet()) {
//			logger.info("------ " + ruleId);
//		}
		return ruleDetailsBOs;
	}

	private <T> void getPropertyFields(Object ob, Map<Integer, RuleMasterModel> rulesQueueForService, T respObject)	throws ServiceException {
		if (rulesQueueForService != null && rulesQueueForService.size() > 0) {
			Map<String, Object> setterMethodMap = new HashMap<String, Object>();
			RuleMasterModel ruleMasterModel = null;
			Map<Integer, RuleDetailsModel> functionQueueForRule = null;
			RuleDetailsModel ruleDetailsModel = null;
			Set<RuleTargetDetailsModel> targetDetailsModels = null;
			try {
				logger.info(rulesQueueForService.keySet().toString());
				//System.out.println(rulesQueueForService.keySet());
				for (Integer ruleId : rulesQueueForService.keySet()) {
					//System.out.println(ruleId);
					logger.info(ruleId.toString());
					// skipping anomaly rules , in case it ran successfully ...
					if (ruleMasterModel != null && targetDetailsModels != null ){
						if(StringUtils.equalsIgnoreCase(ruleMasterModel.getAnomalyRuleId(),String.valueOf(ruleId))){
							ruleMasterModel = rulesQueueForService.get(ruleId);
							continue;
						}
					}
					ruleMasterModel = rulesQueueForService.get(ruleId);
					functionQueueForRule = getFunctionsForRules(ruleMasterModel);
					if (functionQueueForRule != null
							&& functionQueueForRule.size() > 0) {
						Set<Integer> functionKeySet = new HashSet(functionQueueForRule.keySet());
						for (Integer functionId : functionQueueForRule.keySet()) {
							if(!functionKeySet.contains(functionId)){
								continue;
							}
							ruleDetailsModel = functionQueueForRule
									.get(functionId);
							//System.out.println("evalConditionsAndGetTargets getting called for " + ruleDetailsModel.getFunctionId());
							logger.info("evalConditionsAndGetTargets getting called for " + ruleDetailsModel.getFunctionId());
							try{
							targetDetailsModels = evalConditionsAndGetTargets(
									ruleDetailsModel, ob, functionQueueForRule,
									respObject,functionKeySet);
							if (targetDetailsModels != null) {
								for (RuleTargetDetailsModel targetDetailsModel : targetDetailsModels) {
									if (targetDetailsModel.getIsField() != null
											&& targetDetailsModel.getIsField()) {
										setterMethodMap
												.put(targetDetailsModel
														.getTargetObject(),
														PropertyUtils
																.getProperty(
																		ob,
																		targetDetailsModel
																				.getTargetValue()));
										logger.info(">>> adding in setter queue "+ targetDetailsModel.getTargetObject()
												+ " "
												+ targetDetailsModel.getTargetValue());
//										System.out
//												.println(">>> adding in setter queue "
//														+ targetDetailsModel
//																.getTargetObject()
//														+ " "
//														+ targetDetailsModel
//																.getTargetValue());
									} else {
										setterMethodMap.put(targetDetailsModel
												.getTargetObject(),
												targetDetailsModel
														.getTargetValue());
										logger.info(">>> adding in setter queue not field "
																+ targetDetailsModel
																.getTargetObject()
														+ " "
														+ targetDetailsModel
																.getTargetValue());
//										System.out
//												.println(">>> adding in setter queue not field "
//														+ targetDetailsModel
//																.getTargetObject()
//														+ " "
//														+ targetDetailsModel
//																.getTargetValue());
									}
//									logger.info(">>> functions "
//											+ functionQueueForRule.get(
//													functionId).getFieldName()
//											+ " "
//											+ PropertyUtils.getProperty(
//													respObject,
//													targetDetailsModel
//															.getTargetValue()));
//									System.out.println(">>> functions "
//											+ functionQueueForRule.get(
//													functionId).getFieldName()
//											+ " "
//											+ PropertyUtils.getProperty(
//													respObject,
//													targetDetailsModel
//															.getTargetValue()));
								}
							}else{
								
							}
							
							}catch (Exception exception) {
								logger.debug(
										"While executing Rule Engine throwing exception, for bo object {} and response {}",
										ob,
										respObject.getClass());
							}
						}
					}
					if(setterMethodMap!=null && setterMethodMap.size()>0){
						copyProperties(respObject, setterMethodMap);
						if(ruleDetailsModel.getEnhancedRuleInvocationRequired()!=null && ruleDetailsModel.getEnhancedRuleInvocationRequired()){
							Class params[] = {respObject.getClass()};
							Method method = ob.getClass().getDeclaredMethod("enhancedRules", params);
							method.invoke(ob, respObject);
						}
					}	
				}
//			} catch (IntrospectionException introspectionException) {
//				logger.debug(
//						"While executing Rule Engine throwing exception, for response object {} in {}",
//						introspectionException.getMessage(),
//						respObject.getClass());
			} catch (Exception exception) {
				exception.printStackTrace();
				ErrorVO errorVO = new ErrorVO("RE001",
						"While executing Rule Engine throwing exception, for response object "
								+ respObject.getClass(), exception.getMessage());
				throw new ServiceException(ExceptionType.EXCEPTION, errorVO);
			}
		}
	}

	private Map<Integer, RuleDetailsModel> getFunctionsForRules(RuleMasterModel ruleMasterModel) {
		Map<Integer, RuleDetailsModel> functionQueueForRule = new TreeMap<Integer, RuleDetailsModel>();
		Set<RuleDetailsModel> ruleDetailsModels = ruleMasterModel
				.getRuleDetailsModels();
		if (ruleDetailsModels != null && ruleDetailsModels.size() > 0) {
			for (RuleDetailsModel ruleDetailsModel : ruleDetailsModels) {
				functionQueueForRule.put(ruleDetailsModel.getFunctionId(),
						ruleDetailsModel);
				logger.info("Added functionId----- "
						+ ruleDetailsModel.getFunctionId()+" for rule ----- "+ruleDetailsModel.getRuleId());
//				System.out.println("Added functionId----- "
//						+ ruleDetailsModel.getFunctionId()+" for rule ----- "+ruleDetailsModel.getRuleId());
			}
		}

		return functionQueueForRule;
	}

	private <T> Set<RuleTargetDetailsModel> evalConditionsAndGetTargets(
			RuleDetailsModel ruleDetailsModel, Object ob,
			Map<Integer, RuleDetailsModel> ruleDetailsModelMap, T respObject,Set<Integer> functionKeySet)
			throws IntrospectionException {

		Integer chainedFunctionId = ruleDetailsModel.getChainedFunctionId();
		Integer anomalyFunctionId = ruleDetailsModel.getAnomalyFunctionId();
		Set<RuleTargetDetailsModel> targetDetailsModels = null;
		Boolean result = null;
		StringBuilder expressionToEval = new StringBuilder();
		logger.info("--- in evalConditionsAndGetTargets " + ruleDetailsModel.getFieldName() + " ob " + ob);
		// System.out.println("--- in evalConditionsAndGetTargets "+ruleDetailsModel.getFieldName()+" ob "+ob);
		if (ruleDetailsModel != null && ob != null) {
			if (StringUtils.isNotBlank(ruleDetailsModel.getFieldName())) {
				expressionToEval.append(ruleDetailsModel.getFieldName());
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(ruleDetailsModel.getFieldName(), ob.getClass());

				if (StringUtils.equalsIgnoreCase("java.lang.String", propertyDescriptor.getPropertyType().getName())) {
					boolean isNotEqualsOperator = false;
					if (StringUtils.equalsIgnoreCase(ruleDetailsModel.getLogicalOperator(), "=="))
						expressionToEval.append(" soundslike ");
					else {
						if (StringUtils.equalsIgnoreCase(ruleDetailsModel.getLogicalOperator(), "in")) {
							String[] inRange = ruleDetailsModel.getFieldValue().split(",");
							if (inRange.length > 0) {
								for (String inr : inRange) {
									expressionToEval = new StringBuilder();
									expressionToEval.append(ruleDetailsModel.getFieldName());
									expressionToEval.append(" soundslike ");
									expressionToEval.append("'");
									expressionToEval.append(inr);
									expressionToEval.append("'");
									result = (Boolean) MVEL.eval(expressionToEval.toString(), ob);
									if (result) {
										break;
									}
								}
							}
						} else if (StringUtils.equalsIgnoreCase(ruleDetailsModel.getLogicalOperator(), "not in")) {
							String[] inRange = ruleDetailsModel.getFieldValue().split(",");
							if (inRange.length > 0) {
								for (String inr : inRange) {
									expressionToEval = new StringBuilder();
									expressionToEval.append(ruleDetailsModel.getFieldName());
									expressionToEval.append(" soundslike ");
									expressionToEval.append("'");
									expressionToEval.append(inr);
									expressionToEval.append("'");
									result = (Boolean) MVEL.eval(expressionToEval.toString(), ob);
									if (result) {
										result = false;
										break;
									}
								}
								result = true;
							}
						} else if (StringUtils.equalsIgnoreCase(ruleDetailsModel.getLogicalOperator(), "!=*")) {
							Field field = null;
							Object iWantThis = null;
							try {
								field = ob.getClass().getDeclaredField(ruleDetailsModel.getFieldName());
								field.setAccessible(true);
								iWantThis = (Object) field.get(ob);
							} catch (NoSuchFieldException e) {
							} catch (SecurityException e) {
							} catch (IllegalArgumentException e) {
							} catch (IllegalAccessException e) {
							}
							if (iWantThis == null) {
								result = false;
							} else {
								ruleDetailsModel.setLogicalOperator("!=");
							}
						}
						if (StringUtils.equalsIgnoreCase(ruleDetailsModel.getLogicalOperator(), "!=")) {
							isNotEqualsOperator = true;
							expressionToEval = new StringBuilder();
							expressionToEval.append('!').append(ruleDetailsModel.getFieldName()).append(".equalsIgnoreCase('").append(ruleDetailsModel.getFieldValue()).append("')");
						}
						else {
							expressionToEval.append(ruleDetailsModel.getLogicalOperator());
						}
					}
					if (!isNotEqualsOperator) {
						expressionToEval.append("'");
						expressionToEval.append(ruleDetailsModel.getFieldValue());
						expressionToEval.append("'");
					}
				} else {
					if (StringUtils.equalsIgnoreCase("java.lang.Boolean", propertyDescriptor.getPropertyType().getName())
							|| StringUtils.equalsIgnoreCase("boolean", propertyDescriptor.getPropertyType().getName())) {
						result = (Boolean) MVEL.eval(expressionToEval.toString(), ob);
						String fieldVal = result.toString();
						if (StringUtils.equalsIgnoreCase(ruleDetailsModel.getLogicalOperator(), "==")) {
							if (ruleDetailsModel.getFieldValue() != null)
								result = StringUtils.equalsIgnoreCase(fieldVal, BooleanUtil.toBooleanObject(ruleDetailsModel.getFieldValue()).toString());
							else
								result = false;
						} else {
							result = !StringUtils.equalsIgnoreCase(fieldVal, BooleanUtil.toBooleanObject(ruleDetailsModel.getFieldValue()).toString());
						}
					} else {
						expressionToEval.append(ruleDetailsModel.getLogicalOperator());
						expressionToEval.append(ruleDetailsModel.getFieldValue());
					}
				}
				// }catch(IntrospectionException introspectionException ){
				// introspectionException.printStackTrace();
				// }
			}
			// if(expressionToEval.toString().length() > 0 &&
			// expressionToEval.toString().contains(" strsim ")){
			// result = (((Double) MVEL.eval(expressionToEval.toString(), ob)) >
			// 0.0) ? Boolean.TRUE : Boolean.FALSE;
			// }
			if (result == null) { // not evaluated for boolean field
				if (expressionToEval.toString().length() > 0) {
					// try{
					result = (Boolean) MVEL.eval(expressionToEval.toString(), ob);
					// }catch(Exception exception){
					// exception.printStackTrace();
					// }
				} else {
					result = true;
				}
			}
			targetDetailsModels = ruleDetailsModel.getRuleTargetDetailsBOs();
			logger.info("Condition executed for functionId " + ruleDetailsModel.getFunctionId() + " " + expressionToEval.toString() + " result " + result);
			// System.out.println("Condition executed for functionId "
			// + ruleDetailsModel.getFunctionId() + " "
			// + stringBuffer.toString() + " result " + result);
			// true
			if (result != null && result) {
				// no more chained conditions, or current condition is true and
				// conditions specified in 'or' is not need to execute
				if (chainedFunctionId == null || StringUtils.equalsIgnoreCase(ruleDetailsModel.getFunctionConcat(), "OR")
						|| StringUtils.equalsIgnoreCase(ruleDetailsModel.getFunctionConcat(), "||")) {
					System.out.println("*** Rule " + ruleDetailsModel.getRuleId() + " function " + ruleDetailsModel.getFieldName() + " " + ruleDetailsModel.getLogicalOperator()
							+ " " + ruleDetailsModel.getFieldValue());
					return targetDetailsModels;
				} else if ((chainedFunctionId != null)
						&& (StringUtils.equalsIgnoreCase(ruleDetailsModel.getFunctionConcat(), "AND") || StringUtils.equalsIgnoreCase(ruleDetailsModel.getFunctionConcat(), "&&") || StringUtils
								.equalsIgnoreCase(ruleDetailsModel.getFunctionConcat(), "|")) || StringUtils.equalsIgnoreCase(ruleDetailsModel.getFunctionConcat(), "&")) {
					return evalConditionsAndGetTargets(ruleDetailsModelMap.get(chainedFunctionId), ob, ruleDetailsModelMap, respObject, functionKeySet);
				}
			} else if (result != null) { // false
				if (anomalyFunctionId != null && anomalyFunctionId != 0) {
					return evalConditionsAndGetTargets(ruleDetailsModelMap.get(anomalyFunctionId), ob, ruleDetailsModelMap, respObject, functionKeySet);
				} else {
					if (chainedFunctionId != null) {
						// remove all chained functions from queue
						Set<Integer> chainedFunctionIds = new HashSet();
						while (chainedFunctionId != null && chainedFunctionId > 0) {
							chainedFunctionIds.add(chainedFunctionId);
							if (ruleDetailsModelMap.get(chainedFunctionId).getChainedFunctionId() != null)
								chainedFunctionId = new Integer(ruleDetailsModelMap.get(chainedFunctionId).getChainedFunctionId());
							else
								chainedFunctionId = null;
						}
						// problem is map on which loop is been invoked , we
						// can't remove keys of it ... due to some
						// ConcurrentModificationException
						functionKeySet.removeAll(chainedFunctionIds);
					}
					return null;
				}
			}
		}
		return targetDetailsModels;
	}

	@Override
	public List<RuleMasterModel> formRuleMasterModel(
			Map<String, List<RuleDetailsBO>> ruleMap) {

		List<RuleMasterModel> ruleMasterModels = new ArrayList<RuleMasterModel>();
		List<RuleDetailsModel> ruleDetailsModels = new ArrayList<RuleDetailsModel>();
		List<RuleTargetDetailsModel> ruleTargetDetailsModels = new ArrayList<RuleTargetDetailsModel>();
		Set<RuleTargetDetailsModel> ruleTargetDetailsModelSet = null;
		Set<RuleDetailsModel> ruleDetailsModelsSet = null;
		// get all Targets -- no.s are same as no of records
		for (String serviceName : ruleMap.keySet()) {
			List<RuleDetailsBO> ruleDetailsBOs = ruleMap.get(serviceName);
			for (RuleDetailsBO ruleDetailsBO : ruleDetailsBOs) {
				ruleTargetDetailsModels
						.add(new RuleTargetDetailsModel(ruleDetailsBO
								.getRuleId(), ruleDetailsBO.getFunctionId(),
								ruleDetailsBO.getTargetObject(), ruleDetailsBO
										.getTargetValue(), ruleDetailsBO
										.getEvalRequired(), ruleDetailsBO
										.getIsField()));
			}
		}
		Collections.sort(ruleTargetDetailsModels);

		// get all functions --
		RuleDetailsModel ruleDetailsModel = null;
		for (String serviceName : ruleMap.keySet()) {
			List<RuleDetailsBO> ruleDetailsBOs = ruleMap.get(serviceName);
			for (RuleDetailsBO ruleDetailsBO : ruleDetailsBOs) {
				ruleDetailsModel = new RuleDetailsModel(
						ruleDetailsBO.getRuleId(),
						ruleDetailsBO.getFieldName(),
						ruleDetailsBO.getLogicalOperator(),
						ruleDetailsBO.getFieldValue(),
						ruleDetailsBO.getFunctionConcat(),
						ruleDetailsBO.getChainedFunctionId(),
						ruleDetailsBO.getFunctionId(), ruleDetailsBO.getAnomalyFunctionId(),ruleDetailsBO.getEnhancedRuleInvocationRequired());
				if (!ruleDetailsModels.contains(ruleDetailsModel)) {
					ruleDetailsModels.add(ruleDetailsModel);
					ruleTargetDetailsModelSet = new HashSet<RuleTargetDetailsModel>();
					for (RuleTargetDetailsModel ruleTargetDetailsModel : ruleTargetDetailsModels) {
						if (ruleDetailsModel.getRuleId() == ruleTargetDetailsModel
								.getRuleId()
								&& ruleDetailsModel.getFunctionId() == ruleTargetDetailsModel
										.getFunctionId()) {
							ruleTargetDetailsModelSet
									.add(ruleTargetDetailsModel);
						}
					}
					ruleDetailsModel
							.setRuleTargetDetailsBOs(ruleTargetDetailsModelSet);
					ruleDetailsModels.add(ruleDetailsModel);
				}

			}
		}
		Collections.sort(ruleDetailsModels);

		// get all rule --
		RuleMasterModel ruleMasterModel = null;
		for (String serviceName : ruleMap.keySet()) {
			List<RuleDetailsBO> ruleDetailsBOs = ruleMap.get(serviceName);
			for (RuleDetailsBO ruleDetailsBO : ruleDetailsBOs) {

				ruleMasterModel = new RuleMasterModel(
						ruleDetailsBO.getRuleId(),
						ruleDetailsBO.getDescription(),
						ruleDetailsBO.getService(),
						ruleDetailsBO.getOrderOfExecution(),
						ruleDetailsBO.getAnomalyRuleId(),
						ruleDetailsBO.getRuleConcat(),
						ruleDetailsBO.getChainedRuleId(),
						ruleDetailsBO.getIsAnomaly(), null);
				if (!ruleMasterModels.contains(ruleMasterModel)) {
					ruleMasterModels.add(ruleMasterModel);
					ruleDetailsModelsSet = new HashSet<RuleDetailsModel>();
					for (RuleDetailsModel detailsModel : ruleDetailsModels) {
						if (ruleMasterModel.getRuleId() == detailsModel
								.getRuleId()) {
							ruleDetailsModelsSet.add(detailsModel);
						}
					}
					ruleMasterModel.setRuleDetailsModels(ruleDetailsModelsSet);
				}
			}
		}
		Collections.sort(ruleMasterModels);
		
//		for(RuleMasterModel ruleMasterModel2 : ruleMasterModels){
//			System.out.println("RuleId "+ ruleMasterModel2.getRuleId());
//			for(RuleDetailsModel ruleDetailsModel2 : ruleMasterModel2.getRuleDetailsModels()){
//				System.out.println("\t"+"functionId "+ ruleDetailsModel2.getFunctionId());
//				for(RuleTargetDetailsModel ruleTargetDetailsModel : ruleDetailsModel2.getRuleTargetDetailsBOs()){
//					System.out.println("\t\t"+"Target "+ ruleTargetDetailsModel.getTargetObject());
//				}
//			}
//		}
		return ruleMasterModels;
	}
	
	//@PostConstruct
	public void getAllRules(){
		try{
			getRuleMap();
//			if(ruleMap!= null && ruleMap.keySet()!=null && ruleMap.keySet().size() >0){
//				getRulesForService(formRuleMasterModel(ruleMap));
//			}
//			System.out.println(">>>>>>>>> "+RuleServiceImpl.ruleMap.get("property").size());
//			for(String key : RuleServiceImpl.ruleMap.keySet()){
//				System.out.println(key+"::::::::"+RuleServiceImpl.ruleMap.get(key));
//			}
//			
		}catch(Exception serviceException){
			serviceException.printStackTrace();
		}
	}
	
	@CacheObject(cacheAction=CacheAction.AddToCache)
	public Map<String, List<RuleDetailsBO>> getRuleMap(){
		try{
		//Creates the genericQueryRequest object
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryName(GET_ALL_RULES);
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("SERVICE_NAMES", "propertyService");
		genericQueryRequest.setParameterMap(paramMap);
		ACPRequest acpGlobalAssetRequest = new ACPRequest(
				ServiceNameMapping.FETCH_DETAILS_AS_MAP,RequestType.JSON,genericQueryRequest);

		//Call the real service and get the response
		//System.out.println("businessDelegate -----"+businessDelegate+" acpGlobalAssetRequest---"+acpGlobalAssetRequest);
		ACPResponse globalAssetResponse = businessDelegate.execute(acpGlobalAssetRequest);
		List<Map<String, Object>> acpResponseList=(ArrayList<Map<String, Object>>)globalAssetResponse.getPayload();
		RuleDetailsBO ruleDetailsBO = null;
		List<RuleDetailsBO> ruleDetailsBOs = new ArrayList<RuleDetailsBO>();
		for(Map<String, Object> recordMap : acpResponseList){
	
			ruleDetailsBO = new RuleDetailsBO(recordMap.get("ruleId"),
					recordMap.get("description"),
					recordMap.get("service"),
					recordMap.get("orderOfExecution"),
					recordMap.get("anomalyRuleId"),
					recordMap.get("ruleConcat"),
					recordMap.get("chainedRuleId"),
					recordMap.get("isAnomaly"),
					recordMap.get("fieldName"),
					recordMap.get("logicalOperator"),
					recordMap.get("fieldValue"),
					recordMap.get("functionConcat"),
					recordMap.get("chainedFunctionId"),
					recordMap.get("functionId"),
					recordMap.get("targetObject"),
					recordMap.get("targetValue"),
					recordMap.get("evalRequired"),
					recordMap.get("isField"),
					recordMap.get("anomalyFunctionId"));
			ruleDetailsBO.setEnhancedRuleInvocationRequired(recordMap.get("enhanced_rule_required"));
			//org.apache.commons.beanutils.BeanUtils.copyProperties(recordMap, ruleDetailsBO );
			ruleDetailsBOs.add(ruleDetailsBO);
		}
		
		ruleMap = new HashMap<String, List<RuleDetailsBO>>();
		//System.out.println(">>>>>>>>> "+ruleDetailsBOs.size());
			for(RuleDetailsBO ruleDetailsBODetail : ruleDetailsBOs ){
				if(ruleMap.containsKey(ruleDetailsBODetail.getService())){
					ruleMap.get(ruleDetailsBODetail.getService()).add(ruleDetailsBODetail);
					//System.out.println(ruleDetailsBODetail.getService()+"::::::::"+RuleServiceImpl.ruleMap.get(ruleDetailsBODetail.getService()));
				}else{
					List<RuleDetailsBO> serviceSpecificRules = new ArrayList<RuleDetailsBO>();
					serviceSpecificRules.add(ruleDetailsBODetail);
					ruleMap.put(ruleDetailsBODetail.getService(),serviceSpecificRules);		
					//System.out.println(ruleDetailsBODetail.getService()+"::::::::"+RuleServiceImpl.ruleMap.get(ruleDetailsBODetail.getService()));
				}
			}
//			if(RuleServiceImpl.ruleMap.keySet()!=null && RuleServiceImpl.ruleMap.keySet().size() >0){
//				getRulesForService(formRuleMasterModel(RuleServiceImpl.ruleMap));
//			}
//			System.out.println(">>>>>>>>> "+RuleServiceImpl.ruleMap.get("property").size());
//			for(String key : RuleServiceImpl.ruleMap.keySet()){
//				System.out.println(key+"::::::::"+RuleServiceImpl.ruleMap.get(key));
//			}
//			
		}catch(ServiceException serviceException){
			serviceException.printStackTrace();
		}
		if(ruleMap!= null && ruleMap.keySet()!=null && ruleMap.keySet().size() >0){
			getRulesForService(formRuleMasterModel(ruleMap));
		}
		return ruleMap;
	}
	
	/*@PostConstruct
	public void initRuleMap() throws IllegalAccessException, InvocationTargetException{
		try{
		//Creates the genericQueryRequest object
		GenericQueryRequest genericQueryRequest = new GenericQueryRequest();
		genericQueryRequest.setQueryName(GET_ALL_RULES);
		genericQueryRequest.setQueryType(QueryType.SELECT);
		genericQueryRequest.setSP(false);
		ACPRequest acpGlobalAssetRequest = new ACPRequest(
				ServiceNameMapping.FETCH_DETAILS_AS_MAP,RequestType.JSON,genericQueryRequest);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("SERVICE_NAMES", "propertyService");
		genericQueryRequest.setParameterMap(paramMap);
		//Call the real service and get the response
		//System.out.println("businessDelegate -----"+businessDelegate+" acpGlobalAssetRequest---"+acpGlobalAssetRequest);
		ACPResponse globalAssetResponse = businessDelegate.execute(acpGlobalAssetRequest);
		List<Map<String, Object>> acpResponseList=(ArrayList<Map<String, Object>>)globalAssetResponse.getPayload();
		RuleDetailsBO ruleDetailsBO = null;
		List<RuleDetailsBO> ruleDetailsBOs = new ArrayList<RuleDetailsBO>();
		for(Map<String, Object> recordMap : acpResponseList){
	
			ruleDetailsBO = new RuleDetailsBO(recordMap.get("ruleId"),
					recordMap.get("description"),
					recordMap.get("service"),
					recordMap.get("orderOfExecution"),
					recordMap.get("anomalyRuleId"),
					recordMap.get("ruleConcat"),
					recordMap.get("chainedRuleId"),
					recordMap.get("isAnomaly"),
					recordMap.get("fieldName"),
					recordMap.get("logicalOperator"),
					recordMap.get("fieldValue"),
					recordMap.get("functionConcat"),
					recordMap.get("chainedFunctionId"),
					recordMap.get("functionId"),
					recordMap.get("targetObject"),
					recordMap.get("targetValue"),
					recordMap.get("evalRequired"),
					recordMap.get("isField"),
					recordMap.get("anomalyFunctionId"));
			//org.apache.commons.beanutils.BeanUtils.copyProperties(recordMap, ruleDetailsBO );
			ruleDetailsBOs.add(ruleDetailsBO);
		}
		
		RuleServiceImpl.ruleMap = new HashMap<String, List<RuleDetailsBO>>();
		//System.out.println(">>>>>>>>> "+ruleDetailsBOs.size());
			for(RuleDetailsBO ruleDetailsBODetail : ruleDetailsBOs ){
				if(RuleServiceImpl.ruleMap.containsKey(ruleDetailsBODetail.getService())){
					RuleServiceImpl.ruleMap.get(ruleDetailsBODetail.getService()).add(ruleDetailsBODetail);
					//System.out.println(ruleDetailsBODetail.getService()+"::::::::"+RuleServiceImpl.ruleMap.get(ruleDetailsBODetail.getService()));
				}else{
					List<RuleDetailsBO> serviceSpecificRules = new ArrayList<RuleDetailsBO>();
					serviceSpecificRules.add(ruleDetailsBODetail);
					RuleServiceImpl.ruleMap.put(ruleDetailsBODetail.getService(),serviceSpecificRules);		
					//System.out.println(ruleDetailsBODetail.getService()+"::::::::"+RuleServiceImpl.ruleMap.get(ruleDetailsBODetail.getService()));
				}
			}
//			if(RuleServiceImpl.ruleMap.keySet()!=null && RuleServiceImpl.ruleMap.keySet().size() >0){
//				getRulesForService(formRuleMasterModel(RuleServiceImpl.ruleMap));
//			}
//			System.out.println(">>>>>>>>> "+RuleServiceImpl.ruleMap.get("property").size());
//			for(String key : RuleServiceImpl.ruleMap.keySet()){
//				System.out.println(key+"::::::::"+RuleServiceImpl.ruleMap.get(key));
//			}
//			
		}catch(ServiceException serviceException){
			serviceException.printStackTrace();
		}
	}*/
}
