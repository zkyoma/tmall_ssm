package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

public interface ProductImageService {
    String type_single = "type_single";
    String type_detail = "type_detail";

    /**
     * 保存
     * 保存到数据库
     * 上传图片到服务器
     * @param productImage
     * @param uploadImageFile
     * @param filePaths
     */
    void addAndUploadImgs(ProductImage productImage, MultipartFile uploadImageFile, String ...filePaths);

    /**
     * 根据id删除
     * 1. 删除数据库的内容
     * 2. 删除服务器对应的图片
     * @param id
     */
    void deleteByIdWithImages(Integer id, ServletContext servletContext);

    /**
     * 设置product的firstProductImage
     * @param product
     */
    void setFirstProductImage(Product product);
}
