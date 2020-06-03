package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    /**
     * 根据oid查询评价信息
     * 填充user信息
     * @param pid
     * @return
     */
    List<Review> getByPidWithUser(int pid);

    /**
     * 更新订单状态
     * 保存评价
     * @param order
     * @param review
     */
    void updateOrderAndAddReview(Order order, Review review);
}
