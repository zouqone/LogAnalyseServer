/**
 * 
 */
package cn.log.test.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

/**
 * @author zouqone
 * @date 2015年3月15日 下午2:48:32
 */
public class JvmTest {

	/**
	 * 
	 */
	public JvmTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void test(){
		Object obj = new Object();
		WeakReference<Object>  wf = new WeakReference<Object>(obj);
		System.out.println(wf.get());
		obj = null;
		System.out.println(wf.get());
		System.out.println(wf.isEnqueued());
		
		
	}
	
	
	public static void testPhantom(){
		Object obj = new Object();
		PhantomReference<Object> pf = new PhantomReference<Object>(obj, null);
		
		System.out.println(pf.get());
		obj = null;
		System.out.println(pf.get());
		System.out.println(pf.isEnqueued());
		
	}
	
	public static void main(String [] args){
		
		testPhantom();
	}

}
