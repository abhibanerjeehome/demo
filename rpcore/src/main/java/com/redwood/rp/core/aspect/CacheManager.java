package com.redwood.rp.core.aspect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import com.redwood.rp.caching.service.CachingService;
import com.redwood.rp.core.annotation.CacheObject;
import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.ServiceException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.json.ErrorVO;

// TODO: Auto-generated Javadoc
/**
 * The Class Cacheable.
 */
@Aspect
public class CacheManager {

	/** The caching service. */
	@Inject
	private CachingService cachingService;

	private static Map<String, Map<String,String>> cacheConfigMap;

	/** The use cache. */
	@Value("${shouldCache}")
	private Boolean useCache;


	@Value("${service.name:propertyService}")
	private String serviceName;
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(CacheManager.class.getName());

	public CacheManager(){
		//this.initializeCacheConfig();
	}
	/**
	 * **************************************************************************
	 * 
	 * Instance Methods
	 * **************************************************************************.
	 *
	 * @param iJoinPoint the i join point
	 * @param cacheObject the cache object
	 * @return the object
	 * @throws Throwable the throwable
	 */

	@Around("@annotation(cacheObject)")
	public Object cacheObject(final ProceedingJoinPoint iJoinPoint, final CacheObject cacheObject) throws Throwable {
		ErrorVO errorVO = null;
		logger.info("cache aspect method add called ...");
		String key=getCacheConfigKey(iJoinPoint);
		logger.info(key);
		if (useCache() && !key.isEmpty()) {

			switch(cacheObject.cacheAction()){

			case AddToCache :
				return addToCache(iJoinPoint,key);

			case ModifyCache :
				return updateCache(iJoinPoint,key);

			case RemoveFromCache :
				return removeCacheObject(iJoinPoint,key);	

			default :
				errorVO = new ErrorVO(ErrorDescription.ERR_CD_CACHE_ASPECT, ErrorDescription.ERR_MSG_CACHE_ASPECT,ErrorDescription.ERR_MSG_CACHE_ASPECT );	
				throw new ServiceException(ExceptionType.EXCEPTION_PARAMETER, errorVO);
			}
		}

		return null;
	}


	/**
	 * Adds the to cache.
	 *
	 * @param iJoinPoint the i join point
	 * @param key the key
	 * @return the object
	 * @throws Throwable 
	 */
	private Object addToCache(final ProceedingJoinPoint iJoinPoint, String key) throws Throwable {

		Object cachableObject = null;
		String cacheDuration = null;
		try {
			try{

				cachableObject = cachingService.getMemcacheObject(key);
				
			} catch (ServiceException serviceException) {
//				logger.error("ERR_MSG=\"Error while fetching data from the cache\" EXCEPTION=", serviceException);       
				String errorDescription = "Error while fetching data from the cache : "+serviceException.getMessage();
				logger.error(MessageFormattor.errorFormat(CacheManager.class.getName(),
		                "addToCache", ExceptionType.EXCEPTION_SERVICE, errorDescription), serviceException);
				throw new ServiceException(ExceptionType.EXCEPTION_SERVICE,
						new ErrorVO(ErrorDescription.ERR_CD_MEMCACHE_ANNOTATION,ErrorDescription.ERR_MSG_MEMCACHE_ANNOTATION, errorDescription)); 		
			}
			if(cachableObject == null){
				logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ object NOT found in cache with key "+key);
				cachableObject = iJoinPoint.proceed();
				cacheDuration = getCacheConfigDuration(iJoinPoint);
				if(StringUtils.isNotEmpty(cacheDuration))
					cachingService.setMemcacheObject(key, Integer.valueOf(getCacheConfigDuration(iJoinPoint)), cachableObject);
				else
					cachingService.setMemcacheObject(key, 3000, cachableObject);

				//logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ object added in cache with key "+key);
			}else{
				logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ object found in cache with key "+key);
			}
		} catch (Throwable throwable) {
			logger.error("ERR_MSG=\"Error while fetching data from the cache\" EXCEPTION=", throwable);
			throw throwable;
		}

		return cachableObject;
	}


	/**
	 * Update cache.
	 *
	 * @param iJoinPoint the i join point
	 * @param key the key
	 * @return the object
	 * @throws Throwable 
	 */
	public Object updateCache(final ProceedingJoinPoint iJoinPoint, String key) throws Throwable {

		Object cachableObject = null;
		String cacheDuration = null;

		if (useCache() && !key.isEmpty()) {
			try {
				cachableObject = iJoinPoint.proceed();
				cacheDuration = getCacheConfigDuration(iJoinPoint);
				if(StringUtils.isNotEmpty(cacheDuration))
					cachingService.setMemcacheObject(key, Integer.valueOf(getCacheConfigDuration(iJoinPoint)), cachableObject);
				else
					cachingService.setMemcacheObject(key, 3000, cachableObject);
			} catch (Throwable throwable) {
				logger.error("ERR_MSG=\"Error while fetching data from the cache\" EXCEPTION=", throwable);
				throw throwable;
			}
		}

		return cachableObject;
	}



