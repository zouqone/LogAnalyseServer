/**
 * 
 */
package cn.log.tool.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.log.tool.dao.IBaseDAO;
import cn.log.tool.util.DetachedCriteriaUtils;
import cn.log.tool.util.Globarle;
import cn.log.tool.util.MatcheType;
import cn.log.tool.util.MyBeanUtils;
import cn.log.tool.util.ReflectionUtils;
import cn.log.tool.util.page.PageData;

/**
 * @author zouqone
 * @date 2014年6月28日 下午11:38:47
 */
public class BaseDAOImpl <K extends Serializable, T extends Serializable> implements IBaseDAO<K, T> {

	private Class<T> entityClass;
	private JdbcTemplate jdbcTemplate;
	private SessionFactory sessionFactory;
	
	/**实体主键名称*/
	private String pkName = null;
	
	/**查询所有HQL*/
	private String HQL_LIST_ALL;
	
	/**查询总记录数HQL*/
    private String HQL_COUNT_ALL;
    
	public Class<T> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
	/**
	 * 获取sessionFactory
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * 设置sessionFactory
	 * @param sessionFactory
	 */
	@Autowired
    @Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 构造方法
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl(){
		this.entityClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
        	//判断是否标注了指定注解
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
                break;
            }
        }
        
        this.HQL_LIST_ALL = new StringBuffer("from ").append(getEntityClass().getSimpleName())
        	.append(" order by ").append(this.pkName).append(" desc").toString();
        this.HQL_COUNT_ALL = new StringBuffer(" select count(_")
            .append(getEntityClass().getSimpleName().toLowerCase())
            .append(") from ").append(getEntityClass().getSimpleName())
        	.append(" as _").append(getEntityClass().getSimpleName().toLowerCase())
        	.toString();
	}
	
	
	
	
	
	/**
	 * 获取Hibernate Session对象
	 */
	@Override
	public Session getSession() {
		//Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		if(!session.isOpen()){
			session = sessionFactory.openSession();
			System.out.println("openSession : "+session.toString());
		}
        return session;
	}
	
	/**
	 * 根据Id加载实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T get(K id) {
		return (T) getSession().get(entityClass, (Serializable) id);
	}
	
	/**
	 * 根据Id加载实体
	 * @param id
	 * @return
	*/
	@Override
	public T load(K id){
		return (T) getSession().load(entityClass, (Serializable) id);
	}
	
	/**
	 * 更新实体
	 * 
	 * @param entity
	*/
	@Override
	public int update(T entity) {
		if(null == entity){
			return 0;
		}
		getSession().clear();//清理Session缓存(很关键)
		getSession().update(entity);
		return 1;
	}
	
	/**
	 * 批量更新实体
	 * @param entities 要更新的对象集合
	 * @return 返回受影响的行数
	 */
	@Override
	public int bulkUpdate(Collection<T> entities){
		int count = 0;  //统计受影响的行数
		if(null == entities || entities.size() == 0){
			return count;
		}
		for(T t : entities){
			if(count % 20 == 0){
				getSession().flush(); //清理Hibernate缓存，防止内存溢出
			}
			getSession().update(t);
			count++;
		}
		return count;
	}
	
	/**
	 * 更新实体Merge方式
	 * 
	 * @param entity
	 */
	public void merge(T entity) {
		getSession().merge(entity);
	}
	
	/**
	 * 删除指定实体
	 */
	public int del(T entity){
		if(null == entity){
			return 0;
		}
		getSession().delete(entity);
		return 1;
	}
	
	/**
	 * 批量删除实体
	 * @return int 返回受影响的行数
	 */
	public int bulkDelete(Collection<T> entites){
		int index = 0;
		for (T t : entites) {
			if(index % 20 == 0){
				getSession().flush();//清理Hibernate缓存
			}
			getSession().delete(t);
			index++;
		}
		return index;
	}
	
	/**
	 * 根据Id删除某个实体
	 * 
	 * @param id
	 */
	public int delete(K id) {
		Object object = get(id);
		if(null == object){
			return 0;
		}
		getSession().clear();//清理Session缓存(很关键)
		getSession().delete(object);
		return 1;
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public Long save(T entity) {
		Object obj = getSession().save(entity);
		return Long.parseLong(obj.toString()); 
	}
	/**
	 * 批量保存实体
	 * @param entites  要保存的实体对象集合
	 * @return  返回保存的对象主键id集合
	 */
	public List<K> bulkSave(Collection<T> entites){
		List<K> list = new ArrayList<K>();
		int index = 0;
		for (T t : entites) {
			if(index % 20 == 0){
				getSession().flush();//清理Hibernate缓存
			}
			K id = (K)getSession().save(t);
			list.add(id);
			index++;
		}
		return list;
	}
	/**
	 * 保存或更新实体
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 查询所有
	 * 
	 * @return List<T>
	 */
	public List<T> findAll() {	
		return (List<T>)getSession().createCriteria(getEntityClass()).list();
	}
	
	/**
	 * 根据主键id数组批量加载实体集合
	 */
	public List<T> findByIds(Object[] ids){
		return (List<T>)getSession().createCriteria(getEntityClass())
			.add(Restrictions.in(this.pkName, ids))
			.list();
	}
	
	/**
	 * 根据主键id集合批量加载实体集合
	 */
	public List<T> findByIds(Collection<K> ids){
		return findByIds(ids.toArray());
	}

	/**
	 * DetachedCriteria方式查询某个实体
	 * DetachedCriteria使用示例：
	 * DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Beauty.class).createAlias("customers", "c");
	 * beautyCriteria.add(Restrictions.eq("c.name", "Gates")):
	 */
	public T findUnique(DetachedCriteria detachedCriteria){
		return (T)detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}
	
	/**
	 * 根据hql查询单个对象
	 */
	public T findUnique(String hql){
		Query query = getSession().createQuery(hql);
		return (T)query.uniqueResult();
	}
	
	/**
	 * 根据hql查询对象
	 */
	public List<T> findList(String hql){
		Query query = getSession().createQuery(hql);
		List<T> lt =(List<T>)query.list();
		return lt;
	}
	
	/**
	 * 根据hql和参数数组查询单个对象
	 */
	public T findUnique(String hql,Object[] condition){
		Query query = getSession().createQuery(hql);
		setParameters(query, condition);
		return (T)query.uniqueResult();
	}
	
	/**
	 * 根据hql和参数集合查询单个对象
	 */
	public T findUnique(String hql,List condition){
		return findUnique(hql,condition.toArray());
	}
	
	/**
	 * 根据条件查询单个对象
	 * @param property 对象属性集合
	 * @param value    参数集合
	 * @param eqOrLike eq/like 是否模糊查询
	 */
	public T findUnique(String[] properties,Object[] values,boolean[] eqOrLikes){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.addOrder(Order.desc("id"));   //id降序
		for(int i = 0; i < properties.length; i++){
			if(eqOrLikes[i]){
				detachedCriteria.add(Restrictions.eq(properties[i], values[i]));
			} else{
				detachedCriteria.add(Restrictions.like(properties[i], values[i].toString(), MatchMode.ANYWHERE));
			}
		}
		return (T)detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}
	
	/**
	 * 根据条件查询单个对象
	 * @param target           参数数据模型对象
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public T findUnique(Object target,String[] propertyNames,MatcheType[] matcheTypes){
		return (T)DetachedCriteriaUtils
			.addCondition(target, null, propertyNames, matcheTypes)
			.getExecutableCriteria(getSession()).uniqueResult();
	}
	
	/**
	 * 根据条件查询单个对象
	 * @param target         参数数据模型对象
	 * @param targetClass    目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public T findUnique(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes){
		return (T)DetachedCriteriaUtils
			.addCondition(target,targetClass, propertyNames, matcheTypes)
			.getExecutableCriteria(getSession()).uniqueResult();
	}
	
	/**
	 * criteria查询
	 */
	public List findByCriteria(DetachedCriteria detachedCriteria){
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}
	
	/**
	 * DetachedCriteria查询(重载)
	 * @param target           目标对象
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public List findByCriteria(Object target,String[] propertyNames,MatcheType[] matcheTypes){
		return findByCriteria(target, null, propertyNames, matcheTypes);
	}
	
	/**
	 * DetachedCriteria查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public List findByCriteria(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes){
		return findByCriteria(target, targetClass, propertyNames, matcheTypes, null, null);
	}
	
	/**
	 * DetachedCriteria查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 * @param sorts            排序字段
	 * @param dirs             排序规则 ASC / DESC
	 */
	public List findByCriteria(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes,String[] sorts,String[] dirs){
		DetachedCriteria detachedCriteria = DetachedCriteriaUtils.addCondition(target,targetClass, propertyNames, matcheTypes,sorts,dirs);
		return findByCriteria(detachedCriteria);
	}
	
	/**
	 * 根据给定hql查询
	 */
	public List findByHql(String hql) {
		Session session = getSession();
		List list = session.createQuery(hql).list();
		//return getSession().createQuery(hql).list();
		//session.close();
		return list;
	}

	/**
	 * 根据给定hql查询(带参)
	 */
	public List findByHql(String hql, Object[] condition) {
		Query query = getSession().createQuery(hql);
		setParameters(query,condition);
		return query.list();
	}

	/**
	 * 根据给定hql查询(带参)--重载
	 */
	public List findByHql(String hql, List condition) {
		return findByHql(hql,condition.toArray());
	}
	/**
	 * hql分页查询
	 * 
	 * @return
	 */
	public PageData findForPage(String hql, int currentPage,
			int pageSize) {
		return findForPage(hql, new Object[]{}, currentPage, pageSize);
	}

	/**
	 * hql分页查询(带参)
	 * 
	 * @return
	 */
	public PageData findForPage(String hql, Object[] condition,
			int currentPage, int pageSize) {
		int total = getTotal(getTotalHql(hql), condition).intValue();
		Query query = getSession().createQuery(hql);
		query = setParameters(query, condition);
		List list = query.setFirstResult((currentPage - 1) * pageSize)
			.setMaxResults(pageSize).list();
		return new PageData(list, total, currentPage, pageSize);
	}
	/**
	 * hql分页查询(带参)重载
	 * 
	 * @return
	 */
	public PageData findForPage(String hql, List condition,
			int currentPage, int pageSize){
		return findForPage(hql,condition.toArray(),currentPage,pageSize);
	}
	
	/**
	 * DetachedCriteria分页查询
	 * DetachedCriteria使用示例：
	 * DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Beauty.class).createAlias("customers", "c");
	 * beautyCriteria.add(Restrictions.eq("c.name", "Gates"));
	 */
	public PageData findForPageByCriteria(DetachedCriteria detachedCriteria, int currentPage,int pageSize){
		List items = new ArrayList();
		PageData pageData = new PageData();
		pageData.setCurrentPage(currentPage);
		pageData.setPageSize(pageSize=pageSize <= 0? Globarle.PAGESIZE : pageSize);
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		CriteriaImpl impl = (CriteriaImpl) criteria;
		Projection projection = impl.getProjection();
		List orderEntries;

		try {
			orderEntries = (List) MyBeanUtils.getPrivateProperty(impl, "orderEntries");
			//因为执行总记录查询时不需要order by关键字，所以需要先把order by部分移除
			MyBeanUtils.setPrivateProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new RuntimeException("Copy the properties of object occur exception.");
		}

		//如果分页，执行查询, 设置总数
		if (currentPage > 0 && pageSize > 0) {
			int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			pageData.setTotalRows(totalCount);
		}

		// 将之前的Projection和OrderBy条件重新设回去
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		try {
			MyBeanUtils.setPrivateProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new RuntimeException("Copy the properties of object occur exception.");
		}
		if (currentPage > 0 && pageSize > 0) {
			criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
		}
		items.addAll(criteria.list());
		if (null != items) {
			pageData.setItems(items);
		}
		return pageData;
	}
	
	/**
	 * DetachedCriteria分页查询(重载)
	 * @param target           目标对象
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public PageData findForPageByCriteria(Object target,String[] propertyNames,MatcheType[] matcheTypes){
		return findForPageByCriteria(target, null, propertyNames, matcheTypes);
	}
	
	/**
	 * DetachedCriteria分页查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 */
	public PageData findForPageByCriteria(Object target,Class targetClass, String[] propertyNames,MatcheType[] matcheTypes){
		return findForPageByCriteria(target, targetClass, propertyNames, matcheTypes, null, null);
	}
	
	/**
	 * DetachedCriteria分页查询(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 * @param sorts            排序字段
	 * @param dirs             排序规则 ASC / DESC
	 */
	public PageData findForPageByCriteria(Object target,Class targetClass, String[] propertyNames,MatcheType[] matcheTypes,String[] sorts,String[] dirs){
		DetachedCriteria detachedCriteria = DetachedCriteriaUtils.addCondition(target, targetClass, propertyNames, matcheTypes,sorts,dirs);
		return findForPageByCriteria(
			detachedCriteria,
			Integer.parseInt(ReflectionUtils.invokeGetMethod(target, "getCurrentPage").toString()),
			Integer.parseInt(ReflectionUtils.getFieldValue(target, "pageSize").toString())
		);
	}
	
	/**
	 * hql批量删除更新(hql类名不能有别名 where xx in(?,?,?,?))
	 * 
	 * @param hql
	 * @param array
	 */
	public int bulkUpdateOrDelete(String hql, Object[] condition) {
		Query query = getSession().createQuery(hql);
		query = setParameters(query, condition);
		return query.executeUpdate();
	}

	/**
	 * hql批量删除更新(重载1)
	 * 
	 * @param hql
	 *            map
	 */
	public int bulkUpdateOrDelete(String hql, Map condition) {
		return bulkUpdateOrDelete(hql, condition.values().toArray());
	}

	/**
	 * hql批量删除更新(重载2)
	 * 
	 * @param hql
	 *            ，List
	 */
	public int bulkUpdateOrDelete(String hql, List condition) {
		return bulkUpdateOrDelete(hql, condition.toArray());
	}

	/**
	 * hql批量删除更新(hql类名不能有别名 wher id in(:condition)) 推荐使用此方法
	 * 
	 * @param hql
	 * @param array
	 */
	public int bulkUpdateOrDelete2(String hql, Object[] condition) {
		Query query = getSession().createQuery(hql);
		if (null != condition && condition.length > 0) {
			query.setParameterList("condition", condition);
		}
		return query.executeUpdate();
	}

	/**
	 * hql批量删除更新(hql类名不能有别名 wher id in(:condition))
	 * 
	 * @param hql
	 * @param array
	 */
	public int bulkUpdateOrDelete2(String hql, Map condition) {
		return bulkUpdateOrDelete2(hql,condition.values().toArray());
	}

	/**
	 * hql批量删除更新(hql类名不能有别名 wher id in(:condition))
	 * 
	 * @param hql
	 * @param array
	 */
	public int bulkUpdateOrDelete2(String hql, List condition) {
		return bulkUpdateOrDelete2(hql,condition.toArray());
	}
	/**
	 * 根据传入id集合批量加载对象集合
	 *     hql：wher id in(:idList)
	 */
	public List bulkFindByIdList(String hql,List idList){
		return bulkFindByIdList(hql,idList.toArray());
	}
	/**
	 * 根据传入id集合批量加载对象集合(重载)
	 *     hql：wher id in(:idList)
	 */
	public List bulkFindByIdList(String hql,Object[] idList){
		Query query = getSession().createQuery(hql);
		if (null != idList && idList.length > 0) {
			query.setParameterList("idList", idList);
		}
		return query.list();
	}
	/**
	 * 根据给定Id判定某个实体是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean has(K id) {
		if (id == null || id.equals("")) {
			return false;
		}
		return (get(id) != null);
	}
	
	/**
	 * 获取当前PO对应表的总记录数(HQL方式)
	 */
	public Long getTotalCountForCurrentPO(){
		return (Long)getSession().createQuery(this.HQL_COUNT_ALL).uniqueResult();
	}
	
	/**
	 * DetachedCriteria方式查询总记录数
	 */
	public Long getTotalCountByCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		//清除查询条件，回收资源
		criteria.setProjection(null);
		return totalCount;
	}
	
	/**
	 * 
	 *方法摘要：DetachedCriteria方式查询总记录数(重载)
	 * @param target           目标对象
	 * @param targetClass      目标对象class类型
	 * @param propertyNames    属性名称集合
	 * @param matcheTypes      每个属性的检索匹配模式
	 *@return Long
	 */
	public Long getTotalCountByCriteria(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes){
		DetachedCriteria detachedCriteria = DetachedCriteriaUtils.addCondition(target,targetClass, propertyNames, matcheTypes);
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		Long totalCount = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		return totalCount;
	}

	/**
	 * 根据HQL查询数据总条数
	 * 
	 * @param hql
	 * @param condition
	 * @return
	 */
	public Long getTotal(String hql) {
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	/**
	 * 根据HQL查询数据总条数(带参)
	 * 
	 * @param hql
	 * @param condition
	 * @return
	 */
	public Long getTotal(String hql, Object[] condition) {
		Query query = getSession().createQuery(hql);
		setParameters(query, condition);
		return (Long)query.uniqueResult();
	}

	/**
	 * 设置参数
	 * 
	 * @param query
	 * @param condition
	 * @return Query
	 */
	private Query setParameters(Query query, Object[] condition) {
		if (null != condition && condition.length != 0) {
			for (int i = 0; i < condition.length; i++) {
				query.setParameter(i, condition[i]);
			}
		}
		return query;
	}

	/************************************** 华丽的分割线 begin ********************************************/
	/************************************* JDBC原生态sql封装 ******************************************/
	/************************************** 华丽的分割线 end *********************************************/

	/**
	 * 适用于增删改操作(不推荐使用此方法，因为此方法会破坏Hibernate的二级缓存体系)
	 * 
	 * @param sql
	 *            例如 delete from tableName where id = ?
	 * @param condition
	 * @return 是否执行成功 true/false
	 */
	public boolean excuteUpdate(String sql, Object[] condition) {
		return getJdbcTemplate().update(sql, condition) > 0;
	}

	/**
	 * 返回单列值（适用于查询总数、平均数、最大值、最小值）
	 * 
	 * @param sql
	 *            例如：select count(*) from user where age > ?
	 * @param condition
	 * @return
	 */
	public int queryForInt(String sql, Object[] condition) {
		return getJdbcTemplate().queryForInt(sql, condition);
	}

	/**
	 * 返回单个对象(适用于主键查询)
	 * 
	 * @param sql
	 *            例如： select * from user where id = ?
	 * @param condition
	 * @return
	 */
	public T queryForObject(String sql, Object[] condition) {
		return (T) getJdbcTemplate()
				.queryForObject(sql, condition, entityClass);
	}

	/**
	 * 返回对象集合
	 * 
	 * @param sql
	 * @param condition
	 * @return
	 */
	public List<T> queryForList(String sql, Object[] condition) {
		return (List<T>) getJdbcTemplate().queryForList(sql, condition,
				entityClass);
	}

	/**
	 * JDBC分页查询
	 * 
	 * @param sql
	 *            初始sql
	 * @param condition
	 *            查询参数
	 * @param currentPage
	 *            当前第几页
	 * @param pageSize
	 *            每页大小
	 * @return
	 */
	public PageData queryForPage(String sql, Object[] condition,
			int currentPage, int pageSize, String dataBaseName) {
		// 总记录数
		int total = queryForInt(getTotalSql(sql), null);
		if (total <= 0) {
			return null;
		}
		String pageSql = getPageSql(sql, currentPage, pageSize, dataBaseName);
		List list = getJdbcTemplate().queryForList(pageSql, condition);
		return new PageData(list, total, currentPage, pageSize);
	}

	/**
	 * 获取分页sql
	 * 
	 * @param sql
	 *            初始sql
	 * @param currentPage
	 *            当前页�
	 * @param pageSize
	 *            每页大小
	 * @param dataBaseName
	 *            数据库类型:sqlserver/mysql/oracle
	 * @return sql
	 */
	private String getPageSql(String sql, int currentPage, int pageSize,
			String dataBaseName) {
		if (null == sql || sql.equals("")) {
			return "";
		}

		StringBuffer stringBuffer = new StringBuffer();
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (dataBaseName.toLowerCase().equals("sqlserver")) {
			stringBuffer
				.append("select top ")
				.append(pageSize)
				.append(" * from (")
				.append(sql).append(") as a where a.id > (select MAX(t.id) from (")
				.append("select top ").append(pageSize * currentPage).append(" id")
				.append(" from (").append(sql).append(") order by id) as t )").append(" order by a.id");
		} else if (dataBaseName.toLowerCase().equals("mysql")) {
			stringBuffer.append("select * from (").append(sql).append(
					") limit ").append((currentPage - 1) * pageSize)
					.append(",").append(pageSize);
		} else if (dataBaseName.toLowerCase().equals("oracle")) {
			stringBuffer.append("select * from(").append(
					"select a.*,rownum r from(").append(sql).append(
					") as a where r <=").append(currentPage * pageSize).append(
					") where r >").append((currentPage - 1) * pageSize);
		}
		return stringBuffer.toString();
	}

	/**
	 * 获取查询总记录数sql
	 * 
	 * @param hql
	 *            初始sql
	 * @return
	 */
	private String getTotalSql(String sql) {
		return "select count(*) from (" + sql + ")";
	}

	/**
	 * 获取查询总记录数的hql
	 * 
	 * @param hql
	 *            初始hql
	 */
	private String getTotalHql(String hql) {
		String s = removeSubSelect(hql);
		int from = s.indexOf("from"); 
		return "select count(*) " + s.substring(from);
	}
	
	/**
	 * 移除key指定的子句，如order by,group by
	 */
	private String removeByKey(String hql,String key){
		int index = hql.indexOf(key.toLowerCase());
		if(index == -1){
			return hql;
		}
		return hql.replace(hql.substring(index),"");
	}
	
	/**
	 * 移除distinct关键字
	 */
	private String removeDistinct(String hql){
		if(hql.indexOf("distinct") == -1){
			return hql;
		}
		return hql.replace("distinct","");
	}
	
	/**
	 * 移除select部分子查询
	 * @param hql 传入的hql语句
	 */
	private String removeSubSelect(String hql){
		String regex = "[^in\\s]+\\([^)]*([^(^)]*?)\\)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(hql);
		while(m.find()){
			String sub = m.group();
			hql = hql.replace(sub, "");
 		}
		return hql;
	}
}
