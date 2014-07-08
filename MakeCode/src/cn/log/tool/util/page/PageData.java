/**
 * 
 */
package cn.log.tool.util.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 一页数据封装对象
 * @author zouqone
 * @createTime 2014-03-16
 */
public class PageData {
	
	/**
	 * 默认每页显示记录条数20
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * 默认当前页码1
	 */
	public static final int DEFAULT_CURRENT_PAGE = 1;
	
	/**输出页码个数*/
	private static final int pageCount = 11;
	
	/**
	 * 默认页面范围大小10
	 */
	//public static final int DEFAULT_PAGE_RANGE_SIZE = 10;
	
    /**
     * 数据集合
     */
	private Collection items;
	
	/**存储待输出的页码集合*/
	private List pageNums;
	
	/**
	 * 总记录数
	 */
	private int totalRows;
	
	/**
	 * 每页显示记录条数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/**
	 * 当前页码
	 */
    private int currentPage = DEFAULT_CURRENT_PAGE;
    
    /**
     * 总页数
     */
    private int totalPage;
    
    /**
     * 偏移量，用于计算该页起始行(从0开始计算)
     */
    private int offset;
    
    /**
     * 区域大小,用于类似于论坛分页效果，如：3 4 5 6 7 8 ... 20 21 22 23 
     */
    //private int pageRangeSize = DEFAULT_PAGE_RANGE_SIZE;
    
    /**
     * 区域总数
     */
    private int totalPageRange;
    
    /**
     * 当前区域包含的页码
     */
    private int[] currentRangePages;
    
    /**
     * 是否还有下一页
     */
    private boolean hasNextPage;
    
	public Collection getItems() {
		if(null == items){
			items = new ArrayList();
		}
		return items;
	}
	
	public void setItems(Collection items) {
		this.items = items;
	}
	
	public int getTotalRows() {
		return totalRows;
	}
	
	public void setTotalRows(int totalRows) {
		if(totalRows <= 0){
			totalRows = 0;
		}
		this.totalRows = totalRows;
	}
	
	public int getPageSize() {
		if(pageSize <= 0){
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		if(currentPage <= 0){
			return DEFAULT_CURRENT_PAGE;
		}
		if(getTotalPage() > 0 && currentPage > getTotalPage()){
			return getTotalPage();
		}
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		if(currentPage <= 0){
			currentPage = DEFAULT_CURRENT_PAGE;
		}
		this.currentPage = currentPage;
	}
	
	public int getTotalPage() {
		totalPage = totalRows / pageSize;
		if(totalRows % pageSize != 0){
			totalPage++;
		}
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		if(totalPage <= 0){
			totalPage = 0;
		}
		this.totalPage = totalPage;
	}

	public int getOffset() {
		offset = getStartOfPage();
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getTotalPageRange() {
		return totalPageRange;
	}
	
	public void setTotalPageRange(int totalPageRange) {
		this.totalPageRange = totalPageRange;
	}
	
	public boolean isHasNextPage() {
		hasNextPage = (getCurrentPage() < getTotalPage());
		return hasNextPage;
	}
	
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	/**
	 * 默认的构造函数
	 */
	public PageData() {
		setPageSize(DEFAULT_PAGE_SIZE);
		setCurrentPage(DEFAULT_CURRENT_PAGE);
		//setPageRangeSize(DEFAULT_PAGE_RANGE_SIZE);
	}
	
	/**
	 * 带页面显示大小的构造函数
	 */
	public PageData(int pageSize) {
		setPageSize(pageSize);
		setCurrentPage(DEFAULT_CURRENT_PAGE);
		//setPageRangeSize(DEFAULT_PAGE_RANGE_SIZE);
	}
	
	/**
	 * 带当前页码和页面显示大小的构造函数
	 */
	public PageData(int currentPage,int pageSize) {
		setPageSize(pageSize);
		setCurrentPage(currentPage);
		//setPageRangeSize(DEFAULT_PAGE_RANGE_SIZE);
	}
	
	/**
	 * 带当前页码和页面显示大小及页面范围大小的构造函数
	 */
	public PageData(int currentPage,int pageSize,int pageRangeSize) {
		setPageSize(pageSize);
		setCurrentPage(currentPage);
		//setPageRangeSize(pageRangeSize);
	}
	
	/**
	 * 带总记录数和数据集合的构造函数
	 */
	public PageData(int totalRows, Collection items) {
		this();
		this.totalRows = totalRows;
		this.items = items;
	}
	
	/**
	 * 带数据集合、总记录数、当前页码、每页显示大小的构造函数
	 */
	public PageData(Collection items, int totalRows, int currentPage, int pageSize) {
		this(currentPage,pageSize);
		this.items = items;
		this.totalRows = totalRows;
	}
	
	/**
	 * 计算当前页记录的开始行号(从1开始计算)
	 */
	protected int getStartOfPage(){
		int num = (currentPage - 1) * pageSize + 1;
		if(num > totalRows){
			num = totalRows;
		}
		return num;
	} 
	
	/**
	 * 计算当前页记录的结束行号(从1开始计算)
	 */
	protected int getEndOfPage(){
		int num = getStartOfPage() + pageSize;		
		if(num >= totalRows){
			return totalRows;
		}
		return num - 1;
	}
	
	public List getPageNums() {
		if(null == pageNums){
			pageNums = new ArrayList();
		}
		//int start = getStart();
		//总页数
		int totalPage = getTotalPage();
		//当前页码
		int currentPage = getCurrentPage();
		
		//若总页数小于约定输出页码的个数
		if(totalPage < pageCount){
			for(int i = 0; i < totalPage; i++){
				pageNums.add(((i + 1) + ""));
			}
		}
		else{
			int diff = currentPage - (pageCount - 1) / 2;
			int index = 0;
			int end = 0;
			//若当前页码小于约定输出页码个数的中间位置
			if(diff < 0){
				end = (currentPage + 1) + (pageCount - 1) / 2 + Math.abs(diff);
			} else {
				index = Math.abs(diff) - 1;
				int remain = totalPage - currentPage;
				
				if(remain < (pageCount - 1) / 2){
					end = totalPage;
				} else{
					end = currentPage + (pageCount - 1) / 2;
				}
			}
			
			for(int i = index; i < end; i++){
				pageNums.add(((i + 1) + ""));
			}
		}
		return pageNums;
	}
	
	public void setPageNums(List pageNums) {
		this.pageNums = pageNums;
	}
}
