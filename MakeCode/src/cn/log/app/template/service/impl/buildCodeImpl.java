/**
 * 
 */
package cn.log.app.template.service.impl;

import java.util.Map;

import org.apache.velocity.VelocityContext;

import cn.log.app.database.vo.ConfigVo;
import cn.log.app.database.vo.TableVo;
import cn.log.app.template.dao.ITemplateEngineVoDao;
import cn.log.app.template.service.IBuildCode;
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
		
		//vo
		String voVm =vmRootPath +"/"+ mapVm.get("vo");
		
		String classPath = ContextUtil.getClassPath();
		ConfigVo configVo = tableVo.getConfigVo();
		
		String packagePath = configVo.getPackagePath();
		String packageRealPath = classPath+"/"+packagePath.replaceAll(".", "/");
		String targetPath = packageRealPath+"/"+configVo.getVo()+".java";
		templateEngineVoDao.writerFile(context, voVm, targetPath);
		
		
	}

}
