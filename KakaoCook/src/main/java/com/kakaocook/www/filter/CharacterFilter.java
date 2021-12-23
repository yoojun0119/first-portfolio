package com.kakaocook.www.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class CharacterFilter
 */
@WebServlet("/CharacterFilter")
public class CharacterFilter extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharacterFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest http_request = (HttpServletRequest)request;
		String method = http_request.getMethod();
		
		if(method.equalsIgnoreCase("POST")) {
			request.setCharacterEncoding("UTF-8");
		}
		chain.doFilter(request, response);
	}
    

}
