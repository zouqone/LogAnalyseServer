/**
 * 
 */
package cn.log.test.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zouqone
 * @date 2014年6月28日 下午7:41:46
 */
public class TestProxy {

	public static String xml = null;
	public static ApplicationContext context = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		autoProxy(); //动态代理模式
		
	}
	
	/**
	 * 动态代理模式
	 */
	public static void autoProxy(){
		xml = "cn/log/test/proxy/applicationContext-proxy.xml";
		context = new ClassPathXmlApplicationContext(xml);
		//得到代理类的Bean（是Spring中提供的（已经在配置文件中实现了Subject接口））
		Subject subject = (Subject) context.getBean("proxy");
		subject.request();//实现真实的方法（同时代理也完成了！）
		
	}

}
