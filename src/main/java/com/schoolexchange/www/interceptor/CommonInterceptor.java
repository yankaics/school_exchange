package com.schoolexchange.www.interceptor;

import com.schoolexchange.www.service.RequestUrlSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator
 * on 2015/12/5
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RequestUrlSecurity requestUrlSecurity;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String test = "/account";
        System.out.println("加密前的字符：" + test);
        System.out.println("加密后的字符：" + requestUrlSecurity.encrypt(test));
        System.out.println("解密后的字符：" + requestUrlSecurity.decrypt(requestUrlSecurity.encrypt(test)));
        HttpSession session = request.getSession();
        if (null == session.getAttribute("sx_user_name")) {
            response.sendRedirect("/to_login?requestUrl=" + request.getRequestURI());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
