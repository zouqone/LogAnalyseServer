/**
 * 
 */
package cn.log.test.fun;

/**
 * @author zouqone
 * @date 2015年3月16日 下午11:18:32
 */
public class MethodTest {

	/**
	 * 
	 */
	public MethodTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test();
		
	}
	
	public static void test(){
		int m = 3;
		int [] arr = {3,4,2,8,9,1,32,6,14,5};
		int len = arr.length;
		int k = 0;
		print(arr);
		while(len>1){
			
			for(int i = 0 ; i< len; i++){
				k = (k+1)%3;
				int a = arr[i];
				if(k==0){
					int temp = arr[i];
					for(int j = i; j< len-1 ; j++){
						arr[j] = arr[j+1];
					}
					arr[len-1]=temp;
					//arr[len-1]=0;
					len--;
					i--;
					System.out.print(temp+" ");
				}
			}
			System.out.println();
			print(arr);
			
		}
		System.out.println();
		print(arr);
		
	}

	public static void print(int[] arr){
		if(arr!=null){
			for (int i : arr) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * 23*(33+45*2)/11
	 */
	public void evalue(){
		String exp = "23*(33+45*2)/11";
		DataStack ds = new DataStack();
		OptStack os = new OptStack();
		char[] expc = exp.toCharArray();
		for(int i = 0 ; i< expc.length;i++){
			char c = expc[i];
			
		}
		
		
	}
	
	public Boolean comparePre(String opt1,String opt2){
		Boolean flag = true;
		if("-+".indexOf(opt1)>-1){
			if("*/".indexOf(opt2)>-1){
				flag = false;
			}
		}
		
		return flag;
		
	}
	
	public class DataStack{
		private Integer data[] = new Integer[100];
		int len =100;
		int top = 0;
		public void push(Integer e){
			if(top<len-1){
				data[top++] = e;
			}
		}
		
		public Integer pop(){
			if(top>0){
				return data[--top];
			}else{
				return null;
			}
			
		}
	}
	
	public class OptStack{
		private String data[] = new String[100];
		int len =100;
		int top = 0;
		public void push(String e){
			if(top<len-1){
				data[top++] = e;
			}
		}
		
		public String pop(){
			if(top>0){
				return data[--top];
			}else{
				return null;
			}
			
		}
	}
	
	
}
