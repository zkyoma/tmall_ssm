package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Property;

import java.util.List;

public interface PropertyMapper {

    /**
     * 查询所有属性
     * @return
     */
    List<Property> list();

    /**
     * 保存属性
     * @param property
     */
    void add(Property property);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Property getById(Integer id);

    /**
     * 更新
     * @param property
     */
    void update(Property property);

    /**
     * 根据cid查询property集合
     * @param cid
     * @return
     */
    List<Property> getByCid(Integer cid);

    /**
     * 根据id查询property，同时带有category
     * @param id
     * @return
     */
    Property getByIdWithCgy(Integer id);
}
