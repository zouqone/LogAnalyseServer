/**
 * 
 */
package cn.log.analyse.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zouqone
 *
 */
public class LoginFilter implements Filter{

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.setCharacterEncoding("UTF-8");
		String curl = request.getRequestURI();
		String turl = curl.substring(curl.indexOf("/",1), curl.length());
		
		HttpSession session = request.getSession(false);
		if(!"/login/login.jsp".equals(turl)){
			if(session==null||session.getAttribute("auth")==null||session.getAttribute("auth").toString().equals("0")){
				//System.out.println("request.getContextPath() = "+request.getContextPath());
				System.out.println("Please User login now! ");
				turl = request.getContextPath()+"/login/login.jsp";
				response.sendRedirect(turl);
				return ;
			}
		}
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}
