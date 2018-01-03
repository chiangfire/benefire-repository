package com.benefire.framework.repository.vendor;

import com.benefire.framework.repository.domain.PageRequest;

/**
 * @author jiang
 */
public interface LimitHandler {
	
	public String processSql(String sql);
	
	public int getOffset(PageRequest pr);
	
	public String supportLimitOne(String sql);

}
