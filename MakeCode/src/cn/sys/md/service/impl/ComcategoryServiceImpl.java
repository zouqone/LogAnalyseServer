package cn.sys.md.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import cn.log.function.vo.FunctionVo;
import cn.log.tool.util.DBHelp;
import cn.log.tool.vo.TreeVo;
import cn.sys.md.dao.IComcategoryDao;
import cn.sys.md.service.IComcategoryService;
import cn.sys.md.vo.ComcategoryVo;


;

/**
 * @author zouqone
 * @date 
 * @Description： 组件分类
 */
public class ComcategoryServiceImpl implements IComcategoryService {
	
	private IComcategoryDao comcategoryDao;

	/**
	 * @return the comcategoryDao
	 */
	public IComcategoryDao getComcategoryDao() {
		return comcategoryDao;
	}

	/**
	 * @param comcategoryDao the comcategoryDao to set
	 */
	public void setComcategoryDao(IComcategoryDao comcategoryDao) {
		this.comcategoryDao = comcategoryDao;
	}
	
	

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#queryAllComcategoryVo()
	 */
	@Override
	public List<ComcategoryVo> queryAllComcategoryVo() {
		// TODO Auto-generated method stub
		List<ComcategoryVo> comcategoryVos = comcategoryDao.queryAllComcategoryVo();
		
		return comcategoryVos;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#queryComcategoryVoByCondition(java.lang.String)
	 */
	@Override
	public List<ComcategoryVo> queryComcategoryVoByCondition(String condition) {
		// TODO Auto-generated method stub
		List<ComcategoryVo> comcategoryVos = comcategoryDao.queryComcategoryVoByCondition(condition);
		return comcategoryVos;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#queryComcategoryVoBySql(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryComcategoryVoBySql(String sql) {
		// TODO Auto-generated method stub
		List<Map> maps = comcategoryDao.queryComcategoryVoBySql(sql);
		return maps;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#find(cn.sys.md.vo.ComcategoryVo)
	 */
	@Override
	public ComcategoryVo find(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		ComcategoryVo vo = comcategoryDao.find(comcategoryVo);
		return vo;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#insertComcategoryVo(cn.sys.md.vo.ComcategoryVo)
	 */
	@Override
	public Object insertComcategoryVo(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		Object object = comcategoryDao.insertComcategoryVo(comcategoryVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#insertComcategoryVo(java.util.List)
	 */
	@Override
	public List<Object> insertComcategoryVo(List<ComcategoryVo> comcategoryVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = comcategoryDao.insertComcategoryVo(comcategoryVoList);
		return objects;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#updateComcategoryVo(cn.sys.md.vo.ComcategoryVo)
	 */
	@Override
	public Object updateComcategoryVo(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		Object object = comcategoryDao.updateComcategoryVo(comcategoryVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#updateComcategoryVo(java.util.List)
	 */
	@Override
	public List<Object> updateComcategoryVo(List<ComcategoryVo> comcategoryVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = comcategoryDao.updateComcategoryVo(comcategoryVoList);
		return objects;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#deleteComcategoryVo(cn.sys.md.vo.ComcategoryVo)
	 */
	@Override
	public Object deleteComcategoryVo(ComcategoryVo comcategoryVo) {
		// TODO Auto-generated method stub
		Object object = comcategoryDao.deleteComcategoryVo(comcategoryVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#deleteComcategoryVoByCondition(java.lang.String)
	 */
	@Override
	public Object deleteComcategoryVoByCondition(String condition) {
		// TODO Auto-generated method stub
		Object object = comcategoryDao.deleteComcategoryVoByCondition(condition);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComcategoryService#queryComcategoryVoTotalNumber()
	 */
	@Override
	public Integer queryComcategoryVoTotalNumber() {
		// TODO Auto-generated method stub
		Integer count = queryComcategoryVoTotalNumber(null);
		return count;
	}

	/**
	 * 查询ComcategoryVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryComcategoryVoTotalNumber(String condition){
		Integer count = comcategoryDao.queryComcategoryVoTotalNumber(condition);
		return count;
	}

	@Override
	public String queryChildren(String parentCode) {
		// TODO Auto-generated method stub
		String condition = DBHelp.AddCondition(null,"parentCode",parentCode,"=","'","'");
		List<ComcategoryVo> comcategoryVos = queryComcategoryVoByCondition(condition);
		if(comcategoryVos==null || comcategoryVos.size() == 0){
			return null;
		}
		
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		for (ComcategoryVo comcategoryVo : comcategoryVos) {
			TreeVo treeVo = new TreeVo();
			String id = comcategoryVo.getId().toString();
			String nodeName = comcategoryVo.getName();
			String nodeCode = comcategoryVo.getCode();
			String nodedesc = comcategoryVo.getName();
			Integer count = queryChildrenCount(nodeCode);
			
			treeVo.setUk(id);
			treeVo.setId(nodeCode);
			treeVo.setName(nodeName);
			treeVo.setpId(parentCode);
			treeVo.setInfo(nodedesc);
			treeVo.setChecked(false);
			treeVo.setIsParent(count>0?true:false);
			treeVo.setHasChild(count>0?true:false);
			treeVoList.add(treeVo);
		}
		JSONArray jsonArray = JSONArray.fromObject(treeVoList);
		String treeListStr = jsonArray.toString();
		return treeListStr;
	}

	private Integer queryChildrenCount(String parentCode) {
		// TODO Auto-generated method stub
		String condition = DBHelp.AddCondition(null,"parentCode",parentCode,"=","'","'");
		
		return queryComcategoryVoTotalNumber(condition);
	}
	
	
}
