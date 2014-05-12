/**
 * 
 */
package cn.log.analyse.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.log.analyse.service.LoginService;

/**
 * @author zouqone
 *
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -179858820418403678L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//System.out.println(System.getProperty("user.dir"));
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		//System.out.println(username+" "+password);
		System.out.println(" User "+username+" login ......");
		LoginService service = new LoginService();
		
		String url = "login/login.jsp";
		if(service.login(username, password)){
			url = "log/index.jsp";//+"?username="+username;
			System.out.println("User "+username+" Login Successfully!");
			HttpSession session = (HttpSession) request.getSession(true);
			session.setAttribute("authUserName", username);
			session.setAttribute("auth", "1");
			//request.setAttribute("username", username);
			
			
		}else{
			System.out.println("User "+username+" Login Failling!");
		}
		//System.out.println(request.getContextPath());
		//RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//dispatcher.forward(request, response);
		response.sendRedirect(url);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
