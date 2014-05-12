/**
 * 
 */
package cn.log.app.database.vo;

/**
 * @author zouqone
 * date 2014年5月11日   上午10:08:39
 * 索引
 */
public class IndexVo {
	
	/**
	 * 
	 */
	private String table_cat;
	
	/**
	 * 
	 */
	private String table_schem;
	
	/**
	 * 表名
	 */
	private String table_name;
	
	/**
	 * 
	 */
	private Boolean non_unique;
	
	/**
	 * 
	 */
	private String index_qualifter;
	
	/**
	 * 
	 */
	private String index_name;
	
	/**
	 * 
	 */
	private Integer type;
	
	/**
	 * 
	 */
	private String ordinal_position;
	
	/**
	 * 
	 */
	private String column_name;
	
	/**
	 * 
	 */
	private String asc_or_desc;
	
	/**
	 * 
	 */
	private Integer cardinality;
	
	/**
	 * 
	 */
	private Integer pages;
	
	/**
	 * 
	 */
	private String filter_condition;
	
	
	
	/**
	 * 
	 */
	public IndexVo() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the table_cat
	 */
	public String getTable_cat() {
		return table_cat;
	}



	/**
	 * @param table_cat the table_cat to set
	 */
	public void setTable_cat(String table_cat) {
		this.table_cat = table_cat;
	}



	/**
	 * @return the table_schem
	 */
	public String getTable_schem() {
		return table_schem;
	}



	/**
	 * @param table_schem the table_schem to set
	 */
	public void setTable_schem(String table_schem) {
		this.table_schem = table_schem;
	}



	/**
	 * @return the table_name
	 */
	public String getTable_name() {
		return table_name;
	}



	/**
	 * @param table_name the table_name to set
	 */
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}



	/**
	 * @return the non_unique
	 */
	public Boolean getNon_unique() {
		return non_unique;
	}



	/**
	 * @param non_unique the non_unique to set
	 */
	public void setNon_unique(Boolean non_unique) {
		this.non_unique = non_unique;
	}



	/**
	 * @return the index_qualifter
	 */
	public String getIndex_qualifter() {
		return index_qualifter;
	}



	/**
	 * @param index_qualifter the index_qualifter to set
	 */
	public void setIndex_qualifter(String index_qualifter) {
		this.index_qualifter = index_qualifter;
	}



	/**
	 * @return the index_name
	 */
	public String getIndex_name() {
		return index_name;
	}



	/**
	 * @param index_name the index_name to set
	 */
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}



	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}



	/**
	 * @return the ordinal_position
	 */
	public String getOrdinal_position() {
		return ordinal_position;
	}



	/**
	 * @param ordinal_position the ordinal_position to set
	 */
	public void setOrdinal_position(String ordinal_position) {
		this.ordinal_position = ordinal_position;
	}



	/**
	 * @return the column_name
	 */
	public String getColumn_name() {
		return column_name;
	}



	/**
	 * @param column_name the column_name to set
	 */
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}



	/**
	 * @return the asc_or_desc
	 */
	public String getAsc_or_desc() {
		return asc_or_desc;
	}



	/**
	 * @param asc_or_desc the asc_or_desc to set
	 */
	public void setAsc_or_desc(String asc_or_desc) {
		this.asc_or_desc = asc_or_desc;
	}



	/**
	 * @return the cardinality
	 */
	public Integer getCardinality() {
		return cardinality;
	}



	/**
	 * @param cardinality the cardinality to set
	 */
	public void setCardinality(Integer cardinality) {
		this.cardinality = cardinality;
	}



	/**
	 * @return the pages
	 */
	public Integer getPages() {
		return pages;
	}



	/**
	 * @param pages the pages to set
	 */
	public void setPages(Integer pages) {
		this.pages = pages;
	}



	/**
	 * @return the filter_condition
	 */
	public String getFilter_condition() {
		return filter_condition;
	}



	/**
	 * @param filter_condition the filter_condition to set
	 */
	public void setFilter_condition(String filter_condition) {
		this.filter_condition = filter_condition;
	}

	
	

}
