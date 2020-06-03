package com.how2java.tmall.interceptor;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OtherInterceptor implements HandlerInterceptor {
    @Autowired
    private CategoryService categoryService;

    /**
     * 业务之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 业务之后，视图显示之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //这里是获取分类集合信息，用于放在搜索栏下面
        List<Category> cs = categoryService.list();
        request.getSession().setAttribute("cs", cs);
        // 这里是获取当前的contextPath:tmall_ssm,用与放在左上角那个变形金刚
        // 点击之后才能够跳转到首页，否则点击之后也仅仅停留在当前页面
        HttpSession session = request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        request.setAttribute("contextPath", contextPath);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
