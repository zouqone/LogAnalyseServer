package cn.log.db.util;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	private static DataSource ds;
	
	static{
		ds = new ComboPooledDataSource();//直接使用即可，不用显示的配置，其会自动识别配置文件  
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection(){
		//创建连接connection
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn = ds.getConnection();
				tl.set(conn);
			}
			return conn;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void startTransaction(){
		try{
			// 得到当前线程上绑定连接开启事务  
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void commit(){
		try{
			Connection conn = tl.get();
			if(conn!=null){
				conn.commit();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback(){
		try{
			Connection conn = tl.get();
			if(conn!=null){
				conn.rollback();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(){
		try{
			Connection conn = tl.get();
			if(conn!=null){
				conn.close();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tl.remove();  //断开连接connection
		}
	}
}
