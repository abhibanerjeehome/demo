package com.redwood.rp.core.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Named;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redwood.rp.core.constant.ErrorDescription;
import com.redwood.rp.core.constant.ExceptionType;
import com.redwood.rp.core.exception.QuartzException;
import com.redwood.rp.core.service.AbstractTask;
import com.redwood.rp.core.service.GenericJob;
import com.redwood.rp.core.service.QuartzService;
import com.redwood.rp.core.util.ErrorVOGenerator;
import com.redwood.rp.core.util.MessageFormattor;

@Named
public class QuartzServiceImpl implements QuartzService {

	private Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class.getName());

	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	/**
	 * @param group
	 * @param name
	 * @param expressionDate
	 * @param abstractTask
	 * @return
	 * @throws QuartzException
	 */
	public boolean createSchedule(String group, String name,
			Date expressionDate, AbstractTask abstractTask)
					throws QuartzException {
		String errorMsg = "";
		try {
			Scheduler scheduler = getScheduler();
			JobDetail jobDetail = new JobDetail("JOB_" + name, "JOB_" + group,
					GenericJob.class);
			jobDetail.getJobDataMap().put("task", abstractTask);
			CronTrigger cronTrigger = new CronTrigger("Trigger_" + name,
					"Trigger_" + group);
			String expression = getExpressionByDate(expressionDate);
			CronExpression cexp = new CronExpression(expression);
			cronTrigger.setCronExpression(cexp);
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();
		} catch (SchedulerException e) {
			errorMsg = "Creating schedule failed. The error information is : "
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "createSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		} catch (ParseException e2) {
			errorMsg = "Creating schedule failed. The error information is : "
					+ e2.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "reSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e2);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		}
		return true;
	}


	public boolean createCronSchedule(String group, String name,
			String expression, AbstractTask abstractTask)
					throws QuartzException {
		String errorMsg = "";
		try {
			Scheduler scheduler = getScheduler();
			JobDetail jobDetail = new JobDetail("JOB_" + name, "JOB_" + group,
					GenericJob.class);
			jobDetail.getJobDataMap().put("task", abstractTask);
			CronTrigger cronTrigger = new CronTrigger("Trigger_" + name,
					"Trigger_" + group);
			CronExpression cexp = new CronExpression(expression);
			cronTrigger.setCronExpression(cexp);
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();
		} catch (SchedulerException e) {
			errorMsg = "Creating schedule failed. The error information is : "
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "createSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		} catch (ParseException e2) {
			errorMsg = "Creating schedule failed. The error information is : "
					+ e2.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "reSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e2);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		}
		return true;
	}
	/**
	 * @param group
	 * @param name
	 * @param expressionDate
	 * @param abstractTask
	 * @return
	 */
	public boolean reSchedule(String group, String name, Date expressionDate,
			AbstractTask abstractTask) throws QuartzException {
		String errorMsg = "";
		try {
			Scheduler scheduler = getScheduler();
			scheduler.unscheduleJob("Trigger_" + name, "Trigger_" + group);

			JobDetail jobDetail = new JobDetail("JOB_" + name, "JOB_" + group,
					GenericJob.class);
			jobDetail.getJobDataMap().put("task", abstractTask);
			CronTrigger cronTrigger = new CronTrigger("Trigger_" + name,
					"Trigger_" + group);
			String expression = getExpressionByDate(expressionDate);
			CronExpression cexp = new CronExpression(expression);
			cronTrigger.setCronExpression(cexp);
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();

		} catch (SchedulerException e) {
			errorMsg = "Creating schedule failed. The error information is : "
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "reSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		} catch (ParseException e) {
			errorMsg = "Creating schedule failed. The error information is : "
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "reSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		}
		return true;
	}

	/**
	 * @param group
	 * @param name
	 * @return
	 * @throws QuartzException
	 */
	public boolean deleteSchedule(String group, String name) throws QuartzException {
		String errorMsg = "";
		try {
			Scheduler scheduler = getScheduler();
			scheduler.unscheduleJob("Trigger_" + name, "Trigger_" + group);
		} catch (SchedulerException e) {
			errorMsg = "Deleting schedule failed. The error information is : "
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "deleteSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		} 
		return true;
	}

	/**
	 * Get the final fire time of the job . If no job exists, null will be returned.
	 * 
	 * @param groupName
	 * @param triggerName
	 * @return
	 * @throws QuartzException
	 */
	public Date getNextFireTime(String groupName, String triggerName) throws QuartzException{
		String errorMsg = "";
		Date fireDate = null;
		try {
			Scheduler scheduler = getScheduler();
			Trigger trigger = scheduler.getTrigger("Trigger_"+triggerName, "Trigger_"+groupName);
			if(trigger!=null){
				fireDate = trigger.getNextFireTime();
			}
		} catch (QuartzException e) {
			errorMsg = "Failed to get the final fire time of groupName :"+groupName +" , triggerName: "+triggerName +". Error info :"
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "getFinalFireTime",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		} catch (SchedulerException e) {
			errorMsg = "Failed to get the final fire time of groupName :"+groupName +" , triggerName: "+triggerName +". Error info :"
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "getFinalFireTime",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		}catch (Exception e){
			errorMsg = "Failed to get the final fire time of groupName :"+groupName +" , triggerName: "+triggerName +". Error info :"
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "getFinalFireTime",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		}
		return fireDate;
	}



	public Scheduler getScheduler() throws QuartzException {
		if (schedulerFactory == null) {
			schedulerFactory = new StdSchedulerFactory();
		}
		Scheduler scheduler = null;

		try {
			scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			String errorMsg = "";
			errorMsg = "Creating schedule failed. The error information is : "
					+ e.getMessage();
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "reSchedule",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg), e);
			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
					ErrorVOGenerator
					.genarateErrorVO("", "",
							ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
							errorMsg));
		}
		return scheduler;
	}

	/**
	 * @param date
	 * @return
	 * @throws QuartzException 
	 */
	private String getExpressionByDate(Date date) throws ParseException {
		if (date == null) {
			String errorMsg = "";
			errorMsg = "Can't format the giving date to Quartz expression format. The input date is null.";
			logger.error(MessageFormattor.errorFormat(
					QuartzServiceImpl.class.getName(), "getExpressionByDate",
					ExceptionType.EXCEPTION_APPLICATION, errorMsg));
			throw new ParseException(ExceptionType.EXCEPTION_APPLICATION + ": " + errorMsg, 0);
			//			throw new QuartzException(ExceptionType.EXCEPTION_UTILITY,
			//					ErrorVOGenerator
			//							.genarateErrorVO("", "",
			//									ErrorDescription.ERR_CD_QUARTZ_CREATE_JOB,
			//									errorMsg));
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//		calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
		//		calendar.get(Calendar.HOUR); // gets hour in 12h format
		//		calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String space = " ";

		return second + space + min + space + hour + space + day + space
				+ month + space + "?" + space + +year;
	}

	public SchedulerFactory getSchedulerFactory() {
		return schedulerFactory;
	}

	public void setSchedulerFactory(SchedulerFactory scheduleFactory) {
		schedulerFactory = scheduleFactory;
	}
}
