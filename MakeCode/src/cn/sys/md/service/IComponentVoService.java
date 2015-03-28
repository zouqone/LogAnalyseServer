package cn.sys.md.service;

import java.util.List;
import java.util.Map;

import cn.sys.md.vo.ComponentVo;


/**
 * @author zouqone
 * @date 
 * @Description： 组件
 */
public interface IComponentVoService {

	/*=======================查询query===========================*/
	/**
	 * 查询所有的componentVo
	 * @return List<ComponentVo> componentVo列表
	 */
	public List<ComponentVo> queryAllComponentVo();
	
	/**
	 * 根据查询条件查询user
	 * @param condition 查询条件
	 * @return List<ComponentVo> componentVo列表
	 */
	public List<ComponentVo> queryComponentVoByCondition(String condition);
	
	/**
	 * 通过SQL查询出componentVo相关结果集列报表
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryComponentVoBySql(String sql);
	
	/**
	 * 通过主键查询componentVo
	 * @param componentVo
	 * @return
	 */
	public ComponentVo find(ComponentVo componentVo);
	
	
	/*=====================插入 insert==========================*/
	
	/**
	 * 插入一个componentVo
	 * @param componentVo
	 * @return componentVoId
	 */
	public Object insertComponentVo(ComponentVo componentVo);
	
	/**
	 * 插入user列表
	 * @param componentVoList
	 * @return
	 */
	public List<Object> insertComponentVo(List<ComponentVo> componentVoList);
	
	
	
	
	
	/*=====================修改update==========================*/
	
	/**
	 * 修改一个user类型
	 * @param componentVo
	 * @return
	 */
	public Object updateComponentVo(ComponentVo componentVo);
	
	/**
	 * 修改user类型列表
	 * @param componentVoList
	 * @return
	 */
	public List<Object> updateComponentVo(List<ComponentVo> componentVoList);
	
	
	/*=====================删除delete==========================*/
	/**
	 * 通过主键删除user
	 * @param componentVo
	 * @return
	 */
	public Object deleteComponentVo(ComponentVo componentVo);
	
	/**
	 * 通过条件删除user
	 * @param condition
	 * @return
	 */
	public Object deleteComponentVoByCondition(String condition);
	
	
	
	/*=====================获取数目==========================*/
	/**
	 * 查询ComponentVo数目
	 * @return
	 */
	public Integer queryComponentVoTotalNumber();
	
	
	/**
	 * 查询ComponentVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryComponentVoTotalNumber(String condition);
	
	
}
