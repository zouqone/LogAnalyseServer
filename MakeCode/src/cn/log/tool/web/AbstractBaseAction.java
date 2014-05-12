/**
 * 
 */
package cn.log.tool.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.log.tool.util.ActionHelp;
import cn.log.tool.vo.RequestVo;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Administrator
 *
 */
public class AbstractBaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8721936204022355381L;

	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpSession session = ServletActionContext.getRequest().getSession(true);
	public RequestVo requestVo = ActionHelp.getNetInfoFromRequest(request);
	
	/**
	 * 
	 */
	public AbstractBaseAction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the requestVo
	 */
	public RequestVo getRequestVo() {
		return requestVo;
	}

	/**
	 * @param requestVo the requestVo to set
	 */
	public void setRequestVo(RequestVo requestVo) {
		this.requestVo = requestVo;
	}

	
}
