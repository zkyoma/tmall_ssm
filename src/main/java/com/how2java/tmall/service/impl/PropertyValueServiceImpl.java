package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.mapper.PropertyValueMapper;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueMapper propertyValueMapper;
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void init(Product product) {
        List<Property> properties = propertyMapper.getByCid(product.getCid());
        Map<String, Integer> params = new HashMap<>();
        for(Property property : properties){
            params.put("ptid", property.getId());
            params.put("pid", product.getId());
            PropertyValue pv = propertyValueMapper.getByPtidAndPid(params);
            if(pv == null){
                pv = new PropertyValue();
                pv.setPid(product.getId());
                pv.setPtid(property.getId());
                propertyValueMapper.add(pv);
            }
        }
    }

    @Override
    public Product getWithCgyAndPvs(int pid) {
        //1. 获取product
        Product product = productMapper.getById(pid);
        ////2. 获取分类
        //Category category = categoryMapper.getById(product.getCid());
        //product.setCategory(category);
        //3. 初始化product的属性
        init(product);
        //4. 查询product对于的属性值
        //List<PropertyValue> propertyValues = propertyValueMapper.getByPid(pid);
        //for(PropertyValue propertyValue : propertyValues){
        //    propertyValue.setProperty(propertyMapper.getById(propertyValue.getPtid()));
        //}
        //product.setPropertyValues(propertyValues);
        product = productMapper.getByIdWithCgyAndPvsPt(pid);
        return product;
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.update(pv);
    }
}
