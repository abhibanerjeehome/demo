package com.redwood.rp.core.util;

/**
 *=====================================================================
 * ACP Number Type Data Handling Utility 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumberUtil {
	private static Logger logger = LoggerFactory.getLogger(NumberUtil.class.getName());

	/**
	 * check ID is Null
	 */
	public static boolean isNullID(Long id ) {
		boolean isNewKey = false;
	    
		if ( id == null || id < 1L ) {
			isNewKey = true;
	    } 
		return isNewKey ;
	}	
	
	/**
	 * check Id is not Null
	 */
	public static boolean isNotNullID(Long id ) {
		boolean isExsited = false;
	    
		if ( isNullID(id) ) {
			isExsited = false;
	    } else {
	    	isExsited = true;
	    }
		return isExsited ;
	}	
	
	/**
	 * check version is Null
	 */
	public static boolean isNullVersion(Integer version ) {
		boolean isNewKey = false;
	    
		if ( version == null || version < 1 ) {
			isNewKey = true;
	    } 
		return isNewKey ;
	}	
	
	/**
	 * check version is not Null
	 */
	public static boolean isNotNullVersion(Integer version ) {
		boolean isExsited = false;
	    
		if ( isNullVersion(version) ) {
			isExsited = false;
	    } else {
	    	isExsited = true;
	    }
		return isExsited ;
	}	

	
	/**
	 * check number
	 */
	public static BigDecimal checkNull(BigDecimal number ) {
		BigDecimal bigDec = null;
	    
		if ( number != null ) {
			bigDec = number;
	    } else {
			bigDec = new BigDecimal(0);
		}
		return bigDec;
	}	
	

	/**
	 * convert BigDecmal to Double
	 */
	public static Double convertDouble(BigDecimal number ) {
		Double doubleObj = null;
	    
		if ( number != null ) {
			doubleObj = number.doubleValue();
	    } else {
	    	doubleObj = new Double(0.0);
		}
		return doubleObj;
	}	

	/**
	 * convert Long to Integer
	 */
	public static Integer convertInteger(Long number ) {
		Integer integerObj = null;
	    
		if ( number != null ) {
			integerObj = number.intValue();
	    } else {
	    	integerObj = new Integer(0);
		}
		return integerObj;
	}	
	
	
	/**
	 * convert BigDecmal to Integer
	 */
	public static Integer convertInteger(BigDecimal number ) {
		Integer integerObj = null;
	    
		if ( number != null ) {
			integerObj = number.intValue();
	    } else {
	    	integerObj = new Integer(0);
		}
		return integerObj;
	}	

	/**
	 * convert Double to BigDecmal
	 */
	public static BigDecimal convertBigDecmal(Double number ) {
		BigDecimal bigDec = null;
	    
		if ( number != null ) {
			bigDec = new BigDecimal(number);
	    } else {
	    	bigDec = null;
		}
		return bigDec;
	}	
	
	/**
	 * convert Integer to BigDecmal
	 */
	public static BigDecimal convertBigDecmal(Integer number ) {
		BigDecimal bigDec = null;
	    
		if ( number != null ) {
			bigDec = new BigDecimal(number);
	    } else {
	    	bigDec = null;
		}
		return bigDec;
	}	
	
	/**
	 * convert String to BigDecmal
	 */
	public static BigDecimal convertBigDecmal(String number ) {
		BigDecimal bigDec = null;
		if ( number == null || "".equals(number)) {
			bigDec = new BigDecimal(0);
			return bigDec;
	    } else {
	    	try{
	    		bigDec = new BigDecimal(number);
			}catch(Exception e){
				bigDec = new BigDecimal(0);
				logger.error("Error: Can't convert '"+bigDec+"' to BigDecimal.", e);
				logger.error(e.getMessage());
			}
			return bigDec;
		}
	}	

	
	/**
	 *  convert temperature
	 */
	public  static enum TemperatureUnit { Kelvin, Celsius, Fahrenheit, Rankine }
	private static final double UnitsFforC = 1.8;
	private static final double FreezeF = 32.0;
	private static final double FreezeAbsC = 273.15;
	
	public static double convertTemperature(double baseValue, TemperatureUnit srcUnit, TemperatureUnit destUnit) {

		if (srcUnit == destUnit) { return baseValue; }
		
		// normalize everything to celsius
		double celsius;
		if (srcUnit==TemperatureUnit.Kelvin) {
			celsius = baseValue - FreezeAbsC;
		} else if (srcUnit==TemperatureUnit.Fahrenheit) {
			celsius = (baseValue - FreezeF) / UnitsFforC;
		} else if (srcUnit==TemperatureUnit.Rankine) {
			celsius = (baseValue / UnitsFforC) - FreezeAbsC;
		} else {
			celsius = baseValue;
		}

		double returnValue;
		// convert out from celsius
		if (destUnit==TemperatureUnit.Kelvin) {
			returnValue = celsius + FreezeAbsC;
		} else if (destUnit==TemperatureUnit.Rankine) {
			returnValue = (celsius + FreezeAbsC) * UnitsFforC;
		} else if (destUnit==TemperatureUnit.Fahrenheit) {
			returnValue = (celsius * UnitsFforC) + FreezeF;
		} else {
			returnValue = celsius;
		}
		
		return (Math.floor((returnValue * 10.0) + 0.5))/10.0;
			
	}
	
	/**
	 * Parse the String to Long
	 * @param inputVal
	 * @return
	 */
	public static Long parseStringToLong(String inputVal){
		Long returnVal = null;
		if(null==inputVal || "".equals(inputVal)){
			returnVal = new Long(0);
			return returnVal;
		}else{
			try{
				returnVal = new Long(inputVal);
			}catch(Exception e){
				returnVal = new Long(0);
				logger.error("Error: Can't convert '"+inputVal+"' to Long.", e);
				logger.error(e.getMessage());
			}
			return returnVal;
		}
	}
	
	/**
	 * Parse the String to Integer
	 * @param inputVal
	 * @return
	 */
	public static Integer parseStringToInteger(String inputVal){
		Integer returnVal = null;
		if(null==inputVal || "".equals(inputVal)){
			return returnVal;
		}else{
			try{
				returnVal = new Integer(inputVal);
			}catch(Exception e){
				logger.error("Error: Can't convert '"+inputVal+"' to Integer.", e);
				logger.error(e.getMessage());
			}
			return returnVal;
		}
	}
	

}

