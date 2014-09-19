/**
 * 
 */
package cn.log.tool.vo;

import java.util.List;

/**
 * @author zouqone
 * jQuery树节点
 */
public class TreeVo{
	
	private String uk;
	
	private String id;

	private String pId;
	
	private String name;
	
	private Boolean isParent;
	
	private Boolean hasChild;
	
	private Boolean open;

	private String file;
	
	private Boolean checked;
	
	private String info;
	
	private String sort;
	
	public TreeVo() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the pId
	 */
	public String getpId() {
		return pId;
	}

	/**
	 * @param pId the pId to set
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isParent
	 */
	public Boolean getIsParent() {
		return isParent;
	}

	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	/**
	 * @return the hasChild
	 */
	public Boolean getHasChild() {
		return hasChild;
	}

	/**
	 * @param hasChild the hasChild to set
	 */
	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * @return the open
	 */
	public Boolean getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the uk
	 */
	public String getUk() {
		return uk;
	}

	/**
	 * @param uk the uk to set
	 */
	public void setUk(String uk) {
		this.uk = uk;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

}
