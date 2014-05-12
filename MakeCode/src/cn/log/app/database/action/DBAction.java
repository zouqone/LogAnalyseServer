/**
 * 
 */
package cn.log.app.database.action;

import java.util.List;

import cn.log.app.database.service.IDBService;
import cn.log.app.database.vo.DatabaseVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zouqone date 2014年5月11日 下午4:32:37
 * 
 */
public class DBAction extends AbstractBaseAction implements
		ModelDriven<DatabaseVo> {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	public IDBService mysqlDBService;
	
	public DatabaseVo databaseVo;
	
	
	
	/**
	 * 
	 */
	public DBAction() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public DatabaseVo getModel() {
		// TODO Auto-generated method stub
		if(databaseVo == null){
			databaseVo = new DatabaseVo();
		}
		return databaseVo;
	}

	public void getDBInfo(){
		String dbInfoStr = request.getParameter("dbInfo");
		String jsonString = "hi";
		DatabaseVo db = mysqlDBService.findDBInfo(databaseVo);
		jsonString = ActionHelp.beanToStr(db);
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	/**
	 * 获取所有的数据库名
	 * @param databaseVo
	 * @return
	 */
	public void getAllDBName(){
		List<String> dbNameList = mysqlDBService.getAllDBName(databaseVo);
		String jsonString = "";
		jsonString = dbNameList.toString();//ActionHelp.beanListToStr(dbNameList);
		jsonString = jsonString.substring(1,jsonString.length()-1);
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param serialversionuid the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/**
	 * @return the mysqlDBService
	 */
	public IDBService getMysqlDBService() {
		return mysqlDBService;
	}

	/**
	 * @param mysqlDBService the mysqlDBService to set
	 */
	public void setMysqlDBService(IDBService mysqlDBService) {
		this.mysqlDBService = mysqlDBService;
	}

	/**
	 * @return the databaseVo
	 */
	public DatabaseVo getDatabaseVo() {
		return databaseVo;
	}

	/**
	 * @param databaseVo the databaseVo to set
	 */
	public void setDatabaseVo(DatabaseVo databaseVo) {
		this.databaseVo = databaseVo;
	}

	
}
