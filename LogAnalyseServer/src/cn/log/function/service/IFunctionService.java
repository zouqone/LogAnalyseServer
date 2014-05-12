
package cn.log.function.service;

import java.util.List;

import cn.log.function.vo.FunctionVo;


/**
 * @author zouqone
 * @see IFunctionService接口
 */
public interface IFunctionService {

	/**
	 * 查询出所有的FunctionVo
	 * @return List<FunctionVo>
	 */
	public List<FunctionVo> getAllFunctionVo();
	
	/**
	 * 通过id获取functionVo
	 * @param id
	 * @return
	 */
	public FunctionVo getFunctionVoById(Integer id);
	
	
	/**
	 * 通过条件查询出所有的FunctionVo
	 * @param functionVo
	 * @return
	 */
	public List<FunctionVo> getFunctionVoByCondition(FunctionVo functionVo);
	
	/**
	 * 修改FunctionVo
	 * @param controlType
	 * @return
	 */
	public boolean updateFunctionVo(FunctionVo functionVo);
	
	/**
	 * 修改FunctionVo列表
	 * @param functionVoList
	 * @return
	 */
	public List<Object> updateFunctionVo(List<FunctionVo> functionVoList);
	
	/**
	 * 创建FunctionVo
	 * @param functionVo
	 * @return
	 */
	public String createFunctionVo(FunctionVo functionVo);
	
	/**
	 * 通过id删除应用
	 * @param id
	 * @return
	 */
	public boolean deleteFunctionVo(Integer id);
	
	/**
	 * 查询所有的子节点
	 * @param pCode
	 * @return
	 */
	public String queryFunctionByParentCode(String pCode);

	/**
	 * @param pCode
	 * @return
	 */
	public String queryCheckFunctionByParentCode(String pCode);
	
	
}
