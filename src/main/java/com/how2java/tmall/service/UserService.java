package com.how2java.tmall.service;

import com.how2java.tmall.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> list();

    /**
     * 判断该用户名是否存在
     * 返回true: 用户名不存在，可以使用
     * 返回false：用户名已经存在，不能使用
     * @param name
     * @return
     */
    boolean getByUserName(String name);

    /**
     * 保存用户
     * @param user
     */
    void add(User user);

    /**
     * 根据name和pwd查询用户
     * @param name
     * @param password
     * @return
     */
    User getByNameAndPwd(String name, String password);
}
