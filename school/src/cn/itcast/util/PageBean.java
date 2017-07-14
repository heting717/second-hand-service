package cn.itcast.util;



import java.util.List;

public class PageBean<T> {
	//当前页数
	private int currentPage = 1;
	//显示每页行数
	private int pageCount = 6;
	//总记录数
	private int totalCount;
	//总页数
	private int totalPage;
	//每页数据
	private List<T> pageData;
	//封装所有查询条件
	private Condition condition;
	
	public int getTotalPage() {
		if(totalCount%pageCount==0){
			totalPage = totalCount/pageCount;
		}else{
			totalPage = totalCount/pageCount+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageCount="
				+ pageCount + ", totalCount=" + totalCount + ", totalPage="
				+ totalPage + ", pageData=" + pageData + ", condition="
				+ condition + "]";
	}
}
