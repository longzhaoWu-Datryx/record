package com.spproject.xdclass.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spproject.xdclass.utils.JWTUtils;
import com.spproject.xdclass.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
/**
 * @author xiaolongzhao
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            System.out.println("正在拦截");
       String accesToken = request.getHeader("token");
       if (accesToken == null){
           accesToken = request.getParameter("token");
       }

       if (StringUtils.isNotBlank(accesToken)){
           Claims claims = JWTUtils.CheckJwt(accesToken);
           if (claims == null){
               sendJsonMessage(response, JsonData.buildError("failed"));
               return false;
           }

           Integer id = (Integer) claims.get("id");
           String name = (String) claims.get("name");

           request.setAttribute("user_id",id);
           request.setAttribute("name",name);

           return true;
       }
       }catch (Exception e){}
        sendJsonMessage(response, JsonData.buildError("failed"));
        return false;
    }
    public static void sendJsonMessage(HttpServletResponse response,Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8 ");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
