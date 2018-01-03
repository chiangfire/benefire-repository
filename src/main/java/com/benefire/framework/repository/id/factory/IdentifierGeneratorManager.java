package com.benefire.framework.repository.id.factory;

import com.benefire.framework.repository.id.GeneratorType;
import com.benefire.framework.repository.id.IdGenerator;

/**
 * @author jiang
 * 
 * ID Generation policy manager
 */
public interface IdentifierGeneratorManager {
	
	public IdGenerator getIdentifierGenerator(GeneratorType type);
	
	public void register(GeneratorType type, IdGenerator generator);

}
