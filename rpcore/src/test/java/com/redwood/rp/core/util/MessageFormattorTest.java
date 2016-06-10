package com.redwood.rp.core.util;

/**
 *=====================================================================
 * ACP Message Formatter Test Suite 
 * 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.redwood.rp.core.util.MessageFormattor;

public class MessageFormattorTest {

	@Test
	public void testInfoFormatWithNullInputs() {
		String outputString = MessageFormattor.infoFormat(null, null, null);
		assertEquals("[*****]Message is empty at .", outputString);
	}
	
	@Test
	public void testInfoFormatWithInfoMessageNull() {
		String outputString = MessageFormattor.infoFormat("abc", "xyz", null);
		assertEquals("[*****]Message is empty at abc.xyz", outputString);
	}
	
	@Test
	public void testInfoFormatWithValidInputs() {
		String outputString = MessageFormattor.infoFormat("abc", "xyz", "This is info message");
		assertEquals("[*****]This is info message at abc.xyz", outputString);
	}

	@Test
	public void testErrorFormatWithNullInputs() {
		String outputString = MessageFormattor.errorFormat(null, null, null, null);
		assertEquals("[?????] null: Exception Message is empty at .", outputString);
	}
	
	@Test
	public void testErrorFormatWithErrorMessageNull() {
		String outputString = MessageFormattor.errorFormat("abc", "xyz", "TestError", null);
		assertEquals("[?????] TestError: Exception Message is empty at abc.xyz", outputString);
	}
	
	@Test
	public void testErrorFormatWithValidInputs() {
		String outputString = MessageFormattor.errorFormat("abc", "xyz", "TestError", "This is error message");
		assertEquals("[?????] TestError: This is error message at abc.xyz", outputString);
	}

}
