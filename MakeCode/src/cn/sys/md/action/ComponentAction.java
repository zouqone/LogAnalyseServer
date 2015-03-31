package cn.sys.md.action;





import java.util.List;

import net.sf.json.JSONObject;
import cn.sys.md.service.IComponentService;
import cn.sys.md.util.IComponentVoConstants;
import cn.sys.md.vo.ComponentVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.util.DBHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;


/**
 * @author zouqone
 * @date 
 * @Description： 组件
 */
@SuppressWarnings("serial")
public class ComponentAction extends AbstractBaseAction implements
ModelDriven<ComponentVo> ,IComponentVoConstants{

	private ComponentVo componentVo;
	private IComponentService componentService;
	
	/**
	 * @return the componentService
	 */
	public IComponentService getComponentService() {
		return componentService;
	}

	/**
	 * @param componentService the componentService to set
	 */
	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}

	
	/**
	 * @return the componentVo
	 */
	public ComponentVo getComponentVo() {
		return componentVo;
	}

	/**
	 * @param componentVo the componentVo to set
	 */
	public void setComponentVo(ComponentVo componentVo) {
		this.componentVo = componentVo;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public ComponentVo getModel() {
		// TODO Auto-generated method stub
		if(componentVo == null){
			componentVo = new ComponentVo();
		}
		return componentVo;
	}
	
	public String getList(){
		
		List<ComponentVo> componentVos = componentService.queryAllComponentVo();
		request.setAttribute(BEANS, componentVos);
		return LIST_PAGE;
	}
	
	public String insert(){
		Object object = componentService.insertComponentVo(componentVo);
		request.setAttribute("count", object);
		return LIST_PAGE;
	}

	public String find(){
		ComponentVo vo = componentService.find(componentVo);
		request.setAttribute(BEAN, vo);
		return UPDATE_PAGE;
	}
	
	public String update(){
		Object object = componentService.updateComponentVo(componentVo);
		request.setAttribute("count", object);
		return LIST_PAGE;
	}
	
	public String delete(){
		Object object = componentService.deleteComponentVo(componentVo);
		request.setAttribute("count", object);
		return DELTE_PAGE;
	}
	
	public String detail(){
		ComponentVo vo = componentService.find(componentVo);
		request.setAttribute(BEAN, vo);
		return DETAIL_PAGE;
	}
	
	
	//ajax 页面请求
	public void query(){
		String condition = request.getParameter("condition");
		List<ComponentVo> componentVos = componentService.queryComponentVoByCondition(condition);
		ActionHelp.WriteStrToOut(response, componentVos);
	}
	
	/**
	 * 查询
	 */
	public void queryByID(){
		String id = request.getParameter("id");
		if(id!=null&&!id.trim().equals("")){
			String condition = DBHelp.AddCondition(null, "id", id, "=", "'", "'");
			List<ComponentVo> componentVos = componentService.queryComponentVoByCondition(condition);
			ActionHelp.WriteStrToOut(response, componentVos);
		}else{
			ActionHelp.WriteStrToOut(response, "[]");
		}
	}
	
	/**
	 * 新增
	 */
	public void addCategory(){
		//String dataStr = request.getParameter("data");
		
		/*if(dataStr == null){
			return ;
		}*/
		//JSONObject json = JSONObject.fromObject(dataStr);
		//ComcategoryVo comcategoryVo = (ComcategoryVo) JSONObject.toBean(json, ComcategoryVo.class);
		
		String jsonString = null;
		Object object = componentService.insertComponentVo(componentVo);
		if("1".equals(object.toString())){
			jsonString = "新增成功";
		}else{
			jsonString = "新增失败"+" "+object;
		}
		
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	/**
	 * 修改
	 */
	public void modifyCategory(){
		/*String dataStr = request.getParameter("data");
		if(dataStr == null){
			return ;
		}
		JSONObject json = JSONObject.fromObject(dataStr);
		ComponentVo comcategoryVo = (ComponentVo) JSONObject.toBean(json, ComponentVo.class);
		*/
		String jsonString = "";
		Object object =  componentService.updateComponentVo(componentVo);
		if("1".equals(object.toString())){
			jsonString = "修改成功";
		}else{
			jsonString = "修改失败"+" "+object;
		}
		
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	/**
	 * 删除
	 */
	public void deleteByPk(){
		String jsonString = "";
		Object object = componentService.deleteComponentVo(componentVo);
		request.setAttribute("count", object);
		if("1".equals(object.toString())){
			jsonString = "删除成功";
		}else{
			jsonString = "删除失败"+" "+object;
		}
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
}
