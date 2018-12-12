package com.star.framework.security.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 拒绝访问
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
    	
//    	System.out.println(accessDeniedException.getMessage());
//    	request.setAttribute("error", accessDeniedException.getMessage());
//    	response.sendRedirect("/login?e=9");
    	
//        response.addHeader("Content-type", "application/json; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.write(accessDeniedException.getMessage());
//        printWriter.flush();
//        printWriter.close();
    }
}
