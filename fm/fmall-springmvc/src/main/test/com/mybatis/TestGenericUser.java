package com.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.common.pagi.mybatis.PageParameter;

public class TestGenericUser {
	// ApplicationContext context = null;
	BeanFactory context = null;
	SqlSession sqlSession=null;

	@Before
	public void initContext() {
		// this.context = new
		// FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");
		this.context = new ClassPathXmlApplicationContext(
				"classpath:spring/*.xml");
		sqlSession=(SqlSessionTemplate)context.getBean("sqlSession");
		
	}
	@Test
	public void test_selectByPage(){
		Map map = new HashMap();
		PageParameter page=new PageParameter();
			map.put("page", page);
		sqlSession.selectList("com.fmall.springmvc.mapper.GenericUserMapper.selectByPage", map);
	}
}
