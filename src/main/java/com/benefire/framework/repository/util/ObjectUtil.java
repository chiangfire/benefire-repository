package com.benefire.framework.repository.util;

import java.util.Arrays;
import java.util.List;
/**
 * @author jiang
 */
public final class ObjectUtil {
	
	public static List<?> objectToList(Object object){
		if(object instanceof List){
			
			return (List<?>)object;
		}
		return Arrays.asList(object);
	}

}
