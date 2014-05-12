/**
 * 
 */
package cn.log.user.service;

import java.util.List;

import cn.log.user.vo.UserVo;

/**
 * @author zouqone
 * @see IUserService接口
 */
public interface IUserService {

	/**
	 * 查询出所有的UserVo
	 * @return List<UserVo>
	 */
	public List<UserVo> getAllUserVo();
	
	/**
	 * 根据查询条件查询UserVo
	 * @param userVo
	 * @return
	 */
	public List<UserVo> queryByCondition(String condition);
	
	/**
	 * 通过ids获取UserVo
	 * @param ids
	 * @return
	 */
	public List<UserVo> getUserVoListByIds(Integer[] ids);
	
	/**
	 * 通过idList获取UserVo
	 * @param idList
	 * @return
	 */
	public List<UserVo> getUserVoListByIds(List<Integer> idList);
	
	/**
	 * 通过id获取UserVo
	 * @param id
	 * @return
	 */
	public UserVo getUserVoById(Integer id);
	
	/**
	 * 通过username获取UserVo
	 * @param username
	 * @return
	 */
	public UserVo getUserVoByName(String username);
	
	/**
	 * 修改UserVo
	 * @param controlType
	 * @return
	 */
	public boolean updateUserVo(UserVo userVo);
	
	/**
	 * 修改UserVo列表
	 * @param userVoList
	 * @return
	 */
	public List<Object> updateUserVo(List<UserVo> userVoList);
	
	/**
	 * 创建UserVo
	 * @param userVo
	 * @return
	 */
	public String createUserVo(UserVo userVo);
	
	/**
	 * 通过id删除应用
	 * @param id
	 * @return
	 */
	public boolean deleteUserVo(Integer id);
	
	/**
	 * 查询UserVo数目
	 * @return
	 */
	public Integer getUserVoTotalNumber();
	
	/**
	 * 连接h2数据库登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password);
	
	/**
	 * 从配置文件中读取配置信息
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginByFile(String username,String password);
}
