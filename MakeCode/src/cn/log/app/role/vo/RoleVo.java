package cn.log.app.role.vo;


/**
 * @author zouqone
 * @date ${date}
 * @Description： 角色
 */
public class RoleVo{


/*=========================属性==============================*/

	/**
	 * ID
	 */
	private Integer Id;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色类型
	 */
	private Integer type;
	



/*=========================构造方法==============================*/

	public RoleVo() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 角色
	 * @param Id
	 * @param name
	 * @param type
	 */ 
	public RoleVo(Integer Id , String name , Integer type){
		this.Id= Id;
		this.name= name;
		this.type= type;
	}


/*=========================set 和 get 方法==============================*/

	/**
	 * @return the Id 
	 */
	public Integer getId () {
		return Id;
	}

	/**
	 *  @param Id the Id to set
	 */
	public void setId (Integer Id) {
		this.Id = Id;
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
	 * @return the type 
	 */
	public Integer getType () {
		return type;
	}

	/**
	 *  @param type the type to set
	 */
	public void setType (Integer type) {
		this.type = type;
	}
}
