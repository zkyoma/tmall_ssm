package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.UserMapper;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public boolean getByUserName(String name) {
        return userMapper.getByUserName(name) == null;
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public User getByNameAndPwd(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userMapper.getByNameAndPwd(user);
    }
}
