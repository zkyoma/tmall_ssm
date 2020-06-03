package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Product;

import java.util.List;

public interface ProductService {

    /**
     * 根据cid查询产品
     * @param cid
     * @return
     */
    List<Product> list(Integer cid);

    /**
     * 保存
     * @param product
     */
    void add(Product product);

    /**
     * 更新
     * @param product
     */
    void update(Product product);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据pid查询product，并填充productImages
     * @param pid
     * @return
     */
    Product getWithAllImagesAndCgy(Integer pid);

    /**
     * 根据id查询product，封装category属性
     * @param id
     * @return
     */
    Product getByIdWithCategory(Integer id);

    /**
     * 根据id查询product
     * 1. 填充productImages
     * 2. 填充propertyValues -- property
     * 3. 填充reviews  ---  user
     * @param pid
     * @return
     */
    Product getWithPisPvsPtRwsUser(Integer pid);

    /**
     * 为product设置productImages
     * @param product
     */
    void setProductAllImages(Product product);

    /**
     * 根据关键字搜素products
     * @param keyword
     * @return
     */
    List<Product> getByKeyword(String keyword);

    /**
     * 设置销量和评价数量
     * @param p
     */
    void setSaleCountAndReviewCount(Product p);

    /**
     * 根据id查询product
     * @param id
     * @return
     */
    Product getById(int id);
}
