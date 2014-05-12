/**
 * 
 */
package cn.log.app.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.log.app.database.vo.DatabaseVo;


/**
 * @author zouqone 数据库帮助类
 */
public class JDBCHelp {
	/**
	 * 数据库连接
	 */
	private static Connection conn = null;

	/**
	 * 数据库访问url
	 */
	private static String url = "";

	/**
	 * 
	 */
	public JDBCHelp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取数据库连接
	 * 
	 * @param databaseVo
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(DatabaseVo databaseVo) throws SQLException {
		if (databaseVo == null) {
			return null;
		}

		String driver = databaseVo.getDriver();
		if (driver == null || driver.equals("")) {
			System.out.println("请设置数据库驱动名称！");
			return null;
		} else {
			System.out.println("加载数据库驱动：" + driver);
		}

		if (driver.equals("com.mysql.jdbc.Driver")) {
			// mysql数据库
			url = "jdbc:mysql://" + databaseVo.getIp() + ":" + databaseVo.getPort()
					+ "/" + databaseVo.getDbName() + "";
		} else if (driver.equals("oracle.jdbc.driver.OracleDriver")) {
			// oracle数据库
			url = "jdbc:oracle:thin:@" + databaseVo.getIp() + ":"
					+ databaseVo.getPort() + ":" + databaseVo.getDbName() + "";
		} else if (driver
				.equals("com.microsoft.jdbc.sqlserver.SQLServerDriver")) {
			// SQLServer数据库
			url = "jdbc:microsoft:sqlserver://" + databaseVo.getIp() + ":"
					+ databaseVo.getPort() + ";DatabaseName=" + databaseVo.getDbName()
					+ "";
		} else if (driver.equals("com.informix.jdbc.IfxDriver")) {
			// informix数据库
			url = "jdbc:informix-sqli://" + databaseVo.getIp() + ":"
					+ databaseVo.getPort() + "/" + databaseVo.getDbName() + "";
		} else if (driver.equals("com.sybase.jdbc.SybDriver")) {
			// sybase数据库
			url = "jdbc:sybase:Tds:" + databaseVo.getIp() + ":" + databaseVo.getPort()
					+ "/" + databaseVo.getDbName() + "";
		} else if (driver.equals("org.postgresql.Driver")) {
			// postgresql数据库
			url = "jdbc:postgresql://" + databaseVo.getIp() + ":"
					+ databaseVo.getPort() + "/" + databaseVo.getDbName() + "";
		} else {
			System.out.println("无此数据库类型的驱动！");
		}

		// 加载驱动
		try {
			Class.forName(databaseVo.getDriver());
			conn = DriverManager.getConnection(url, databaseVo.getUsername(),
					databaseVo.getPassword());
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载出错!");
		}

		return conn;
	}

	/**
	 * 释放连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close(); // 关闭连接
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从结果集中获取所有列名
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getColumnListFormRSMD(ResultSet rs) throws SQLException{
		List<String> ls = new ArrayList<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		// 取得结果集列数
        int columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
        	ls.add(rsmd.getColumnLabel(i));
        }
		return ls;
	}

	/**
	 * 从结果集中获取数据列表
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> getListFromRSMD(ResultSet rs)
			throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		// 取得结果集列数
		int columnCount = rsmd.getColumnCount();
		// 构造泛型结果集
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> data = null;
		/*
		 * System.out.println(); for (int i = 1; i <= columnCount; i++) {
		 * System.out.print(rsmd.getColumnLabel(i)+"\t\t"); }
		 * System.out.println(); for (int i = 1; i <= columnCount; i++) {
		 * System.out.print(rsmd.getColumnName(i)+"\t\t"); }
		 * System.out.println();
		 */
		// 循环结果集
		while (rs.next()) {
			data = new HashMap<String, Object>();
			String key = null;
			Object value = null;
			// 每循环一条将列名和列值存入Map
			for (int i = 1; i <= columnCount; i++) {
				key = rsmd.getColumnLabel(i);
				value = rs.getObject(rsmd.getColumnLabel(i));
				data.put(rsmd.getColumnLabel(i),
						rs.getObject(rsmd.getColumnLabel(i)));
				// System.out.print(value+"\t\t");
			}
			//System.out.println();
			// 将整条数据的Map存入到List中
			datas.add(data);
		}
		return datas;
	}

}
