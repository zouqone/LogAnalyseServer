/**
 * 
 */
package cn.log.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;

/**
 * @author zouqone
 *
 */
public class H2Util {
	
	private static String url;
	private static String username;
	private static String password;
	private static String driver;

	/**
	 * 
	 */
	public H2Util() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		url = "jdbc:h2:D:LogAnalyseServer/database/h2/test";
		username="sa";
		password="";
		driver="org.h2.Driver";
		try {
			Class.forName(driver);
			JdbcConnectionPool cp = JdbcConnectionPool.create(url,username,password);
			conn = cp.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static DataSource register(){
		url = "jdbc:h2:D:LogAnalyseServer/database/h2/test";
		username="sa";
		password="";
		
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(url);
		ds.setUser(username);
		ds.setPassword(password);
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ctx.bind("jdbc/dsName", ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public static Connection getConnectionByDS(){
		Context ctx = null;
		Connection conn = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/dsName");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    public static void showResultSet(ResultSet rs) throws SQLException {
    	if(rs==null){
    		return ;
    	}
    	ResultSetMetaData rsmd = rs.getMetaData();
    	int count = rsmd.getColumnCount();
    	for (int i = 1; i <= count; i++) {
			System.out.print(rsmd.getColumnLabel(i)+"\t");
		}
		System.out.println();
        while (rs.next()) {
			for (int i = 1; i <= count ; i++) {
				System.out.print(rs.getString(i)+""+ "\t");
			}
        	
			System.out.println();
        }
    }

}
