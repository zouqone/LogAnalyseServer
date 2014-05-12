/**
 * 
 */
package cn.log.analyse.service;

import java.util.Locale;
import java.util.ResourceBundle;

import cn.log.user.service.IUserService;
import cn.log.user.service.impl.UserServiceImpl;
import cn.log.user.vo.UserVo;

/**
 * @author zouqone
 *
 */
public class LoginService {

	/**
	 * 连接h2数据库登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password){
		boolean res = false;
		if(username!=null&&password !=null){
			IUserService service = new UserServiceImpl();
			UserVo userVo = service.getUserVoByName(username);
			if(userVo!=null&&password.equals(userVo.getPassword())){
				res = true;
			}else if(userVo==null || userVo.getUsername()==null){
				res = loginByFile(username,password);
			}
			
		}
		return res;
	}
	
	/**
	 * 从配置文件中读取配置信息
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginByFile(String username,String password){
		boolean res = false;
		String baseName = "auth";
		if(username!=null&&password !=null){
			ResourceBundle rb = ResourceBundle.getBundle(baseName,Locale.getDefault());
			String user = rb.getString("username");
			String pass = rb.getString("password");
			if(username.equals(user)&&password.equals(pass)){
				res = true;
			}
		}
		return res;
	}
	
}
