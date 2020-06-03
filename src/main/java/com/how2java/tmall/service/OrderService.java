package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    /**
     * 查询所有订单信息，并封装好订单项信息，封装订单项的产品信息
     * @return
     */
    List<Order> list();

    /**
     * 发货更新
     * @param order
     */
    void updateDelivery(Order order);

    /**
     * 生成订单
     * 1. 保存订单
     * 2. 判断是立即购买还是购物车购买
     *    1) 立即购买
     *        保存订单  保存订单项
     *    2) 购物车购买
     *        保存订单  更新订单项
     * @param order
     * @param uid
     * @param ois
     * @param isBuyNow
     * @return
     */
    Float addAndReturnTotal(Order order, int uid, List<OrderItem> ois, boolean isBuyNow);

    /**
     * 获取user对应的所有订单（删除的除外），并填充订单项信息
     * @param uid
     * @return
     */
    List<Order> list(int uid);

    /**
     * 支付更新
     * 1. 更新付款时间
     * 2. 更新订单状态
     * @param oid
     * @return
     */
    Order updatePay(int oid);

    /**
     * 根据id查询order，填充orderItem，firstProductImage
     * @param id
     * @return
     */
    Order getByOid(int id);

    /**
     * 确认收货后，更新收货时间，订单状态
     * @param oid
     */
    void updateOrderConfirmed(int oid);

    /**
     * 删除订单（修改订单状态为delete）
     * @param oid
     */
    void deleteOrder(int oid);
}
