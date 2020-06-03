package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 业务层
 */
public interface CategoryService {

    /**
     * 分页查询分类
     * @return
     */
    List<Category> list();

    /**
     * 保存分类
     * @param c
     */
    void addAndUploadImage(Category c, MultipartFile uploadImageFile, File imageFolder) throws IOException;

    /**
     * 删除数据库信息
     * 删除图片
     * @param id
     * @param realPath
     */
    void deleteWithImg(Integer id, String realPath);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 更新数据库信息
     * 更新图片
     * @param c
     * @param uploadImag
     * @param imgFile
     */
    void updateWithImage(Category c, MultipartFile uploadImag, File imgFile) throws IOException;

    /**
     * 查询所有category
     * 1.填充products
     * 2.填充productsByRow
     * @return
     */
    List<Category> listWithPdts();

    /**
     * 根据id查询category，并封装好products信息，设置product的销量和评价数
     * @param cid
     * @return
     */
    Category getByIdWithPdts(Integer cid);
}
