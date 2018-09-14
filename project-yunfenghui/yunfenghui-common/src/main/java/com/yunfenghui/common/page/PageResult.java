package com.yunfenghui.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @param <E>
 * @author wanghao
 */
public class PageResult<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize;
	private long total;
	private List<E> rows;

	public PageResult(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public PageResult(int currentPage, int pageSize, long total, List<E> rows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
	}

	public int getCurrentPage() {
		if (total > 0 && currentPage > 1) {
			int ps = pages();
			if (currentPage > ps) {
				currentPage = ps;
			}
		} else {
			currentPage = 1;
		}
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotal() {
		return total;
	}

	public List<E> getRows() {
		return rows;
	}

	public boolean isHasNext() {
		return total > (currentPage * pageSize);
	}

	private int pages() {
		int pages = (int) (total / pageSize);
		if (total % pageSize > 0) {
			pages += 1;
		}
		return pages;
	}

	public boolean isHasPre() {
		boolean hasPre = false;
		int cp = getCurrentPage();
		if (total > 0) {
			if (cp <= pages() && cp > 1) {
				hasPre = true;
			}
		}
		return hasPre;
	}
}
