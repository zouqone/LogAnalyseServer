package cn.sys.md.service;

import java.util.List;
import java.util.Map;

import cn.sys.md.vo.ComcategoryVo;


/**
 * @author zouqone
 * @date 
 * @Description： 组件分类
 */
public interface IComcategoryService {

	/*=======================查询query===========================*/
	/**
	 * 查询所有的comcategoryVo
	 * @return List<ComcategoryVo> comcategoryVo列表
	 */
	public List<ComcategoryVo> queryAllComcategoryVo();
	
	/**
	 * 根据查询条件查询user
	 * @param condition 查询条件
	 * @return List<ComcategoryVo> comcategoryVo列表
	 */
	public List<ComcategoryVo> queryComcategoryVoByCondition(String condition);
	
	/**
	 * 通过SQL查询出comcategoryVo相关结果集列报表
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryComcategoryVoBySql(String sql);
	
	/**
	 * 通过主键查询comcategoryVo
	 * @param comcategoryVo
	 * @return
	 */
	public ComcategoryVo find(ComcategoryVo comcategoryVo);
	
	
	/*=====================插入 insert==========================*/
	
	/**
	 * 插入一个comcategoryVo
	 * @param comcategoryVo
	 * @return comcategoryVoId
	 */
	public Object insertComcategoryVo(ComcategoryVo comcategoryVo);
	
	/**
	 * 插入user列表
	 * @param comcategoryVoList
	 * @return
	 */
	public List<Object> insertComcategoryVo(List<ComcategoryVo> comcategoryVoList);
	
	
	
	
	
	/*=====================修改update==========================*/
	
	/**
	 * 修改一个user类型
	 * @param comcategoryVo
	 * @return
	 */
	public Object updateComcategoryVo(ComcategoryVo comcategoryVo);
	
	/**
	 * 修改user类型列表
	 * @param comcategoryVoList
	 * @return
	 */
	public List<Object> updateComcategoryVo(List<ComcategoryVo> comcategoryVoList);
	
	
	/*=====================删除delete==========================*/
	/**
	 * 通过主键删除user
	 * @param comcategoryVo
	 * @return
	 */
	public Object deleteComcategoryVo(ComcategoryVo comcategoryVo);
	
	/**
	 * 通过条件删除user
	 * @param condition
	 * @return
	 */
	public Object deleteComcategoryVoByCondition(String condition);
	
	
	
	/*=====================获取数目==========================*/
	/**
	 * 查询ComcategoryVo数目
	 * @return
	 */
	public Integer queryComcategoryVoTotalNumber();
	
	
	/**
	 * 查询ComcategoryVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryComcategoryVoTotalNumber(String condition);
	
	
}
