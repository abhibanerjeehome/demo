package com.redwood.rp.core.service;

/**
 * Basic interface of task class. All the task class should implement this interface.
 */
public interface AbstractTask {

	/**
	 * This method will be invoke automatically when the job started. All the
	 * children have to implement this method.
	 */
	public void execute();
}
