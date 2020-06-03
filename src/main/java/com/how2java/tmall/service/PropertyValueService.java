package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;

public interface PropertyValueService {
    /**
     * 初始化product的属性值
     * @param product
     */
    void init(Product product);

    /**
     * 根据pid查询pvs，并填充分类信息，属性值信息
     * @param pid
     * @return
     */
    Product getWithCgyAndPvs(int pid);

    /**
     * 更新value值
     * @param pv
     */
    void update(PropertyValue pv);
}
