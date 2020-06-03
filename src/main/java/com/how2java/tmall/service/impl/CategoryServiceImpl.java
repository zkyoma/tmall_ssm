package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.CategoryMapper;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public void addAndUploadImage(Category c, MultipartFile uploadImageFile, File imageFolder) throws IOException {
        //1. 保存分类
        categoryMapper.add(c);
        //2. 上传图片到服务器
        //获取上传文件夹对象
        //上传文件对象
        File imageFile = new File(imageFolder, c.getId() + ".jpg");
        if (!imageFile.getParentFile().exists()) {
            //上传文件夹不存在，则创建
            imageFile.getParentFile().mkdirs();
        }
        //上传图片
        uploadImageFile.transferTo(imageFile);
        //图片转换为jpg格式
        BufferedImage img = ImageUtil.change2jpg(imageFile);
        ImageIO.write(img, "jpg", imageFile);
    }

    @Override
    public void deleteWithImg(Integer id, String imageFolder) {
        //1. 删除数据库信息
        categoryMapper.deleteById(id);
        //2. 删除图片
        File imageFile = new File(imageFolder, id + ".jpg");
        imageFile.delete();
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void updateWithImage(Category c, MultipartFile uploadImagFile, File imgFile) throws IOException {
        //1. 更新数据库
        categoryMapper.update(c);
        //2. 更新上传图片
        //上传图片
        if(!imgFile.getParentFile().exists()) {
            imgFile.getParentFile().mkdirs();
        }
        if (uploadImagFile != null && !uploadImagFile.isEmpty()) {
            uploadImagFile.transferTo(imgFile);
            //图片转换为jpg格式
            BufferedImage img = ImageUtil.change2jpg(imgFile);
            ImageIO.write(img, "jpg", imgFile);
        }
    }

    @Override
    public List<Category> listWithPdts() {
        List<Category> cs = categoryMapper.listWithPdts();
        int productNumberEachRow = 8;
        for(Category c : cs){
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for(int i = 0; i < products.size(); i += productNumberEachRow){
                int size = (i + productNumberEachRow) > products.size() ? products.size() : (i + productNumberEachRow);
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            //为product设置firstProductImage
            for(Product p : products){
                productImageService.setFirstProductImage(p);
            }
            c.setProductsByRow(productsByRow);
        }
        return cs;
    }

    @Override
    public Category getByIdWithPdts(Integer cid) {
        Category c = categoryMapper.getByIdWithPdts(cid);
        for(Product p : c.getProducts()){
            //设置销量
            productService.setSaleCountAndReviewCount(p);
            //设置firstProductImage
            productImageService.setFirstProductImage(p);
        }
        return c;
    }
}
