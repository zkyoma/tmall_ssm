package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有
     * @return
     */
    List<User> list();

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    User getByUserName(String name);

    /**
     * 保存用户
     * @param user
     */
    void add(User user);

    /**
     * 根据name和pwd查询
     * @param user
     * @return
     */
    User getByNameAndPwd(User user);
}
