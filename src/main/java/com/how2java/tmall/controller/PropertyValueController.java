package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.PropertyValueService;
import com.how2java.tmall.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PropertyValueController {
    @Autowired
    private PropertyValueService propertyValueService;

    /**
     * 进入属性修改页面
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin_propertyValue/{pid}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer pid, Model model){
        Product product = propertyValueService.getWithCgyAndPvs(pid);
        model.addAttribute("p", product);
        return "admin/editPropertyValue";
    }

    @ResponseBody
    @RequestMapping(value = "/admin_propertyValue", method = RequestMethod.PUT)
    public ResultInfo update(PropertyValue pv){
        propertyValueService.update(pv);
        return ResultInfo.success();
    }
}
