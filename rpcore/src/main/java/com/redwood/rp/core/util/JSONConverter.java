package com.redwood.rp.core.util;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.vo.json.ErrorVO;

public class JSONConverter {
    private static Logger mLogger = LoggerFactory.getLogger(JSONConverter.class.getName());
	
	// @SuppressWarnings("unchecked")
	public static  Map<String,String> convertJSONtoMap(String jsonString) throws UtilityException {
		 Map<String,String> map= new HashMap<String,String>();

		 try{
			 ObjectMapper mapper = new ObjectMapper();
		     map = mapper.readValue(jsonString, HashMap.class);
		
		 }catch(Exception e){
	        mLogger.error(MessageFormattor.errorFormat(JSONConverter.class.getName(),
	                    "convertJSONtoMap", ExceptionType.EXCEPTION_JSON,
	                    e.getMessage()), e);
	       throw new UtilityException(ExceptionType.EXCEPTION_UTILITY,
		            new ErrorVO(ErrorDescription.ERR_CD_UT_JSON_CONVERSION, ErrorDescription.ERR_MSG_UT_JSON_CONVERSION, e.getMessage()));
		}
		
		return map;
		
	}
	
}
