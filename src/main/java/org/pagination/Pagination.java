package org.pagination;

import java.util.List;

public interface Pagination<T> {

	public List<T> getList();
	public Integer getTotalLines();
	public Integer getTotalPages();
	public Integer getPageIndex();
	public Integer getPageSize();
	public Boolean getIsFirst();
	public Boolean getIsLast();
	public Boolean getHasNext();
	public Boolean getHasPrevious();
}
