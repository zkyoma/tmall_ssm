package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Property;

import java.util.List;

public interface PropertyService {

    /**
     * 查询属性
     * @return
     */
    List<Property> list();

    /**
     * 保存属性
     * @param property
     */
    void add(Property property);

    /**
     * 根据id删除属性
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Property getByIdWithCategory(Integer id);

    /**
     * 更新propertyh
     * @param property
     */
    void update(Property property);
}
