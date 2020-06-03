package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.OrderItemMapper;
import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.mapper.ReviewMapper;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductImageService productImageService;

    @Override
    public List<Product> list(Integer cid) {
        List<Product> products = productMapper.listWithSingles(cid);
        for(Product product : products){
            productImageService.setFirstProductImage(product);
        }
        return products;
    }

    @Override
    public void add(Product product) {
        product.setCreateDate(new Date());
        productMapper.add(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public Product getWithAllImagesAndCgy(Integer pid) {
        //1. 查询product
        Product product = productMapper.getByIdWithAllImagesAndCgy(pid);
        //2. 封装参数，查询productImages集合
        setProductAllImages(product);
        return product;
    }

    @Override
    public Product getByIdWithCategory(Integer id) {
        return productMapper.getByIdWithCategory(id);
    }

    @Override
    public Product getWithPisPvsPtRwsUser(Integer pid) {
        //查询product
        Product product = productMapper.getByIdWithPisAndPvsPt(pid);
        //查询reviews集合
        List<Review> reviews = reviewMapper.getByPidWithUser(pid);
        product.setReviews(reviews);
        //设置总评价数
        product.setReviewCount(reviews.size());
        //设置销量
        int saleCount = orderItemMapper.getSaleCountByPid(pid);
        product.setSaleCount(saleCount);
        //设置images
        setProductAllImages(product);
        return product;
    }

    @Override
    public void setProductAllImages(Product product) {
        List<ProductImage> productImagesSingle = new ArrayList<>();
        List<ProductImage> productImagesDetail = new ArrayList<>();
        for(ProductImage pi : product.getProductImages()){
            if(ProductImageService.type_single.equals(pi.getType())){
                productImagesSingle.add(pi);
            }else{
                productImagesDetail.add(pi);
            }
        }
        product.setProductSingleImages(productImagesSingle);
        product.setProductDetailImages(productImagesDetail);
        productImageService.setFirstProductImage(product);
    }

    @Override
    public List<Product> getByKeyword(String keyword) {
        List<Product> ps = productMapper.getByKeyword(keyword);
        for(Product p : ps){
            //设置firstProductImage
            productImageService.setFirstProductImage(p);
            //设置销量和评价数量
           setSaleCountAndReviewCount(p);
        }
        return ps;
    }

    @Override
    public void setSaleCountAndReviewCount(Product p) {
        int saleCount = orderItemMapper.getSaleCountByPid(p.getId());
        p.setSaleCount(saleCount);
        int reviewCount = reviewMapper.getCountByPid(p.getId());
        p.setReviewCount(reviewCount);
    }

    @Override
    public Product getById(int id) {
        Product product = productMapper.getById(id);
        productImageService.setFirstProductImage(product);
        return product;
    }
}
