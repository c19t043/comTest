package com.kybaby.newbussiness.doctorclinic.domain;

public class PageBean {
	/**
	 * 当前页
	 */
	private int curPage;   
	/**
	 * 总页数  
	 */  
    private int pageCount;           
    /**
	 * 总行数  
	 */
    private int rowsCount;  
    /**
     * 每页多少行  
     */
    private int pageSize;      
      
    public int getCurPage() {  
        return curPage;  
    }  
    public void setCurPage(int curPage) {  
        this.curPage = curPage;  
    }  
    public int getPageCount() {  
        return pageCount;  
    }  
    public void setPageCount(int pageCount) {  
        this.pageCount = pageCount;  
    }  
    public int getPageSize() {  
        return pageSize;  
    }  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
    public int getRowsCount() {  
        return rowsCount;  
    }  
    public void setRowsCount(int rowsCount) {  
        this.rowsCount = rowsCount;  
    }  
}
