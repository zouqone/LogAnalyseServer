package cn.log.app.user.service;

import java.util.List;
import java.util.Map;

import cn.log.app.user.vo.UserVo;


/**
 * @author zouqone
 * @date 
 * @Description： 
 */
public interface IUserVoService {

	/*=======================查询query===========================*/
	/**
	 * 查询所有的userVo
	 * @return List<UserVo> userVo列表
	 */
	public List<UserVo> queryAllUserVo();
	
	/**
	 * 根据查询条件查询user
	 * @param condition 查询条件
	 * @return List<UserVo> userVo列表
	 */
	public List<UserVo> queryUserVoByCondition(String condition);
	
	/**
	 * 通过SQL查询出userVo相关结果集列报表
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryUserVoBySql(String sql);
	
	/**
	 * 通过主键查询userVo
	 * @param userVo
	 * @return
	 */
	public UserVo find(UserVo userVo);
	
	
	/*=====================插入 insert==========================*/
	
	/**
	 * 插入一个userVo
	 * @param userVo
	 * @return userVoId
	 */
	public Object insertUserVo(UserVo userVo);
	
	/**
	 * 插入user列表
	 * @param userVoList
	 * @return
	 */
	public List<Object> insertUserVo(List<UserVo> userVoList);
	
	
	
	
	
	/*=====================修改update==========================*/
	
	/**
	 * 修改一个user类型
	 * @param userVo
	 * @return
	 */
	public Object updateUserVo(UserVo userVo);
	
	/**
	 * 修改user类型列表
	 * @param userVoList
	 * @return
	 */
	public List<Object> updateUserVo(List<UserVo> userVoList);
	
	
	/*=====================删除delete==========================*/
	/**
	 * 通过主键删除user
	 * @param userVo
	 * @return
	 */
	public Object deleteUserVo(UserVo userVo);
	
	/**
	 * 通过条件删除user
	 * @param condition
	 * @return
	 */
	public Object deleteUserVoByCondition(String condition);
	
	
	
	/*=====================获取数目==========================*/
	/**
	 * 查询UserVo数目
	 * @return
	 */
	public Integer queryUserVoTotalNumber();
	
	
	/**
	 * 查询UserVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryUserVoTotalNumber(String condition);
	
	
}
