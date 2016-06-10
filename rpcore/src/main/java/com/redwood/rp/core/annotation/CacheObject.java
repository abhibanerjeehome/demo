/**
 * Annotation for logging. This will be used for defining the point cut property of the aspect Logger.
 */
package com.redwood.rp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface CacheObject.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheObject{

	/**
	 * Cache action.
	 *
	 * @return the cache action
	 */
	CacheAction cacheAction();// default CacheAction.AddToCahce;
	
	
}


