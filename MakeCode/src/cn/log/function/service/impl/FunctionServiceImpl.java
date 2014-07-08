/**
 * 
 */
package cn.log.function.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import cn.log.db.util.JdbcUtils;
import cn.log.function.dao.IFunctionDao;
import cn.log.function.service.IFunctionService;
import cn.log.function.vo.FunctionVo;
import cn.log.tool.vo.TreeVo;


/**
 * @author zouqone
 * @see FunctionService接口实现
 */
@Transactional
@Service("functionService")  
public class FunctionServiceImpl implements IFunctionService {

	/**
	 * FunctionDao接口
	 */
	private IFunctionDao functionDao ;
	
	

	/**
	 * @return the functionDao
	 */
	public IFunctionDao getFunctionDao() {
		return functionDao;
	}

	/**
	 * @param functionDao the functionDao to set
	 */
	public void setFunctionDao(IFunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#insertFunctionVo(cn.log.function.vo.FunctionVo)
	 * @param functionVo
	 * @return
	 */
	@Override
	public String insertFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		Integer id = 0;
		//Connection conn = JdbcUtils.getConnection();
		//JdbcUtils.startTransaction();
		id = functionDao.insertFunctionVo(functionVo);
		//int s = 1/0;
		return id.toString();
	}
	
	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#getAllFunctionVo()
	 */
	@Override
	public List<FunctionVo> getAllFunctionVo() {
		// TODO Auto-generated method stub
		
		return functionDao.queryAllFunctionVo();
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#getFunctionVoById(java.lang.Integer)
	 */
	@Override
	public FunctionVo getFunctionVoById(Integer id) {
		// TODO Auto-generated method stub
		String condition = " where id = \""+id+"\""; 
		List<FunctionVo> functionList = functionDao.queryFunctionVoByCondition(condition);
		if(functionList!=null&&functionList.size()>0){
			return functionList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#updateFunctionVo(cn.log.function.vo.FunctionVo)
	 */
	@Override
	public List<FunctionVo> getFunctionVoByCondition(FunctionVo functionVo){
		String condition = "";
		if(functionVo==null){
			return null;
		}
		
		if(functionVo.getId()!=null&&!functionVo.getId().toString().trim().equals("")){
			condition +="and  id like \"%"+functionVo.getId()+"%\"";
		}
		if(functionVo.getNcode()!=null&&!functionVo.getNcode().toString().trim().equals("")){
			condition +="and  ncode like \"%"+functionVo.getNcode()+"%\"";
		}
		if(functionVo.getNodename()!=null&&!functionVo.getNodename().toString().trim().equals("")){
			condition +="and  nodename like \"%"+functionVo.getNodename()+"%\"";
		}
		if(functionVo.getNodedesc()!=null&&!functionVo.getNodedesc().toString().trim().equals("")){
			condition +="and  nodedesc like \"%"+functionVo.getNodedesc()+"%\"";
		}
		if(functionVo.getParentncode()!=null&&!functionVo.getParentncode().toString().trim().equals("")){
			condition +="and  parentncode like \"%"+functionVo.getParentncode()+"%\"";
		}
		
		if(!condition.trim().equals("")){
			condition = " where "+condition.substring(4);
		}
		
		List<FunctionVo> functionVoList = functionDao.queryFunctionVoByCondition(condition );
		
		return functionVoList;
	
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#updateFunctionVo(cn.log.function.vo.FunctionVo)
	 */
	@Override
	public boolean updateFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		return functionDao.updateFunctionVo(functionVo);
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#updateFunctionVo(java.util.List)
	 */
	@Override
	public List<Object> updateFunctionVo(List<FunctionVo> functionVoList) {
		// TODO Auto-generated method stub
		return functionDao.updateFunctionVo(functionVoList);
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#createFunctionVo(cn.log.function.vo.FunctionVo)
	 */
	@Override
	public String createFunctionVo(FunctionVo functionVo) {
		// TODO Auto-generated method stub
		try{
			Integer id = functionDao.insertFunctionVo(functionVo);
			//Integer is = 1/0;
			//functionVo.setId(id);
			if(id!=null){
				return id.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//functionDao.insertFunctionVo(functionVo);
		
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.function.service.IFunctionService#deleteFunctionVo(java.lang.String)
	 */
	@Override
	public boolean deleteFunctionVo(Integer id) {
		// TODO Auto-generated method stub
		return functionDao.deleteFunctionVoByParam("id", id.toString(), "=");
	}

	/* (non-Javadoc)
	 * @see cn.zs.system.function.service.IFunctionService#queryFunctionByParentCode(java.lang.String)
	 */
	@Override
	public String queryFunctionByParentCode(String pCode) {
		// TODO Auto-generated method stub
		List<FunctionVo> functionList= null ;
		functionList = queryFunction(pCode);
		if(functionList == null || functionList.size()==0){
			return "[]";
		}
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		for (FunctionVo function : functionList) {
			TreeVo treeVo = new TreeVo();
			String nodeName = function.getNodename();
			String nodeCode = function.getNcode();
			String link = function.getLink();
			List<FunctionVo> functionChildList = queryFunction(nodeCode);
			
			treeVo.setId(nodeCode);
			treeVo.setName(nodeName);
			treeVo.setpId(pCode);
			treeVo.setFile(link);
			treeVo.setIsParent(functionChildList.size()>0?true:false);
			treeVo.setHasChild(functionChildList.size()>0?true:false);
			treeVoList.add(treeVo);
		}
		JSONArray jsonArray = JSONArray.fromObject(treeVoList);
		String treeListStr = jsonArray.toString();
		return treeListStr;
	}
	
	public List<FunctionVo> queryFunction(String pCode){
		List<FunctionVo> functionList= null ;
		String condition = " where parentncode = '"+pCode+"'"; 
		functionList = functionDao.queryFunctionVoByCondition(condition);
		return functionList;
	}
	
	public String queryCheckFunctionByParentCode(String pCode) {
		// TODO Auto-generated method stub
		List<FunctionVo> functionList= null ;
		functionList = queryFunction(pCode);
		if(functionList == null || functionList.size()==0){
			return "[]";
		}
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		for (FunctionVo function : functionList) {
			TreeVo treeVo = new TreeVo();
			String id = function.getId().toString();
			String nodeName = function.getNodename();
			String nodeCode = function.getNcode();
			String link = function.getLink();
			String nodedesc = function.getNodedesc();
			List<FunctionVo> functionChildList = queryFunction(nodeCode);
			
			treeVo.setUk(id);
			treeVo.setId(nodeCode);
			treeVo.setName(nodeName);
			treeVo.setpId(pCode);
			treeVo.setFile(link);
			treeVo.setInfo(nodedesc);
			treeVo.setChecked(false);
			treeVo.setIsParent(functionChildList.size()>0?true:false);
			treeVo.setHasChild(functionChildList.size()>0?true:false);
			treeVoList.add(treeVo);
		}
		JSONArray jsonArray = JSONArray.fromObject(treeVoList);
		String treeListStr = jsonArray.toString();
		return treeListStr;
	}


}
