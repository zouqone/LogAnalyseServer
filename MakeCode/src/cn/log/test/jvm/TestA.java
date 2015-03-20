/**
 * 
 */
package cn.log.test.jvm;

/**
 * @author zouqone
 * @date 2015年3月15日 下午11:57:57
 */
public class TestA {

	public String byn ;
	
	/**
	 * 
	 */
	public TestA() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		shuxianhua();
		
		
	}
	
	protected int gets(){
		return 34;
	}

	/**
	 *  
	 */
	public static void calgrid(){
		int mount = 6;
		
		System.out.println(getGrids(mount));
		
		
	}
	
	public static int getGrids(int n){
		if(n==1 || n==2){
			return 1;
		}
		return getGrids(n-1)+getGrids(n-2);
	}
	
	public static void test2(){
		int start = 101 , end = 200;
		int [] arr = new int[200/2];
		int len = 0;
		for(int i = start ; i <= end ; i++){
			if(isShu(i)){
				arr[len++] = i;
				System.out.print(i +" ");
			}
		}
		System.out.println();
		System.out.println(len);
		
		
	}
	
	public  static Boolean isShu(int num){
		Boolean flag = true;
		
		int se = 2 ,ee = (int) Math.floor( Math.sqrt(num));
		for(int k = se; k<=ee ; k++){
			if(num%k==0){
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	public static void shuxianhua(){
		int a,b,c;
		int num ;
		for(int i = 100; i < 1000 ; i++){
			a = i/100;
			//b = i/10%10;
			b = (i%100)/10;
			c = i%10;
			num = (int) (Math.pow(a, 3)+Math.pow(b, 3)+Math.pow(c, 3));
			if(i == num){
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}
	
}
