/**
 * 
 */
package cn.log.tool.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author zouqone
 * @date 2014-5-18 下午2:26:01
 * @Description: TODO
 */
public class PropertyHelp {

	/**
	 * 从配置文件中读取属性
	 * @param path
	 * @return
	 */
	public static Properties readProperty(String path) {
		Properties properties = new Properties();

		InputStream in = null;

		try {
			in = new BufferedInputStream(new FileInputStream(path));
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return properties;
	}
	
	/**
	 * 设置属性
	 * @param properties
	 * @param path
	 */
	public static void setProperty(Properties properties ,String path){
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(path));
			properties.store(out, "set Properties "+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
