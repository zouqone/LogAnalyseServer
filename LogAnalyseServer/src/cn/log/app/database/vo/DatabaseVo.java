/**
 * 
 */
package cn.log.app.database.vo;

import java.util.List;

/**
 * 
 * @author zouqone
 * date 2014年5月11日   上午8:49:38
 * 数据库信息
 */
public class DatabaseVo {

	/**
	 * 数据库类型
	 */
	private String type;
	
	/**
	 * 数据库驱动
	 */
	private String driver;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 端口号
	 */
	private String port;
	
	/**
	 * 数据库名
	 */
	private String dbName;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 数据库中的所有表
	 */
	private List<TableVo> tableVoList;
	
	/**
	 * 
	 */
	public DatabaseVo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}


	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}


	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}


	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}


	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}





	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}


	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the tableVoList
	 */
	public List<TableVo> getTableVoList() {
		return tableVoList;
	}


	/**
	 * @param tableVoList the tableVoList to set
	 */
	public void setTableVoList(List<TableVo> tableVoList) {
		this.tableVoList = tableVoList;
	}
	
	

}
