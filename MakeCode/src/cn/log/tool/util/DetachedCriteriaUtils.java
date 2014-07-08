/**
 * 
 */
package cn.log.tool.util;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


/**
 * @author zouqone
 *
 */
public class DetachedCriteriaUtils {
	/**
	 * 设置离线查询条件(重载)
	 * @param target         参数数据模型对象
	 * @param propertyNames  属性名称数组，即哪些属性需要纳入查询条件
	 * @param matcheTypes    每个属性对应的检索匹配模式，与propertyNames元素放置顺序应保持一致
	 */
	public static DetachedCriteria addCondition(Object target,String[] propertyNames,MatcheType[] matcheTypes){
		return addCondition(target, null, propertyNames, matcheTypes);
	}
	
	/**
	 * 设置离线查询条件
	 * @param target         参数数据模型对象
	 * @param targetClass    目标对象class类型
	 * @param propertyNames  属性名称数组，即哪些属性需要纳入查询条件
	 * @param matcheTypes    每个属性对应的检索匹配模式，与propertyNames元素放置顺序应保持一致
	 */
	public static DetachedCriteria addCondition(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes){
		return addCondition(target,targetClass,propertyNames,matcheTypes,null,null);
	}
	
	/**
	 * 设置离线查询条件
	 * @param target         参数数据模型对象
	 * @param targetClass    目标对象class类型
	 * @param propertyNames  属性名称数组，即哪些属性需要纳入查询条件
	 * @param matcheTypes    每个属性对应的检索匹配模式，与propertyNames元素放置顺序应保持一致
	 * @param sorts          排序字段
	 * @param dirs           排序规则 ASC / DESC
	 */
	public static DetachedCriteria addCondition(Object target,Class targetClass,String[] propertyNames,MatcheType[] matcheTypes,String[] sorts,String[] dirs){
		if(null == target ||
				   null == propertyNames || 
				   null == matcheTypes ||
				   propertyNames.length == 0 ||
				   matcheTypes.length == 0 || 
				   propertyNames.length != matcheTypes.length){
					return null;
				}
				DetachedCriteria detachedCriteria = null;
				if(null == targetClass){
					detachedCriteria = DetachedCriteria.forClass(target.getClass());
				} else{
					detachedCriteria = DetachedCriteria.forClass(targetClass);
				}
				
				for(int i = 0; i < propertyNames.length; i++){
					Criterion criterion = null;
					if(MatcheType.ASSOCIATE_EQ.equals(matcheTypes[i])){
						String[] proNames = propertyNames[i].split("\\.");
						if(null != proNames && proNames.length > 1){
							
							//获取关联对象
							Object associateObject = ReflectionUtils.invokeGetterMethod(target, proNames[0]);
							//获取关联对象的属性值
							Object associateValue = ReflectionUtils.getFieldValue(associateObject, proNames[1]);
							criterion = getCriterion(propertyNames[i],associateValue,matcheTypes[i]);
							if(null != criterion){
								detachedCriteria.createAlias(proNames[0], proNames[0]);
							}
						}
					} else{
						if(MatcheType.BETWEEN.equals(matcheTypes[i])){
							if(ReflectionUtils.hasThisField(target, propertyNames[i]+"From") && 
							    ReflectionUtils.hasThisField(target, propertyNames[i]+"To")){
								criterion = getCriterion(
									propertyNames[i],
									new Object[]{
										ReflectionUtils.getFieldValue(target, propertyNames[i]+"From"),
										ReflectionUtils.getFieldValue(target, propertyNames[i]+"To"),
									},
									matcheTypes[i]
								);
							}
							
						} else if(MatcheType.IN.equals(matcheTypes[i]) ||
								  MatcheType.NOTIN.equals(matcheTypes[i])){
							if(ReflectionUtils.hasThisField(target, propertyNames[i]+"Array")){
								criterion = getCriterion(
									propertyNames[i],
									ReflectionUtils.getFieldValue(target, propertyNames[i] + "Array"),
									matcheTypes[i]
								);
							} else if(ReflectionUtils.hasThisField(target, propertyNames[i]+"List")){
								criterion = getCriterion(
									propertyNames[i],
									ReflectionUtils.getFieldValue(target, propertyNames[i] + "List"),
									matcheTypes[i]
								);
							}
							
						} else{
							criterion = getCriterion(
								propertyNames[i],
								ReflectionUtils.getFieldValue(target, propertyNames[i]),
								matcheTypes[i]
							);
						}
						
					}
					if(null != criterion){
						detachedCriteria.add(criterion);
					}
				}
				//设置排序规则
				if(!StringUtils.isEmpty(sorts) && !StringUtils.isEmpty(dirs) && dirs.length == sorts.length){
					for (int j = 0; j < sorts.length; j++) {
						if(null != dirs[j] && null != sorts[j]){
							if(dirs[j].toLowerCase().equals("desc")){
								detachedCriteria.addOrder(Order.desc(sorts[j]));  
							} else if(dirs[j].toLowerCase().equals("asc")){
								detachedCriteria.addOrder(Order.asc(sorts[j]));
							}
						}
					}
				} else{
					detachedCriteria.addOrder(Order.asc("id")); //默认ID升序排序
				}
				return detachedCriteria;
	}
	
