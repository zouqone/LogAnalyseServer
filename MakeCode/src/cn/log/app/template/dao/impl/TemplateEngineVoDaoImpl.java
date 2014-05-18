/**
 * 
 */
package cn.log.app.template.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import cn.log.app.template.dao.ITemplateEngineVoDao;
import cn.log.app.template.vo.TemplateEngineVo;
import cn.log.tool.util.ContextUtil;
import cn.log.tool.util.PropertyHelp;

/**
 * @author zouqone
 * @date 2014-5-18 下午2:12:06 
 * @Description: TODO
 */
public class TemplateEngineVoDaoImpl implements ITemplateEngineVoDao{

	@Override
	public TemplateEngineVo makeEngineVo() {
		// TODO Auto-generated method stub
		TemplateEngineVo engineVo = new TemplateEngineVo();
		engineVo = initEngineVo(engineVo);
		return engineVo;
	}
	
	@Override
	public TemplateEngineVo initEngineVo(TemplateEngineVo engineVo) {
		// TODO Auto-generated method stub
		String classPath = ContextUtil.getClassPath();
		String templatePropertyPath = classPath+"/"+"template.properties";
		Properties templateProperties = PropertyHelp.readProperty(templatePropertyPath);
		
		String RUNTIME_LOG = templateProperties.getProperty("logPath");
		String FILE_RESOURCE_LOADER_PATH = templateProperties.getProperty("templatePath");
		String INPUT_ENCODING = templateProperties.getProperty("input_encoding");
		String ENCODING_DEFAULT = templateProperties.getProperty("encoding_default");
		String OUTPUT_ENCODING = templateProperties.getProperty("output_encoding");
		
		Properties properties = new Properties();
		properties.setProperty(Velocity.RUNTIME_LOG, RUNTIME_LOG);
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, FILE_RESOURCE_LOADER_PATH); //src/cn/vm
		properties.setProperty(Velocity.INPUT_ENCODING, INPUT_ENCODING);
		properties.setProperty(Velocity.ENCODING_DEFAULT, ENCODING_DEFAULT);
		properties.setProperty(Velocity.OUTPUT_ENCODING, OUTPUT_ENCODING);
		
		//初始化模板引擎
		engineVo.initTemplateEngineVo(properties);
		engineVo.setTemplatePath(FILE_RESOURCE_LOADER_PATH);
		return engineVo;
	}

	@Override
	public void writerFile(VelocityContext context , String vmPath, String targetPath) {
		// TODO Auto-generated method stub
		String classPath = targetPath.substring(0,targetPath.lastIndexOf("/"));
		File file = new File(classPath);
		
		if (!file.exists()) {
			file.mkdirs();
		}
		
		Template template = Velocity.getTemplate(vmPath);
		FileWriter fw = null;
		Writer writer = null;
		try {
			fw = new FileWriter(targetPath);
			writer = new PrintWriter(fw);
			//转换输出
			template.merge(context, writer);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
