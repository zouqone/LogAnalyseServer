/**
 * 
 */
package cn.log.app.database.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.log.app.database.dao.IDBDao;
import cn.log.app.database.util.IDBconstant;
import cn.log.app.database.util.JDBCHelp;
import cn.log.app.database.vo.ColumnVo;
import cn.log.app.database.vo.DatabaseVo;
import cn.log.app.database.vo.TableVo;


/**
 * @author zouqone
 * date 2014年5月11日   下午4:30:16
 * 
 */
public class MysqlDBDaoImpl implements IDBDao {

	public IDBDao dbDao;
	
	/**
	 * 
	 */
	public MysqlDBDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @return the dbDao
	 */
	public IDBDao getDbDao() {
		return dbDao;
	}



	/**
	 * @param dbDao the dbDao to set
	 */
	public void setDbDao(IDBDao dbDao) {
		this.dbDao = dbDao;
	}



	/* (non-Javadoc)
	 * @see cn.log.app.database.dao.IDBDao#findDBInfo(cn.log.app.database.vo.DatabaseVo)
	 */
	@Override
	public DatabaseVo findDBInfo(DatabaseVo databaseVo) {
		// TODO Auto-generated method stub
		
		String dbName = databaseVo.getDbName();
		String username = databaseVo.getUsername();
		String password = databaseVo.getPassword();
		String url = "jdbc:mysql://"+databaseVo.getIp()+":"+databaseVo.getPort()+"/"+dbName;
		
		Connection conn = null;
		ResultSet rs = null;
		DatabaseMetaData dbmd =null;
		ResultSetMetaData rsmd = null;
		
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			dbmd = conn.getMetaData();
			
			//获取所有表
			rs = dbmd.getTables(null, null, null, null);//TABLE_NAME
			List<TableVo> tableVos = new ArrayList<TableVo>();
			databaseVo.setTableVoList(tableVos);
            while (rs.next()) {
            	TableVo tableVo = new TableVo();
            	String tableName = rs.getObject(IDBconstant.TABLE_NAME).toString();
            	tableVo.setTable_name(tableName);
            	tableVo.setDbName(dbName);
            	tableVos.add(tableVo);
            }
			
			if(tableVos!=null&&tableVos.size()>0){
				String tableName = null;
				for (TableVo tableVo : tableVos) {
					
					tableName = tableVo.getTable_name();
					rs = dbmd.getColumns(null, null, tableName, null);
					List<ColumnVo> columnVoList = new ArrayList<ColumnVo>();
					tableVo.setColumnVoList(columnVoList);
					
					ColumnVo columnVo = null;
					Object object = null;
					//int k = 1;
					while (rs.next()) {
						rsmd = rs.getMetaData();
						columnVo = new ColumnVo();
						
						object = rs.getObject(IDBconstant.COLUMN_NAME);
						object = object==null?"":object;
						columnVo.setField(object.toString());
						
						object = rs.getObject(IDBconstant.TYPE_NAME);
						object = object==null?"":object;
						columnVo.setType(object.toString().toLowerCase());
						//columnVo.setColumnJavaType(rsmd.getColumnClassName(1));
						
						object = rs.getObject(IDBconstant.REMARKS);
						object = object==null?"":object;
						columnVo.setComment(object.toString());
						columnVoList.add(columnVo);
					}
					
					rs = dbmd.getPrimaryKeys(null, null, tableName);
					while (rs.next()) {
						object = rs.getObject(IDBconstant.COLUMN_NAME);
						object = object==null?"":object;
						tableVo.setPrimarykey(object.toString());
					}
					
					rs = dbmd.getExportedKeys(null, null, tableName);
					while (rs.next()) {
						object = rs.getObject(IDBconstant.PKCOLUMN_NAME);
						object = object==null?"":object;
						tableVo.setForeignKey(object.toString());
						object = rs.getObject(IDBconstant.FK_NAME);
						object = object==null?"":object;
						tableVo.setForeignKeyName(object.toString());
						object = rs.getObject(IDBconstant.FKTABLE_NAME);
						object = object==null?"":object;
						tableVo.setForeignKeyTableName(object.toString());
					}
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
                    if(rs != null && rs.next()) {
                            String create = rs.getString(2);
                            String comment = parse(create);
                            tableVo.setComment(comment);
                    }
				}
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return databaseVo;
	}

	/**
	 * 获取所有的数据库名
	 * @param databaseVo
	 * @return
	 */
	public List<String> getAllDBName(DatabaseVo databaseVo) {
		// TODO Auto-generated method stub
		List<String> dbNameList = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String sql = " show databases ";
		
		String dbName = databaseVo.getDbName();
		String username = databaseVo.getUsername();
		String password = databaseVo.getPassword();
		String url = "jdbc:mysql://"+databaseVo.getIp()+":"+databaseVo.getPort()+"/"+dbName;
		
		try {
			//conn = DriverManager.getConnection(url, username, password);
			conn = JDBCHelp.getConnection(databaseVo);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("exec SQL : "+sql);
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			List<String> ls = JDBCHelp.getColumnListFormRSMD(rs);
			List<Map<String, Object>> datas = JDBCHelp.getListFromRSMD(rs);
			//int rows = datas.size();
			//int cols = ls.size();
			String key = ls.get(0);
			for (Map<String, Object> map : datas) {
				dbNameList.add(map.get(key).toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbNameList;
	}
	
    public String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if(index < 0) {
                return "";
        }
        comment = all.substring(index+9);
        comment = comment.substring(0,comment.length() - 1);
        return comment;
    }
}