	/**
	 * Removes the cache object.
	 *
	 * @param iJoinPoint the i join point
	 * @param key the key
	 * @return the object
	 * @throws Throwable 
	 */
	public Object removeCacheObject(final ProceedingJoinPoint iJoinPoint, String key) throws Throwable{

		if (useCache() && !key.isEmpty()) {
			try {

				cachingService.deletetMemacacheObject(key);
				return iJoinPoint.proceed();
			} catch (Throwable throwable) {
				logger.error("ERR_MSG=\"Error while fetching data from the cache\" EXCEPTION=", throwable);
				throw throwable;
			}
		}

		return null;
	}

	/**
	 * Generate key.
	 *
	 * @param iJoinPoint the i join point
	 * @return the string
	 * @throws Throwable the throwable
	 */
	@SuppressWarnings("unused")
	private String  getUri(ProceedingJoinPoint iJoinPoint) throws Throwable{

		Signature signature = iJoinPoint.getSignature();

		StringBuffer key = new StringBuffer();
		key.append(signature.getDeclaringType().getSimpleName());
		key.append("&");
		key.append(iJoinPoint.getSignature().getName());
		//key.append(fetchMethodArgAndVal( iJoinPoint));

		return key.toString();
	}

	/**
	 * Method for arguments.
	 *
	 * @param iJoinPoint the i join point
	 * @return String
	 * @throws JSONException the jSON exception
	 */
	@SuppressWarnings("unused")
	private String fetchMethodArgAndVal(final ProceedingJoinPoint iJoinPoint) throws JSONException {
		StringBuffer returnValue = new StringBuffer();
		Object[] listOfArguments = iJoinPoint.getArgs();
		Signature signature = iJoinPoint.getSignature();
		JSONObject methodParamNameValues = null;
		String[] parameterNames = null;
		MethodSignature methodSignature = null;
		if (signature instanceof MethodSignature) {
			methodSignature = (MethodSignature) signature;
			parameterNames = methodSignature.getParameterNames();
		}
		if (listOfArguments != null && parameterNames != null) {
			methodParamNameValues = new JSONObject();
			for (int iCounter = 0; iCounter < listOfArguments.length; iCounter++) {
				if (listOfArguments[iCounter] != null) {
					methodParamNameValues.put(parameterNames[iCounter], listOfArguments[iCounter]);
				} else {
					methodParamNameValues.put(parameterNames[iCounter], "NULL");
				}
			}
		}
		if (methodParamNameValues != null) {
			returnValue.append("&parameters").append("=").append(methodParamNameValues.toString());
		}
		return returnValue.toString();
	}


