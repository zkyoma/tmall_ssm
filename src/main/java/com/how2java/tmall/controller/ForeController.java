package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.how2java.tmall.pojo.*;
import com.how2java.tmall.service.*;
import com.how2java.tmall.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ForeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReviewService reviewService;

    /**
     * 前台首页
     * @param model
     * @return
     */
    @RequestMapping("/forehome")
    public String home(Model model){
        List<Category> cs = categoryService.listWithPdts();
        model.addAttribute("cs", cs);
        return "fore/home";
    }

    /**
     * 用户名是否可用
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUser")
    public ResultInfo checkUser(@RequestParam("name") String name){
        System.out.println(name);
        boolean flag = userService.getByUserName(name);
        if(flag){
            return ResultInfo.success().add("msg", "用户名可用");
        }
        return ResultInfo.error().add("msg", "用户名已经存在，请更换");
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/foreregister")
    public String register(User user){
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping("/forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session){
        User user = userService.getByNameAndPwd(name, password);
        if(user == null){
            model.addAttribute("msg", "账号或密码错误");
            return "fore/login";
        }
        session.setAttribute("user", user);
        //把购物车的总数量放在session域中
        int cartTotalItemNumber = orderItemService.getCartTotalItemNumber(user.getId());
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        return "redirect:forehome";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/forelogout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:forehome";
    }

    /**
     * 产品页
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping("/foreproduct")
    public String product(@RequestParam("pid") Integer pid, Model model){
        Product product = productService.getWithPisPvsPtRwsUser(pid);
        model.addAttribute("p", product);
        return "fore/product";
    }

    /**
     * 检查是否登录
     * @param session
     * @return
     */
    @RequestMapping("/forecheckLogin")
    @ResponseBody
    public ResultInfo checkLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            return ResultInfo.success();
        }
        return ResultInfo.error();
    }

    /**
     * 模态框，ajax登录
     * @param name
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/foreloginAjax")
    @ResponseBody
    public ResultInfo loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session){
        User user = userService.getByNameAndPwd(name, password);
        if(user != null){
            session.setAttribute("user", user);
            //把购物车的总数量放在session域中
            int cartTotalItemNumber = orderItemService.getCartTotalItemNumber(user.getId());
            session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
            return ResultInfo.success();
        }
        return ResultInfo.error().add("msg", "账号或密码错误");
    }

    /**
     * 分类页
     * @return
     */
    @RequestMapping("/forecategory")
    public String category(@RequestParam("cid") Integer cid, @RequestParam(value = "sort", defaultValue = "all") String sort, Model model){
        Category c = categoryService.getByIdWithPdts(cid);
        if(null!=sort){
            switch(sort){
                case "review":
                    c.getProducts().sort((p1, p2) -> p2.getReviewCount() - p1.getReviewCount());
                    break;
                case "date" :
                    c.getProducts().sort((p1, p2) -> p2.getCreateDate().compareTo(p1.getCreateDate()));
                    break;

                case "saleCount" :
                    c.getProducts().sort((p1, p2) -> p2.getSaleCount() - p1.getSaleCount());
                    break;

                case "price":
                    c.getProducts().sort((p1, p2) -> (int) (p2.getPromotePrice() - p1.getPromotePrice()));
                    break;

                case "all":
                    c.getProducts().sort((p1, p2) -> p2.getSaleCount() * p2.getReviewCount() - p1.getSaleCount() * p1.getReviewCount());
                    break;
            }
        }
        model.addAttribute("c", c);
        return "fore/category";
    }

    /**
     * 搜索
     * @param keyword
     * @param model
     * @return
     */
    @RequestMapping("/foresearch")
    public String search(String keyword, Model model){
        PageHelper.startPage(0, 20);
        List<Product> ps = productService.getByKeyword(keyword);
        model.addAttribute("ps", ps);
        return "fore/searchResult";
    }

    /**
     * 立即购买
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @RequestMapping("/forebuyNow")
    public String buyNow(int pid, int num, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Product p = productService.getById(pid);

        float total= p.getPromotePrice() * num;
        OrderItem oi = new OrderItem();
        oi.setNumber(num);
        oi.setProduct(p);
        oi.setUid(user.getId());
        oi.setPid(pid);

        List<OrderItem> ois = new ArrayList<>();
        ois.add(oi);
        session.setAttribute("ois", ois);
        session.setAttribute("isBuyNow", true);
        model.addAttribute("total", total);
        return "fore/buy";
    }

    /**
     * 添加购物车
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @RequestMapping("/foreaddCart")
    @ResponseBody
    public ResultInfo addCart(int pid, int num, HttpSession session){
        User user = (User) session.getAttribute("user");
        orderItemService.addCart(pid, num, user.getId());
        //更新session中cartTotalNumber的数量
        int cartTotalItemNumber = (int) session.getAttribute("cartTotalItemNumber");
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber + num);
        System.out.println(cartTotalItemNumber);
        return ResultInfo.success().add("msg", "加入购物车成功！");
    }

    /**
     * 购物车页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/forecart")
    public String cart(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<OrderItem> ois = orderItemService.getByUidInCart(user.getId());
        model.addAttribute("ois", ois);
        return "fore/cart";
    }

    /**
     * 购物车中购买
     * @return
     */
    @RequestMapping("/forebuy")
    public String buy(String[] oiid, Model model, HttpSession session) {
        List<OrderItem> ois = orderItemService.getByIdsArr(oiid);
        float total = 0;
        for(OrderItem oi : ois){
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
        }
        model.addAttribute("total", total);
        session.setAttribute("ois", ois);
        session.setAttribute("isBuyNow", false);

        return "fore/buy";
    }

    /**
     * 更改购物车订单项的数量
     * @param pid
     * @param number
     * @param session
     * @return
     */
    @RequestMapping("/forechangeOrderItem")
    @ResponseBody
    public ResultInfo changeOrderItem(int pid, int number, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ResultInfo.error();
        }
        orderItemService.changeOrderItem(pid, number, user.getId());
        session.setAttribute("cartTotalItemNumber", number);
        return ResultInfo.success();
    }

    /**
     * 购物车删除订项
     * @param oiid
     * @param session
     * @return
     */
    @RequestMapping("/foredeleteOrderItem")
    @ResponseBody
    public ResultInfo deleteOrderItem(int oiid, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ResultInfo.error();
        }
        //1. 删除数据库内容
        orderItemService.deleteById(oiid);
        //2. 更新session中的数据
        int cartTotalItemNumber = orderItemService.getCartTotalItemNumber(user.getId());
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        return ResultInfo.success();
    }

    /**
     * 提交订单
     * @param order
     * @param session
     * @return
     */
    @RequestMapping("/forecreateOrder")
    public String createOrder(Order order, HttpSession session){
        User user = (User) session.getAttribute("user");
        boolean isBuyNow = (boolean) session.getAttribute("isBuyNow");
        List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
        float total = orderService.addAndReturnTotal(order, user.getId(), ois, isBuyNow);
        session.removeAttribute("ois");
        session.removeAttribute("isBuyNow");

        int number = 0;
        for(OrderItem oi : ois){
            number += oi.getNumber();
        }
        int cartTotalItemNumber = (int) session.getAttribute("cartTotalItemNumber");
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber - number);

        return "redirect:forealipay?oid=" + order.getId() + "&total=" + total;
    }

    /**
     * 确认支付
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping("/forepayed")
    public String payed(int oid, Model model) {
        Order order = orderService.updatePay(oid);
        model.addAttribute("o", order);
        return "fore/payed";
    }

    /**
     * 我的订单页
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/forebought")
    public String bought(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Order> os = orderService.list(user.getId());
        model.addAttribute("os", os);
        return "fore/bought";
    }

    /**
     * 确认收货页面
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping("/foreconfirmPay")
    public String confirmPay(int oid, Model model){
        Order order = orderService.getByOid(oid);
        model.addAttribute("o", order);
        return "fore/confirmPay";
    }

    /**
     * 确认收货
     * @param oid
     * @return
     */
    @RequestMapping("/foreorderConfirmed")
    public String orderConfirmed(int oid){
        orderService.updateOrderConfirmed(oid);
        return "fore/orderConfirmed";
    }

    /**
     * 删除订单
     * @param oid
     * @return
     */
    @RequestMapping("/foredeleteOrder")
    @ResponseBody
    public ResultInfo deleteOrder(int oid){
        orderService.deleteOrder(oid);
        return ResultInfo.success();
    }

    /**
     * 评价页面
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping("/forereview")
    public String review(int oid, Model model){
        Order order = orderService.getByOid(oid);
        Product product = order.getOrderItems().get(0).getProduct();
        productService.setSaleCountAndReviewCount(product);
        List<Review> reviews = reviewService.getByPidWithUser(product.getId());
        model.addAttribute("o", order);
        model.addAttribute("p", product);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    /**
     * 提交评价
     * @return
     */
    @RequestMapping("/foredoreview")
    public String doreview(int pid, int oid, String content, HttpSession session){
        Order order = new Order();
        order.setStatus(OrderService.finish);
        order.setId(oid);

        Review review = new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setCreateDate(new Date());
        User user = (User) session.getAttribute("user");
        review.setUid(user.getId());
        reviewService.updateOrderAndAddReview(order, review);

        return "redirect:forereview?oid=" + oid + "&showonly=true";
    }
}