	/**
	 * 根据属性的检索匹配模式调用具体的Restriction方法
	 * @param propertyName   属性名称
	 * @param value          属性值
	 * @param matcheType     属性的HQL检索匹配模式
	 */
	private static Criterion getCriterion(String propertyName,Object value,MatcheType matcheType){
		Criterion criterion = null;
		if(!StringUtils.isEmpty(value)){
			if(MatcheType.EQUAL.equals(matcheType) || MatcheType.ASSOCIATE_EQ.equals(matcheType)){
				criterion = Restrictions.eq(propertyName, value);
			} else if(MatcheType.NOTEQUAL.equals(matcheType)){
				criterion = Restrictions.not(Restrictions.eq(propertyName, value));
			} else if(MatcheType.LIKE.equals(matcheType) || MatcheType.ASSOCIATE_LIKE.equals(matcheType)){
				criterion = Restrictions.like(propertyName, value.toString(), MatchMode.ANYWHERE);
			} else if(MatcheType.LLIKE.equals(matcheType)){
				criterion = Restrictions.like(propertyName, "%" + value);
			} else if(MatcheType.RLIKE.equals(matcheType)){
				criterion = Restrictions.like(propertyName, value + "%");
			} else if(MatcheType.GE.equals(matcheType)){
				criterion = Restrictions.ge(propertyName, value);
			} else if(MatcheType.lE.equals(matcheType)){
				criterion = Restrictions.le(propertyName, value);
			} else if(MatcheType.GT.equals(matcheType)){
				criterion = Restrictions.gt(propertyName, value);
			} else if(MatcheType.lT.equals(matcheType)){
				criterion = Restrictions.lt(propertyName, value);
			} else if(MatcheType.BETWEEN.equals(matcheType)){
				Object[] objects = (Object[])value;
				if(null != objects[0] && null != objects[1]){
					criterion = Restrictions.between(propertyName, objects[0], objects[1]);
				}
			} else if(MatcheType.IN.equals(matcheType)){
				if(value.getClass().isArray() && !GerneralUtils.haveNullElement(value)){
					criterion = Restrictions.in(propertyName, (Object[])value);
				} else if(value instanceof Collection && !GerneralUtils.haveNullElement(value)){
					criterion = Restrictions.in(propertyName, (Collection)value);
				}
			} else if(MatcheType.NOTIN.equals(matcheType)){
				if(value.getClass().isArray() && !GerneralUtils.haveNullElement(value)){
					criterion = Restrictions.in(propertyName, (Object[])value);
				} else if(value instanceof Collection && !GerneralUtils.haveNullElement(value)){
					criterion = Restrictions.in(propertyName, (Collection)value);
				}
				if(null != criterion){
					criterion = Restrictions.not(criterion);
				}
			} else if(MatcheType.ISNULL.equals(matcheType)){
				criterion = Restrictions.isNull(propertyName);
			} else if(MatcheType.ISNOTNULL.equals(matcheType)){
				criterion = Restrictions.not(Restrictions.isNull(propertyName));
			}
		}
		return criterion;
	}
}
