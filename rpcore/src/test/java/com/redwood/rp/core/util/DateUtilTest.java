package com.redwood.rp.core.util;
/**
 *=====================================================================
 * ACP Date Utility Test Suite 
 * 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.DateUtil;

public class DateUtilTest {

	@Test
	public void testUndeployQueueWithNullInput() throws UtilityException {
		assertNull(DateUtil.undeployQueue(null));
	}
	
	@Test
	public void testUndeployQueueWithValidInput() throws UtilityException {
		Calendar cal = Calendar.getInstance();
		Date outputDate = DateUtil.undeployQueue(cal.getTime());
		cal.set(Calendar.MILLISECOND, 0); // truncating mill seconds
		assertEquals(cal.getTime(), outputDate);
	}

	@Test
	public void testConvertToGmtWithNullInput() {
		assertNull(DateUtil.convertToGmt(null));
	}
	
	@Test
	public void testConvertToGmtWithValidInput() {
		Calendar cal = Calendar.getInstance();
		
		Date gmtDate = DateUtil.convertToGmt(cal.getTime());
		
		cal.add(Calendar.MILLISECOND, -TimeZone.getDefault().getRawOffset());
		
		assertEquals(cal.getTime(), gmtDate);
	}

	@Test
	public void testParseDateToStringWithNullDate() {
		assertNull(DateUtil.parseDateToString(null, new SimpleDateFormat()));
	}
	
	@Test
	public void testParseDateToStringWithNullDateFormat() {
		assertNull(DateUtil.parseDateToString(new Date(), null));
	}
	
	@Test
	public void testParseDateToStringWithValidInput() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = DateUtil.parseDateToString(cal.getTime(), dateFormat);
		assertEquals(dateFormat.format(cal.getTime()), dateStr);
	}

	@Test
	public void testParseStringToDateWithNullDateString() {
		assertNull(DateUtil.parseStringToDate(null, new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	@Test
	public void testParseStringToDateWithNullDateFormat() {
		assertNull(DateUtil.parseStringToDate("17/11/2021", null));
	}
	
	@Test
	public void testParseStringToDateWithInvalidInput() {
		assertNull(DateUtil.parseStringToDate("17-11-2021", new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	@Test
	public void testParseStringToDateWithValidInput() {
		Date parsedDate = DateUtil.parseStringToDate("17/11/2021", new SimpleDateFormat("dd/MM/yyyy"));
		assertEquals("Wed Nov 17 00:00:00 IST 2021", parsedDate.toString());
	}


}
