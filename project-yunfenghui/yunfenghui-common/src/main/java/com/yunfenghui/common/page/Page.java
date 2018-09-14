package com.yunfenghui.common.page;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author wanghao
 */
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize;

	public Page() {
	}

	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Page{" + "currentPage=" + currentPage + ", pageSize=" + pageSize + '}';
	}
}
