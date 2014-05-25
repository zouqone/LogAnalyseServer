/**
 * 
 */
package cn.log.tool.util;

/**
 * @author zouqone
 * @date 2014-5-18 下午2:20:26 
 * @Description: TODO
 */
public class ContextUtil {

	/**
	 * 获取classpath绝对路径
	 * @return
	 */
	public static String getClassPath(){
		String classPath = null;
		classPath = ContextUtil.class.getResource("/" + "").toString();
		classPath = classPath.substring(6); //截掉路径的”file:“前缀
		//System.out.println("ClassPath的绝对路径 = "+classPath); 
		return classPath;
	}
	
	/**
	 * 获取项目根目录
	 * @return
	 */
	public static String getWorkSpacePath(){
		String root = null;
		String classPath = getClassPath();
		root = getIndexFormRight(classPath,"/",4);
		return root;
	}
	
	public static String getIndexFormRight(String str,String s,Integer indexRight){
		if(indexRight == null||indexRight<0){
			indexRight = 1;
		}
		for(int i = 0 ; i <indexRight ; i++ ){
			int endIndex = str.lastIndexOf(s);
			str = str.substring(0,endIndex);
		}
		return str;
	}
}
