package com.benefire.framework.repository.starter;

import java.lang.reflect.Proxy;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import com.benefire.framework.repository.RepositoryProxyFactory;
import com.benefire.framework.repository.core.RepositoryFactoryInvocationHandler;
import com.benefire.framework.repository.core.RepositoryProxyManager;
import com.benefire.framework.repository.core.support.RelationalRepositoryProxyManager;
import com.benefire.framework.repository.vendor.Database;
/**
 * @author jiang
 */
public class RelationalRepositoryProxyFactory implements RepositoryProxyFactory,InitializingBean{
	
	
	private RepositoryProxyManager repositoryProxyManager;
	
	//named_query file directory
	private String namedQueryPath;
	//no named_query file directory to the server is fatal
	private boolean failFast;
	
	private DataSource dataSource;
	
	private Database database;
	
	private ClassLoader loader = getClass().getClassLoader();

	@Override
	public Object createRepositoryProxy(Class<?> clazz) {
		
		return Proxy.newProxyInstance(loader, new Class[]{clazz}, 
		       new RepositoryFactoryInvocationHandler(repositoryProxyManager));
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.repositoryProxyManager = new RelationalRepositoryProxyManager(dataSource,database,namedQueryPath,failFast);
	}
	
	public void setDataSource(DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
		} else {
			this.dataSource = dataSource;
		}
		this.dataSource = dataSource;
	}

	public void setNamedQueryPath(String namedQueryPath) {
		this.namedQueryPath = namedQueryPath;
	}

	public void setFailFast(boolean failFast) {
		this.failFast = failFast;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

}
