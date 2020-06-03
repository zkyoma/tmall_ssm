package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper {

    /**
     * 查询pid对于的销量
     * @param pid
     * @return
     */
    Integer getSaleCountByPid(Integer pid);

    /**
     * 根据uid查询购物车中orderItem的集合
     * orderItem还没有生成order，oid为-1
     * @return
     */
    List<OrderItem> getByUidInCart(int uid);

    /**
     * 保存orderItem
     * @param orderItem
     */
    void add(OrderItem orderItem);

    /**
     * 根据id，更新number的值
     * @param oi
     */
    void updateNumberById(OrderItem oi);

    /**
     * 根据uid查询orderItem集合，并联合查询product，再联合查询productSingleImages
     * @param uid
     * @return
     */
    List<OrderItem> getByUidInCartWithPdt(int uid);

    /**
     * 根据id数组查询orderItem集合
     * 联合查询products
     * 联合查询productSingleImages
     * @return
     */
    List<OrderItem> getByIdsArrWithPds(String[] ids);

    /**
     * 根据uid查询oid为-1，即还在购物车的orderItem的数量和
     * @param uid
     * @return
     */
    Integer getSumNumberByUid(int uid);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据id的list集合，批量修改对应的oid
     * @param params "oid" --- oid    "list" --- List<Integer>
     */
    void updateOidByIdList(Map<String, Object> params);
}
