package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.OrderMapper;
import com.how2java.tmall.mapper.ReviewMapper;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Review> getByPidWithUser(int pid) {
        return reviewMapper.getByPidWithUser(pid);
    }

    @Override
    public void updateOrderAndAddReview(Order order, Review review) {
        orderMapper.updateDateAndStatus(order);
        reviewMapper.add(review);
    }
}
