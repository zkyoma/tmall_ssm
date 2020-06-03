package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Review;

import java.util.List;

public interface ReviewMapper {
    /**
     * 根据pid查询reviews，并联合查询user
     * @param pid
     * @return
     */
    List<Review> getByPidWithUser(Integer pid);

    /**
     * 获取pid对于评价的数量
     * @param pid
     * @return
     */
    Integer getCountByPid(Integer pid);

    /**
     * 保存评价
     * @param review
     */
    void add(Review review);
}
