package org.pagination;

import java.util.List;

public interface QueryHander<T> {

	public Integer getTotalLines();

	public List<T> getList(Integer pageIndex, Integer pageSize);

}
