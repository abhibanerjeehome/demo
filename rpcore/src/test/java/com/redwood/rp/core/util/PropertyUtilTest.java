package com.redwood.rp.core.util;

/**
 *=====================================================================
 * ACP Property Utility Test Suite 
 * 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

@Named
public class PropertyUtilTest {
	
	@Value("${jmq.naming.factory.initial}")
	private String jmqNamingFactoryInitial;
	
	@Value("${test.test.test}")
	private String test;
	
	@Test
	public void testGetValueByKey() {
		assertNotNull(jmqNamingFactoryInitial);
		assertNull(test);
	}

}
