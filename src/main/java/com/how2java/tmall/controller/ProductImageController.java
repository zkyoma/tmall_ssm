package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductService productService;

    /**
     * 显示产品图片
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping("/admin_productImage_list")
    public String list(@RequestParam("pid") Integer pid, Model model){
        Product p = productService.getWithAllImagesAndCgy(pid);
        model.addAttribute("p", p);
        return "admin/listProductImage";
    }

    /**
     * 保存
     * @param uploadImageFile
     * @param productImage
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin_productImage", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(@RequestParam("image") MultipartFile uploadImageFile, ProductImage productImage, HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();

        if(ProductImageService.type_single.equals(productImage.getType())){
            String imageFolder = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_SINGLE);
            String imageFolder_small = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_SINGLE_SMALL);
            String imageFolder_middle = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_SINGLE_MIDDLE);
            productImageService.addAndUploadImgs(productImage, uploadImageFile, imageFolder, imageFolder_middle, imageFolder_small);
        }else{
            String imageFolder = servletContext.getRealPath(ImageUtil.IMG_PRODUCT_DETAIL);
            productImageService.addAndUploadImgs(productImage, uploadImageFile, imageFolder);
        }
        return ResultInfo.success();
    }

    @RequestMapping(value = "/admin_productImage/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultInfo deleteById(@PathVariable Integer id, HttpServletRequest request){
        //1. 根据id获取productImag对象
        //2. 根据不同的类型删除不同的图片信息
        //3. 删除数据库内容
        productImageService.deleteByIdWithImages(id, request.getServletContext());
        return ResultInfo.success();
    }
}
