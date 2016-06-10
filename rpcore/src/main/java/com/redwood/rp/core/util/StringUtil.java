package com.redwood.rp.core.util;

/**
 *=====================================================================
 * ACP String Stream Handling Utility 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.io.Reader;
import java.sql.Clob;

public final class StringUtil {
  

  /**
   * Test for null/empty and no whitespace string.
   * @param string
   * @return true if string is null or blank
   */
  public static boolean isBlank(String string) {
    return (string == null || string.trim().length() == 0);
  }

  /**
   * Test for not null string.
   * @param string
   * @return true if string is not null or blank
   */
  public static boolean isNotBlank(String aString) {
	boolean isBlank = false;
	
	if ( aString != null ) {
	   isBlank = aString.trim().length() != 0;
	} 
	return isBlank;
  }

  /**
   * Checks if a string contains a boolean true.
   * Currently the strings "true", "yes", and "1" are considered true.
   * All other strings (including null) are considered false.
   */
  public static boolean isTrue(String str) {
    return ( (str != null)
             && (str.equalsIgnoreCase("true")
             || str.equalsIgnoreCase("yes")
             || str.equals("1")
          ));
  }

  /**
   * Test for Integer in string.
   * @param string
   * @return true if string is Int.
   */
  public static boolean isNumber(String value) {
    try {
    	if (value.contains(".")) {
    		Float.parseFloat(value);
    	} else {
    		Integer.parseInt(value);
    	}
        return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
    
  
  /** 
   * convert Clob to string 
   * @param clobObject Clob
   * @return String
   */
  public static String convertClobToString(Clob clobObject ) {
	  String stringClob = "";
	  
	  try {
		  if ( clobObject != null && clobObject.length() > 0L ) {
			 StringBuilder sb = new StringBuilder();
			 char[] buffer = new char[(int)clobObject.length()];
			
			 Reader colbReader = clobObject.getCharacterStream();
			 while (colbReader.read(buffer) != -1) {
			     sb.append(buffer);
			 }
			 stringClob = new String(sb); 
		  }
	  
	  } catch (Exception e) {
		  stringClob = "";
	  }
	  
	  return stringClob;
   }

  /** 
   * convert Clob to string 
   * @param clobObject Clob
   * @return String
   */
  public static String convertClobToString_save(Clob clobInData) {
	  String stringClob = "";
	  
	  try {
		  if ( clobInData != null ) {
			 long i = 1;
			 int clobLength = (int) clobInData.length();
			 stringClob = clobInData.getSubString(i, clobLength);
		  }
	  } catch (Exception e) {
		  stringClob = "";
	  }
	  
	  return stringClob;
   }
  
  
  public static String convertStringArrayToString(String[] tokens,String separator){
	  
	  String tokenString=new String();
	  if(StringUtil.isBlank(separator))
	  
	    if (tokens != null && tokens.length ==1){
	          tokenString= tokens[0];
	    }else if(tokens != null) {
	    	StringBuilder tokenBuilder = new StringBuilder();
	    	
	    	for(String globalPropId : tokens){
	    		tokenBuilder.append( globalPropId);
	    		tokenBuilder.append( separator);
	    	}
	    	
	    	tokenString =  tokenBuilder.toString();
	    }
	  return tokenString;
  }


  
}

