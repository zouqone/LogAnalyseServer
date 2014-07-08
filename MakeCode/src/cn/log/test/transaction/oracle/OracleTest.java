/**
 * 
 */
package cn.log.test.transaction.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.log.db.util.JdbcUtils;
import cn.log.function.vo.FunctionVo;

/**
 * @author zouqone
 * @date 2014年7月6日 下午5:55:55
 */
public class OracleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FunctionVo functionVo = new FunctionVo(4, "1-1", "1-1", "test", "app");
		Connection conn = getConn();
		try {
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into Functions(id , ncode , nodename , nodedesc , parentnode , links) values(? ,? ,? ,? ,? ,?)";
			Object params[] = { 
					functionVo.getId() ,functionVo.getNcode() ,functionVo.getNodename() ,functionVo.getNodedesc() ,functionVo.getParentncode()
					,functionVo.getLink()};
			System.out.println("插入数据，执行sql语句 : "+sql);
			qr.update(conn, sql, params);
			functionVo.setId(28);
			params[0]=functionVo.getId() ;
			qr.update(conn, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			try{
				if(conn!=null){
					conn.rollback();
				}
			}catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
		try{
			if(conn!=null){
				conn.commit();
			}
		}catch (Exception e3) {
			throw new RuntimeException(e3);
		}
	}
	
	public static Connection getConn(){
		Connection conn = null;
		String configName = "";
		Properties properties = new Properties();
		DataSource ds = new ComboPooledDataSource(configName);
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	

}