	/**
	 * Check if cache can be used.
	 *It is a configured value
	 * @return true, if yes
	 */
	private boolean useCache() {
		return useCache;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void initializeCacheConfig(){
		logger.info("initializeCacheConfig called");
		HierarchicalConfiguration aConfig = null;
		cacheConfigMap = new HashMap<String,Map<String,String>>();
		Map<String,String> cacheConfigParamMap = new HashMap<String,String>();

		try {
			aConfig = new XMLConfiguration(new ClassPathResource("cache-config.xml").getFile());
		} catch(FileNotFoundException ex) {
			logger.info("WARN_MSG=\"Cache-Config.xml not found in classpath!\"");
		} catch (ConfigurationException anException) {
			logger.info("ERR_MSG=\"Failed to read configuration\" EXCEPTION="+ anException);
		} catch (IOException anException) {
			logger.info("ERR_MSG=\"Failed to open cache-config.xml\" EXCEPTION="+ anException);
		}
		if (aConfig != null) {
			aConfig.setExpressionEngine(new XPathExpressionEngine());
			Object aURIValues = aConfig.getProperty("/service/uri");
			List<String> aCacheURIList = null;
			if (aURIValues instanceof Collection) {
				aCacheURIList = (List<String>) aURIValues;
			}else if(aURIValues instanceof String) {
				aCacheURIList = new ArrayList<String>();
				aCacheURIList.add(aURIValues.toString());
			}
			aURIValues.getClass();
			if (aCacheURIList != null) {
				for(String aCacheURI : aCacheURIList){
					cacheConfigParamMap = new HashMap<String,String>();
					logger.info("---------------------aCacheURI--------------"+aCacheURI);
					cacheConfigParamMap.put("cacheKey", getConfigProperty(aConfig, aCacheURI, "cacheKey"));
					cacheConfigParamMap.put("cacheDuration", getConfigProperty(aConfig, aCacheURI, "cacheDuration"));
					cacheConfigParamMap.put("paramsToBeAddedInCache", getConfigProperty(aConfig, aCacheURI, "paramsToBeAddedInCache"));
					logger.info("---------------------cacheKey + cacheKey + paramsToBeAddedInCache--------------"+cacheConfigParamMap.get("cacheKey")
							+" "+cacheConfigParamMap.get("cacheDuration")+" " +cacheConfigParamMap.get("paramsToBeAddedInCache"));
					cacheConfigMap.put(aCacheURI, cacheConfigParamMap);
				}
			}
			for(String aCacheURI : cacheConfigMap.keySet()){
				logger.info("-----" +aCacheURI+"---"+cacheConfigMap.get(aCacheURI));
			}
		}
	}

	private String generateKey(final ProceedingJoinPoint iJoinPoint,String keyConfigured, List<String> paramsConfigured){

		StringBuffer keyStr = new StringBuffer();
		keyStr.append(keyConfigured);
		Object[] listOfArguments = iJoinPoint.getArgs();
		Signature signature = iJoinPoint.getSignature();
		Map<String,Object> methodParamNameValues = null;
		String[] parameterNames = null;
		MethodSignature methodSignature = null;
		if (signature instanceof MethodSignature) {
			methodSignature = (MethodSignature) signature;
			parameterNames = methodSignature.getParameterNames();
		}
		if (listOfArguments != null && parameterNames != null) {
			methodParamNameValues = new HashMap<String,Object>();
			for (int iCounter = 0; iCounter < listOfArguments.length; iCounter++) {
				if (listOfArguments[iCounter] != null) {
					methodParamNameValues.put(parameterNames[iCounter], listOfArguments[iCounter]);
				} else {
					methodParamNameValues.put(parameterNames[iCounter], "NULL");
				}

				logger.info("##### " +parameterNames[iCounter] + listOfArguments[iCounter]);
			}
		}

		Object paramVal = null;
		if (paramsConfigured != null && !paramsConfigured.isEmpty()) {
			//logger.info("************* param in generated key " + paramsConfigured.get(0));
			for(String param : paramsConfigured) {
				if(StringUtils.equalsIgnoreCase("serviceName", param)){
					keyStr.append("_");
					keyStr.append("serviceName");
					keyStr.append("_");
					keyStr.append(serviceName);
				}
				paramVal = methodParamNameValues.get(param);
				if(paramVal != null && StringUtils.isNotBlank(paramVal.toString())){
					keyStr.append("_");
					keyStr.append(param);
					keyStr.append("_");
					keyStr.append(paramVal.toString());
				}
			}
		}
		//logger.info("************* generated key " + keyStr);
		return keyStr.toString();
	}

	/**
	 * Returns the cache config property value.
	 *
	 * @param config configuration
	 * @param uRI URI or method 
	 * @param property Property
	 * @return Value
	 */
	private  String getConfigProperty(HierarchicalConfiguration config, String uRI, String property) {
		return (String) config.getProperty("service[uri='" + uRI + "']/config/" + property);
	}

	private  String getCacheConfigKey(final ProceedingJoinPoint iJoinPoint){

		logger.info("------------" + iJoinPoint.getSignature().getDeclaringTypeName() + "." + iJoinPoint.getSignature().getName());
		String paramsToBeAddedInCache = cacheConfigMap.get(iJoinPoint.getSignature().getDeclaringTypeName()+"."+iJoinPoint.getSignature().getName()).get("paramsToBeAddedInCache");
		String[] paramNameArr;
		List<String> paramNameList = new ArrayList<String>();
		if(paramsToBeAddedInCache != null && paramsToBeAddedInCache.length() >0){
			paramNameArr = paramsToBeAddedInCache.split(",");
			for(String param : paramNameArr){
				paramNameList.add(param);
				logger.info("getCacheConfigKey : %%%%%%%%%%%%%%%%%% : "+param);
			}
		}
		String key = generateKey(iJoinPoint,cacheConfigMap.get(iJoinPoint.getSignature().getDeclaringTypeName()+"."+iJoinPoint.getSignature().getName()).get("cacheKey"),paramNameList);
		//System.out.println(key);
		return key;
	}
	
	private  String getCacheConfigDuration(final ProceedingJoinPoint iJoinPoint){

		logger.info("------------"+cacheConfigMap.get(iJoinPoint.getSignature().getDeclaringTypeName()+"."+iJoinPoint.getSignature().getName()).get("cacheDuration"));
		return cacheConfigMap.get(iJoinPoint.getSignature().getDeclaringTypeName()+"."+iJoinPoint.getSignature().getName()).get("cacheDuration");
	}
}
