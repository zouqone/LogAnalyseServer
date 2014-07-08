package cn.log.tool.util;

import java.util.Date;

/**
 * 系统常量类
 * @author lanxiaowei
 *
 */
public class Globarle {
	public static final int PAGESIZE = 10;          //每页显示条数
	public static final int TOPAGE = 1;            //默认第一页
	public static final int BUFFER_SIZE = 20 * 1024; //默认缓冲区大小20KB
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd"; //缺省的日期格式
	public static final String SYS_CURRENTUSER = "currentUser";  //系统当前用户
	public static final String VALIDATE_CODE = "validateCode";      //验证码
	public static final String USER_RESOURCES = "USERRESOURCE";     //当前登录用户的所有权限
	public static final String DEFAULT_PASSWORD = "123456";         //初始默认密码
	
	public static final Date BASE_DATE = new Date(0);              //基准时间：1970年01月01日 00:00:00
	
	public static final String SERVICE_PACKAGE = "com.yida.framework.modules.service.";
	
	public static final String UPLOAD_DIR = "upload";
}
