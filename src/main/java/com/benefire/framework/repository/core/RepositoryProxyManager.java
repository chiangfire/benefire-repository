package com.benefire.framework.repository.core;

import java.lang.reflect.Method;

/**
 * @author jiang
 */
public interface RepositoryProxyManager {
	
	public Object invokeProxyMethod(Method method, Object[] args) throws Throwable;
}
