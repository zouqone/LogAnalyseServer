/**
 * 
 */
package cn.log.test.proxy.impl;

import cn.log.test.proxy.Subject;

/**
 * @author zouqone
 * @date 2014年6月28日 下午7:12:01
 */
public class RealSubject implements Subject {

	/* (non-Javadoc)
	 * @see cn.log.test.proxy.Subject#request()
	 */
	@Override
	public void request() {
		// TODO Auto-generated method stub
		System.out.println("真实的主题的实现的方法");
	}

}
