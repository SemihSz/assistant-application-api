package com.earthquake.api.service.logger;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Semih, 8.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
public class LogClientRequestInterceptor extends HandlerInterceptorAdapter {


    private byte[] content;

    private  Map<String, String[]> parameterMap;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        log.info("Status = {}", response.getStatus());
        log.info("Request parameter = {}", request.getParameterMap());
        parameterMap = request.getParameterMap();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //we can add attributes in the modelAndView and use that in the view page
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL::" + request.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis());
        log.info("Request URL::" + request.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
}
