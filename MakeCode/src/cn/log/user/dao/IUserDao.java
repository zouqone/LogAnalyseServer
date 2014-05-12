/**
 * 数据库Dao接口层
 */
package cn.log.user.dao;

import java.util.List;

import cn.log.user.vo.UserVo;


/**
 * @author zouqone
 * @see User接口
 */
public interface IUserDao {

	/**
	 * 查询所有的User
	 * @return List<UserVo> User列表
	 */
	public List<UserVo> queryAllUserVo();
	
	/**
	 * 根据查询条件查询user
	 * @param condition 查询条件
	 * @return List<UserVo> User列表
	 */
	public List<UserVo> queryUserVoByCondition(String condition);
	
	/**
	 * 插入一个User
	 * @param userVo
	 * @return userId
	 */
	public Integer insertUserVo(UserVo userVo);
	
	/**
	 * 插入user列表
	 * @param userVoList
	 * @return
	 */
	public List<Integer> insertUserVo(List<UserVo> userVoList);
	
	/**
	 * 修改一个user类型
	 * @param userVo
	 * @return
	 */
	public boolean updateUserVo(UserVo userVo);
	
	/**
	 * 修改user类型列表
	 * @param userVoList
	 * @return
	 */
	public List<Object> updateUserVo(List<UserVo> userVoList);
	
	/**
	 * 通过条件删除UserVo
	 * @param paramName 参数名
	 * @param paramValue 参数值
	 * @param paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @return 
	 */
	public boolean deleteUserVoByParam(String paramName,String paramValue,String paramOption);
	
	/**
	 * 批量删除UserVo
	 * @param paramList List<String[] paramName 参数名  ,paramValue 参数值 , paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @return
	 */
	public List<Object> deleteUserVoByParamList(List<String[]> paramList);
	
	/**
	 * 通过条件删除user
	 * @param condition
	 * @return
	 */
	public boolean deleteUserVoByCondition(String condition);
	
	/**
	 * 查询UserVo数目
	 * @return
	 */
	public Integer queryUserVoTotalNumber();
}
