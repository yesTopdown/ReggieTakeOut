package com.xxxx.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.xxxx.reggie.common.BaseContext;
import com.xxxx.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *检测用户是否登录
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1= (HttpServletRequest) request;
        HttpServletResponse response1= (HttpServletResponse) response;
        //1.获取请求的URI
        String requestURI = request1.getRequestURI();
        //不需要处理的请求路径
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3.如果不需要处理，则直接放行
        if(check){
            chain.doFilter(request1,response1);

            return;
        }
        //4-1.判断登录状态。如果已登录。则直接放行 管理员
        if(request1.getSession().getAttribute("employee")!=null){
            Long empId = (Long) request1.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            long id = Thread.currentThread().getId();
            log.info("当前线程的id是{}",id);
            chain.doFilter(request1,response1);
            return;
        }
        //4-2.判断登录状态。如果已登录。则直接放行 移动端
        if(request1.getSession().getAttribute("user")!=null){
            Long userId = (Long) request1.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            long id = Thread.currentThread().getId();
            log.info("当前线程的id是{}",id);
            chain.doFilter(request1,response1);
            return;
        }
        //5. 如果未登录则返回为登录结果
        response1.getWriter().write(JSON.toJSONString(R.error("NOT LOGIN")));
        //log.info("拦截到请求：{}",request1.getRequestURI());

    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
