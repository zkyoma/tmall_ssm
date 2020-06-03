package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.PropertyValue;

import java.util.Map;

public interface PropertyValueMapper {

    /**
     * 保存propertyValue
     * @param pv
     */
    void add(PropertyValue pv);

    /**
     * 根据pid, ptid查询
     * @return
     */
    PropertyValue getByPtidAndPid(Map<String, Integer> params);

    ///**
    // * 根据pid查询属性值集合
    // * @param pid
    // * @return
    // */
    //List<PropertyValue> getByPid(int pid);

    /**
     * 更新value
     * @param pv
     */
    void update(PropertyValue pv);
}
