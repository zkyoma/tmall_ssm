package com.how2java.tmall.service;

import com.how2java.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    /**
     * 添加购物车
     * 1. 查询该user的购物车有没有该product
     * 2. 如果有，在原来的基础更改数量
     * 3. 如果没有，创建orderItem对象保存数据库
     * @param pid
     * @param num
     * @param id
     */
    void addCart(int pid, int num, Integer id);

    /**
     * 根据uid查询在购物车的所有orderItem
     * 并填充好products,firstProductImage
     * @param id
     * @return
     */
    List<OrderItem> getByUidInCart(int id);

    /**
     * 根据id数组来查询orderItem集合
     * 1. 填充products
     * 2. 设置firstProductImage
     * @param oiid
     * @return
     */
    List<OrderItem> getByIdsArr(String[] oiid);

    /**
     * 查询购物车总数量
     * @param id
     * @return
     */
    Integer getCartTotalItemNumber(int id);

    /**
     * 更新购物车对应orderItem的数量
     * 通过uid, pid确定
     * @param pid
     * @param number
     * @param uid
     */
    void changeOrderItem(int pid, int number, int uid);

    /**
     * 根据id删orderItem
     * @param id
     */
    void deleteById(int id);
}
