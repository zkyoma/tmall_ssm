package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> list() {
        return propertyMapper.list();
    }

    @Override
    public void add(Property property) {
        propertyMapper.add(property);
    }

    @Override
    public void deleteById(Integer id) {
        propertyMapper.deleteById(id);
    }

    @Override
    public Property getByIdWithCategory(Integer id) {
        //Property property = propertyMapper.getById(id);
        //Category category = categoryMapper.getById(property.getCid());
        //property.setCategory(category);
        return propertyMapper.getByIdWithCgy(id);
        //return property;
    }

    @Override
    public void update(Property property) {
        propertyMapper.update(property);
    }
}
