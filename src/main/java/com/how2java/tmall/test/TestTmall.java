package com.how2java.tmall.test;

import com.how2java.tmall.mapper.CategoryMapper;
import com.how2java.tmall.mapper.OrderItemMapper;
import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.mapper.ReviewMapper;
import com.how2java.tmall.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestTmall {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void testCategory(){
        List<Category> categories = categoryMapper.list();
        for(Category c : categories){
            System.out.println(c);
        }
    }

    @Test
    public  void test(){
        //准备分类测试数据：
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?serverTimezone=UTC",
                        "root", "123456");
                Statement s = c.createStatement()) {
            for (int i = 1; i <=10 ; i++) {
                String sqlFormat = "insert into category values (null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);
                s.execute(sql);
            }
            System.out.println("已经成功创建10条分类测试数据");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList(){
        List<Integer> list = new ArrayList<>();
        list.add(222);
        System.out.println(list.get(0));
    }

    @Test
    public void testGetByIdWithPisAndPvsPtAndRewsUser(){
      /*  List<Review> reviews = reviewMapper.getByPidWithUser(901);
        System.out.println(reviews.size());*/
       /* List<OrderItem> ois = orderItemMapper.getByPid(959);
        System.out.println(ois);*/
    /*    Category category = categoryMapper.getByIdWithPdts(60);
        System.out.println(category.getProducts());*/
        System.out.println(orderItemMapper.getSaleCountByPid(95));
    }
}