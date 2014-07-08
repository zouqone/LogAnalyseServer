package cn.log.function.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author zouqone
 * @see 功能节点
 */
@Entity
@Table(name="function")
@SuppressWarnings("serial")
public class FunctionVo implements Serializable {


/*=========================属性==============================*/

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	/**
	 * 节点编码
	 */
	@Column(name="ncode")
	private String ncode;
	
	/**
	 * 节点名称
	 */
	@Column(name="nodename")
	private String nodename;
	
	/**
	 * 节点描述
	 */
	@Column(name="nodedesc")
	private String nodedesc;
	
	/**
	 * 上级节点编码
	 */
	@Column(name="parentncode")
	private String parentncode;
	
	/**
	 * 链接
	 */
	private String link;


/*=========================构造方法==============================*/

	public FunctionVo() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 功能节点
	 * @param id
	 * @param ncode
	 * @param nodename
	 * @param nodedesc
	 * @param parentncode
	 */ 
	public FunctionVo(Integer id , String ncode , String nodename , String nodedesc , String parentncode){
		this.id= id;
		this.ncode= ncode;
		this.nodename= nodename;
		this.nodedesc= nodedesc;
		this.parentncode= parentncode;
	}


/*=========================set 和  get 方法==============================*/

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
	 * @return the ncode 
	 */
	public String getNcode () {
		return ncode;
	}

	/**
	 *  @param ncode the ncode to set
	 */
	public void setNcode (String ncode) {
		this.ncode = ncode;
	}
	/**
	 * @return the nodename 
	 */
	public String getNodename () {
		return nodename;
	}

	/**
	 *  @param nodename the nodename to set
	 */
	public void setNodename (String nodename) {
		this.nodename = nodename;
	}
	/**
	 * @return the nodedesc 
	 */
	public String getNodedesc () {
		return nodedesc;
	}

	/**
	 *  @param nodedesc the nodedesc to set
	 */
	public void setNodedesc (String nodedesc) {
		this.nodedesc = nodedesc;
	}
	/**
	 * @return the parentncode 
	 */
	public String getParentncode () {
		return parentncode;
	}

	/**
	 *  @param parentncode the parentncode to set
	 */
	public void setParentncode (String parentncode) {
		this.parentncode = parentncode;
	}
	
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
}