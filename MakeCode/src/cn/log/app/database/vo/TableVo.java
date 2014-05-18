/**
 * 
 */
package cn.log.app.database.vo;

import java.util.List;

/**
 * @author zouqone
 * date 2014年5月11日   上午8:50:11
 * 数据库中的表信息
 */
public class TableVo {

	/**
	 * 表名
	 */
	private String table_name;
	
	/**
	 * 表名备注
	 */
	private String comment;
	
	/**
	 * 表类型
	 */
	private String table_type;
	
	/**
	 * 字符集
	 */
	private String enCode;
	
	/**
	 * 列信息列表
	 */
	private List<ColumnVo> columnVoList;
	
	/**
	 * 索引
	 */
	private List<IndexVo> indexVoList;
	
	/**
	 * 触发器
	 */
	private List<TriggerVo> triggerVoList;
	
	/**
	 * 数据库名
	 */
	private String dbName;
	
	/**
	 * 主键
	 */
	private String primarykey;
	
	/**
	 * 主键名
	 */
	private String primaryKeyName;
	
	/**
	 * 外键
	 */
	private String foreignKey;
	
	/**
	 * 外键名
	 */
	private String foreignKeyName;
	
	/**
	 * 外键表名
	 */
	private String foreignKeyTableName;
	
	/**
	 * 生成代码所需的配置信息
	 */
	private ConfigVo configVo;
	
	
	/**
	 * 
	 */
	public TableVo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the table_name
	 */
	public String getTable_name() {
		return table_name;
	}

	/**
	 * @param table_name the table_name to set
	 */
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the table_type
	 */
	public String getTable_type() {
		return table_type;
	}

	/**
	 * @param table_type the table_type to set
	 */
	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}

	/**
	 * @return the enCode
	 */
	public String getEnCode() {
		return enCode;
	}

	/**
	 * @param enCode the enCode to set
	 */
	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}

	/**
	 * @return the columnVoList
	 */
	public List<ColumnVo> getColumnVoList() {
		return columnVoList;
	}

	/**
	 * @param columnVoList the columnVoList to set
	 */
	public void setColumnVoList(List<ColumnVo> columnVoList) {
		this.columnVoList = columnVoList;
	}

	/**
	 * @return the indexVoList
	 */
	public List<IndexVo> getIndexVoList() {
		return indexVoList;
	}

	/**
	 * @param indexVoList the indexVoList to set
	 */
	public void setIndexVoList(List<IndexVo> indexVoList) {
		this.indexVoList = indexVoList;
	}

	/**
	 * @return the triggerVoList
	 */
	public List<TriggerVo> getTriggerVoList() {
		return triggerVoList;
	}

	/**
	 * @param triggerVoList the triggerVoList to set
	 */
	public void setTriggerVoList(List<TriggerVo> triggerVoList) {
		this.triggerVoList = triggerVoList;
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
	 * @return the primarykey
	 */
	public String getPrimarykey() {
		return primarykey;
	}

	/**
	 * @param primarykey the primarykey to set
	 */
	public void setPrimarykey(String primarykey) {
		this.primarykey = primarykey;
	}

	/**
	 * @return the primaryKeyName
	 */
	public String getPrimaryKeyName() {
		return primaryKeyName;
	}

	/**
	 * @param primaryKeyName the primaryKeyName to set
	 */
	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}

	/**
	 * @return the foreignKey
	 */
	public String getForeignKey() {
		return foreignKey;
	}

	/**
	 * @param foreignKey the foreignKey to set
	 */
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	/**
	 * @return the foreignKeyName
	 */
	public String getForeignKeyName() {
		return foreignKeyName;
	}

	/**
	 * @param foreignKeyName the foreignKeyName to set
	 */
	public void setForeignKeyName(String foreignKeyName) {
		this.foreignKeyName = foreignKeyName;
	}

	/**
	 * @return the foreignKeyTableName
	 */
	public String getForeignKeyTableName() {
		return foreignKeyTableName;
	}

	/**
	 * @param foreignKeyTableName the foreignKeyTableName to set
	 */
	public void setForeignKeyTableName(String foreignKeyTableName) {
		this.foreignKeyTableName = foreignKeyTableName;
	}

	/**
	 * @return the configVo
	 */
	public ConfigVo getConfigVo() {
		return configVo;
	}

	/**
	 * @param configVo the configVo to set
	 */
	public void setConfigVo(ConfigVo configVo) {
		this.configVo = configVo;
	}

	
}
