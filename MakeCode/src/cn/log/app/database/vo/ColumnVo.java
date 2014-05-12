/**
 * 
 */
package cn.log.app.database.vo;

/**
 * @author zouqone
 * date 2014年5月11日   上午8:50:29
 * 数据库表中的字段信息
 * 
 */
public class ColumnVo {

	/**
	 * 列字段名
	 */
	private String field;

	/**
	 * 列字段数据库数据类型
	 */
	private String type;

	/**
	 * 排序规则
	 */
	private String collation;

	/**
	 * 是否可为空
	 */
	private Boolean isNull;
	

	/**
	 * 键值类型
	 */
	private String key;

	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 默认值
	 */
	private String defaultValue;

	/**
	 * 扩展属性
	 */
	private String extra;

	/**
	 * 权限级别
	 */
	private String privileges;

	/**
	 * 列备注
	 */
	private String comment;
	
	/**
	 * 表
	 */
	private String table;
	
	/**
	 * 
	 */
	public ColumnVo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
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
	 * @return the collation
	 */
	public String getCollation() {
		return collation;
	}

	/**
	 * @param collation the collation to set
	 */
	public void setCollation(String collation) {
		this.collation = collation;
	}

	/**
	 * @return the isNull
	 */
	public Boolean getIsNull() {
		return isNull;
	}

	/**
	 * @param isNull the isNull to set
	 */
	public void setIsNull(Boolean isNull) {
		this.isNull = isNull;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * @return the privileges
	 */
	public String getPrivileges() {
		return privileges;
	}

	/**
	 * @param privileges the privileges to set
	 */
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
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
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}

	
}
