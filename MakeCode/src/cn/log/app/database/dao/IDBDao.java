/**
 * 
 */
package cn.log.app.database.dao;

import java.util.List;

import cn.log.app.database.vo.DatabaseVo;

/**
 * @author zouqone
 * date 2014年5月11日   下午4:26:52
 *
 */
public interface IDBDao {

	/**
	 * 获取数据库信息
	 * @param databaseVo
	 * @return
	 */
	public DatabaseVo findDBInfo(DatabaseVo databaseVo);

	/**
	 * 获取所有的数据库名
	 * @param databaseVo
	 * @return
	 */
	public List<String> getAllDBName(DatabaseVo databaseVo);
}
