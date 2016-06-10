package com.redwood.rp.core.util;
/**
 *=====================================================================
 * ACP Date Type Data Handling Utility 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import static com.redwood.rp.core.constant.CoreUtilConst.DATE_FORMAT_WITH_NO_TIME;
import static com.redwood.rp.core.constant.CoreUtilConst.DATE_FORMAT_WITH_TIME;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.UtilityException;
import com.redwood.rp.core.util.MessageFormattor;
import com.redwood.rp.core.vo.json.ErrorVO;

public class DateUtil {
    private static Logger mLogger = LoggerFactory.getLogger(DateUtil.class.getName());
	public static SimpleDateFormat sdf_bidStatus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * get undeploy queue date
	 * 
	 * @param jDate
	 *            Date
	 * @return Date the set on date
	 * @throws Exception
	 */
	public static Date undeployQueue(Date jDate) throws UtilityException {
	    try {
	    	Date date = null;
			if (jDate != null) {
			   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
			   format.setCalendar(cal);
			
			   // Date date = format.parse("2011-08-02 00:15:30");
			   date = format.parse( parseDateToString(jDate, format) );
			}
			return date;
	    } catch (ParseException ex) {
			mLogger.error(MessageFormattor.errorFormat(DateUtil.class.getName(),
					      "undeployQueue", ExceptionType.EXCEPTION_UTILITY,
					      ex.getMessage()), ex);
			throw new UtilityException(ExceptionType.EXCEPTION_UTILITY,
					                   new ErrorVO(ErrorDescription.ERR_CD_UT_DATE_CONVERSION, ErrorDescription.ERR_CD_UT_DATE_CONVERSION, ex.getMessage()));
	    }

	}

	/**
	 * Convert date to GMT
	 * 
	 * @param date
	 *            source date
	 * @return Date GMT Date
	 */
	public static Date convertToGmt(Date date) {
		TimeZone tz = TimeZone.getDefault();

		Date retDate = null;
		if ( date != null ) {
			retDate = new Date(date.getTime() - tz.getRawOffset());

			// if we are now in DST, back off by the delta. Note that we are
		    // checking the GMT date, this is the KEY.
		    if (tz.inDaylightTime(retDate)) {
			   Date dstDate = new Date(retDate.getTime() - tz.getDSTSavings());

			   // check to make sure we have not crossed back into standard time
			   // this happens when we are on the cusp of DST (7pm the day before
			   // the change for PDT)
			  if (tz.inDaylightTime(dstDate)) {
				 retDate = dstDate;
			  }
		    }
		}
		return retDate;
	}

	/**
	 * Parse date to string according to specific format
	 * 
	 * @param date
	 * @param sdf
	 * @return
	 */
	public static String parseDateToString(Date date, SimpleDateFormat sdf) {
		if (date == null || sdf == null) {
			return null;
		} else {
			return sdf.format(date);
		}
	}

	/**
	 * Parse a string specific format with to date
	 * 
	 * @param dateStr
	 * @param sdf
	 * @return
	 */
	public static Date parseStringToDate(String dateStr, SimpleDateFormat sdf)  {
		if (dateStr == null || sdf == null) {
			return null;
		} else {
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				String errMsg = "Can't find parse the '" + dateStr + "' to date' with format" + sdf.toPattern()	+ ".";
				mLogger.error(MessageFormattor.errorFormat( DateUtil.class.getName(), "parseStringToDate",
						ExceptionType.EXCEPTION_UTILITY, errMsg), e);
//			    throw new UtilityException(ExceptionType.EXCEPTION_UTILITY,
//					                   ErrorVOGenerator.genarateErrorVO("", "",  "", errMsg));
				return null;
			}
		}
	}


	/**
	 * Difference in days for current.
	 *
	 * @param countyAuctionDT the county auction dt
	 * @return the int
	 */
	public static int differenceInDaysForCurrent(Date countyAuctionDT)  {
		return  (int) ( ((Calendar.getInstance().getTime()).getTime() - countyAuctionDT.getTime()) / (1000 * 60 * 60 * 24) );
	}
	
	/**
	 * Difference in minutes for current.
	 *
	 * @param countyAuctionDT the county auction dt
	 * @return the int
	 */
	public static int differenceInMinutesForCurrent(Date countyAuctionDT)  {
		return  (int) ( ((Calendar.getInstance().getTime()).getTime() - countyAuctionDT.getTime()) / (1000 * 60) );
	}
	
	/**
	 * Difference in seconds for current.
	 *
	 * @param aDate
	 * @return int
	 */
	public static int differenceInSecondsForCurrent(Date aDate)  {
		return  (int) ( ((Calendar.getInstance().getTime()).getTime() - aDate.getTime()) / 1000 );
	}
	
	public static Timestamp objectToTimestampConverter(Object dateRequest)  {
		Timestamp result=null;
		if(dateRequest  != null){
			String date=dateRequest.toString();
			try{
			if(date.length()>10)
				result=(new Timestamp((new SimpleDateFormat(DATE_FORMAT_WITH_TIME).parse(dateRequest .toString())).getTime()));
			else
				result=(new Timestamp((new SimpleDateFormat(DATE_FORMAT_WITH_NO_TIME).parse(dateRequest .toString())).getTime()));
			}catch(ParseException ex){
				mLogger.error("Couldn't parse date"+ ex.getMessage(), ex);
				
			}
		}
		return result;
	}
	
	
	
}
