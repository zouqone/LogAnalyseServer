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
		String packageName = tableName.toLowerCase();  //包名
		String packagePath =configVo.getPackagePath(); //包路径
		String author = "zouqone"; //作者
		String className = configVo.getVo(); //类名
		String classParam = className.substring(0, 1).toLowerCase()+className.substring(1) ; //类参数
		String showName =  tableVo.getComment();  //中文显示名
		String primaryKey = tableVo.getPrimarykey();

		context.put("package",packagePath);
		context.put("author",author);
		context.put("class",className);
		context.put("info",showName==null?"":showName);
		context.put("tableName",className);
		context.put("updatekey",primaryKey);
		context.put("primarykey",primaryKey);
		context.put("classparam",classParam);
		context.put("extpackage","import java.util.*; ");
		List<AttributeVo> attributeVoList =  getAttributeListFromDB(tableVo.getColumnVoList());
		context.put("attributeList", attributeVoList);
		
		//vo
		String voVm = mapVm.get("vo");
		String root = ContextUtil.getWorkSpacePath();
		
		String packageRealPath = root+"/src/"+packagePath.replace(".", "/");
		String targetPath = packageRealPath+"/vo/"+configVo.getVo()+".java";
		templateEngineVoDao.writerFile(templateEngineVo, voVm, targetPath);
		
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
					dataType = "Date";
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
