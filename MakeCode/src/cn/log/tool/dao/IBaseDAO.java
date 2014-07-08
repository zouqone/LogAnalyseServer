/**
 * 
 */
package cn.log.tool.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import cn.log.tool.util.MatcheType;
import cn.log.tool.util.page.PageData;

/**
 * @author zouqone
 * @date 2014年6月28日 下午11:34:08
 */
public interface IBaseDAO <K extends Serializable, T extends Serializable>{

	/**
	 * 获取Hibernate session
	 * @return session
	 */
	public Session getSession();
	
	
	/*========================加载实体==========================*/
	
	/**
	 * 根据Id加载实体 Session.get方法
	 * @param id
	 * @return
	 */
	public T get(K id); 
	
	/**
	 * 根据Id加载实体 Session.load方法
	 * @param id
	 * @return
	 */
	public T load(K id);
	
	
	/*========================更新实体==========================*/
	
	/**
	 * 更新实体
	 * @param entity
	 */
	public int update(T entity);
	
	/**
	 * 批量更新实体
	 * @param entities 要更新的对象集合
	 * @return 返回受影响的行数
	 */
	public int bulkUpdate(Collection<T> entities);
	
	/**
	 * 更新实体Merge方式 (Like save or update)
	 * 
	 * @param entity
	 */
	public void merge(T entity);
	
	
	/*========================删除实体==========================*/
	
	/**
	 * 根据Id删除某个实体
	 * @param id
	 */
	public int delete(K id);
	
	/**
	 * 删除指定实体
	 */
	public int del(T entity);
	
	/**
	 * 批量删除实体
	 */
	public int bulkDelete(Collection<T> entites);
	
	
	/*========================保存实体==========================*/
	
	/**
	 * 保存实体
	 * @param entity
	 */
	public Long save(T entity);
	
	/**
	 * 批量保存实体
	 * @param entites  要保存的实体对象集合
	 * @return  返回保存的对象主键id集合
	 */
	public List<K> bulkSave(Collection<T> entites);
	
	/**
	 * 保存或更新实体
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	
	
	/*========================查询实体==========================*/
	
	/**
	 * 查询所有
	 * @return List<T>
	 */
	public List<T> findAll();
	
	/**
	 * 根据主键id数组批量加载实体集合
	 */
	public List<T> findByIds(Object[] ids);
	
	/**
	 * 根据主键id集合批量加载实体集合
	 */
	public List<T> findByIds(Collection<K> ids);
	
	
	/*========================查询单个对象==========================*/
	
	/**
	 * DetachedCriteria方式查询某个实体
	 */
	public T findUnique(DetachedCriteria detachedCriteria);
	
	/**
	 * 根据hql查询单个对象
	 */
	public T findUnique(String hql);
	
	/**
	 * 根据hql和参数数组查询单个对象
	 */
	public T findUnique(String hql,Object[] condition);
	
	/**
	 * 根据hql和参数集合查询单个对象
	 */
	public T findUnique(String hql,List condition);
	
	/**
	 * 根据条件查询单个对象
	 * @param properties 对象属性集合
	 * @param values     参数集合
	 * @param eqOrLikes  eq/like 是否模糊查询
	 */
	public T findUnique(String[] properties,Object[] values,boolean[] eqOrLikes);
	
	/**
	 * 根据条件查询单个对象
	 * @param target           参数数据模型对象
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public T findUnique(Object target,String[] propertyNames,MatcheType[] matcheTypes);
	
	/**
	 * 根据条件查询单个对象(重载)
	 * @param target         参数数据模型对象
	 * @param targetClass    目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public T findUnique(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes);
	
	
	/*========================hql查询==========================*/
	
	/**
	 * 根据给定hql查询
	 */
	public List findByHql(String hql);
	
	/**
	 * 根据给定hql查询(带参)
	 */
	public List findByHql(String hql, Object[] condition);

	/**
	 * 根据给定hql查询(带参)--重载
	 */
	public List findByHql(String hql, List condition);
	
	/**
	 * hql分页查询
	 * 
	 * @return
	 */
	public PageData findForPage(String hql, int currentPage,int pageSize);

	/**
	 * hql分页查询(带参)
	 * 
	 * @return
	 */
	public PageData findForPage(String hql, Object[] condition,
			int currentPage, int pageSize);

	/**
	 * hql分页查询(带参)重载
	 * 
	 * @return
	 */
	public PageData findForPage(String hql, List condition,
			int currentPage, int pageSize);
	
	
	/*========================criteria查询==========================*/
	
	/**
	 * criteria查询
	 */
	public List findByCriteria(DetachedCriteria detachedCriteria);
	
