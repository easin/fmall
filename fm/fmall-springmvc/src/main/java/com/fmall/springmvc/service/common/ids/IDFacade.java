package com.fmall.springmvc.service.common.ids;

import java.util.UUID;

import com.fmall.springmvc.service.common.ids.UUIDGenerator;


public class IDFacade {
	public static  String getPKID(){
		IDGenerator gen=new UUIDGenerator();
		return gen.getId();
	}
	
	

	
	public static void main(String[] args) {
		System.out.println(getPKID());
	}
}
                                 