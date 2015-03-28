package cn.sys.md.service.impl;

import java.util.List;
import java.util.Map;

import cn.sys.md.dao.IComponentDao;
import cn.sys.md.service.IComponentVoService;
import cn.sys.md.vo.ComponentVo;


;

/**
 * @author zouqone
 * @date 
 * @Description： 组件
 */
public class ComponentVoServiceImpl implements IComponentVoService {
	
	private IComponentDao componentDao;

	/**
	 * @return the componentDao
	 */
	public IComponentDao getComponentDao() {
		return componentDao;
	}

	/**
	 * @param componentDao the componentDao to set
	 */
	public void setComponentDao(IComponentDao componentDao) {
		this.componentDao = componentDao;
	}
	
	

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#queryAllComponentVo()
	 */
	@Override
	public List<ComponentVo> queryAllComponentVo() {
		// TODO Auto-generated method stub
		List<ComponentVo> componentVos = componentDao.queryAllComponentVo();
		
		return componentVos;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#queryComponentVoByCondition(java.lang.String)
	 */
	@Override
	public List<ComponentVo> queryComponentVoByCondition(String condition) {
		// TODO Auto-generated method stub
		List<ComponentVo> componentVos = componentDao.queryComponentVoByCondition(condition);
		return componentVos;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#queryComponentVoBySql(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryComponentVoBySql(String sql) {
		// TODO Auto-generated method stub
		List<Map> maps = componentDao.queryComponentVoBySql(sql);
		return maps;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#find(cn.sys.md.vo.ComponentVo)
	 */
	@Override
	public ComponentVo find(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		ComponentVo vo = componentDao.find(componentVo);
		return vo;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#insertComponentVo(cn.sys.md.vo.ComponentVo)
	 */
	@Override
	public Object insertComponentVo(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		Object object = componentDao.insertComponentVo(componentVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#insertComponentVo(java.util.List)
	 */
	@Override
	public List<Object> insertComponentVo(List<ComponentVo> componentVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = componentDao.insertComponentVo(componentVoList);
		return objects;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#updateComponentVo(cn.sys.md.vo.ComponentVo)
	 */
	@Override
	public Object updateComponentVo(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		Object object = componentDao.updateComponentVo(componentVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#updateComponentVo(java.util.List)
	 */
	@Override
	public List<Object> updateComponentVo(List<ComponentVo> componentVoList) {
		// TODO Auto-generated method stub
		List<Object> objects = componentDao.updateComponentVo(componentVoList);
		return objects;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#deleteComponentVo(cn.sys.md.vo.ComponentVo)
	 */
	@Override
	public Object deleteComponentVo(ComponentVo componentVo) {
		// TODO Auto-generated method stub
		Object object = componentDao.deleteComponentVo(componentVo);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#deleteComponentVoByCondition(java.lang.String)
	 */
	@Override
	public Object deleteComponentVoByCondition(String condition) {
		// TODO Auto-generated method stub
		Object object = componentDao.deleteComponentVoByCondition(condition);
		return object;
	}

	/* (non-Javadoc)
	 * @see cn.sys.md.service.IComponentVoService#queryComponentVoTotalNumber()
	 */
	@Override
	public Integer queryComponentVoTotalNumber() {
		// TODO Auto-generated method stub
		Integer count = queryComponentVoTotalNumber(null);
		return count;
	}

	/**
	 * 查询ComponentVo数目
	 * @param condition
	 * @return
	 */
	public Integer queryComponentVoTotalNumber(String condition){
		Integer count = componentDao.queryComponentVoTotalNumber(condition);
		return count;
	}
	
}
