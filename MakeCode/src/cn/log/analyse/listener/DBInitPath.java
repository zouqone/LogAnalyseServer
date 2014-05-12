/**
 * 
 */
package cn.log.analyse.listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import cn.log.db.util.JdbcUtils;


/**
 * @author zouqone
 *
 */
public class DBInitPath implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("==========初始化信息进行销毁==========");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		System.out.println("==========系统初始化==========");
		
		String classPath = getClassPath();
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		String root = servletContext.getRealPath("/");
		String c3p0Path = null;
		String dbPath = null;
		String dbH2Lock = null;
		root = new File(root).getParentFile().getPath();
		c3p0Path = root+"/"+"src/c3p0.properties";
		dbPath = root+"/LogAnalyseServer/database/h2/test";//"c3p0.jdbcUrl=jdbc:h2:"
		dbH2Lock = dbPath+".lock.db";
		
		removeLock(dbH2Lock);
		setDBPath(dbPath,c3p0Path); //修改src路径下c3p0.properties
		
		String classPath_c3p0Property = classPath+"/c3p0.properties";
		setDBPath(dbPath,classPath_c3p0Property); //修改ClassPath路径下c3p0.properties
		
		Connection conn = JdbcUtils.getConnection();
		System.out.println("connecting database...... ");
		if(conn!=null){
			System.out.println("connect database successfully !");
			System.out.println("database Directory =  "+dbPath);
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("connecting database failling !");
		}
		System.out.println("==========系统初始化结束==========");
	}
	
	public void removeLock(String lockPath){
		File lockFile = new File(lockPath);
		if(lockFile!=null){
			if(lockFile.exists()){
				lockFile.delete();
			}
		}
	}
	
	public void setDBPath(String dbPath,String c3p0Path){
		 Properties props = new Properties();
		 InputStream in = null;
		 OutputStream out = null;
		 try {
			in = new BufferedInputStream(new FileInputStream(c3p0Path));
			 //in = DBInitPath.class.getResourceAsStream("../../../../c3p0.properties");
			 props.load(in);
			/* Enumeration<?> en = props.propertyNames();
			 while (en.hasMoreElements()){
				 String key = (String)en.nextElement();
				 String property = props.getProperty(key);
				 System.out.println(key + "="+property);
			 }*/
			 
			 props.setProperty("c3p0.jdbcUrl", "jdbc:h2:"+dbPath);
			 out = new BufferedOutputStream(new FileOutputStream(c3p0Path));
			 props.store(out, "init H2 database Path");
			 
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
	
	/**
	 * 获取项目ClassPath的绝对路径
	 * @return
	 */
	public String getClassPath(){
		String classPath = null;
		classPath = DBInitPath.class.getResource("/" + "").toString();
		classPath = classPath.substring(6); //截掉路径的”file:“前缀
		System.out.println("ClassPath的绝对路径 = "+classPath); 
		return classPath;
	}

}
