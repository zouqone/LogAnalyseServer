package cn.sys.md.dao.impl;




																					



import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.sys.md.dao.IComcategoryDao;
import cn.sys.md.vo.ComcategoryVo;
import cn.log.db.util.JdbcUtils;
import cn.log.tool.util.DBHelp;
import cn.log.tool.util.DBSquence;
import cn.log.tool.util.StringUtils;
import cn.sys.md.util.IComcategoryVoConstants;



/**
 * @author zouqone
 * @date 
 * @Description： 组件分类
 */
public class ComcategoryDaoImpl implements IComcategoryDao ,IComcategoryVoConstants{

	
	/*=======================查询query===========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#queryAllComcategoryVo()
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComcategoryVo> queryAllComcategoryVo() {
		// TODO Auto-generated method stub
		List<ComcategoryVo> comcategoryVos = null;
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = SQL_QUERY_ALL;
			System.out.println("查询数据，执行sql语句 : "+sql);
			comcategoryVos = (List<ComcategoryVo>) qr.query(sql,  new BeanListHandler(ComcategoryVo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return comcategoryVos;
		}
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#queryComcategoryVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<ComcategoryVo> queryComcategoryVoByCondition(String condition) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_QUERY_ALL;
		sql =DBHelp.addWhereCondition(sql, condition);
		
		System.out.println("查询数据，执行sql语句 : "+sql);
		List<ComcategoryVo> comcategoryVos = null;
		try {
			comcategoryVos = (List<ComcategoryVo>) qr.query(sql,  new BeanListHandler(ComcategoryVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return comcategoryVos;
		}
		
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#queryComcategoryVoBySql(java.lang.String)
	 * @param sql
	 * @return
	 */
	@Override
	public List<Map> queryComcategoryVoBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#find(cn.sys.md.vo.ComcategoryVo)
	 * @param comcategoryVo
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Override
	public ComcategoryVo find(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_FIND_BY_PK;
		
		ComcategoryVo vo = null;
		if(comcategoryVo.getId()== null){
			return vo;
		}
		String id = comcategoryVo.getId().toString();
		//Integer id = comcategoryVo.getId();
		Object params[] = {id};
		System.out.println("查询数据，执行sql语句 : "+sql+",params : "+StringUtils.join(params));
		try {
			vo =  (ComcategoryVo) qr.query(sql,id, new BeanHandler(ComcategoryVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return vo;
	}

	
	
	
	
	/*=====================插入 insert==========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#insertComcategoryVo(cn.sys.md.vo.ComcategoryVo)
	 * @param comcategoryVo
	 * @return
	 */
	@Override
	public Object insertComcategoryVo(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_INSERT;
		String id = comcategoryVo.getId()==null ?DBSquence.nextId():comcategoryVo.getId().toString();
		comcategoryVo.setId(id);
		Object params[] = { 
				comcategoryVo.getId() ,comcategoryVo.getParentcode() ,comcategoryVo.getCode() ,comcategoryVo.getName() ,comcategoryVo.getDetail() ,comcategoryVo.getCreatetime() ,comcategoryVo.getCreator() ,comcategoryVo.getModifytime() ,comcategoryVo.getModifer() ,comcategoryVo.getTs() ,comcategoryVo.getDr()
				};
		Object object = null;
		
		System.out.println("插入数据，执行sql语句 : "+sql+",params : "+StringUtils.join(params));
		try {
			object = qr.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#insertComcategoryVo(java.util.List)
	 * @param comcategoryVoList
	 * @return
	 */
	@Override
	public List<Object> insertComcategoryVo(List<ComcategoryVo> comcategoryVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = new ArrayList<Object>();
		for (ComcategoryVo comcategoryVo : comcategoryVoList) {
			Object object = insertComcategoryVo(comcategoryVo);
			objects.add(object);
		}
		return objects;
	}
	
	
	
	
	
	/*=====================修改update==========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#updateComcategoryVo(cn.sys.md.vo.ComcategoryVo)
	 * @param comcategoryVo
	 * @return
	 */
	@Override
	public Object updateComcategoryVo(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		Object object = null;
		
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql =SQL_UPDATE_BY_PK;
			Object params[] = { 
					comcategoryVo.getId() ,comcategoryVo.getParentcode() ,comcategoryVo.getCode() ,comcategoryVo.getName() ,comcategoryVo.getDetail() ,comcategoryVo.getCreatetime() ,comcategoryVo.getCreator() ,comcategoryVo.getModifytime() ,comcategoryVo.getModifer() ,comcategoryVo.getTs() ,comcategoryVo.getDr()
					,comcategoryVo.getId()
					};
			System.out.println("插入数据，执行sql语句 : "+sql+",params : "+StringUtils.join(params));
			object = qr.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#updateComcategoryVo(java.util.List)
	 * @param comcategoryVoList
	 * @return
	 */
	@Override
	public List<Object> updateComcategoryVo(List<ComcategoryVo> comcategoryVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = new ArrayList<Object>();
		for (ComcategoryVo comcategoryVo : comcategoryVoList) {
			Object object = updateComcategoryVo(comcategoryVo);
			objects.add(object);
		}
		return objects;
	}

	
	
	
	
	
	/*=====================删除delete==========================*/

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#deleteComcategoryVo(cn.sys.md.vo.ComcategoryVo)
	 * @param comcategoryVo
	 * @return
	 */
	@Override
	public Object deleteComcategoryVo(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_DELETE_BY_PK;
		
		Object params[] = { 
				comcategoryVo.getId()
				};
		Object object = null;
		try{
			System.out.println("删除数据，执行sql语句 : "+sql+",params : "+StringUtils.join(params));
			object = qr.update(sql,params);
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#deleteComcategoryVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@Override
	public Object deleteComcategoryVoByCondition(String condition) {
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
			return e.getMessage();
		}
		return object;
	}

	/*=====================获取数目==========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComcategoryDao#queryComcategoryVoTotalNumber()
	 * @return
	 */
	@Override
	public Integer queryComcategoryVoTotalNumber() {
		// TODO Auto-generated method stub
		return queryComcategoryVoTotalNumber(null);
	}

	/**
	 * 查询ComcategoryVo数目
	 * @param condition
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer queryComcategoryVoTotalNumber(String condition){
		Long counts = new Long(0);
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_COUNT;
		sql = DBHelp.addWhereCondition(sql, condition);
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
