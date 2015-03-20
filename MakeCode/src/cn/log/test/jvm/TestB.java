/**
 * 
 */
package cn.log.test.jvm;

/**
 * @author zouqone
 * @date 2015年3月15日 下午11:58:09
 */
public class TestB extends TestA{

	public String str = null;
	
	/**
	 * 
	 */
	public TestB() {
		// TODO Auto-generated constructor stub
		super();
		//this();
		super.byn = "23";
		this.str = "11";
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestB tv = new TestB();
		System.out.println(tv.str+" "+tv.byn);
	}
	protected int gets(){
		return 34;
	}
}
