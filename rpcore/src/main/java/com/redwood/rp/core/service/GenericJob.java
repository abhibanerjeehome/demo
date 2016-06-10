package com.redwood.rp.core.service;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
//import com.redwood.rp.core.aspect.LogAuctionJobEventsAspect;

/**
 * Basic class of job.
 */
public class GenericJob implements Job {

	@SuppressWarnings("unused")
	private AbstractTask task;

	@SuppressWarnings("rawtypes")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Map dataMap = context.getJobDetail().getJobDataMap();
		AbstractTask task = (AbstractTask)dataMap.get("task");
		if (task != null) {
			task.execute();
		}

	}

}