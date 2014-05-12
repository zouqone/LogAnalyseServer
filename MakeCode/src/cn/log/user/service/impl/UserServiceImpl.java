/**
 * 
 */
package cn.log.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import cn.log.user.dao.IUserDao;
import cn.log.user.dao.impl.UserDaoImpl;
import cn.log.user.service.IUserService;
import cn.log.user.vo.UserVo;

/**
 * @author zouqone
 * @see UserService接口实现
 */
public class UserServiceImpl implements IUserService {

	/**
	 * UserDao接口
	 */
	private IUserDao userDao ;
	
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		//userDao = new UserDaoImpl();
	}
	

	/**
	 * @return the userDao
	 */
	public IUserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


	/* (non-Javadoc)
	 * @see cn.log.user.service.IUserService#getAllUserVo()
	 */
	@Override
	public List<UserVo> getAllUserVo() {
		// TODO Auto-generated method stub
		
		return userDao.queryAllUserVo();
	}

	/* (non-Javadoc)
	 * @see cn.log.user.service.IUserService#getUserVoById(java.lang.Integer)
	 */
	@Override
	public UserVo getUserVoById(Integer id) {
		// TODO Auto-generated method stub
		String condition = " where id = \'"+id+"\'"; 
		List<UserVo> userList = userDao.queryUserVoByCondition(condition);
		if(userList!=null){
			return userList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.user.service.IUserService#updateUserVo(cn.log.user.vo.UserVo)
	 */
	@Override
	public boolean updateUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.updateUserVo(userVo);
	}

	/* (non-Javadoc)
	 * @see cn.log.user.service.IUserService#updateUserVo(java.util.List)
	 */
	@Override
	public List<Object> updateUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		return userDao.updateUserVo(userVoList);
	}

	/* (non-Javadoc)
	 * @see cn.log.user.service.IUserService#createUserVo(cn.log.user.vo.UserVo)
	 */
	@Override
	public String createUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		Integer id = userDao.insertUserVo(userVo);
		if(id!=null){
			return id.toString();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.user.service.IUserService#deleteUserVo(java.lang.String)
	 */
	@Override
	public boolean deleteUserVo(Integer id) {
		// TODO Auto-generated method stub
		return userDao.deleteUserVoByParam("id", id.toString(), "=");
	}

	@Override
	public UserVo getUserVoByName(String username) {
		// TODO Auto-generated method stub
		String condition = " where username = \'"+username+"\'"; 
		List<UserVo> userList = userDao.queryUserVoByCondition(condition);
		if(userList!=null&&userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<UserVo> getUserVoListByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		List<UserVo> userVoList = new ArrayList<UserVo>();
		if(ids!=null&&ids.length>0){
			for (Integer id : ids) {
				UserVo userVo = getUserVoById(id);
				userVoList.add(userVo);
			}
		}
		return userVoList;
	}

	@Override
	public List<UserVo> getUserVoListByIds(List<Integer> idList) {
		// TODO Auto-generated method stub
		List<UserVo> userVoList = new ArrayList<UserVo>();
		if(idList!=null&&idList.size()>0){
			for (Integer id : idList) {
				UserVo userVo = getUserVoById(id);
				userVoList.add(userVo);
			}
		}
		return userVoList;
	}

	@Override
	public List<UserVo> queryByCondition(String condition) {
		// TODO Auto-generated method stub
		//String condition = " where username = \'"+username+"\'"; 
		if(condition==null || condition.equals("")){
			return null;
		}
		List<UserVo> userList = userDao.queryUserVoByCondition(condition);
		if(userList!=null&&userList.size()>0){
			return userList;
		}
		return null;
	}

	@Override
	public Integer getUserVoTotalNumber() {
		// TODO Auto-generated method stub
		Integer number = userDao.queryUserVoTotalNumber();
		return number;
	}

	/**
	 * 连接h2数据库登录
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public boolean login(String username,String password){
		boolean res = false;
		if(username!=null&&password !=null){
			//IUserService service = new UserServiceImpl();
			//UserVo userVo = service.getUserVoByName(username);
			UserVo userVo = getUserVoByName(username);
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
	@Override
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
