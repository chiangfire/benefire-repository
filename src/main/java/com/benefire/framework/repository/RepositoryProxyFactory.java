package com.benefire.framework.repository;
/**
 * @author jiang
 */
public interface RepositoryProxyFactory {
	
	public Object createRepositoryProxy(Class<?> clazz);

}
