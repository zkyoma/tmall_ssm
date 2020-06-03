package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页显示
     * @param model
     * @return
     */
    @RequestMapping("/admin_category_list")
    public String list(Model model, @RequestParam(value = "pn", defaultValue = "1")Integer pn){
        //1. 设置开始页码，以及每页大小，默认每页显示5个
        PageHelper.startPage(pn, 5);
        //2. 下面的查询即变为分页查询
        List<Category> cs = categoryService.list();
        //3. 封装到pageInfo中，传入连续显示的页数
        PageInfo<Category> pageInfo = new PageInfo<>(cs, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/listCategory";
    }

    /**
     * 新增分类
     * @param c
     * @param uploadImageFile
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/admin_category", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(Category c, @RequestParam("image") MultipartFile uploadImageFile, HttpServletRequest request) throws IOException {
        //保存分类并且上传图片
        categoryService.addAndUploadImage(c, uploadImageFile, new File(request.getServletContext().getRealPath(ImageUtil.IMG_CATEGORY)));
        return ResultInfo.success();
    }

    /**
     * 删除分类
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin_category/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultInfo delete(@PathVariable Integer id, HttpServletRequest request){
        //删除数据库分类信息，删除图片
        categoryService.deleteWithImg(id, request.getServletContext().getRealPath(ImageUtil.IMG_CATEGORY));
        return ResultInfo.success();
    }

    /**
     * 查找分类
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin_category/{id}", method = RequestMethod.GET)
    public String getCategory(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.getById(id);
        model.addAttribute("c", category);
        return "admin/editCategory";
    }

    @ResponseBody
    @RequestMapping(value = "/admin_category_update", method = RequestMethod.POST)
    public ResultInfo update(Category c, @RequestParam("image") MultipartFile uploadImag, HttpServletRequest request) throws IOException {
        //1. 更新数据库
        //2. 更新图片
        //获取图片File对象
        File imgFile = new File(request.getServletContext().getRealPath(ImageUtil.IMG_CATEGORY), c.getId() + ".jpg");
        categoryService.updateWithImage(c, uploadImag, imgFile);
        return ResultInfo.success();
    }
}
