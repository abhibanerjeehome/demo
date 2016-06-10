package com.redwood.rp.core.util;
/**
 *=====================================================================
 * ACP JAXB Utility Test Suite 
 * 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.junit.Ignore;

import com.redwood.rp.core.exception.UtilityException;

/**
 * @author huan.chen
 * 
 */
public class JAXBUtilTest{
    private static Logger mLogger = LoggerFactory.getLogger(JAXBUtilTest.class.getName());

	/**
	 * Test marshallerObject() method
	 */
//	@Test
//	public void testMarshallerObject() {
//		BidStatus bs = new BidStatus();
//		bs.setEventAssetAuctionId("eventAssetAuctionId");
//		bs.setAssetId("assetId");
//		bs.setStartDateTime("startDateTime");
//		bs.setStopDateTime("stopDateTime");
//		bs.setRevisedStopDateTime("revisedStopDateTime");
//		bs.setEventAuctionStatus("eventAuctionStatus");
//		bs.setBidIncrement(new BigDecimal("123"));
//		bs.setStartingBid(new BigDecimal("123"));
//		bs.setReservePrice(new BigDecimal("123"));
//		bs.setTempUserName("tempUserName");
//		bs.setCurBidTransactionId(1234);
//		bs.setCurBidAmount(new BigDecimal("123"));
//		bs.setAssetSKU("123");
//		bs.setAssetType("house");
//		bs.setAssetTitle("asdf");
//		bs.setCurEventBuyerId("curEventBuyerId");
//		bs.setPreviousValue(new BigDecimal("123"));
//		bs.setTempAssetInfo("");
//		bs.setAssetDescription("");
//		bs.setAssetOverview("");
//		bs.setNextBidAmount(new BigDecimal("123"));
//		bs.setAssetImage("");
//		BidResponse bsm = new BidResponse();
//		bsm.setName("auction_status");
//		bsm.setSource("auction_engine");
//		bsm.setStatus(bs);
//        try {
//		    String xml = JAXBUtil.marshallerObject(bsm,new String[]{});
//			assertNotNull(xml);
//			assertFalse("".equals(xml));
//	    } catch (UtilityException e) {
//		    logger.info( e.getStackTrace() );
//        }
//	}

