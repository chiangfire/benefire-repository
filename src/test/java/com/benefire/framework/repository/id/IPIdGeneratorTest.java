package com.benefire.framework.repository.id;


import java.io.Serializable;

import org.junit.Test;

import com.benefire.framework.repository.id.support.IPIdGenerator;

public class IPIdGeneratorTest {

	@Test
	public void generate() {
    	long time = System.currentTimeMillis();
    	IPIdGenerator g = new IPIdGenerator();
    	for(int i=0;i<100000;i++){
    		Serializable id = g.generate();
    		System.err.println(id);
    	}
    	System.err.println("time consuming："+(System.currentTimeMillis() - time));
	}

}
