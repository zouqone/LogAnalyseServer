/**
 * 
 */
package cn.log.test.proxy.impl;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author zouqone
 * @date 2014年6月28日 下午7:14:11
 */
public class ProxySubjectBeforeAdvice implements MethodBeforeAdvice {

	/* (non-Javadoc)
	 * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws Throwable
	 */
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("代理之前的任务～～～～～");
	}

}
