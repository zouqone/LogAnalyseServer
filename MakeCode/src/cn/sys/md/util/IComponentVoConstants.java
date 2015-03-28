package cn.sys.md.util;



																							
/**
 * @author zouqone
 * @date 
 * @Description： 组件
 */
public interface IComponentVoConstants {

	/**
	 * BS的规划化名称
	 */
	public final static String SERVICE_KEY = "${service}";
	
	/**
	 * 表名
	 */
	public final static String TABLE_NAME = "md_component";
	
	/**
	 * 表名注释
	 */
	public final static String TABLE_NAME_COMMENT = "用户信息";

	/**
	 * 默认查询条件链接关键字
	 */
	public final static String DEFAULT_SQL_CONTACT_KEYWORD = " where ";
	
	/**
	 * 默认查询条件
	 */
	public final static String DEFAULT_QUERY_WHERE_ENABLE = DEFAULT_SQL_CONTACT_KEYWORD+" 1=1 ";

	/**
	 * 默认排序字段
	 */
	public final static String DEFAULT_ORDER_CODE = "Id";

	
	//web层 Action 跳转
	/**
	 * 列表页面
	 */
	public final static String LIST_PAGE = "list";
	
	/**
	 * 新增页面
	 */
	public final static String ADD_PAGE = "insert";
	
	/**
	 * 修改页面
	 */
	public final static String UPDATE_PAGE = "update";
	
	/**
	 * 删除页面
	 */
	public final static String DELTE_PAGE ="delete";
	
	/**
	 * 详情页面
	 */
	public final static String DETAIL_PAGE ="detail";
	
	/**
	 * 错误页面
	 */
	public final static String ERROR_PAGE ="error";
	
	
	/**
	 * beans
	 */
	public final static String BEANS ="beans";
	
	/**
	 * bean
	 */
	public final static String BEAN ="bean";
	
	
	

	//SQL语句
	/**
	 * 插入
	 */
	public final static String SQL_INSERT = "insert into md_component(md_component.id , md_component.code , md_component.name , md_component.detail , md_component.createtime , md_component.creator , md_component.modifytime , md_component.modifer , md_component.ts , md_component.dr , md_component.componentid , md_component.comcategoryid) values(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?) ";
	
	/**
	 * 通过PK删除
	 */
	public final static String SQL_DELETE_BY_PK = "delete from md_component where Id=?";

	/**
	 * 批量删除
	 */
	public final static String SQL_DELETE_MULTI_BY_PKS = "delete from md_component";

	/**
	 * 通过PK修改
	 */
	public final static String SQL_UPDATE_BY_PK = "update md_component set md_component.id = ?  ,md_component.code = ?  ,md_component.name = ?  ,md_component.detail = ?  ,md_component.createtime = ?  ,md_component.creator = ?  ,md_component.modifytime = ?  ,md_component.modifer = ?  ,md_component.ts = ?  ,md_component.dr = ?  ,md_component.componentid = ?  ,md_component.comcategoryid = ?   where Id = ?";

	/**
	 * 查询记录数目
	 */
	public final static String SQL_COUNT = "select count(*) from md_component";

	/**
	 * 查询所有记录
	 */
	public final static String SQL_QUERY_ALL = "SELECT md_component.id , md_component.code , md_component.name , md_component.detail , md_component.createtime , md_component.creator , md_component.modifytime , md_component.modifer , md_component.ts , md_component.dr , md_component.componentid , md_component.comcategoryid  FROM md_component";

	/**
	 * 通过PK查询
	 */
	public final static String SQL_FIND_BY_PK = SQL_QUERY_ALL+DEFAULT_SQL_CONTACT_KEYWORD+" Id = ? ";


}
