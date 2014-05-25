/**
 * 
 */
package cn.log.app.template.vo;

/**
 * @author zouqone
 * @date 2014-5-18 下午1:41:18 
 * @Description: TODO 属性模板对象
 */
public class AttributeVo {

	/**
	 * 属性名称
	 */
	private String atrributeName;

	/**
	 * 数据类型
	 */
	private String dataType;

	/**
	 * 默认值
	 */
	private String defaultValue;

	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 权限类型
	 */
	private String authType;

	/**
	 * 作用范围
	 */
	private String applyType;
	
	/**
	 * 
	 */
	public AttributeVo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public AttributeVo(String atrributeName, String dataType,
			String defaultValue, String comment, String authType,
			String applyType) {
		this.atrributeName = atrributeName;
		this.dataType = dataType;
		this.defaultValue = defaultValue;
		this.comment = comment;
		this.authType = authType;
		this.applyType = applyType;
	}



	/**
	 * @return the atrributeName
	 */
	public String getAtrributeName() {
		return atrributeName;
	}

	/**
	 * @param atrributeName the atrributeName to set
	 */
	public void setAtrributeName(String atrributeName) {
		this.atrributeName = atrributeName;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}

	/**
	 * @return the applyType
	 */
	public String getApplyType() {
		return applyType;
	}

	/**
	 * @param applyType the applyType to set
	 */
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	
}
