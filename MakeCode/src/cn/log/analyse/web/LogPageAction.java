/**
 * 
 */
package cn.log.analyse.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author zouqone
 *
 */
public class LogPageAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		String logType=request.getParameter("logType");
		String url = "";
		if(logType.equals("nginx")){
			url = "/log/analyse/nginx/index.html";
		}else if(logType.equals("apache")){
			url = "/log/analyse/apache2/index.html";
		}
		HttpSession session = request.getSession();
		String auth = (String)session.getAttribute("auth");
		if(auth==null||auth.equals("0")){
			url = "login/login.jsp";
		}
		url = request.getContextPath()+""+url;
		System.out.println("当前操作 ："+ logType);
		response.sendRedirect(url);
		//RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//dispatcher.forward(request, response);
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
