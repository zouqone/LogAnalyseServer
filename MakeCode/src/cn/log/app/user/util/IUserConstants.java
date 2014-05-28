/**
 * 
 */
package cn.log.app.user.util;

/**
 * @author zouqone
 * @date 2014年5月28日 下午9:42:27
 */
public interface IUserConstants {

	/**
	 * BS的规划化名称
	 */
	public final static String SERVICE_KEY = "IUserService";
	
	/**
	 * 表名
	 */
	public final static String TABLE_NAME = "user";
	
	/**
	 * 表名注释
	 */
	public final static String TABLE_NAME_COMMENT = "用户信息";

	/**
	 * 默认查询条件链接关键字
	 */
	public final static String DEFAULT_SQL_CONTACT_KEYWORD = " where ";
	
	/**
	 * 默认查询条件
	 */
	public final static String DEFAULT_QUERY_WHERE_ENABLE = DEFAULT_SQL_CONTACT_KEYWORD+" 1=1 ";

	/**
	 * 默认排序字段
	 */
	public final static String DEFAULT_ORDER_CODE = "id";

	
	
	
	//SQL语句
	
	/**
	 * 插入
	 */
	public final static String SQL_INSERT = "insert into user(user.id, user.username, user.password, user.age, user.sex, user.address, user.info, user.email) values(?,?,?,?,?,?,?,?) ";
	
	/**
	 * 通过PK删除
	 */
	public final static String SQL_DELETE_BY_PK = "delete from user where ID=?";

	/**
	 * 批量删除
	 */
	public final static String SQL_DELETE_MULTI_BY_PKS = "delete from user";

	/**
	 * 通过PK修改
	 */
	public final static String SQL_UPDATE_BY_PK = "update user set id = ? ,username = ? ,password = ? ,age = ? ,sex = ? ,address = ? ,Info = ? ,email = ?  where id = ?";

	/**
	 * 查询记录数目
	 */
	public final static String SQL_COUNT = "select count(*) from user";

	/**
	 * 查询所有记录
	 */
	public final static String SQL_QUERY_ALL = "SELECT user.id, user.username, user.password, user.age, user.sex, user.address, user.info, user.email  FROM user";

	/**
	 * 通过PK查询
	 */
	public final static String SQL_FIND_BY_PK = SQL_QUERY_ALL+DEFAULT_SQL_CONTACT_KEYWORD+" id = ? ";


}
