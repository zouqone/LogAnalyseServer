package cn.log.app.user.service.impl;

import java.util.List;
import java.util.Map;

import cn.log.app.user.dao.IUserVoDao;
import cn.log.app.user.service.IUserVoService;
import cn.log.app.user.vo.UserVo;


;

/**
 * @author zouqone
 * @date 
 * @Description： 
 */
public class UserVoServiceImpl implements IUserVoService {
	
	private IUserVoDao userVoDao;

	/**
	 * @return the userVoDao
	 */
	public IUserVoDao getUserVoDao() {
		return userVoDao;
	}

	/**
	 * @param userVoDao the userVoDao to set
	 */
	public void setUserVoDao(IUserVoDao userVoDao) {
		this.userVoDao = userVoDao;
	}
	
	

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#queryAllUserVo()
	 */
	@Override
	public List<UserVo> queryAllUserVo() {
		// TODO Auto-generated method stub
		List<UserVo> userVos = userVoDao.queryAllUserVo();
		
		return userVos;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#queryUserVoByCondition(java.lang.String)
	 */
	@Override
	public List<UserVo> queryUserVoByCondition(String condition) {
		// TODO Auto-generated method stub
		List<UserVo> userVos = userVoDao.queryUserVoByCondition(condition);
		return userVos;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#queryUserVoBySql(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryUserVoBySql(String sql) {
		// TODO Auto-generated method stub
		List<Map> maps = userVoDao.queryUserVoBySql(sql);
		return maps;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#find(cn.log.app.user.vo.UserVo)
	 */
	@Override
	public UserVo find(UserVo userVo) {
		// TODO Auto-generated method stub
		UserVo vo = userVoDao.find(userVo);
		return vo;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#insertUserVo(cn.log.app.user.vo.UserVo)
	 */
	@Override
	public Object insertUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		Object object = userVoDao.insertUserVo(userVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#insertUserVo(java.util.List)
	 */
	@Override
	public List<Object> insertUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = userVoDao.insertUserVo(userVoList);
		return objects;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#updateUserVo(cn.log.app.user.vo.UserVo)
	 */
	@Override
	public Object updateUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		Object object = userVoDao.updateUserVo(userVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#updateUserVo(java.util.List)
	 */
	@Override
	public List<Object> updateUserVo(List<UserVo> userVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = userVoDao.updateUserVo(userVoList);
		return objects;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#deleteUserVo(cn.log.app.user.vo.UserVo)
	 */
	@Override
	public Object deleteUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		Object object = userVoDao.deleteUserVo(userVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#deleteUserVoByCondition(java.lang.String)
	 */
	@Override
	public Object deleteUserVoByCondition(String condition) {
		// TODO Auto-generated method stub
		Object object = userVoDao.deleteUserVoByCondition(condition);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.user.service.IUserVoService#queryUserVoTotalNumber()
	 */
	@Override
	public Integer queryUserVoTotalNumber() {
		// TODO Auto-generated method stub
		Integer count = queryUserVoTotalNumber(null);
		return count;
	}

	/**
	 * 查询UserVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryUserVoTotalNumber(String condition){
		Integer count = userVoDao.queryUserVoTotalNumber(condition);
		return count;
	}
	
}
