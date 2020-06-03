package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 保存
     * @param product
     */
    void add(Product product);

    /**
     * 根据id查询product
     * @param id
     * @return
     */
    Product getById(Integer id);

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
     * 根据cid查询product集合，并封装好singleImages
     * @param cid
     * @return
     */
    List<Product> listWithSingles(Integer cid);

    /**
     * 根据id查询product，封装category属性
     * @param id
     * @return
     */
    Product getByIdWithCategory(Integer id);

    /**
     * 根据id查询product，填充所有的productImages
     * @param pid
     * @return
     */
    Product getByIdWithAllImagesAndCgy(Integer pid);

    /**
     * 根据id查询product，填充propertyValues，且为每个propertyValue填充property
     * @param pid
     * @return
     */
    Product getByIdWithCgyAndPvsPt(int pid);

    /**
     * 根据id查询product
     * 1. 关联查询productImages
     * 2. 关联查询propertyValues  --- property
     * 3. 关联查询reviews  --- user
     * @param pid
     */
    Product getByIdWithPisAndPvsPt(Integer pid);

    /**
     * 根据关键字查询
     * @param keyword
     * @return
     */
    List<Product> getByKeyword(String keyword);
}
