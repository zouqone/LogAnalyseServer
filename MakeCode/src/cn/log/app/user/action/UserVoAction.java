package cn.log.app.user.action;





import java.util.List;

import cn.log.app.user.service.IUserVoService;
import cn.log.app.user.util.IUserVoConstants;
import cn.log.app.user.vo.UserVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;


/**
 * @author zouqone
 * @date 
 * @Description： 
 */
@SuppressWarnings("serial")
public class UserVoAction extends AbstractBaseAction implements
ModelDriven<UserVo> ,IUserVoConstants{

	private UserVo userVo;
	private IUserVoService userVoService;
	
	/**
	 * @return the userVoService
	 */
	public IUserVoService getUserVoService() {
		return userVoService;
	}

	/**
	 * @param userVoService the userVoService to set
	 */
	public void setUserVoService(IUserVoService userVoService) {
		this.userVoService = userVoService;
	}

	
	/**
	 * @return the userVo
	 */
	public UserVo getUserVo() {
		return userVo;
	}

	/**
	 * @param userVo the userVo to set
	 */
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public UserVo getModel() {
		// TODO Auto-generated method stub
		if(userVo == null){
			userVo = new UserVo();
		}
		return userVo;
	}
	
	public String getList(){
		
		List<UserVo> userVos = userVoService.queryAllUserVo();
		request.setAttribute(BEANS, userVos);
		request.setAttribute("msg", "ss");
		return LIST_PAGE;
	}
	
	public String insert(){
		Object object = userVoService.insertUserVo(userVo);
		request.setAttribute("count", object);
		return LIST_PAGE;
	}

	public String update(){
		UserVo vo = userVoService.find(userVo);
		request.setAttribute(BEAN, vo);
		return UPDATE_PAGE;
	}
	
	public String save(){
		Object object = userVoService.updateUserVo(userVo);
		request.setAttribute("count", object);
		return LIST_PAGE;
	}
	
	public String delete(){
		String idStr = request.getParameter("ids");
		String[] ids = idStr.split(",");
		for (String id : ids) {
			UserVo vo = new UserVo();
			vo.setId(new Integer(id));
			userVoService.deleteUserVo(vo);
		}
		request.setAttribute("count", ids.length);
		return DELTE_PAGE;
	}
	
	public void ajaxDelete(){
		String idStr = request.getParameter("ids");
		String[] ids = idStr.split(",");
		Integer count = 0;
		for (String id : ids) {
			UserVo vo = new UserVo();
			vo.setId(new Integer(id));
			userVoService.deleteUserVo(vo);
			count++;
		}
		//request.setAttribute("count", ids.length);
		ActionHelp.WriteStrToOut(response, new Integer(count).toString());
	}
	
	public String detail(){
		UserVo vo = userVoService.find(userVo);
		request.setAttribute(BEAN, vo);
		return DETAIL_PAGE;
	}
	
	
	//ajax 请求页面
	public void query(){
		String condition = request.getParameter("condition");
		List<UserVo> userVos = userVoService.queryUserVoByCondition(condition);
		ActionHelp.WriteStrToOut(response, userVos);
	}
}
