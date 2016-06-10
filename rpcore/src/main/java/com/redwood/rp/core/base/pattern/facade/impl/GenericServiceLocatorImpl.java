package com.redwood.rp.core.base.pattern.facade.impl;
/**
 *=====================================================================
 * Generic Service Locator Implementation 
 *
 * a generic service locator to locate a session facade pattern based 
 * DAS service for serving service calls in data accessing layer
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import com.redwood.rp.core.base.pattern.facade.GenericServiceLocator;

public class GenericServiceLocatorImpl implements GenericServiceLocator {

	// facade service instance
	private Object facadeService = null;
 
	public Object getFacadeService() {
		return facadeService;
	}

	public void setFacadeService(Object facadeService) {
		this.facadeService = facadeService;
	}
	
}
