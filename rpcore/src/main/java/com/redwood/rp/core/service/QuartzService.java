package com.redwood.rp.core.service;

import java.util.Date;

import org.quartz.Scheduler;

import com.redwood.rp.core.exception.QuartzException;
import com.redwood.rp.core.service.AbstractTask;

public interface QuartzService {
	
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
			throws QuartzException;
	
	
	public boolean createCronSchedule(String group, String name,
			String expression, AbstractTask abstractTask)
			throws QuartzException;
	/**
	 * @param group
	 * @param name
	 * @param expressionDate
	 * @param abstractTask
	 * @return
	 */
	public boolean reSchedule(String group, String name, Date expressionDate,
			AbstractTask abstractTask) throws QuartzException;
	
	/**
	 * @param group
	 * @param name
	 * @return
	 * @throws QuartzException
	 */
	public boolean deleteSchedule(String group, String name) throws QuartzException;
	
	/**
	 * @param groupName
	 * @param triggerName
	 * @return
	 * @throws QuartzException
	 */
	public Date getNextFireTime(String groupName, String triggerName) throws QuartzException;
	
	
	/**
	 * @return
	 */
	public Scheduler getScheduler() throws QuartzException;
	
	
}
