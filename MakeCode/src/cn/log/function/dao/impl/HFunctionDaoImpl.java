/**
 * 
 */
package cn.log.function.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.dbutils.QueryRunner;

import cn.log.db.util.JdbcUtils;
import cn.log.function.dao.IFunctionDao;
import cn.log.function.vo.FunctionVo;
import cn.log.tool.dao.impl.BaseDAOImpl;

/**
 * @author zouqone
 * @date 2014年6月28日 下午11:49:51
 */
public class HFunctionDaoImpl extends BaseDAOImpl<Integer,FunctionVo> implements IFunctionDao {

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#queryAllFunctionVo()
	 * @return
	 */
	@Override
	public List<FunctionVo> queryAllFunctionVo() {
		// TODO Auto-generated method stub
		List<FunctionVo> functionVos = null;
		StringBuffer sb=new StringBuffer();
		try {
			sb.append("from FunctionVo ");
			//System.out.println("查询数据，执行sql语句 : "+sb);
			functionVos=super.findByHql(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return functionVos;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#queryFunctionVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@Override
	public List<FunctionVo> queryFunctionVoByCondition(String condition) {
		// TODO Auto-generated method stub
		List<FunctionVo> functionVos = null;
		StringBuffer sb=new StringBuffer();
		try {
			sb.append("from FunctionVo ");
			sb.append(condition);
			//System.out.println("查询数据，执行sql语句 : "+sb);
			functionVos=super.findByHql(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return functionVos;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#insertFunctionVo(cn.log.function.vo.FunctionVo)
	 * @param functionVo
	 * @return
	 */
	@Override
	@Transactional
	public Integer insertFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		Integer num = null;
		//try{
			Serializable io = save(functionVo);
			num = Integer.valueOf(io.toString());
		//}catch(Exception e){
			//e.printStackTrace();
			//throw new RuntimeException();
		//}
		return num;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#insertFunctionVo(java.util.List)
	 * @param functionVoList
	 * @return
	 */
	@Override
	public List<Integer> insertFunctionVo(List<FunctionVo> functionVoList) {
		// TODO Auto-generated method stub
		if(functionVoList == null){
			return null;
		}
		
		List<Integer> stateList = new ArrayList<Integer>();
		for (FunctionVo functionVo : functionVoList) {
			Integer state = insertFunctionVo(functionVo);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#updateFunctionVo(cn.log.function.vo.FunctionVo)
	 * @param functionVo
	 * @return
	 */
	@Override
	public boolean updateFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		boolean bo=true;
		try {
			super.merge(functionVo);
		}catch (Exception e) {
			bo=false;
		}
		return bo;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#updateFunctionVo(java.util.List)
	 * @param functionVoList
	 * @return
	 */
	@Override
	public List<Object> updateFunctionVo(List<FunctionVo> functionVoList) {
		// TODO Auto-generated method stub
		if(functionVoList == null){
			return null;
		}
		
		List<Object> stateList = new ArrayList<Object>();
		for (FunctionVo functionVo : functionVoList) {
			boolean state = updateFunctionVo(functionVo);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#deleteFunctionVoByParam(java.lang.String, java.lang.String, java.lang.String)
	 * @param paramName
	 * @param paramValue
	 * @param paramOption
	 * @return
	 */
	@Override
	public boolean deleteFunctionVoByParam(String paramName, String paramValue,
			String paramOption) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		String sql = "delete from Function where "+paramName+" "+paramOption+" ? ";
		Object params[] = { 
				paramValue
		};
		System.out.println("删除数据，执行sql语句 : "+sql);
		Integer flag=0;
		//flag = qr.update(sql, params);
		flag = delete(new Integer(paramValue));
		if(flag > 0 ){
			return true;
		}else{
			return false;
		}
				
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#deleteFunctionVoByParamList(java.util.List)
	 * @param paramList
	 * @return
	 */
	@Override
	public List<Object> deleteFunctionVoByParamList(List<String[]> paramList) {
		// TODO Auto-generated method stub
		if(paramList == null){
			return null;
		}
		
		List<Object> stateList = new ArrayList<Object>();
		for (String[] params : paramList) {
			String paramName = params[0];
			String paramValue =  params[1];
			String paramOption =  params[2];
			boolean state = deleteFunctionVoByParam(paramName,paramValue,paramOption);
			stateList.add(state);
		}
		return stateList;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.dao.IFunctionDao#deleteFunctionVoByCondition(java.lang.String)
	 * @param condition
	 * @return
	 */
	@Override
	public boolean deleteFunctionVoByCondition(String condition) {
		// TODO Auto-generated method stub
		if(condition == null){
			return false;
		}
		try{
			QueryRunner qr = new QueryRunner();
			String sql = "delete from Function where "+condition;
			System.out.println("删除数据，执行sql语句 : "+sql);
			qr.update(JdbcUtils.getConnection(),sql, null);
		}catch (Exception e) {
			;
		}
		return false;
	}

}
