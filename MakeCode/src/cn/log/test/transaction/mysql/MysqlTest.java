/**
 * 
 */
package cn.log.test.transaction.mysql;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

import cn.log.db.util.JdbcUtils;
import cn.log.function.vo.FunctionVo;

/**
 * @author zouqone
 * @date 2014年7月6日 下午5:28:47
 */
public class MysqlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FunctionVo functionVo = new FunctionVo(null, "1-1", "1-1", "test", "app");
		Connection conn = JdbcUtils.getConnection();
		try {
			//conn.setAutoCommit(false);
			JdbcUtils.startTransaction();
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "insert into Function(id , ncode , nodename , nodedesc , parentncode , link) values(? ,? ,? ,? ,? ,?)";
			Object params[] = { 
					functionVo.getId() ,functionVo.getNcode() ,functionVo.getNodename() ,functionVo.getNodedesc() ,functionVo.getParentncode()
					,functionVo.getLink()};
			System.out.println("插入数据，执行sql语句 : "+sql);
			qr.update(conn,sql, params);
			functionVo.setId(28);
			params[0]=functionVo.getId() ;
			qr.update(conn,sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtils.rollback();
			
		}
		JdbcUtils.commit();
	}
	
}
