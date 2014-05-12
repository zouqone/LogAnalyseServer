/**
 * 
 */
package cn.log.app.database.service.impl;

import java.util.List;

import cn.log.app.database.dao.IDBDao;
import cn.log.app.database.service.IDBService;
import cn.log.app.database.vo.DatabaseVo;

/**
 * @author zouqone
 * date 2014年5月11日   下午4:23:48
 *
 */
public class MysqlDBServcieImpl implements IDBService {

	public IDBDao mysqlDBDao;
	
	/**
	 * 
	 */
	public MysqlDBServcieImpl() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return the mysqlDBDao
	 */
	public IDBDao getMysqlDBDao() {
		return mysqlDBDao;
	}


	/**
	 * @param mysqlDBDao the mysqlDBDao to set
	 */
	public void setMysqlDBDao(IDBDao mysqlDBDao) {
		this.mysqlDBDao = mysqlDBDao;
	}


	/* (non-Javadoc)
	 * @see cn.log.app.database.service.IDBService#findDBInfo(cn.log.app.database.vo.DatabaseVo)
	 */
	@Override
	public DatabaseVo findDBInfo(DatabaseVo databaseVo) {
		// TODO Auto-generated method stub
		databaseVo = mysqlDBDao.findDBInfo(databaseVo);
		return databaseVo;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.database.service.IDBService#findDBInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DatabaseVo findDBInfo(String url, String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取所有的数据库名
	 * @param databaseVo
	 * @return
	 */
	public List<String> getAllDBName(DatabaseVo databaseVo){
		return mysqlDBDao.getAllDBName(databaseVo);
	}

}
