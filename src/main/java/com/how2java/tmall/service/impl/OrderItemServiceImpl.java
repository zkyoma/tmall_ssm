package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.OrderItemMapper;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductImageService productImageService;

    @Override
    public void addCart(int pid, int num, Integer uid) {
        //1. 查询该user购物车的订单项集合
        List<OrderItem> ois = orderItemMapper.getByUidInCart(uid);
        boolean flag = false;
        //2. 判断是否有该product
        for(OrderItem oi : ois){
            if(oi.getPid() == pid){
                //若有，在原来的基础上增加product的数量
                oi.setNumber(oi.getNumber() + num);
                //更新数量到数据库
                orderItemMapper.updateNumberById(oi);
                flag = true;
                break;
            }
        }
        if(!flag){
            //若没有，则创建新的orderItem，并保存到数据库
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(num);
            orderItem.setPid(pid);
            orderItem.setUid(uid);
            orderItem.setOid(-1);
            orderItemMapper.add(orderItem);
        }
    }

    @Override
    public List<OrderItem> getByUidInCart(int uid) {
        List<OrderItem> ois = orderItemMapper.getByUidInCartWithPdt(uid);
        for(OrderItem oi : ois){
            productImageService.setFirstProductImage(oi.getProduct());
        }
        return ois;
    }

    @Override
    public List<OrderItem> getByIdsArr(String[] oiid) {
        List<OrderItem> ois = orderItemMapper.getByIdsArrWithPds(oiid);
        for(OrderItem oi : ois){
            productImageService.setFirstProductImage(oi.getProduct());
        }
        return ois;
    }

    @Override
    public Integer getCartTotalItemNumber(int uid) {
        return orderItemMapper.getSumNumberByUid(uid);
    }

    @Override
    public void changeOrderItem(int pid, int number, int uid) {
        List<OrderItem> ois = orderItemMapper.getByUidInCart(uid);
        for(OrderItem oi : ois){
            if(oi.getPid() == pid){
                oi.setNumber(number);
                orderItemMapper.updateNumberById(oi);
                break;
            }
        }
    }

    @Override
    public void deleteById(int id) {
        orderItemMapper.deleteById(id);
    }
}