	/**
	 * DetachedCriteria查询(重载)
	 * @param target           目标对象
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public List findByCriteria(Object target,String[] propertyNames,MatcheType[] matcheTypes);
	
	/**
	 * DetachedCriteria查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public List findByCriteria(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes);
	
	/**
	 * DetachedCriteria查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 * @param sorts            排序字段
	 * @param dirs             排序规则 ASC / DESC
	 */
	public List findByCriteria(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes,String[] sorts,String[] dirs);
	
	/**
	 * DetachedCriteria分页查询(重载)
	 * @param target           目标对象
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public PageData findForPageByCriteria(Object target,String[] propertyNames,MatcheType[] matcheTypes);
	
	/**
	 * DetachedCriteria分页查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 * @param sorts            排序字段
	 * @param dirs             排序规则 ASC / DESC
	 */
	public PageData findForPageByCriteria(Object target,Class targetClass, String[] propertyNames,MatcheType[] matcheTypes,String[] sorts,String[] dirs);
	
	/**
	 * DetachedCriteria分页查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public PageData findForPageByCriteria(Object target,Class targetClass, String[] propertyNames,MatcheType[] matcheTypes);
	
	
	/*========================批量删除更新==========================*/
	
	/**
	 * hql批量删除更新
	 * @param hql,array
	 */
	public int bulkUpdateOrDelete(String hql,Object[] condition);
	
	/**
	 * hql批量删除更新(重载1)
	 * @param hql，Map
	 */
	public int bulkUpdateOrDelete(String hql,Map condition);
	
	/**
	 * hql批量删除更新(重载2)
	 * @param hql，List
	 */
	public int bulkUpdateOrDelete(String hql,List condition);
	
	/**
	 * hql批量删除更新(hql类名不能有别名 wher xxx.id in(:condition)) 推荐使用此方法
	 * 
	 * @param hql
	 *            ,array
	 */
	public int bulkUpdateOrDelete2(String hql, Object[] condition);
	/**
	 * hql批量删除更新(hql类名不能有别名 wher xxx.id in(:condition))
	 * @param hql,array
	 */
	public int bulkUpdateOrDelete2(String hql,List condition);
	/**
	 * hql批量删除更新(hql类名不能有别名 wher xxx.id in(:condition))
	 * 
	 * @param hql
	 *            ,array
	 */
	public int bulkUpdateOrDelete2(String hql, Map condition);
	
	
	
	
	
	/*========================其他方法==========================*/
	/**
	 * 根据给定Id判定某个实体是否存在
	 * @param id
	 * @return
	 */
	public boolean has(K id);
	
	/**
	 * 获取当前PO对应表的总记录数(HQL方式)
	 */
	public Long getTotalCountForCurrentPO();
	
	/**
	 * 
	 *方法摘要：DetachedCriteria方式查询总记录数
	 *@param
	 *@return Long
	 */
	public Long getTotalCountByCriteria();
	
	/**
	 * 
	 *方法摘要：DetachedCriteria方式查询总记录数(重载)
	 *@param
	 *@return Long
	 */
	public Long getTotalCountByCriteria(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes);
	
	/**
	 * 根据HQL查询数据总条数
	 * @param hql
	 * @param condition
	 * @return
	 */
	public Long getTotal(String hql);
	
	/**
	 * 根据HQL查询数据总条数(带参)
	 * @param hql
	 * @param condition
	 * @return
	 */
	public Long getTotal(String hql,Object[] condition);
	
	
	/*========================jdbc sql==========================*/
	
	/**
	 * 适用于增删改操作(不推荐使用此方法，因为此方法会破坏Hibernate的二级缓存体系)
	 * @param sql 例如  delete from tableName where id = ?
	 * @param condition
	 * @return 是否执行成功  true/false
	 */
	public boolean excuteUpdate(String sql,Object[] condition);
	
	/**
	 * 返回单列值（适用于查询总数、平均数、最大值、最小值）
	 * @param sql  例如：select count(*) from user where age > ? 
	 * @param condition
	 * @return
	 */
	public int queryForInt(String sql,Object[] condition);
	
	/**
	 * 返回单个对象(适用于主键查询)
	 * @param sql 例如： select * from user where id = ?
	 * @param condition
	 * @return
	 */
	public T queryForObject(String sql,Object[] condition);
	
	/**
	 * 返回对象集合
	 * @param sql
	 * @param condition
	 * @return
	 */
	public List<T> queryForList(String sql,Object[] condition);
	
	/**
	 * JDBC分页查询
	 * @param sql          初始sql
	 * @param condition    查询参数
	 * @param currentPage  当前第几页
	 * @param pageSize     每页大小
	 * @return
	 */
	public PageData queryForPage(String sql,Object[] condition,int currentPage,int pageSize,String dataBaseName);
}
