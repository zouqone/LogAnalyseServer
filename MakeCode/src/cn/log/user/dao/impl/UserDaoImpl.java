/**
 * 数据库Dao接口层实现
 */
package cn.log.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.log.db.util.JdbcUtils;
import cn.log.user.dao.IUserDao;
import cn.log.user.vo.UserVo;

/**
 * @author zouqone
 * @see 菜单User_Dao接口实现
 */
public class UserDaoImpl implements IUserDao {

	/* (non-Javadoc)
	 * 查询所有的user
	 * @return List<UserVo> 菜单类型列表
	 * @see cn.log.user.dao.IUserDao#queryAllUserVo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserVo> queryAllUserVo() {
		// TODO Auto-generated method stub
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select id,username,password,age,sex,address,info,email from user ";
			System.out.println("查询数据，执行sql语句 : "+sql);
			return (List<UserVo>) qr.query(sql,  new BeanListHandler(UserVo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 根据查询条件查询user
	 * @param condition 查询条件
	 * @return List<UserVo> 菜单类型列表
	 * @see cn.log.user.dao.IUserDao#queryUserVoByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserVo> queryUserVoByCondition(String condition) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select id,username,password,age,sex,address,Info,email from user ";
		sql =sql +"  "+ condition;
		System.out.println("查询数据，执行sql语句 : "+sql);
		try {
			return (List<UserVo>)qr.query(sql,  new BeanListHandler(UserVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 插入一个user
	 * @param userVo
	 * @return userId
	 * @see cn.log.user.dao.IUserDao#insertUserVo(cn.log.user.vo.UserVo)
	 */
	@Override
	public Integer insertUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id,username,password,age,sex,address,Info,email) values(?,?,?,?,?,?,?,?)";
			Object params[] = { 
					userVo.getId(),userVo.getUsername(),userVo.getPassword(),userVo.getAge(),userVo.getSex(),userVo.getAddress(),userVo.getInfo(),userVo.getEmail()
					};
			System.out.println("插入数据，执行sql语句 : "+sql);
			return qr.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 插入user列表
	 * @param userVoList
	 * @return
	 * @see cn.log.user.dao.IUserDao#insertUserVo(java.util.List)
	 */
	@Override
	public List<Integer> insertUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		if(userVoList == null){
			return null;
		}
		
		List<Integer> stateList = new ArrayList<Integer>();
		for (UserVo userVo : userVoList) {
			Integer state = insertUserVo(userVo);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * 修改一个user类型
	 * @param userVo
	 * @return
	 * @see cn.log.user.dao.IUserDao#updateUserVo(cn.log.user.vo.UserVo)
	 */
	@Override
	public boolean updateUserVo(UserVo userVo) {
		// TODO Auto-generated method stub

		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update user set id = ? ,username = ? ,password = ? ,age = ? ,sex = ? ,address = ? ,Info = ? ,email = ?  where id = ?";
			Object params[] = { 
					userVo.getId(),userVo.getUsername(),userVo.getPassword(),userVo.getAge(),userVo.getSex(),userVo.getAddress(),userVo.getInfo(),userVo.getEmail() , userVo.getId()
					};
			System.out.println("修改数据，执行sql语句 : "+sql);
			int i = qr.update(sql, params);
			if(i>0){
				return true;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * 修改user类型列表
	 * @param userVoList
	 * @see cn.log.user.dao.IUserDao#updateUserVo(java.util.List)
	 */
	@Override
	public List<Object> updateUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		if(userVoList == null){
			return null;
		}
		
		List<Object> stateList = new ArrayList<Object>();
		for (UserVo userVo : userVoList) {
			boolean state = updateUserVo(userVo);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * 通过条件删除UserVo
	 * @param paramName 参数名
	 * @param paramValue 参数值
	 * @param paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @see cn.log.user.dao.IUserDao#deleteUserVoByParam(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteUserVoByParam(String paramName, String paramValue,
			String paramOption) {
		// TODO Auto-generated method stub
		try{
			QueryRunner qr = new QueryRunner();
			String sql = "delete from user where "+paramName+" "+paramOption+" ? ";
			Object params[] = { 
					paramValue
			};
			System.out.println("删除数据，执行sql语句 : "+sql);
			Integer flag = qr.update(JdbcUtils.getConnection(),sql, params);
			if(flag > 0 ){
				return true;
			}
		}catch (Exception e) {
			;
		}
		return false;
	}

	/* (non-Javadoc)
	 * 批量删除UserVo
	 * @param paramList List<String[] paramName 参数名  ,paramValue 参数值 , paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @return
	 * @see cn.log.user.dao.IUserDao#deleteUserVoByParamList(java.util.List)
	 */
	@Override
	public List<Object> deleteUserVoByParamList(List<String[]> paramList) {
		// TODO Auto-generated method stub
		if(paramList == null){
			return null;
		}
		
		List<Object> stateList = new ArrayList<Object>();
		for (String[] params : paramList) {
			String paramName = params[0];
			String paramValue =  params[1];
			String paramOption =  params[2];
			boolean state = deleteUserVoByParam(paramName,paramValue,paramOption);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * 通过条件删除user
	 * @param condition
	 * @return
	 * @see cn.log.user.dao.IUserDao#deleteUserVoByCondition(java.lang.String)
	 */
	@Override
	public boolean deleteUserVoByCondition(String condition) {
		// TODO Auto-generated method stub
		if(condition == null){
			return false;
		}
		try{
			QueryRunner qr = new QueryRunner();
			String sql = "delete from user where "+condition;
			System.out.println("删除数据，执行sql语句 : "+sql);
			qr.update(JdbcUtils.getConnection(),sql, null);
		}catch (Exception e) {
			;
		}
		return false;
	}

	@Override
	public Integer queryUserVoTotalNumber() {
		// TODO Auto-generated method stub
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from user ";
			System.out.println("查询数据，执行sql语句 : "+sql);
			ResultSetHandler rsh = new ScalarHandler();
			Long counts = new Long(0);
			counts = (Long) qr.query(sql,  rsh);
			return new Integer(counts.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
