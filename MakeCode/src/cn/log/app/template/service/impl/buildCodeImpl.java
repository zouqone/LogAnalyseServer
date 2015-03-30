/**
 * 
 */
package cn.log.app.template.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import cn.log.app.database.vo.ColumnVo;
import cn.log.app.database.vo.ConfigVo;
import cn.log.app.database.vo.TableVo;
import cn.log.app.template.dao.ITemplateEngineVoDao;
import cn.log.app.template.service.IBuildCode;
import cn.log.app.template.vo.AttributeVo;
import cn.log.app.template.vo.TemplateEngineVo;
import cn.log.tool.util.ContextUtil;



/**
 * @author zouqone
 * @date 2014-5-18 下午2:01:13 
 * @Description: TODO
 */
public class buildCodeImpl implements IBuildCode {

	public ITemplateEngineVoDao templateEngineVoDao;
	
	/**
	 * @return the templateEngineVoDao
	 */
	public ITemplateEngineVoDao getTemplateEngineVoDao() {
		return templateEngineVoDao;
	}

	/**
	 * @param templateEngineVoDao the templateEngineVoDao to set
	 */
	public void setTemplateEngineVoDao(ITemplateEngineVoDao templateEngineVoDao) {
		this.templateEngineVoDao = templateEngineVoDao;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.template.service.IBuildCode#buildCode(cn.log.app.database.vo.TableVo, cn.log.app.template.vo.TemplateEngineVo)
	 */
	@Override
	public void buildCode(TableVo tableVo, TemplateEngineVo templateEngineVo) {
		// TODO Auto-generated method stub
		templateEngineVo = templateEngineVoDao.initEngineVo(templateEngineVo);
		VelocityContext context = templateEngineVo.getContext();
		String vmRootPath = templateEngineVo.getTemplatePath();
		Map<String, String> mapVm = templateEngineVo.getMapVM();
		
		ConfigVo configVo = tableVo.getConfigVo();
		
		String tableName = tableVo.getTable_name(); //表名
		String packagePath =configVo.getPackagePath(); //包路径
		String author = "zouqone"; //作者
		String className = configVo.getVo(); //类名
		String classParam = className.substring(0, 1).toLowerCase()+className.substring(1) ; //类参数
		String showName =  tableVo.getComment();  //中文显示名
		String primaryKey = tableVo.getPrimarykey();
		System.out.println(primaryKey);
		primaryKey = "Id";
		
		String dao = configVo.getDao();
		String service = configVo.getService();
		String vo = configVo.getVo();
		String web = configVo.getWeb();
		String strutsxml = configVo.getStrutsxml();
		String springxml = configVo.getSpringxml();
		
		context.put("dao",dao);
		context.put("service",service);
		context.put("vo",vo);
		context.put("web",web);
		
		context.put("package",packagePath);
		context.put("author",author);
		context.put("class",className);
		context.put("info",showName==null?"":showName);
		context.put("table",tableName);
		context.put("tableName",className);
		context.put("pk",primaryKey);
		context.put("updatekey",primaryKey);
		context.put("primarykey",primaryKey);
		context.put("classparam",classParam);
		context.put("extpackage","import java.util.*; ");
		List<AttributeVo> attributeVoList =  getAttributeListFromDB(tableVo.getColumnVoList());
		context.put("attributeList", attributeVoList);
		
		String root = ContextUtil.getWorkSpacePath();
		String webRoot = configVo.getWebRoot();
		String packageRealPath = root+"/src/"+packagePath.replace(".", "/");
		String strutsPath = root+"/src/"+configVo.getStrutsxml();
		String springPath = root+"/src/"+configVo.getSpringxml();
		String jspPath = webRoot+configVo.getJspPath();
		
		String targetPath = null;
		String vm = null;
		
		if(vo !=null){
			vm = mapVm.get("vo");
			targetPath = packageRealPath+"/vo/"+vo+".java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
			vm = mapVm.get("constants");
			targetPath = packageRealPath+"/util/I"+vo+"Constants"+".java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
		}
		
		if(service!=null){
			vm = mapVm.get("iservice");
			targetPath = packageRealPath+"/service/"+service+".java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
			vm = mapVm.get("service");
			targetPath = packageRealPath+"/service/impl/"+service.substring(1)+"Impl.java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
		}
		
		
		if(dao!=null){
			vm = mapVm.get("idao");
			targetPath = packageRealPath+"/dao/"+dao+".java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);

			vm = mapVm.get("dao");
			targetPath = packageRealPath+"/dao/impl/"+dao.substring(1)+"Impl.java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
		}
		
		if(web!=null){

			vm = mapVm.get("web");
			targetPath = packageRealPath+"/action/"+web+".java";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
		}
		
		if(springxml!=null){
			vm = mapVm.get("spring");
			templateEngineVoDao.writerFile(templateEngineVo, vm, springPath);
			
		}
		if(strutsxml!=null){
			vm = mapVm.get("struts");
			templateEngineVoDao.writerFile(templateEngineVo, vm, strutsPath);
		
		}
		
		if(jspPath!=null){
			vm = mapVm.get("list");
			targetPath = jspPath+"/"+classParam+"List.jsp";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
			vm = mapVm.get("insert");
			targetPath = jspPath+"/"+classParam+"Insert.jsp";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
			
			vm = mapVm.get("detail");
			targetPath = jspPath+"/"+classParam+"Detail.jsp";
			templateEngineVoDao.writerFile(templateEngineVo, vm, targetPath);
		}
		
	}

	/**
	 * 获取属性列表
	 * @param columnVoList
	 * @return
	 */
	public List<AttributeVo> getAttributeListFromDB(List<ColumnVo> columnVoList){
		List<AttributeVo> attributeVoList = new ArrayList<AttributeVo>();
		if(columnVoList!=null&&columnVoList.size()>0){
			AttributeVo attributeVo = null;
			for (ColumnVo columnVo : columnVoList) {
				String atrributeName = columnVo.getField();
				
				String dataType = null;
				String type = columnVo.getType().toLowerCase();
				if(type.indexOf("int")>=0){
					dataType = "Integer";
				}else if(type.indexOf("date")>=0){
					dataType = "String";
				}else {
					dataType = "String";
				}
				
				String defaultValue = columnVo.getValue();
				String otherInfo = columnVo.getComment();
				otherInfo = otherInfo==null?"":otherInfo;
				
				attributeVo = new AttributeVo(atrributeName, dataType, defaultValue, otherInfo, null, null);
				attributeVoList.add(attributeVo);
			}
		}
		return attributeVoList;
	}
}
