/**
 * 
 */
package cn.log.test.proxy.impl;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;


/**
 * @author zouqone
 * @date 2014年6月28日 下午7:15:20
 */
public class ProxySubjectAfterAdvice implements AfterReturningAdvice {

	/* (non-Javadoc)
	 * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Throwable
	 */
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("在代理之后完成的任务～～");
	}

}
