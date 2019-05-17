package com.gjs.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet Filter implementation class JspFillter
 * @param <Admin>
 */
@WebFilter("/JspFillter")
public class JspFillter implements Filter {

    private static final ServletResponse HttpServletResponse = null;
	private HttpSession session;
	private Object user;
	

	/**
     * Default constructor. 
     */
    public JspFillter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		 HttpServletRequest request1 = (HttpServletRequest) request;
		 
		 String uri = request1.getRequestURI();
		
		 if(uri.contains("login.jsp")||uri.contains("/regist")) {
			 chain.doFilter(request1, response);
		 }else {
//			 request1.getSession().getAttribute("user");
			 
			 request.setAttribute("msg0", "您没登陆，请登录");
			 request.getRequestDispatcher("login.jsp").forward(request1, response);
				 
			 }
}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
