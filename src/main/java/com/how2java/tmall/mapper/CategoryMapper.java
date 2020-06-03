package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Category;

import java.util.List;

public interface CategoryMapper {

    /**
     * 分页查询
     * @return
     */
    List<Category> list();

    /**
     * 保存分类信息
     * @param c
     */
    void add(Category c);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 更新
     * @param c
     */
    void update(Category c);

    /**
     * 查询所有category，关联查询products集合
     * @return
     */
    List<Category> listWithPdts();

    /**
     * 根据id查询category，联合查询对应的products
     * @param cid
     * @return
     */
    Category getByIdWithPdts(Integer cid);
}
