package com.wby.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonFilter implements Filter {
	private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	 
    public CommonFilter() {
    }
 
    public void destroy() {
    }
 
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest hr = (HttpServletRequest) request;
        long start = System.currentTimeMillis();
 
        String url = hr.getServletPath().trim();
        String method = hr.getMethod().trim();
        if ("POST".equalsIgnoreCase(method)) {
        	//if ("/File/upLoad".equalsIgnoreCase(url) || "/BusinessInfo/upLoadStyle".equalsIgnoreCase(url)|| "/InforMation/upLoad".equalsIgnoreCase(url)) {
        	String enctype = hr.getContentType();
    		if (!StringUtils.isEmpty(enctype) && enctype.contains("multipart/form-data")){
    			//文件上传  multipart/form-data
        		//不转换的话用getParameter获取到得参数为null
        		MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(hr);
        		request = multiReq;
			}
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);
            chain.doFilter(requestWrapper, response);
		}else{
			chain.doFilter(request, response);
		}
        
        long end = System.currentTimeMillis();
        System.out.println("==============连接�?" + url + "，请求方式："+method+"，响应时间：" + (end - start));
    }
 
    public void init(FilterConfig fConfig) throws ServletException {
    }
 

}
