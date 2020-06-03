package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.OrderItemMapper;
import com.how2java.tmall.mapper.OrderMapper;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<Order> list() {
        //1. 查询所有订单信息
        List<Order> orders = orderMapper.listWithOisPdt();
        //2. 封装订单项信息
        for(Order order : orders){
            float total = 0;
            int totalNumber = 0;
            for(OrderItem orderItem : order.getOrderItems()) {
                Product product = orderItem.getProduct();
                productImageService.setFirstProductImage(product);
                total += orderItem.getNumber() * product.getPromotePrice();
                totalNumber += orderItem.getNumber();
            }
            order.setTotal(total);
            order.setTotalNumber(totalNumber);
        }
        return orders;
    }

    @Override
    public void updateDelivery(Order order) {
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderMapper.updateDateAndStatus(order);
    }

    @Override
    public Float addAndReturnTotal(Order order, int uid, List<OrderItem> ois, boolean isBuyNow) {
        //1. 保存订单
        order.setUid(uid);
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt();
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setStatus(OrderService.waitPay);
        orderMapper.add(order);
        float total = 0;
        //2. 判断是否是立即购买
        if(isBuyNow){
            //立即购买
            //保存订单项
            OrderItem orderItem = ois.get(0);
            orderItem.setOid(order.getId());
            orderItemMapper.add(orderItem);
            total = orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
        }else{
            //购物车购买
            //修改对应orderItem的oid
            List<Integer> oiidList = new ArrayList<>();
            for(OrderItem oi : ois){
                total += oi.getNumber() * oi.getProduct().getPromotePrice();
                oiidList.add(oi.getId());
            }
            //封装参数map
            Map<String, Object> params = new HashMap<>();
            params.put("oid", order.getId());
            params.put("list", oiidList);
            orderItemMapper.updateOidByIdList(params);
        }
        return total;
    }

    @Override
    public List<Order> list(int uid) {
        List<Order> os = orderMapper.getByUidWithOutDelete(uid);
        for(Order o : os){
            float total = 0;
            int number = 0;
            for(OrderItem oi : o.getOrderItems()){
                productImageService.setFirstProductImage(oi.getProduct());
                number += oi.getNumber();
                total = oi.getNumber() * oi.getProduct().getPromotePrice();
            }
            o.setTotal(total);
            o.setTotalNumber(number);
        }
        return os;
    }

    @Override
    public Order updatePay(int oid) {
        Order order = orderMapper.getById(oid);
        order.setId(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderMapper.updateDateAndStatus(order);
        return order;
    }

    @Override
    public Order getByOid(int id) {
        Order order = orderMapper.getByIdWithOis(id);
        for(OrderItem orderItem : order.getOrderItems()){
            productImageService.setFirstProductImage(orderItem.getProduct());
        }
        return order;
    }

    @Override
    public void updateOrderConfirmed(int oid) {
        Order order = new Order();
        order.setId(oid);
        order.setConfirmDate(new Date());
        order.setStatus(OrderService.waitReview);
        orderMapper.updateDateAndStatus(order);
    }

    @Override
    public void deleteOrder(int oid) {
        Order order = new Order();
        order.setId(oid);
        order.setStatus(OrderService.delete);
        orderMapper.updateDateAndStatus(order);
    }
}
