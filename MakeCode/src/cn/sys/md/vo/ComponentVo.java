package cn.sys.md.vo;

import java.util.*; 

/**
 * @author zouqone
 * @date 
 * @Description： 组件
 */
public class ComponentVo{


/*=========================属性==============================*/

	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String code;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private String detail;
	
	/**
	 * 
	 */
	private Date createtime;
	
	/**
	 * 
	 */
	private String creator;
	
	/**
	 * 
	 */
	private Date modifytime;
	
	/**
	 * 
	 */
	private String modifer;
	
	/**
	 * 
	 */
	private String ts;
	
	/**
	 * 
	 */
	private String dr;
	
	/**
	 * 
	 */
	private String componentid;
	
	/**
	 * 
	 */
	private String comcategoryid;
	



/*=========================构造方法==============================*/

	public ComponentVo() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 组件
	 * @param id
	 * @param code
	 * @param name
	 * @param detail
	 * @param createtime
	 * @param creator
	 * @param modifytime
	 * @param modifer
	 * @param ts
	 * @param dr
	 * @param componentid
	 * @param comcategoryid
	 */ 
	public ComponentVo(String id , String code , String name , String detail , Date createtime , String creator , Date modifytime , String modifer , String ts , String dr , String componentid , String comcategoryid){
		this.id= id;
		this.code= code;
		this.name= name;
		this.detail= detail;
		this.createtime= createtime;
		this.creator= creator;
		this.modifytime= modifytime;
		this.modifer= modifer;
		this.ts= ts;
		this.dr= dr;
		this.componentid= componentid;
		this.comcategoryid= comcategoryid;
	}


/*=========================set 和 get 方法==============================*/

	/**
	 * @return the id 
	 */
	public String getId () {
		return id;
	}

	/**
	 *  @param id the id to set
	 */
	public void setId (String id) {
		this.id = id;
	}
	/**
	 * @return the code 
	 */
	public String getCode () {
		return code;
	}

	/**
	 *  @param code the code to set
	 */
	public void setCode (String code) {
		this.code = code;
	}
	/**
	 * @return the name 
	 */
	public String getName () {
		return name;
	}

	/**
	 *  @param name the name to set
	 */
	public void setName (String name) {
		this.name = name;
	}
	/**
	 * @return the detail 
	 */
	public String getDetail () {
		return detail;
	}

	/**
	 *  @param detail the detail to set
	 */
	public void setDetail (String detail) {
		this.detail = detail;
	}
	/**
	 * @return the createtime 
	 */
	public Date getCreatetime () {
		return createtime;
	}

	/**
	 *  @param createtime the createtime to set
	 */
	public void setCreatetime (Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the creator 
	 */
	public String getCreator () {
		return creator;
	}

	/**
	 *  @param creator the creator to set
	 */
	public void setCreator (String creator) {
		this.creator = creator;
	}
	/**
	 * @return the modifytime 
	 */
	public Date getModifytime () {
		return modifytime;
	}

	/**
	 *  @param modifytime the modifytime to set
	 */
	public void setModifytime (Date modifytime) {
		this.modifytime = modifytime;
	}
	/**
	 * @return the modifer 
	 */
	public String getModifer () {
		return modifer;
	}

	/**
	 *  @param modifer the modifer to set
	 */
	public void setModifer (String modifer) {
		this.modifer = modifer;
	}
	/**
	 * @return the ts 
	 */
	public String getTs () {
		return ts;
	}

	/**
	 *  @param ts the ts to set
	 */
	public void setTs (String ts) {
		this.ts = ts;
	}
	/**
	 * @return the dr 
	 */
	public String getDr () {
		return dr;
	}

	/**
	 *  @param dr the dr to set
	 */
	public void setDr (String dr) {
		this.dr = dr;
	}
	/**
	 * @return the componentid 
	 */
	public String getComponentid () {
		return componentid;
	}

	/**
	 *  @param componentid the componentid to set
	 */
	public void setComponentid (String componentid) {
		this.componentid = componentid;
	}
	/**
	 * @return the comcategoryid 
	 */
	public String getComcategoryid () {
		return comcategoryid;
	}

	/**
	 *  @param comcategoryid the comcategoryid to set
	 */
	public void setComcategoryid (String comcategoryid) {
		this.comcategoryid = comcategoryid;
	}
}
