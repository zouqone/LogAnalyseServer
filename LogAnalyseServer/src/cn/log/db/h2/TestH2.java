/**
 * 
 */
package cn.log.db.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import cn.log.db.util.H2Util;
import cn.log.db.util.JdbcUtils;

/**
 * @author zouqone
 *
 */
public class TestH2 {

	public static Connection conn ;
	public static String username="sa";
	public static String password="";
	public static String url = "jdbc:h2:D:LogAnalyseServer/database/h2/test";
	
	/**
	 * 
	 */
	public TestH2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//conn = getConnection();
		//conn = JdbcUtils.getConnection();
		conn = H2Util.getConnection();
		String sql = "select * from test.user where username = \'admin\'";
		execSql(sql, conn);
		closeConnection(conn);
	}
	
	public static void execSql(String sql , Connection conn){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			showResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
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
}
