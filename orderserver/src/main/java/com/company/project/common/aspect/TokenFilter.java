package com.company.project.common.aspect;


import com.company.project.common.util.Base64Util;
import com.company.project.common.util.MD5Util;
import com.company.project.common.pojo.TokenPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        String token = request.getParameter("token");
        TokenPojo tokenPojo = new TokenPojo();
        String salt = tokenPojo.getSalt();
        long currentTime = System.currentTimeMillis();
        currentTime/=60000;
        String userAgentMD5 = MD5Util.getMD5(request.getHeader("User-Agent"));
        String verifyCodeGet = token.substring(0,5);
        String messageBase64 = token.substring(5,token.length());
        String message = Base64Util.getFromBase64(messageBase64);
        String messageSalt = message + salt;
        String verifyCode = MD5Util.getMD5(messageSalt).substring(3,8);
        if(!verifyCode.equals(verifyCodeGet)) return setResponse(response);
        String userAgentMD5Before = message.substring(0,16);
        if(!userAgentMD5.equals(userAgentMD5Before)) return setResponse(response);
        String beforeTime = message.substring(16,24);
        long passTime = currentTime - Long.parseLong(beforeTime);
        if(passTime > 24*60) return setResponse(response);
        String userId = message.substring(24);
        request.setAttribute("userId",userId);
        return true;
    }

    private boolean setResponse(HttpServletResponse response) throws IOException {
        response.getOutputStream().write("{\"code\":403,\"message\":\"token error\"}".getBytes());
        return false;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}

