package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.ProductImage;

public interface ProductImageMapper {
    ///**
    // * 根据pid和类型查询图片
    // * @return
    // */
    //List<ProductImage> getByPidAndType(Map<String, Object> params);

    /**
     * 保存
     * @param productImage
     */
    void add(ProductImage productImage);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id获取对象
     * @param id
     */
    ProductImage getById(Integer id);
}
