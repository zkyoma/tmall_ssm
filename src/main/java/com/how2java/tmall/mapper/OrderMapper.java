package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Order;

import java.util.List;

public interface OrderMapper {
    /**
     * 查询所有订单信息
     * @return
     */
    List<Order> list();

    /**
     * 查询所有order
     * 为每个order填充orderItems集合
     * 为每个orderItem填充product
     * @return
     */
    List<Order> listWithOisPdt();

    /**
     * 保存订单
     * @param order
     */
    void add(Order order);

    /**
     * 根据uid查询订单集合（除delete外）
     * 联合查询订单项
     * @param uid
     * @return
     */
    List<Order> getByUidWithOutDelete(int uid);

    /**
     * 更新发货时间和订单状态
     * @param order
     */
    void updateDateAndStatus(Order order);

    /**
     * 根据id查询订单
     * @param oid
     * @return
     */
    Order getById(int oid);

    /**
     * 根据id查询订单，联合查询订单项集合，product，firstProductImage
     * @param id
     * @return
     */
    Order getByIdWithOis(int id);
}
