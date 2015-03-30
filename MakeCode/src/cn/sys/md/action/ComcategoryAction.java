package cn.sys.md.action;





import java.util.List;

import net.sf.json.JSONObject;
import cn.sys.md.service.IComcategoryService;
import cn.sys.md.util.IComcategoryVoConstants;
import cn.sys.md.vo.ComcategoryVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.util.DBHelp;
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

	public void addCategory(){
		String dataStr = request.getParameter("data");
		String jsonString = "hi";
		if(dataStr == null){
			return ;
		}
		JSONObject json = JSONObject.fromObject(dataStr);
		ComcategoryVo comcategoryVo = (ComcategoryVo) JSONObject.toBean(json, ComcategoryVo.class);
		Object object =  comcategoryService.insertComcategoryVo(comcategoryVo);
		if("1".equals(object.toString())){
			jsonString = "新增成功";
		}else{
			jsonString = "新增失败"+" "+object;
		}
		
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	public String update(){
		ComcategoryVo vo = comcategoryService.find(comcategoryVo);
		request.setAttribute(BEAN, vo);
		return UPDATE_PAGE;
	}
	public void modifyCategory(){
		String dataStr = request.getParameter("data");
		String jsonString = "";
		if(dataStr == null){
			return ;
		}
		JSONObject json = JSONObject.fromObject(dataStr);
		ComcategoryVo comcategoryVo = (ComcategoryVo) JSONObject.toBean(json, ComcategoryVo.class);
		Object object =  comcategoryService.updateComcategoryVo(comcategoryVo);
		if("1".equals(object.toString())){
			jsonString = "修改成功";
		}else{
			jsonString = "修改失败"+" "+object;
		}
		
		ActionHelp.WriteStrToOut(response, jsonString);
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
	
	public void deleteByPk(){
		String jsonString = "";
		Object object = comcategoryService.deleteComcategoryVo(comcategoryVo);
		if("1".equals(object.toString())){
			jsonString = "删除成功";
		}else{
			jsonString = "删除失败"+" "+object;
		}
		ActionHelp.WriteStrToOut(response, jsonString);
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
	
	/**
	 * 通过code查询
	 */
	public void queryByCode(){
		String code = request.getParameter("code");
		if(code!=null&&!code.trim().equals("")){
			String condition = DBHelp.AddCondition(null, "code", code, "=", "'", "'");
			List<ComcategoryVo> comcategoryVos = comcategoryService.queryComcategoryVoByCondition(condition);
			ActionHelp.WriteStrToOut(response, comcategoryVos);
		}else{
			ActionHelp.WriteStrToOut(response, "[]");
		}
		
	}
	
	/**
	 * 通过id查询
	 */
	public void queryByID(){
		String id = request.getParameter("id");
		if(id!=null&&!id.trim().equals("")){
			String condition = DBHelp.AddCondition(null, "id", id, "=", "'", "'");
			List<ComcategoryVo> comcategoryVos = comcategoryService.queryComcategoryVoByCondition(condition);
			ActionHelp.WriteStrToOut(response, comcategoryVos);
		}else{
			ActionHelp.WriteStrToOut(response, "[]");
		}
		
	}
	
	public void queryChildren(){
		String parentCode = request.getParameter("parentCode");
		
		String info = comcategoryService.queryChildren(parentCode);
		
		ActionHelp.WriteStrToOut(response, info);
	}
}
