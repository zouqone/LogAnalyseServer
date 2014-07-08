package cn.log.tool.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * 反射工具类
 * 
 * @author Lanxiaowei
 * @createTime 2012-4-30 下午05:59:24
 */
public class ReflectionUtils {
	static {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		ConvertUtils.register(dc, Date.class);
	}

	/**
	 * 调用Getter方法
	 */
	public static Object invokeGetterMethod(Object target, String propertyName) {
		String getterMethodName = "get" + org.apache.commons.lang3.StringUtils.capitalize(propertyName);
		return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
	}
	
	/**
	 * 调用Getter方法
	 */
	public static Object invokeGetMethod(Object target, String methodName) {
		return invokeMethod(target, methodName, new Class[] {}, new Object[] {});
	}

	/**
	 * 调用Setter方法.使用value的Class来查找Setter方法.
	 */
	public static void invokeSetterMethod(Object target, String propertyName, Object value) {
		invokeSetterMethod(target, propertyName, value, null);
	}

	/**
	 * 调用Setter方法.
	 * 
	 * @param propertyType
	 *            用于查找Setter方法,为空时使用value的Class替代
	 */
	public static void invokeSetterMethod(Object target, String propertyName, Object value, Class<?> propertyType) {
		Class<?> type = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + org.apache.commons.lang3.StringUtils.capitalize(propertyName);
		invokeMethod(target, setterMethodName, new Class[] { type }, new Object[] { value });
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("直接读取对象属性值出现异常", e);
		}
		return result;
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("直接设置对象属性值出现异常", e);
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 */
	public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);

		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}
		method.setAccessible(true);
		try {
			return method.invoke(object, parameters);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		return convertReflectionExceptionToUnchecked(null, e);
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception(重载)
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(String desc, Exception e) {
		desc = (desc == null) ? "Unexpected Checked Exception." : desc;
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException || e instanceof NoSuchMethodException) {
			return new IllegalArgumentException(desc, e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException(desc, ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(desc, e);
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod. 若向上转型到Object仍无法找到, 返回null.
	 */
	protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		if (null == object) {
			return null;
		}
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {// NOSONAR
				// Method不在当前类定义,继续向上转型
				continue;
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField. 若向上转型到Object仍无法找到, 返回null.
	 */
	protected static Field getDeclaredField(final Object object, final String fieldName) {
		if (null == object || null == fieldName || fieldName.equals("")) {
			return null;
		}
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
				continue;
			}
		}
		return null;
	}

	/**
	 * 强行设置Field可访问
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射, 获得定义Class时声明的父类的泛型参数的类型 若无法找到, 返回Object.class 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 * @param index
	 *            父类泛型参数的索引，从0开始计算
	 * @return Class 返回父类index位置的泛型参数的class
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 通过反射, 获得定义Class时声明的父类的泛型参数的类型， 默认index等于0 若无法找到, 返回Object.class
	 * 
	 * @param clazz
	 * @return Class 返回父类index位置的泛型参数的class
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 提取集合中的对象的某属性的属性值(通过getter函数), 组合成List.
	 * 
	 * @param collection
	 *            数据集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();
		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
		return list;
	}
	
	/**
	 * 提取集合中的对象的某属性的属性值(通过getter函数), 组合成数组.
	 * 
	 * @param collection
	 *            数据集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static Object[] convertElementPropertyToArray(final Collection collection, final String propertyName) {
		Object[] arrays = new Object[collection.size()];
		try {
			int index = 0;
			for (Object obj : collection) {
				arrays[index] = PropertyUtils.getProperty(obj, propertyName);
				index++;
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
		return arrays;
	}

	/**
	 * 提取集合中的对象的某属性的属性值(通过getter函数), 用指定的分割符分隔组成字符串.
	 * 
	 * @param collection
	 *            数据集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return org.apache.commons.lang3.StringUtils.join(list, separator);
	}
	
	/**
	 * 提取集合中的对象的某属性的属性值(通过getter函数), 
	 * 用指定的分割符分隔组成字符串，默认分隔符是逗号
	 * 
	 * @param collection
	 *            数据集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(final Collection collection, final String propertyName) {
		return convertElementPropertyToString(collection,propertyName,",");
	}
	
	/**
	 *方法摘要：检测某对象是否包含某属性
	 *@param propertyName  属性名称
	 *@return boolean 是否包含
	 */
	public static boolean hasThisField(Object target,String propertyName){
		Field field = getDeclaredField(target, propertyName);
		if (null == field) {
			return false;
		}
		return true;
	}

	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param value  待转换的字符串
	 * @param toType 转换目标类型
	 */
	@SuppressWarnings("unchecked")
	public static <T>T convertStringToObject(String value, Class<T> toType) {
		try {
			return (T) ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}
}
