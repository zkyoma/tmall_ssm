package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ProductImageMapper;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public void addAndUploadImgs(ProductImage productImage, MultipartFile uploadImageFile, String... filePaths) {
        //1. 保存到数据库
        productImageMapper.add(productImage);
        //2. 上传到服务器
        String fileName = productImage.getId() + ".jpg";
        File imageFile = new File(filePaths[0], fileName);
        if (!imageFile.getParentFile().exists()) {
            imageFile.getParentFile().mkdirs();
        }
        try {
            uploadImageFile.transferTo(imageFile);
            BufferedImage img = ImageUtil.change2jpg(imageFile);
            ImageIO.write(img, "jpg", imageFile);
            if (ProductImageService.type_single.equals(productImage.getType())) {
                File f_middle = new File(filePaths[1], fileName);
                File f_small = new File(filePaths[2], fileName);

                ImageUtil.resizeImage(imageFile, 56, 56, f_small);
                ImageUtil.resizeImage(imageFile, 217, 190, f_middle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByIdWithImages(Integer id, ServletContext servletContext) {
        //1. 根据id获取productImages对象
        ProductImage pi = productImageMapper.getById(id);
        int pid = pi.getPid();
        //2. 判断图片类型
        String fileName = pi.getId() + ".jpg";
        if(pi.getType().equals(ProductImageService.type_single)){
            //type_single类型
            //获取要删除文件的路径
            String fileSingle = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_SINGLE);
            String fileSingle_middle = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_SINGLE_MIDDLE);
            String fileSingle_small = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_SINGLE_SMALL);
            //删除文件
            new File(fileSingle, fileName).delete();
            new File(fileSingle_middle, fileName).delete();
            new File(fileSingle_small, fileName).delete();
        }else{
            String fileDetail = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_DETAIL);
            new File(fileDetail).delete();
        }
        //3. 删除数据库的信息
        productImageMapper.deleteById(id);
    }

    @Override
    public void setFirstProductImage(Product product) {
        if(product.getProductSingleImages() != null && product.getProductSingleImages().size() > 0){
            product.setFirstProductImage(product.getProductSingleImages().get(0));
        }
    }
}
