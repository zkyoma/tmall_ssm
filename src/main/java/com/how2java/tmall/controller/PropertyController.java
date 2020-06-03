package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 属性分页显示
     * @return
     */
    @RequestMapping("/admin_property_list")
    public String list(@RequestParam("cid") Integer cid, @RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        //1. 设置开始页码，每页大小
        PageHelper.startPage(pn, 5);
        //2. 查询
        List<Property> ps = propertyService.list();
        //3. 封装到pageInfo中
        PageInfo<Property> pageInfo = new PageInfo<>(ps, 5);
        //4. 查询分类
        Category category = categoryService.getById(cid);
        //5. 放入request域中
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("c", category);
        model.addAttribute("params", "&cid=" + cid);
        return "admin/listProperty";
    }

    /**
     * 新增属性
     * @param property
     * @return
     */
    @RequestMapping(value = "/admin_property", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(Property property){
        //保存property
        propertyService.add(property);
        return ResultInfo.success().add("cid", property.getCid());
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin_property/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultInfo delete(@PathVariable("id") Integer id){
        propertyService.deleteById(id);
        return ResultInfo.success();
    }

    /**
     * 根据id获取
     */
    @RequestMapping(value = "/admin_property/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable Integer id, Model model){
        Property property = propertyService.getByIdWithCategory(id);
        model.addAttribute("p", property);
        model.addAttribute("params", "&cid=" + property.getCid());
        return "admin/editProperty";
    }

    /**
     * 更新
     * @param property
     * @return
     */
    @RequestMapping(value = "/admin_property", method = RequestMethod.PUT)
    @ResponseBody
    public ResultInfo update(Property property){
        propertyService.update(property);
        return ResultInfo.success().add("cid", property.getCid());
    }
}
