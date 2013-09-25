package com.fmall.springmvc.mapper;

import com.fmall.springmvc.javabean.GenericUser;

public interface GenericUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GenericUser record);

    int insertSelective(GenericUser record);

    GenericUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GenericUser record);

    int updateByPrimaryKey(GenericUser record);
}