package cn.sys.md.action;





import java.util.List;

import cn.sys.md.service.IComcategoryService;
import cn.sys.md.util.IComcategoryVoConstants;
import cn.sys.md.vo.ComcategoryVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;


/**
 * @author zouqone
 * @date 
 * @Description： 组件分类
 */
@SuppressWarnings("serial")
public class ComcategoryAction extends AbstractBaseAction implements
ModelDriven<ComcategoryVo> ,IComcategoryVoConstants{

	private ComcategoryVo comcategoryVo;
	private IComcategoryService comcategoryService;
	
	/**
	 * @return the comcategoryService
	 */
	public IComcategoryService getComcategoryService() {
		return comcategoryService;
	}

	/**
	 * @param comcategoryService the comcategoryService to set
	 */
	public void setComcategoryService(IComcategoryService comcategoryService) {
		this.comcategoryService = comcategoryService;
	}

	
	/**
	 * @return the comcategoryVo
	 */
	public ComcategoryVo getComcategoryVo() {
		return comcategoryVo;
	}

	/**
	 * @param comcategoryVo the comcategoryVo to set
	 */
	public void setComcategoryVo(ComcategoryVo comcategoryVo) {
		this.comcategoryVo = comcategoryVo;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public ComcategoryVo getModel() {
		// TODO Auto-generated method stub
		if(comcategoryVo == null){
			comcategoryVo = new ComcategoryVo();
		}
		return comcategoryVo;
	}
	
	public String getList(){
		
		List<ComcategoryVo> comcategoryVos = comcategoryService.queryAllComcategoryVo();
		request.setAttribute(BEANS, comcategoryVos);
		return LIST_PAGE;
	}
	
	public String insert(){
		Object object = comcategoryService.insertComcategoryVo(comcategoryVo);
		request.setAttribute("count", object);
		return LIST_PAGE;
	}

	public String update(){
		ComcategoryVo vo = comcategoryService.find(comcategoryVo);
		request.setAttribute(BEAN, vo);
		return UPDATE_PAGE;
	}
	
	public String save(){
		Object object = comcategoryService.updateComcategoryVo(comcategoryVo);
		request.setAttribute("count", object);
		return LIST_PAGE;
	}
	
	public String delete(){
		Object object = comcategoryService.deleteComcategoryVo(comcategoryVo);
		request.setAttribute("count", object);
		return DELTE_PAGE;
	}
	
	public String detail(){
		ComcategoryVo vo = comcategoryService.find(comcategoryVo);
		request.setAttribute(BEAN, vo);
		return DETAIL_PAGE;
	}
	
	
	//ajax 请求页面
	public void query(){
		String condition = request.getParameter("condition");
		List<ComcategoryVo> comcategoryVos = comcategoryService.queryComcategoryVoByCondition(condition);
		ActionHelp.WriteStrToOut(response, comcategoryVos);
	}
}
