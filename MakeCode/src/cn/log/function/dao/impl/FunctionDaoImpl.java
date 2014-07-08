/**
 * 数据库Dao接口层实现
 */
package cn.log.function.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.SessionFactory;

import cn.log.db.util.JdbcUtils;
import cn.log.function.dao.IFunctionDao;
import cn.log.function.vo.FunctionVo;

/**
 * @author zouqone
 * @see Function_Dao接口实现
 */
public class FunctionDaoImpl implements IFunctionDao {

		
	//private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * 查询所有的Function
	 * @return List<FunctionVo> 菜单类型列表
	 * @see cn.log.function.dao.IFunctionDao#queryAllFunctionVo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionVo> queryAllFunctionVo() {
		// TODO Auto-generated method stub
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select id , ncode , nodename , nodedesc , parentncode ,link from Function ";
			System.out.println("查询数据，执行sql语句 : "+sql);
			return (List<FunctionVo>) qr.query(sql,  new BeanListHandler(FunctionVo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 根据查询条件查询Function
	 * @param condition 查询条件
	 * @return List<FunctionVo> 菜单类型列表
	 * @see cn.log.function.dao.IFunctionDao#queryFunctionVoByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionVo> queryFunctionVoByCondition(String condition) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select id , ncode , nodename , nodedesc , parentncode ,link from Function ";
		sql =sql +"  "+ condition;
		System.out.println("查询数据，执行sql语句 : "+sql);
		try {
			return (List<FunctionVo>)qr.query(sql,  new BeanListHandler(FunctionVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 插入一个Function
	 * @param functionVo
	 * @return FunctionId
	 * @see cn.log.function.dao.IFunctionDao#insertFunctionVo(cn.log.function.vo.FunctionVo)
	 */
	@Override
	public Integer insertFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into Function(id , ncode , nodename , nodedesc , parentncode , link) values(? ,? ,? ,? ,? ,?)";
			Object params[] = { 
					functionVo.getId() ,functionVo.getNcode() ,functionVo.getNodename() ,functionVo.getNodedesc() ,functionVo.getParentncode()
					,functionVo.getLink()};
			System.out.println("插入数据，执行sql语句 : "+sql);
			return qr.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 插入Function列表
	 * @param functionVoList
	 * @return
	 * @see cn.log.function.dao.IFunctionDao#insertFunctionVo(java.util.List)
	 */
	@Override
	public List<Integer> insertFunctionVo(List<FunctionVo> functionVoList) {
		// TODO Auto-generated method stub
		if(functionVoList == null){
			return null;
		}
		
		List<Integer> stateList = new ArrayList<Integer>();
		for (FunctionVo functionVo : functionVoList) {
			Integer state = insertFunctionVo(functionVo);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * 修改一个Function类型
	 * @param functionVo
	 * @return
	 * @see cn.log.function.dao.IFunctionDao#updateFunctionVo(cn.log.function.vo.FunctionVo)
	 */
	@Override
	public boolean updateFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update Function set id = ?  ,ncode = ?  ,nodename = ?  ,nodedesc = ?  ,parentncode = ? ,link = ?  where id = ?";
			Object params[] = { 
					functionVo.getId() ,functionVo.getNcode() ,functionVo.getNodename() ,functionVo.getNodedesc() ,functionVo.getParentncode()
					,functionVo.getLink(),functionVo.getId()
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
	 * 修改Function类型列表
	 * @param functionVoList
	 * @see cn.log.function.dao.IFunctionDao#updateFunctionVo(java.util.List)
	 */
	@Override
	public List<Object> updateFunctionVo(List<FunctionVo> functionVoList) {
		// TODO Auto-generated method stub
		if(functionVoList == null){
			return null;
		}
		
		List<Object> stateList = new ArrayList<Object>();
		for (FunctionVo functionVo : functionVoList) {
			boolean state = updateFunctionVo(functionVo);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * 通过条件删除FunctionVo
	 * @param paramName 参数名
	 * @param paramValue 参数值
	 * @param paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @see cn.log.function.dao.IFunctionDao#deleteFunctionVoByParam(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteFunctionVoByParam(String paramName, String paramValue,
			String paramOption) {
		// TODO Auto-generated method stub
		try{
			QueryRunner qr = new QueryRunner();
			String sql = "delete from Function where "+paramName+" "+paramOption+" ? ";
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
	 * 批量删除FunctionVo
	 * @param paramList List<String[] paramName 参数名  ,paramValue 参数值 , paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @return
	 * @see cn.log.function.dao.IFunctionDao#deleteFunctionVoByParamList(java.util.List)
	 */
	@Override
	public List<Object> deleteFunctionVoByParamList(List<String[]> paramList) {
		// TODO Auto-generated method stub
		if(paramList == null){
			return null;
		}
		
		List<Object> stateList = new ArrayList<Object>();
		for (String[] params : paramList) {
			String paramName = params[0];
			String paramValue =  params[1];
			String paramOption =  params[2];
			boolean state = deleteFunctionVoByParam(paramName,paramValue,paramOption);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * 通过条件删除Function
	 * @param condition
	 * @return
	 * @see cn.log.function.dao.IFunctionDao#deleteFunctionVoByCondition(java.lang.String)
	 */
	@Override
	public boolean deleteFunctionVoByCondition(String condition) {
		// TODO Auto-generated method stub
		if(condition == null){
			return false;
		}
		try{
			QueryRunner qr = new QueryRunner();
			String sql = "delete from Function where "+condition;
			System.out.println("删除数据，执行sql语句 : "+sql);
			qr.update(JdbcUtils.getConnection(),sql, null);
		}catch (Exception e) {
			;
		}
		return false;
	}

}
