package cn.sys.md.dao.impl;




																							



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.sys.md.dao.IComponentDao;
import cn.sys.md.vo.ComponentVo;
import cn.log.db.util.JdbcUtils;
import cn.log.tool.util.DBHelp;
import cn.log.tool.util.DBSquence;
import cn.sys.md.util.IComponentVoConstants;



/**
 * @author zouqone
 * @date 
 * @Description： 组件
 */
public class ComponentDaoImpl implements IComponentDao ,IComponentVoConstants{

	
	/*=======================查询query===========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}queryAllComponentVo()
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentVo> queryAllComponentVo() {
		// TODO Auto-generated method stub
		List<ComponentVo> componentVos = null;
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = SQL_QUERY_ALL;
			System.out.println("查询数据，执行sql语句 : "+sql);
			componentVos = (List<ComponentVo>) qr.query(sql,  new BeanListHandler(ComponentVo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return componentVos;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}queryComponentVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentVo> queryComponentVoByCondition(String condition) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_QUERY_ALL;
		sql =DBHelp.addWhereCondition(sql, condition);
		System.out.println("查询数据，执行sql语句 : "+sql);
		List<ComponentVo> componentVos = null;
		try {
			componentVos = (List<ComponentVo>) qr.query(sql,  new BeanListHandler(ComponentVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return componentVos;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}queryComponentVoBySql(java.lang.String)
	 * @param sql
	 * @return
	 */
	@Override
	public List<Map> queryComponentVoBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}find(cn.sys.md.vo.ComponentVo)
	 * @param componentVo
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Override
	public ComponentVo find(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_FIND_BY_PK;
		System.out.println("查询数据，执行sql语句 : "+sql);
		ComponentVo vo = null;
		if(componentVo.getId()== null){
			return vo;
		}
		String id = componentVo.getId().toString();
		//Integer id = componentVo.getId();
		try {
			vo =  (ComponentVo) qr.query(sql,id, new BeanHandler(ComponentVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	
	
	
	
	/*=====================插入 insert==========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}insertComponentVo(cn.sys.md.vo.ComponentVo)
	 * @param componentVo
	 * @return
	 */
	@Override
	public Object insertComponentVo(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_INSERT;
		String id = componentVo.getId()==null ?DBSquence.nextId():componentVo.getId().toString();
		componentVo.setId(id);
		Object params[] = { 
				componentVo.getId() ,componentVo.getCode() ,componentVo.getName() ,componentVo.getDetail() ,componentVo.getCreatetime() ,componentVo.getCreator() ,componentVo.getModifytime() ,componentVo.getModifer() ,componentVo.getTs() ,componentVo.getDr()  ,componentVo.getComcategoryid()
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
	 * @see cn.sys.md.dao.IComponentDao${sp}insertComponentVo(java.util.List)
	 * @param componentVoList
	 * @return
	 */
	@Override
	public List<Object> insertComponentVo(List<ComponentVo> componentVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = new ArrayList<Object>();
		for (ComponentVo componentVo : componentVoList) {
			Object object = insertComponentVo(componentVo);
			objects.add(object);
		}
		return objects;
	}
	
	
	
	
	
	/*=====================修改update==========================*/
	
	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}updateComponentVo(cn.sys.md.vo.ComponentVo)
	 * @param componentVo
	 * @return
	 */
	@Override
	public Object updateComponentVo(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		Object object = null;
		
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql =SQL_INSERT;
			Object params[] = { 
					componentVo.getId() ,componentVo.getCode() ,componentVo.getName() ,componentVo.getDetail() ,componentVo.getCreatetime() ,componentVo.getCreator() ,componentVo.getModifytime() ,componentVo.getModifer() ,componentVo.getTs() ,componentVo.getDr()  ,componentVo.getComcategoryid()
					,componentVo.getId()
					};
			System.out.println("插入数据，执行sql语句 : "+sql);
			object = qr.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}updateComponentVo(java.util.List)
	 * @param componentVoList
	 * @return
	 */
	@Override
	public List<Object> updateComponentVo(List<ComponentVo> componentVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = new ArrayList<Object>();
		for (ComponentVo componentVo : componentVoList) {
			Object object = updateComponentVo(componentVo);
			objects.add(object);
		}
		return objects;
	}

	
	
	
	
	
	/*=====================删除delete==========================*/

	/* (non-Javadoc)
	 * @see cn.sys.md.dao.IComponentDao${sp}deleteComponentVo(cn.sys.md.vo.ComponentVo)
	 * @param componentVo
	 * @return
	 */
	@Override
	public Object deleteComponentVo(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = SQL_DELETE_MULTI_BY_PKS;
		System.out.println("删除数据，执行sql语句 : "+sql);
		Object params[] = { 
				componentVo.getId()
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
	 * @see cn.sys.md.dao.IComponentDao${sp}deleteComponentVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@Override
	public Object deleteComponentVoByCondition(String condition) {
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
	 * @see cn.sys.md.dao.IComponentDao${sp}queryComponentVoTotalNumber()
	 * @return
	 */
	@Override
	public Integer queryComponentVoTotalNumber() {
		// TODO Auto-generated method stub
		return queryComponentVoTotalNumber(null);
	}

	/**
	 * 查询ComponentVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryComponentVoTotalNumber(String condition){
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
