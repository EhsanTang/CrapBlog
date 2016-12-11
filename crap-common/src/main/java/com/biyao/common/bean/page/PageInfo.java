/** 
  * Copyright @ 2016  shuibian Co. Ltd. 
  * All right reserved. 
  * @author: Lijiannan 
  * date: 	 2016年8月4日上午10:16:54 
  */
package com.biyao.common.bean.page;

/**
 * @description 分页信息
 * @author		Lijiannan
 * @time   		2016年8月4日上午10:16:54
 * @version 	1.0
 */
public class PageInfo {

	private int pageIndex;
	private int pageSize;
	private int totalRows;
	private int totalPage;
	
	public PageInfo(){}
	public PageInfo(int pageIndex, int pageSize, int totalRows, int totalPage){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
		this.totalPage = totalPage;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}
	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
