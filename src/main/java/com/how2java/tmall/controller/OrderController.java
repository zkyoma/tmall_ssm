package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查看订单
     * @param pn
     * @param model
     * @return
     */
    @RequestMapping("/admin_order_list")
    public String list(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        PageHelper.startPage(pn, 5);
        List<Order> orders = orderService.list();
        PageInfo<Order> pageInfo = new PageInfo<>(orders, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/listOrder";
    }

    /**
     * 发货
     * @param order
     * @return
     */
    @RequestMapping("/admin_order_delivery")
    public String delivery(Order order){
        orderService.updateDelivery(order);
        return "redirect:admin_order_list";
    }
}