	/**
	 * Test marshallerObjectWithXsdValidation() method
	 */
//	@Test
//	public void testMarshallerObjectWithXsdValidation() {
//		ErrorLogVO errlogVo = new ErrorLogVO();
//		errlogVo.setCreatedDatetime( "2011-10-27 12:00:00" );
//		errlogVo.setCreatedUser("Mark");
//		errlogVo.setLastUpdatedDatetime("2011-10-30 12:00:00");
//		errlogVo.setLastUpdatedUser("David");
//
//		errlogVo.setCorrelationId("1");
//		errlogVo.setServiceName("Data Broker");
//		errlogVo.setServiceMethod("loggingJavaErrorMessage");
//		errlogVo.setRequestMessage(null);
//		errlogVo.setErrorCode("12000");
//		errlogVo.setSeverityCode("10000");
//		errlogVo.setErrorMessage(null);
//		errlogVo.setWebUserId(1);
//		errlogVo.setWebUsername("David");
//		errlogVo.setIpAddress("david@auction.com");
//		errlogVo.setDomainId(1);
//		errlogVo.setDomainBuildId("DomainBuildId");
//		errlogVo.setEmailAddress("david@auction.com");
//        
//        try {
//		    String xml = JAXBUtil.marshallerObjectWithXsdValidation(errlogVo, new String[]{}, "errorLog.xsd");
//			assertNotNull(xml);
//			assertFalse("".equals(xml));
//	    } catch (UtilityException e) {
//		    logger.info( e.getStackTrace() );
//        }
//	}
//    
	/**
	 * Test the unmarshallerWithoutXsdValidation
	 */
//	@Test
//	public void testUnmarshallerWithoutXsdValidation() {
//		BidStatus bs = new BidStatus();
//		bs.setEventAssetAuctionId("eventAssetAuctionId");
//		bs.setAssetId("assetId");
//		bs.setStartDateTime("startDateTime");
//		bs.setStopDateTime("stopDateTime");
//		bs.setRevisedStopDateTime("revisedStopDateTime");
//		bs.setEventAuctionStatus("eventAuctionStatus");
//		bs.setBidIncrement(new BigDecimal("123"));
//		bs.setStartingBid(new BigDecimal("123"));
//		bs.setReservePrice(new BigDecimal("123"));
//		bs.setTempUserName("tempUserName");
//		bs.setCurBidTransactionId(1234);
//		bs.setCurBidAmount(new BigDecimal("123"));
//		bs.setAssetSKU("123");
//		bs.setAssetType("house");
//		bs.setAssetTitle("asdf");
//		bs.setCurEventBuyerId("curEventBuyerId");
//		bs.setPreviousValue(new BigDecimal("123"));
//		bs.setTempAssetInfo("");
//		bs.setAssetDescription("");
//		bs.setAssetOverview("");
//		bs.setNextBidAmount(new BigDecimal("123"));
//		bs.setAssetImage("");
//		BidResponse bsm = new BidResponse();
//		bsm.setName("auction_status");
//		// bsm.setSource("auction_engine");
//		bsm.setStatus(bs);
//        
//		String xml = "";
//		BidResponse message = null;
//		try {
//		    xml = JAXBUtil.marshallerObject(bsm,new String[]{});
//			assertNotNull(xml);
//
//			message = (BidResponse) JAXBUtil.unmarshallerWithoutXsdValidation(xml, BidResponse.class);
//	    } catch (UtilityException e) {
//		    logger.info( e.getStackTrace() );
//        }
//
//
//		assertNotNull(message);
//		assertNotNull(message.getStatus());
//		assertEquals(message.getStatus().getAssetId(), bs.getAssetId());
//		assertEquals(message.getStatus().getAssetImage(), bs.getAssetImage());
//		assertEquals(message.getStatus().getRevisedStopDateTime(),
//				bs.getRevisedStopDateTime());
//		assertEquals(message.getStatus().getPreviousValue(),
//				bs.getPreviousValue());
//		assertEquals(message.getStatus().getEventAssetAuctionId(),
//				bs.getEventAssetAuctionId());
//		assertEquals(message.getStatus().getCurBidAmount(),
//				bs.getCurBidAmount());
//	}

//	/**
//	 * Test the unmarshallerWithXsdValidation
//	 */
//	@Test
//	@Ignore
//	public void testUnmarshallerWithXsdValidation() {
//		ErrorLogVO errlogVo = new ErrorLogVO();
//		errlogVo.setCreatedDatetime( "2011-10-27 12:00:00" );
//		errlogVo.setCreatedUser("Mark");
//		errlogVo.setLastUpdatedDatetime("2011-10-30 12:00:00");
//		errlogVo.setLastUpdatedUser("David");
//
//		errlogVo.setCorrelationId("1");
//		errlogVo.setServiceName("Data Broker");
//		errlogVo.setServiceMethod("loggingJavaErrorMessage");
//		errlogVo.setRequestMessage(null);
//		errlogVo.setErrorCode("12000");
//		errlogVo.setSeverityCode("10000");
//		errlogVo.setErrorMessage(null);
//		errlogVo.setWebUserId(1);
//		errlogVo.setWebUsername("David");
//		errlogVo.setIpAddress("david@auction.com");
//		errlogVo.setDomainId(1);
//		errlogVo.setDomainBuildId("DomainBuildId");
//		errlogVo.setEmailAddress("david@auction.com");
//
//		try {
//			String xml = JAXBUtil.marshallerObject(errlogVo,new String[]{});
//		    assertNotNull(xml);
//
//		    ErrorLogVO message = (ErrorLogVO) JAXBUtil.unmarshallerWithXsdValidation(xml,  ErrorLogVO.class, "errorLog.xsd");
//		    assertNull(message);
//
//		    // Set the value of "auction_engine" value
//		    errlogVo.setSource("Data Broker");
//			xml = JAXBUtil.marshallerObject(errlogVo,new String[]{});
//		    message = (ErrorLogVO) JAXBUtil.unmarshallerWithXsdValidation(xml,  ErrorLogVO.class, "errorLog.xsd");
//			assertNotNull(message);
//			assertNotNull(message.getCorrelationId());
//			assertEquals(message.getServiceName(), errlogVo.getServiceName());
//
//		} catch (UtilityException e) {
//			    logger.info( e.getStackTrace() );
//	    }
// 
//	}	
	/**
	 * Test the unmarshallerWithXsdValidation
	 */
//	@Test
//	@Ignore
//	public void testUnmarshallerWithXsdValidation1() {
//		BidStatus bs = new BidStatus();
//		bs.setEventAssetAuctionId("eventAssetAuctionId");
//		bs.setAssetId("assetId");
//		bs.setStartDateTime("startDateTime");
//		bs.setStopDateTime("stopDateTime");
//		bs.setRevisedStopDateTime("revisedStopDateTime");
//		bs.setEventAuctionStatus("eventAuctionStatus");
//		bs.setBidIncrement(new BigDecimal("123"));
//		bs.setStartingBid(new BigDecimal("123"));
//		bs.setReservePrice(new BigDecimal("123"));
//		bs.setTempUserName("tempUserName");
//		bs.setCurBidTransactionId(1234);
//		bs.setCurBidAmount(new BigDecimal("123"));
//		bs.setAssetSKU("123");
//		bs.setAssetType("house");
//		bs.setAssetTitle("asdf");
//		bs.setCurEventBuyerId("curEventBuyerId");
//		bs.setPreviousValue(new BigDecimal("123"));
//		bs.setTempAssetInfo("");
//		bs.setAssetDescription("");
//		bs.setAssetOverview("");
//		bs.setNextBidAmount(new BigDecimal("123"));
//		bs.setAssetImage("");
//		BidResponse bsm = new BidResponse();
//		bsm.setName("auction_status");
////		bsm.setSource("auction_engine");
//		bsm.setStatus(bs);
//
//		// "auction_engine" setting is missing
//		try {
//			String xml = JAXBUtil.marshallerObject(bsm,new String[]{});
//		    assertNotNull(xml);
//
//		    BidResponse message = (BidResponse) JAXBUtil.unmarshallerWithXsdValidation(xml,
//			  	                      BidResponse.class, "test/bidStatus.xsd");
//		    assertNull(message);
//
//		    // Set the value of "auction_engine" value
//			bsm.setSource("auction_engine");
//			xml = JAXBUtil.marshallerObject(bsm,new String[]{});
//			message = (BidResponse) JAXBUtil.unmarshallerWithXsdValidation(xml,
//					BidResponse.class, "test/bidStatus.xsd");
//			assertNotNull(message);
//			assertNotNull(message.getStatus());
//			assertEquals(message.getStatus().getAssetId(), bs.getAssetId());
//			assertEquals(message.getStatus().getAssetImage(), bs.getAssetImage());
//			assertEquals(message.getStatus().getRevisedStopDateTime(),
//					bs.getRevisedStopDateTime());
//			assertEquals(message.getStatus().getPreviousValue(),
//					bs.getPreviousValue());
//			assertEquals(message.getStatus().getEventAssetAuctionId(),
//					bs.getEventAssetAuctionId());
//			assertEquals(message.getStatus().getCurBidAmount(),
//					bs.getCurBidAmount());
//
//		} catch (UtilityException e) {
//			    logger.info( e.getStackTrace() );
//	    }
// 
//
//	}
}
