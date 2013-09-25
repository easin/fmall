package com.fmall.springmvc.service.common.ids;

import java.util.UUID;



public  class UUIDGenerator implements IDGenerator{

	public String getId() {
		
		return UUID.randomUUID().toString();
	}
	                                            
}
