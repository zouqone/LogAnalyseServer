/**
 * 
 */
package cn.log.tool.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.log.tool.vo.RequestVo;

/**
 * @author Administrator
 * 
 */
public class ActionHelp {

	public static RequestVo requestVo;
	
	/**
	 * 
	 */
	public ActionHelp() {
		// TODO Auto-generated constructor stub
		requestVo = new RequestVo();
	}

	/**
	 * 将字符串写入输出流
	 * @param response
	 * @param info
	 */
	@SuppressWarnings("unused")
	public static void WriteStrToOut(HttpServletResponse response ,String info) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		if(response == null){
			return ;
		}
		
		// 输出流
		PrintWriter out = null;
		String outInfo = "";
		
		if(info!=null){
			outInfo = info;
		}

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.write(outInfo);
			//out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 获取Request信息
	 * @param request
	 * @return
	 */
	public static RequestVo getNetInfoFromRequest(HttpServletRequest request){
		if(request == null){
			return null;
		}
		
		if(requestVo == null){
			requestVo = new RequestVo();
		}
		requestVo.setMethod(request.getMethod());
		requestVo.setURI(request.getRequestURI());
		requestVo.setProtocol(request.getProtocol());
		requestVo.setServletPath(request.getServletPath());
		requestVo.setQueryString(request.getQueryString());
		requestVo.setContentLength(request.getContentLength());
		requestVo.setServerName(request.getServerName());
		requestVo.setPort(""+request.getServerPort());
		requestVo.setRemoteAddr(request.getRemoteAddr());
		requestVo.setRemoteHost(request.getRemoteHost());
		requestVo.setHeaderAccept(request.getHeader("accept"));
		requestVo.setHeaderAcceptEncoding(request.getHeader("accept-encoding"));
		requestVo.setHeaderHost(request.getHeader("Host"));
		requestVo.setHeaderUserAgent(request.getHeader("User-Agent"));
		requestVo.setEnumHead(request.getHeaderNames());
		
		return requestVo;
	}
	
	/**
	 * 对象转JSON字符串
	 * @param object
	 * @return
	 */
	public static String beanToStr(Object object){
		String str = null;
		if(object!=null){
			JSONObject jsonObject = JSONObject.fromObject(object);
			str = jsonObject.toString();
		}
		return str;
	}

	/**
	 * 对象数组转就SON字符串
	 * @param objectList
	 * @return
	 */
	public static String beanListToStr(List<Object> objectList){
		String str = null;
		if(objectList != null){
			JSONArray jsonArray = JSONArray.fromObject(objectList);
			str = jsonArray.toString();
		}
		return str;
	}
}
