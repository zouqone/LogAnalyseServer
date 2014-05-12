package cn.log.user.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.log.tool.util.ActionHelp;
import cn.log.tool.vo.RequestVo;
import cn.log.tool.web.AbstractBaseAction;
import cn.log.user.service.IUserService;
import cn.log.user.vo.UserVo;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class UserAction extends AbstractBaseAction implements ModelDriven<UserVo> {
	
	public UserVo user;
	
	public IUserService userService;
	
	/**
	 * @return the user
	 */
	public UserVo getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserVo user) {
		this.user = user;
	}


	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserVo getModel() {
		// TODO Auto-generated method stub
		if( null == user){
			user = new UserVo();
		}
		return user;
	}
	
	public String login() {
		request = ServletActionContext.getRequest();
		requestVo = ActionHelp.getNetInfoFromRequest(request);
		String username = user.getUsername();
		String password = user.getPassword();
		System.out.println(" User "+username+" login ......");
		if(userService.login(username, password)){
			System.out.println("User "+username+" Login Successfully!"
					+" From RemoteAddr : "+requestVo.getRemoteAddr()
					+" Host : "+requestVo.getRemoteHost());
			session.setAttribute("authUserName", username);
			session.setAttribute("auth", "1");
			return "success";
		}else{
			System.out.println("User "+username+" Login Failling! From RemoteAddr : "
					+requestVo.getRemoteAddr()
					+" Host : "+requestVo.getRemoteHost());
			if(username == null || username.trim().equals("")){
				System.out.println("User Name is NULL!");
			}
			if(password == null || password.trim().equals("")){
				System.out.println("User Password is NULL!");
			}
			return "error";
		}
	}

	/**
	 * 用户注册
	 * @return
	 */
	public String register() {
		//用户注册
		String outInfo = userService.createUserVo(user);
		return "success";
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String logout() {
		requestVo = ActionHelp.getNetInfoFromRequest(request);
		HttpSession session = request.getSession();
		System.out.println("User "+session.getAttribute("authUserName")+" Logout Successfully! "
				+" From RemoteAddr : "+requestVo.getRemoteAddr()
				+" Host : "+requestVo.getRemoteHost());
		session.removeAttribute("authUserName");
		session.removeAttribute("auth");
		return "logout";
	}
	
	/**
	 * 新增用户
	 */
	public void add(){
		String outInfo = userService.createUserVo(user);
		ActionHelp.WriteStrToOut(response, outInfo);
	}
	
	/**
	 * 批量新增用户
	 */
	public void adds(){
		String UserVoListStr = (String)request.getParameter("UserVoList");
		JSONArray jsonArray = JSONArray.fromObject(UserVoListStr);
		List<UserVo> userVoList = JSONArray.toList(jsonArray,UserVo.class);
		Integer k = 0;
		if(userVoList!=null&&userVoList.size()>0){
			for (UserVo userVo : userVoList) {
				//userVo.setId(null);
				userService.createUserVo(userVo);
				k++;
			}
		}
		String outInfo = k.toString();
		ActionHelp.WriteStrToOut(response, outInfo);
	}

	/**
	 * 查询所有的记录
	 */
	public void queryAll() {
		List<UserVo> userVoList = userService.getAllUserVo();
		JSONArray jsonArray = JSONArray.fromObject(userVoList);
		String outInfo = jsonArray.toString();
		ActionHelp.WriteStrToOut(response, outInfo);
	}

	/**
	 * 根据ID查询
	 */
	public void queryById() {
		//查询所有的记录
		String ids = (String)request.getParameter("ids");
		String[] idArray = ids.split(",");
		
		String outInfo = "[]";
		
		//根据id查询对象
		if(idArray!=null&&idArray.length>0){
			List<Integer> idList = new ArrayList<Integer>();
			for (String idstr : idArray) {
				Integer id = Integer.parseInt(idstr);
				idList.add(id);
			}
			List<UserVo> userVoList = userService.getUserVoListByIds(idList);
			if(userVoList!=null){
				JSONArray jsonArray = JSONArray.fromObject(userVoList);
				outInfo = jsonArray.toString();
			}
		}
		ActionHelp.WriteStrToOut(response, outInfo);
	}
	
	/**
	 * 根据查询条件查询
	 */
	public void queryByCondition() {
		//根据查询条件查询
		String condition = (String)request.getParameter("condition");
		List<UserVo> userVoList = null;
		String outInfo = null;
		
		if(condition==null||condition.equals("")){
			userVoList = userService.getAllUserVo();
		}else{
			userVoList = userService.queryByCondition(condition);
		}
		
		if(userVoList!=null&&userVoList.size()>0){
			JSONArray jsonArray = JSONArray.fromObject(userVoList);
			outInfo = jsonArray.toString();
		}else{
			outInfo = "[]";
		}
		ActionHelp.WriteStrToOut(response, outInfo);
	}

	
	/**
	 * 单个修改用户
	 * @return
	 */
	public void update() {
		String UserVoStr = (String)request.getParameter("UserVo");
		JSONObject jsonObject = JSONObject.fromObject(UserVoStr);
		user = (UserVo) JSONObject.toBean(jsonObject,UserVo.class);
		String outInfo = userService.updateUserVo(user)?"success":"fail";
		ActionHelp.WriteStrToOut(response, outInfo);
	}

	/**
	 * 批量修改用户
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void updates() {
		String UserVoListStr = (String)request.getParameter("UserVoList");
		JSONArray jsonArray = JSONArray.fromObject(UserVoListStr);
		List<UserVo> userVoList = JSONArray.toList(jsonArray,UserVo.class);
		int k = 0;
		if(userVoList!=null&&userVoList.size()>0){
			for (UserVo userVo : userVoList) {
				//userVo.setId(null);
				k += userService.updateUserVo(userVo)?1:0;
			}
		}
		String outInfo = ""+k;
		ActionHelp.WriteStrToOut(response, outInfo);
	}
	
	/**
	 * 删除对象
	 */
	public void delete() {
		String ids = (String)request.getParameter("ids");
		String[] idArray = ids.split(",");
		String outInfo = "";
		
		//删除对象
		if(idArray!=null&&idArray.length>0){
			for (String id : idArray) {
				userService.deleteUserVo(Integer.parseInt(id));
			}
			//查询所有的记录
			List<UserVo> userVoList = userService.getAllUserVo();
			JSONArray jsonArray = JSONArray.fromObject(userVoList);
			outInfo = jsonArray.toString();
		}
		ActionHelp.WriteStrToOut(response, outInfo);
	}
	
	/**
	 * 查询所有的记录条数
	 */
	public void getCount(){
		Integer number = userService.getUserVoTotalNumber();
		String outInfo = number.toString();
		ActionHelp.WriteStrToOut(response, outInfo);
	}


}