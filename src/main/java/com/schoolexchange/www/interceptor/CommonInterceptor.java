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
        String requestUrl = request.getRequestURI();
        HttpSession session = request.getSession();
        if (null == session.getAttribute("sx_user_name")) {
            response.sendRedirect("/to_login?requestUrl=" + requestUrlSecurity.encrypt(requestUrl));
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
