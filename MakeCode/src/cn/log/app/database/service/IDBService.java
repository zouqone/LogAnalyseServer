/**
 * 
 */
package cn.log.app.database.service;

import java.util.List;

import cn.log.app.database.vo.DatabaseVo;

/**
 * @author zouqone
 * date 2014年5月11日   下午4:16:38
 *
 */
public interface IDBService {

	/**
	 * 获取数据库信息
	 * @param databaseVo
	 * @return
	 */
	public DatabaseVo findDBInfo(DatabaseVo databaseVo);

	/**
	 * 获取数据库信息
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public DatabaseVo findDBInfo(String url,String username,String password);
	
	/**
	 * 获取所有的数据库名
	 * @param databaseVo
	 * @return
	 */
	public List<String> getAllDBName(DatabaseVo databaseVo);
}
