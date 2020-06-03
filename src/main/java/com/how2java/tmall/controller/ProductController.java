package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 显示分页
     * @param cid
     * @param pn
     * @param model
     * @return
     */
    @RequestMapping("/admin_product_list")
    public String list(@RequestParam("cid") Integer cid, @RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 5);
        List<Product> products = productService.list(cid);
        PageInfo<Product> pageInfo = new PageInfo<>(products, 5);
        Category category = categoryService.getById(cid);
        model.addAttribute("c", category);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("params", "&cid=" + cid);
        return "admin/listProduct";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/admin_product", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(Product product){
        productService.add(product);
        return ResultInfo.success().add("cid", product.getCid());
    }

    /**
     * 根据id查询
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin_product/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id, Model model){
        Product product = productService.getByIdWithCategory(id);
        //Category category = categoryService.getById(product.getCid());
        //product.setCategory(category);
        model.addAttribute("p", product);
        return "admin/editProduct";
    }

    /**
     * 更新
     * @param product
     * @return
     */
    @RequestMapping(value = "/admin_product", method = RequestMethod.PUT)
    @ResponseBody
    public ResultInfo update(Product product){
        productService.update(product);
        return ResultInfo.success().add("cid", product.getCid());
    }

    @RequestMapping(value = "/admin_product/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultInfo deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return ResultInfo.success();
    }
}
