package cn.log.app.user.vo;

import java.util.*; 

/**
 * @author zouqone
 * @date 
 * @Description： 
 */
public class UserVo{


/*=========================属性==============================*/

	/**
	 * 用户ID
	 */
	private Integer id;
	
	/**
	 * 用户
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 住址
	 */
	private String address;
	
	/**
	 * 
	 */
	private String info;
	
	/**
	 * 邮箱
	 */
	private String email;
	



/*=========================构造方法==============================*/

	public UserVo() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param age
	 * @param sex
	 * @param address
	 * @param info
	 * @param email
	 */ 
	public UserVo(Integer id , String username , String password , Integer age , String sex , String address , String info , String email){
		this.id= id;
		this.username= username;
		this.password= password;
		this.age= age;
		this.sex= sex;
		this.address= address;
		this.info= info;
		this.email= email;
	}


/*=========================set 和 get 方法==============================*/

	/**
	 * @return the id 
	 */
	public Integer getId () {
		return id;
	}

	/**
	 *  @param id the id to set
	 */
	public void setId (Integer id) {
		this.id = id;
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
	public String getSex () {
		return sex;
	}

	/**
	 *  @param sex the sex to set
	 */
	public void setSex (String sex) {
		this.sex = sex;
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
	 * @return the info 
	 */
	public String getInfo () {
		return info;
	}

	/**
	 *  @param info the info to set
	 */
	public void setInfo (String info) {
		this.info = info;
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
}
