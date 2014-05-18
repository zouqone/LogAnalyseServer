/**
 * 
 */
package cn.log.app.template.vo;

import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * @author zouqone
 * @date 2014-5-18 下午12:30:04 
 * @Description: TODO
 */
public class TemplateEngineVo {

	
	/**
	 * 模板所在路径
	 */
	private String templatePath;

	/**
	 * java类的生成路径
	 */
	private String outFilePath;

	/**
	 * 模板配置属性
	 */
	private Properties properties;

	/**
	 * velocity的上下文context
	 */
	private VelocityContext context;
	
	/**
	 * 模板列表
	 */
	private Map<String ,String> mapVM;
	
			
	/**
	 * 
	 */
	public TemplateEngineVo() {
		// TODO Auto-generated constructor stub
	}
	
	public void initTemplateEngineVo(){
		if(properties!=null){
			initTemplateEngineVo(properties);
		}
	}
	
	/**
	 * 初始化模板引擎
	 * @param properties
	 */
	public void initTemplateEngineVo(Properties properties){
		if(properties!=null){
			this.properties = properties;
			Velocity.init(properties);
			context = new VelocityContext();
		}
	}

	/**
	 * @return the templatePath
	 */
	public String getTemplatePath() {
		return templatePath;
	}

	/**
	 * @param templatePath the templatePath to set
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/**
	 * @return the outFilePath
	 */
	public String getOutFilePath() {
		return outFilePath;
	}

	/**
	 * @param outFilePath the outFilePath to set
	 */
	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the context
	 */
	public VelocityContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(VelocityContext context) {
		this.context = context;
	}

	/**
	 * @return the mapVM
	 */
	public Map<String, String> getMapVM() {
		return mapVM;
	}

	/**
	 * @param mapVM the mapVM to set
	 */
	public void setMapVM(Map<String, String> mapVM) {
		this.mapVM = mapVM;
	}
	
	

}
