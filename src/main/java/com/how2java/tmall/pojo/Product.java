package com.how2java.tmall.pojo;
 
import java.util.Date;
import java.util.List;

public class Product {
    private Integer id;
    private String name;
    private String subTitle;
    private Float originalPrice;
    private Float promotePrice;
    private Integer stock;
    private Integer cid;
    private Date createDate;

    /*非数据库字段*/
    private Category category;
    private List<ProductImage> productImages;  //所有图片
    private ProductImage firstProductImage;
    private List<ProductImage> productSingleImages;  //多个单张图片
    private List<ProductImage> productDetailImages;  //多个详情图片
    private List<PropertyValue> propertyValues;  //product对于多个属性值
    private List<Review> reviews; //评价集合
    private int saleCount; //销量
    private int reviewCount; //评价数量

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }
 
    public Category getCategory() {
        return category;
    }
 
    public void setCategory(Category category) {
        this.category = category;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
 
    public String getSubTitle() {
        return subTitle;
    }
 
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }
 
    public Float getOriginalPrice() {
        return originalPrice;
    }
 
    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }
 
    public Float getPromotePrice() {
        return promotePrice;
    }
 
    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }
 
    public Integer getStock() {
        return stock;
    }
 
    public void setStock(Integer stock) {
        this.stock = stock;
    }
 
    public Integer getCid() {
        return cid;
    }
 
    public void setCid(Integer cid) {
        this.cid = cid;
    }
 
    public Date getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}