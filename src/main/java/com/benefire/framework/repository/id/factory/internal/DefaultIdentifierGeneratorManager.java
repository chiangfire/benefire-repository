package com.benefire.framework.repository.id.factory.internal;

import java.util.concurrent.ConcurrentHashMap;

import com.benefire.framework.repository.id.GeneratorType;
import com.benefire.framework.repository.id.IdGenerator;
import com.benefire.framework.repository.id.factory.IdentifierGeneratorManager;
import com.benefire.framework.repository.id.support.IPIdGenerator;
/**
 * @author jiang
 * 
 * default ID Generation policy manager
 */
public class DefaultIdentifierGeneratorManager implements IdentifierGeneratorManager{
	
	private ConcurrentHashMap<GeneratorType, IdGenerator> generatorStrategyMap = new ConcurrentHashMap<GeneratorType,IdGenerator>();
	
	public DefaultIdentifierGeneratorManager(){
		register(GeneratorType.SNOWFLAKE_IP,new IPIdGenerator());
		//register(GeneratorType.SNOWFLAKE_HOST,new HostNameIdGenerator());
	}
	

	@Override
	public IdGenerator getIdentifierGenerator(GeneratorType type) {
		
		return generatorStrategyMap.get(type);
	}

	@Override
	public void register(GeneratorType type, IdGenerator generator) {
		if(null != generator){
			generatorStrategyMap.put( type, generator );
		}
	}

}
