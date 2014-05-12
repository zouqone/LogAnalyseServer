/**
 * 
 */
package cn.log.user.vo;


/**
 * @author zouqone
 * @see User
 */
public class UserVo {
	
	
	/*=========================属性==============================*/
	/**
	 * id 
	 */
	private Integer id = null;

	/**
	 * username 
	 */
	private String username = null;

	/**
	 * password 
	 */
	private String password = null;

	/**
	 * age 
	 */
	private Integer age = null;

	/**
	 * sex 
	 */
	private String sex = null;

	/**
	 * address 
	 */
	private String address = null;

	/**
	 * Info 
	 */
	private String Info = null;

	/**
	 * email 
	 */
	private String email = null;

	
	
	public UserVo(Integer id, String username, String password, Integer age,
			String sex, String address, String info, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.address = address;
		Info = info;
		this.email = email;
	}

	
	
	/*=========================set 和  get 方法==============================*/
	
	

	public UserVo() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the id 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *  @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *  @param username the username to set
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
	 *  @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the age 
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 *  @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the sex 
	 */
	public String getSex() {
		return sex;
	}

	/**
	 *  @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the address 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 *  @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the Info 
	 */
	public String getInfo() {
		return Info;
	}

	/**
	 *  @param Info the Info to set
	 */
	public void setInfo(String Info) {
		this.Info = Info;
	}

	/**
	 * @return the email 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *  @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
}
