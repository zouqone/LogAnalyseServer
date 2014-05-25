package cn.log.app.user.vo;

import java.util.*; 

/**
 * @author zouqone
 * @date 
 * @Description： 用户表
 */
public class UserVo{


/*=========================属性==============================*/

	/**
	 * 用户ID
	 */
	private Integer Id;
	
	/**
	 * 用户编码
	 */
	private String username;
	
	/**
	 * 用户表
	 */
	private String password;
	
	/**
	 * 用户真实名
	 */
	private String realname;
	
	/**
	 * 角色
	 */
	private Integer role;
	
	/**
	 * 用户状态
	 */
	private Integer flag;
	
	/**
	 * 电话号码
	 */
	private String telephone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 
	 */
	private String position;
	
	/**
	 * 所属部门
	 */
	private Integer department;
	
	/**
	 * 住址
	 */
	private String address;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 出生日期
	 */
	private Date birthdate;
	
	/**
	 * 创建日期
	 */
	private Date createtime;
	
	/**
	 * 修改日期
	 */
	private Date modifytime;
	



/*=========================构造方法==============================*/

	public UserVo() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 用户表
	 * @param Id
	 * @param username
	 * @param password
	 * @param realname
	 * @param role
	 * @param flag
	 * @param telephone
	 * @param email
	 * @param position
	 * @param department
	 * @param address
	 * @param age
	 * @param sex
	 * @param birthdate
	 * @param createtime
	 * @param modifytime
	 */ 
	public UserVo(Integer Id , String username , String password , String realname , Integer role , Integer flag , String telephone , String email , String position , Integer department , String address , Integer age , Integer sex , Date birthdate , Date createtime , Date modifytime){
		this.Id= Id;
		this.username= username;
		this.password= password;
		this.realname= realname;
		this.role= role;
		this.flag= flag;
		this.telephone= telephone;
		this.email= email;
		this.position= position;
		this.department= department;
		this.address= address;
		this.age= age;
		this.sex= sex;
		this.birthdate= birthdate;
		this.createtime= createtime;
		this.modifytime= modifytime;
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
	 * @return the username 
	 */
	public String getUsername () {
		return username;
	}

	/**
	 *  @param username the username to set
	 */
	public void setUsername (String username) {
		this.username = username;
	}
	/**
	 * @return the password 
	 */
	public String getPassword () {
		return password;
	}

	/**
	 *  @param password the password to set
	 */
	public void setPassword (String password) {
		this.password = password;
	}
	/**
	 * @return the realname 
	 */
	public String getRealname () {
		return realname;
	}

	/**
	 *  @param realname the realname to set
	 */
	public void setRealname (String realname) {
		this.realname = realname;
	}
	/**
	 * @return the role 
	 */
	public Integer getRole () {
		return role;
	}

	/**
	 *  @param role the role to set
	 */
	public void setRole (Integer role) {
		this.role = role;
	}
	/**
	 * @return the flag 
	 */
	public Integer getFlag () {
		return flag;
	}

	/**
	 *  @param flag the flag to set
	 */
	public void setFlag (Integer flag) {
		this.flag = flag;
	}
	/**
	 * @return the telephone 
	 */
	public String getTelephone () {
		return telephone;
	}

	/**
	 *  @param telephone the telephone to set
	 */
	public void setTelephone (String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the email 
	 */
	public String getEmail () {
		return email;
	}

	/**
	 *  @param email the email to set
	 */
	public void setEmail (String email) {
		this.email = email;
	}
	/**
	 * @return the position 
	 */
	public String getPosition () {
		return position;
	}

	/**
	 *  @param position the position to set
	 */
	public void setPosition (String position) {
		this.position = position;
	}
	/**
	 * @return the department 
	 */
	public Integer getDepartment () {
		return department;
	}

	/**
	 *  @param department the department to set
	 */
	public void setDepartment (Integer department) {
		this.department = department;
	}
	/**
	 * @return the address 
	 */
	public String getAddress () {
		return address;
	}

	/**
	 *  @param address the address to set
	 */
	public void setAddress (String address) {
		this.address = address;
	}
	/**
	 * @return the age 
	 */
	public Integer getAge () {
		return age;
	}

	/**
	 *  @param age the age to set
	 */
	public void setAge (Integer age) {
		this.age = age;
	}
	/**
	 * @return the sex 
	 */
	public Integer getSex () {
		return sex;
	}

	/**
	 *  @param sex the sex to set
	 */
	public void setSex (Integer sex) {
		this.sex = sex;
	}
	/**
	 * @return the birthdate 
	 */
	public Date getBirthdate () {
		return birthdate;
	}

	/**
	 *  @param birthdate the birthdate to set
	 */
	public void setBirthdate (Date birthdate) {
		this.birthdate = birthdate;
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
}
