package cn.log.app.user.dao.impl;




															



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.log.app.user.dao.IUserVoDao;
import cn.log.app.user.vo.UserVo;
import cn.log.db.util.JdbcUtils;
import cn.log.tool.util.DBHelp;
import cn.log.app.user.util.IUserVoConstants;



/**
 * @author zouqone
 * @date 
 * @Description： 
 */
public class UserVoDaoImpl implements IUserVoDao ,IUserVoConstants{

	
	/*=======================查询query===========================*/
	
	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#queryAllUserVo()
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserVo> queryAllUserVo() {
		// TODO Auto-generated method stub
		List<UserVo> userVos = null;
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = SQL_QUERY_ALL;
			System.out.println("查询数据，执行sql语句 : "+sql);
			userVos = (List<UserVo>) qr.query(sql,  new BeanListHandler(UserVo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#queryUserVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserVo> queryUserVoByCondition(String condition) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_QUERY_ALL;
		sql =DBHelp.AddCondition(sql, condition);
		System.out.println("查询数据，执行sql语句 : "+sql);
		List<UserVo> userVos = null;
		try {
			userVos = (List<UserVo>) qr.query(sql,  new BeanListHandler(UserVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userVos;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#queryUserVoBySql(java.lang.String)
	 * @param sql
	 * @return
	 */
	@Override
	public List<Map> queryUserVoBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#find(cn.log.app.user.vo.UserVo)
	 * @param userVo
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Override
	public UserVo find(UserVo userVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_FIND_BY_PK;
		System.out.println("查询数据，执行sql语句 : "+sql);
		UserVo vo = null;
		Integer id = userVo.getId();
		try {
			vo =  (UserVo) qr.query(sql,id, new BeanHandler(UserVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	
	
	
	
	/*=====================插入 insert==========================*/
	
	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#insertUserVo(cn.log.app.user.vo.UserVo)
	 * @param userVo
	 * @return
	 */
	@Override
	public Object insertUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_INSERT;
		Object params[] = { 
				userVo.getId() ,userVo.getUsername() ,userVo.getPassword() ,userVo.getAge() ,userVo.getSex() ,userVo.getAddress() ,userVo.getInfo() ,userVo.getEmail()
				};
		Object object = null;
		
		System.out.println("插入数据，执行sql语句 : "+sql);
		try {
			object = qr.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#insertUserVo(java.util.List)
	 * @param userVoList
	 * @return
	 */
	@Override
	public List<Object> insertUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = new ArrayList<Object>();
		for (UserVo userVo : userVoList) {
			Object object = insertUserVo(userVo);
			objects.add(object);
		}
		return objects;
	}
	
	
	
	
	
	/*=====================修改update==========================*/
	
	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#updateUserVo(cn.log.app.user.vo.UserVo)
	 * @param userVo
	 * @return
	 */
	@Override
	public Object updateUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		Object object = null;
		
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql =SQL_INSERT;
			Object params[] = { 
					userVo.getId() ,userVo.getUsername() ,userVo.getPassword() ,userVo.getAge() ,userVo.getSex() ,userVo.getAddress() ,userVo.getInfo() ,userVo.getEmail()
					,userVo.getId()
					};
			System.out.println("插入数据，执行sql语句 : "+sql);
			object = qr.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#updateUserVo(java.util.List)
	 * @param userVoList
	 * @return
	 */
	@Override
	public List<Object> updateUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = new ArrayList<Object>();
		for (UserVo userVo : userVoList) {
			Object object = updateUserVo(userVo);
			objects.add(object);
		}
		return objects;
	}

	
	
	
	
	
	/*=====================删除delete==========================*/

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#deleteUserVo(cn.log.app.user.vo.UserVo)
	 * @param userVo
	 * @return
	 */
	@Override
	public Object deleteUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_DELETE_BY_PK;
		System.out.println("删除数据，执行sql语句 : "+sql);
		Object params[] = { 
				userVo.getId()
				};
		Object object = null;
		try{
			object = qr.update(sql,params);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#deleteUserVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@Override
	public Object deleteUserVoByCondition(String condition) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_DELETE_MULTI_BY_PKS+" "+DEFAULT_QUERY_WHERE_ENABLE+" ";
		sql = DBHelp.AddCondition(sql, condition);
		System.out.println("删除数据，执行sql语句 : "+sql);
		Object object = null;
		try{
			object = qr.update(sql, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	/*=====================获取数目==========================*/
	
	/* (non-Javadoc)
	 * @see cn.log.app.user.dao.IUserVoDao#queryUserVoTotalNumber()
	 * @return
	 */
	@Override
	public Integer queryUserVoTotalNumber() {
		// TODO Auto-generated method stub
		return queryUserVoTotalNumber(null);
	}

	/**
	 * 查询UserVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryUserVoTotalNumber(String condition){
		Long counts = new Long(0);
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_COUNT;
		sql = DBHelp.AddCondition(sql, condition);
		System.out.println("查询数据，执行sql语句 : "+sql);
		ResultSetHandler rsh = new ScalarHandler();
		try {
			counts = (Long) qr.query(sql,  rsh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Integer(counts.toString());
	}
}
