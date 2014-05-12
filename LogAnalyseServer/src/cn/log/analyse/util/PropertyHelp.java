/**
 * 
 */
package cn.log.analyse.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author zouqone
 *
 */
public class PropertyHelp {

	public static void readProperties(String fileNamePath) throws IOException{
		 Properties props = new Properties();
		 InputStream in = null;
		 try {
			in = new BufferedInputStream(new FileInputStream(fileNamePath));
			 //in = PropertyHelp.class.getResourceAsStream(fileNamePath);
			 props.load(in);
			 Enumeration<?> en = props.propertyNames();
			 while (en.hasMoreElements()){
				 String key = (String)en.nextElement();
				 String property = props.getProperty(key);
				 System.out.println(key + "="+property);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in != null){
				in.close();
			}
		}

	}
	
	

		
}
