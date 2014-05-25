/**
 * 
 */
package cn.log.app.template.dao;

import org.apache.velocity.VelocityContext;

import cn.log.app.template.vo.TemplateEngineVo;

/**
 * @author zouqone
 * @date 2014-5-18 下午2:07:13 
 * @Description: TODO
 */
public interface ITemplateEngineVoDao {

	/**
	 * 生成模板引擎对象
	 * @return
	 */
	public TemplateEngineVo makeEngineVo();
	
	/**
	 * 初始化模板引擎
	 * @param engineVo
	 * @return
	 */
	public TemplateEngineVo initEngineVo(TemplateEngineVo engineVo);
	
	/**
	 * 生成模板
	 * @param templateEngineVo
	 * @param vmPath
	 * @param targetPath
	 */
	public void writerFile(TemplateEngineVo templateEngineVo , String vmPath, String targetPath);
	
}
