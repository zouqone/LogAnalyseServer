/**
 * 
 */
package cn.log.test.fun;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @author zouqone
 * @date 2015年3月18日 下午10:56:30
 */
public class StringCaculate {

	/**
	 * 数据栈
	 */
	private Stack<BigDecimal> numbers = new Stack<BigDecimal>();
	
	/**
	 * 运算符栈
	 */
	private Stack<Character> chs = new Stack<Character>();
	
	
	/**
	 * 
	 */
	public StringCaculate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringCaculate sc = new StringCaculate();
		String exp = "-1.5/2+(-1)";
		System.out.println(sc.cac(exp));
		//System.out.println(".".matches("[.]"));
	}


	public BigDecimal cac(String exp){
		//exp = "1/2";
		Stack<BigDecimal> numbers = new Stack<BigDecimal>();
		Stack<Character> chs = new Stack<Character>();
		chs.push('#');
		StringBuffer sb = new StringBuffer(exp);
		StringBuffer num = new StringBuffer();
		String tem = null;
		sb.append("#");
	
		String pre = null;
		while(sb.length()>0||chs.lastElement()!='#'){
			tem = sb.substring(0, 1).trim();
			
			if(isNum(tem)|| ( ("(".equals(pre)||pre==null) &&"+-".indexOf(tem)>-1 ) ){
				num.append(tem);
				sb.delete(0, 1);
				//pre = null;
			}else if(!"".equals(tem)){
				
				if(num.length()>0&&!"".equals(num)){
					BigDecimal bd = new BigDecimal(num.toString().trim());
					numbers.push(bd);
					num.delete(0, num.length());
				}
				if(!chs.isEmpty()){
					Character opt1 = chs.lastElement();
					Character opt2 = tem.charAt(0);
					if(opt1=='#'&&opt2=='#'){
						break;
					}
					Character pro = compareOpt(opt1,opt2).charAt(0);
					switch(pro){
					case '<' : chs.push(opt2);sb.delete(0, 1);break;
					case '=' : chs.pop();sb.delete(0, 1);break;
					case '>' : 
						BigDecimal num2 = numbers.pop();
						BigDecimal num1 = numbers.pop();//numbers.isEmpty()?new BigDecimal(0):numbers.pop();
						Character opt = chs.pop();
						BigDecimal res = caculate(num1, num2, opt);
						numbers.push(res);
						//chs.push(opt2);
						break;
					}
				}
				pre = tem;
			}
		}
		
		return numbers.pop();
	}
       
	private boolean isNum(String num){
		return num.matches("[0-9.]");
	}
	
	
	private BigDecimal caculate(BigDecimal num1,BigDecimal num2 , char opt){
		BigDecimal num = null;
		
		switch(opt){
		case '+': num = num1.add(num2);break;
		case '-': num = num1.subtract(num2);break;
		case '*': num = num1.multiply(num2);break;
		case '/':
			if(!num2.equals(new BigDecimal(0))){
				num = num1.divide(num2);
			}
			break;
		}
		return num;
	}
	
	private String compareOpt(char opt1 , char opt2){
		String flag = null;
		if("+-".indexOf(opt1)>-1){
			if("*/(".indexOf(opt2)>-1){
				flag = "<";
			}else{
				flag = ">";
			}
		}
		if("*/".indexOf(opt1)>-1){
			if("(".indexOf(opt2)>-1){
				flag = "<";
			}else{
				flag = ">";
			}
		}
		if("(".indexOf(opt1)>-1){
			if(")".indexOf(opt2)>-1){
				flag = "=";
			}else{
				flag = "<";
			}
		}
		if(")".indexOf(opt1)>-1){
			flag = ">";
		}
		if('#' == opt1){
			if('#' == opt2){
				flag = "=";
			}else{
				flag = "<";
			}
		}
		return flag;
	}
	
	

}
