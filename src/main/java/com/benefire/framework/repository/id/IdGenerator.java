package com.benefire.framework.repository.id;

import java.io.Serializable;

/**
 * @author jiang
 */
public interface IdGenerator {
	
    /**
     * Id
     */
	Serializable generate();

}
