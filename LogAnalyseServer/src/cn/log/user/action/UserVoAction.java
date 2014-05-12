/**
 * 
 */
package cn.log.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.log.user.service.IUserService;
import cn.log.user.service.impl.UserServiceImpl;
import cn.log.user.vo.UserVo;

/**
 * @author zouqone
 * 
 */
public class UserVoAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		String opt = (String) request.getParameter("opt");
		System.out.println("Option : "+opt);
		
		// 输出流
		PrintWriter out = null;
		String outInfo = "";

		//创建服务
		IUserService service = new UserServiceImpl();
		
		if ("queryAll".equals(opt)) {
			//查询所有的记录
			List<UserVo> userVoList = service.getAllUserVo();
			JSONArray jsonArray = JSONArray.fromObject(userVoList);
			outInfo = jsonArray.toString();
		}else if ("queryByCondition".equals(opt)) {
			//根据查询条件查询
			String condition = (String)request.getParameter("condition");
			List<UserVo> userVoList = null;
			if(condition==null||condition.equals("")){
				userVoList = service.getAllUserVo();
			}else{
				userVoList = service.queryByCondition(condition);
			}
			
			if(userVoList!=null&&userVoList.size()>0){
				JSONArray jsonArray = JSONArray.fromObject(userVoList);
				outInfo = jsonArray.toString();
			}else{
				outInfo = "[]";
			}
			
		}else if ("query".equals(opt)) {
			//查询所有的记录
			String ids = (String)request.getParameter("ids");
			String[] idArray = ids.split(",");
			//根据id查询对象
			if(idArray!=null&&idArray.length>0){
				List<Integer> idList = new ArrayList<Integer>();
				for (String idstr : idArray) {
					Integer id = Integer.parseInt(idstr);
					idList.add(id);
				}
				List<UserVo> userVoList = service.getUserVoListByIds(idList);
				if(userVoList!=null){
					JSONArray jsonArray = JSONArray.fromObject(userVoList);
					outInfo = jsonArray.toString();
				}else{
					outInfo = "[]";
				}
			}

		}else if ("mod".equals(opt)) {
			//修改用户
			String UserVoStr = (String)request.getParameter("UserVo");
			JSONObject jsonObject = JSONObject.fromObject(UserVoStr);
			UserVo userVo = (UserVo) JSONObject.toBean(jsonObject,UserVo.class);
			outInfo = service.updateUserVo(userVo)?"success":"fail";
			
		}else if ("mods".equals(opt)) {
			//修改用户
			String UserVoListStr = (String)request.getParameter("UserVoList");
			JSONArray jsonArray = JSONArray.fromObject(UserVoListStr);
			List<UserVo> userVoList = JSONArray.toList(jsonArray,UserVo.class);
			int k = 0;
			if(userVoList!=null&&userVoList.size()>0){
				for (UserVo userVo : userVoList) {
					//userVo.setId(null);
					k += service.updateUserVo(userVo)?1:0;
				}
			}
			outInfo = "success";
			
		} else if ("add".equals(opt)) {
			//新增用户
			String UserVoStr = (String)request.getParameter("UserVo");
			JSONObject jsonObject = JSONObject.fromObject(UserVoStr);
			UserVo userVo = (UserVo) JSONObject.toBean(jsonObject,UserVo.class);
			outInfo = service.createUserVo(userVo);
		} else if ("adds".equals(opt)) {
			//新增用户
			String UserVoListStr = (String)request.getParameter("UserVoList");
			JSONArray jsonArray = JSONArray.fromObject(UserVoListStr);
			List<UserVo> userVoList = JSONArray.toList(jsonArray,UserVo.class);
			if(userVoList!=null&&userVoList.size()>0){
				for (UserVo userVo : userVoList) {
					//userVo.setId(null);
					service.createUserVo(userVo);
				}
			}
			outInfo = "success";
		}else if ("del".equals(opt)) {
			String ids = (String)request.getParameter("ids");
			String[] idArray = ids.split(",");
			//删除对象
			if(idArray!=null&&idArray.length>0){
				for (String id : idArray) {
					service.deleteUserVo(Integer.parseInt(id));
				}
				//查询所有的记录
				List<UserVo> userVoList = service.getAllUserVo();
				JSONArray jsonArray = JSONArray.fromObject(userVoList);
				outInfo = jsonArray.toString();
			}
		} else if("getCount".equals(opt)){
			Integer number = service.getUserVoTotalNumber();
			outInfo = number.toString();
		}

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		// return null;
	}
}
