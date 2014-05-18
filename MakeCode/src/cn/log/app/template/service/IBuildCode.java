/**
 * 
 */
package cn.log.app.template.service;

import cn.log.app.database.vo.TableVo;
import cn.log.app.template.vo.TemplateEngineVo;

/**
 * @author zouqone
 * @date 2014-5-18 下午1:44:24 
 * @Description: TODO
 */
public interface IBuildCode {

	/**
	 * 通过数据库信息和模板生成Java代码
	 * @param tableVo
	 * @param templateEngineVo
	 */
	public void buildCode(TableVo tableVo , TemplateEngineVo templateEngineVo);
	
}
