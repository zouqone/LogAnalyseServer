package cn.log.function.dao;

import java.util.List;

import cn.log.function.vo.FunctionVo;


/**
 * @author zouqone
 * @see Function接口
 */
public interface IFunctionDao {

	/**
	 * 查询所有的Function
	 * @return List<FunctionVo> Function列表
	 */
	public List<FunctionVo> queryAllFunctionVo();
	
	/**
	 * 根据查询条件查询Function
	 * @param condition 查询条件
	 * @return List<FunctionVo> Function列表
	 */
	public List<FunctionVo> queryFunctionVoByCondition(String condition);
	
	/**
	 * 插入一个Function
	 * @param functionVo
	 * @return functionId
	 */
	public Integer insertFunctionVo(FunctionVo functionVo);
	
	/**
	 * 插入function列表
	 * @param functionVoList
	 * @return
	 */
	public List<Integer> insertFunctionVo(List<FunctionVo> functionVoList);
	
	/**
	 * 修改一个function类型
	 * @param functionVo
	 * @return
	 */
	public boolean updateFunctionVo(FunctionVo functionVo);
	
	/**
	 * 修改function类型列表
	 * @param functionVoList
	 * @return
	 */
	public List<Object> updateFunctionVo(List<FunctionVo> functionVoList);
	
	/**
	 * 通过条件删除FunctionVo
	 * @param paramName 参数名
	 * @param paramValue 参数值
	 * @param paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @return 
	 */
	public boolean deleteFunctionVoByParam(String paramName,String paramValue,String paramOption);
	
	/**
	 * 批量删除FunctionVo
	 * @param paramList List<String[] paramName 参数名  ,paramValue 参数值 , paramOption 参数操作符 = ,< , > ,!= ,>=,<= 等等
	 * @return
	 */
	public List<Object> deleteFunctionVoByParamList(List<String[]> paramList);
	
	/**
	 * 通过条件删除function
	 * @param condition
	 * @return
	 */
	public boolean deleteFunctionVoByCondition(String condition);
}
