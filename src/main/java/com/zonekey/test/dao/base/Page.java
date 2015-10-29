package com.zonekey.test.dao.base;

import java.util.List;
import java.util.Map;

/**
 * 分页查询返回数据 前台传递参数时只传递pageNo,pageSize
 * 
 * @author admin
 * 
 * @param <T>
 */
public class Page {
	// 起始行号，
	private int startIndex;
	// 总的记录数
	private long totalCount;
	// 每页记录数，*
	private int pageSize;
	// 总的页数
	private int pageCount;
	// 当前页号，*
	private int pageNo;
	// 显示多少个页号
	private int pageNums = 10;
	// 起始页号
	private int startOfPage;
	// 结束页号
	private int endOfPage;
	// 数据集
	private List<?> data;
	// 查询关键字
	private Map<String, String> keywords;

	/**
	 * 传参时创建
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param keywords
	 */
	public Page(int pageNo, int pageSize, Map<String, String> keywords) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.keywords = keywords;
		this.startIndex = (pageNo - 1) * pageSize;
	}

	/**
	 * 封装数据时创建
	 * 
	 * @param startIndex
	 * @param totalCount
	 * @param pageSize
	 * @param list
	 */
	public Page(int startIndex, long totalCount, int pageSize, List<?> list) {
		// 分页时传参
		this.startIndex = startIndex;
		this.pageSize = pageSize;
		// 查询数据后取值
		this.totalCount = totalCount;
		// 计算页号
		this.setPageNo();
		// 计算总的页数
		this.setPageCount();
		// 计算页号的起始值
		this.setStartOfPage();
		// 计算页号的结束值
		this.setEndOfPage();
		this.data = list;
	}

	public Page() {
	}

	public int getStartIndex() {
		return startIndex;
	}

	//
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public long getTotalCount() {
		return totalCount;
	}

	//
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	//
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 计算总的页数
	 * 
	 * @param pageCount
	 */
	public void setPageCount() {
		this.pageCount = (int) (totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
	}

	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页
	 * 
	 * @param pageNo
	 */
	public void setPageNo() {
		this.pageNo = startIndex % pageSize + 1;
	}

	public int getStartOfPage() {
		return startOfPage;
	}

	/**
	 * 设置起始页号
	 * 
	 * @param startOfPage
	 */
	public void setStartOfPage() {
		if (pageCount > pageNums) {
			if (pageNo > 6) {
				this.startOfPage = (pageCount - pageNo > 4) ? pageNo - 5 : (pageCount - 9);
			} else {
				this.startOfPage = 1;
			}
		} else {
			this.startOfPage = 1;
		}
	}

	public int getEndOfPage() {
		return endOfPage;
	}

	/**
	 * 设置结束页号
	 * 
	 * @return
	 */
	public void setEndOfPage() {
		if (pageCount > pageNums) {
			if (pageNo > 6) {
				// this.startOfPage = (pageCount - pageNo > 4) ? pageNo - 5 :
				// (pageCount - 9);
				this.endOfPage = (pageCount - pageNo > 4) ? pageNo + 4 : pageCount;
			} else {
				// this.startOfPage = 1;
				this.endOfPage = 10;
			}
		} else {
			// this.startOfPage = 1;
			this.endOfPage = pageCount;
		}
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Map<String, String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Map<String, String> keywords) {
		this.keywords = keywords;
	}

	public int getPageNums() {
		return pageNums;
	}

	/**
	 * 设置显示的页号的个数 默认值为10
	 * 
	 * @param pageNums
	 */
	public void setPageNums(int pageNums) {
		this.pageNums = pageNums;
	}

	@Override
	public String toString() {
		return "Page [startIndex=" + startIndex + ", totalCount=" + totalCount + ", pageSize=" + pageSize + ", pageCount=" + pageCount + ", pageNo=" + pageNo + ", pageNums=" + pageNums
				+ ", startOfPage=" + startOfPage + ", endOfPage=" + endOfPage + ", data=" + data + ", keywords=" + keywords + "]";
	}

}
