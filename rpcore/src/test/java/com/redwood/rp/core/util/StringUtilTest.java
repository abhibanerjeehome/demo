package com.redwood.rp.core.util;
/**
 *=====================================================================
 * ACP String Utility Test Suite 
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

import com.redwood.rp.core.util.StringUtil;

public class StringUtilTest {

	@Test
	public void testIsBlankWithNull() {
		assertEquals(true, StringUtil.isBlank(null));
	}
	
	@Test
	public void testIsBlankWithEmptyString() {
		assertEquals(true, StringUtil.isBlank(""));
	}
	
	@Test
	public void testIsBlankWithWhitespaces() {
		assertEquals(true, StringUtil.isBlank("        "));
	}
	
	@Test
	public void testIsBlankWithValidString() {
		assertEquals(false, StringUtil.isBlank("test"));
	}

	@Test
	public void testIsNotBlankWithNull() {
		assertEquals(false, StringUtil.isNotBlank(null));
	}
	
	@Test
	public void testIsNotBlankWithEmptyString() {
		assertEquals(false, StringUtil.isNotBlank(""));
	}
	
	@Test
	public void testIsNotBlankWithWhitespaces() {
		assertEquals(false, StringUtil.isNotBlank("   "));
	}
	
	@Test
	public void testIsNotBlankWithValidString() {
		assertEquals(true, StringUtil.isNotBlank("test"));
	}

	@Test
	public void testIsTrueWithNull() {
		assertEquals(false, StringUtil.isTrue(null));
	}
	
	@Test
	public void testIsTrueWithInvalidValue() {
		assertEquals(false, StringUtil.isTrue("test"));
	}
	
	@Test
	public void testIsTrueWithValueTrue() {
		assertEquals(true, StringUtil.isTrue("true"));
	}
	
	@Test
	public void testIsTrueWithValueYes() {
		assertEquals(true, StringUtil.isTrue("yes"));
	}
	
	@Test
	public void testIsTrueWithValue1() {
		assertEquals(true, StringUtil.isTrue("1"));
	}

	@Test
	public void testIsNumberWithNull() {
		assertEquals(false, StringUtil.isNumber(null));
	}
	
	@Test
	public void testIsNumberWithInvalidValue() {
		assertEquals(false, StringUtil.isNumber("ABC.34"));
	}
	
	@Test
	public void testIsNumberWithValidInteger() {
		assertEquals(true, StringUtil.isNumber("123"));
	}
	
	@Test
	public void testIsNumberWithValidFloatValue() {
		assertEquals(true, StringUtil.isNumber("123.45"));
	}

}
