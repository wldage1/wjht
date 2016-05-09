package com.sw.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class MySecurityFilter extends FilterSecurityInterceptor {
    
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
	throws IOException, ServletException
	{
		FilterInvocation fi = new FilterInvocation(srequest, sresponse, chain);
		invoke(fi);
	}	
  
}