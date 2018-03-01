package org.pagination;

import java.util.List;

public class DefaultPagination<T> implements Pagination<T> {
		private List<T> list;
		private Integer totalLines;
		private Integer totalPages;
		private Integer pageIndex=0;
		private Integer pageSize=3;
		private QueryHander<T> queryHander;
		
		public DefaultPagination(Integer pageIndex,Integer pageSize,QueryHander<T> queryHander) {
			if (pageIndex == null) {
				pageIndex = this.pageIndex;
			}
			if (pageSize == null) {
				pageSize = this.pageSize;
			}
			if (queryHander == null) {
				throw new IllegalArgumentException("查询回调对象不能为空！");
			}
			this.queryHander = queryHander;
			setTotalLines();
			setTotalPages();
			setPageSize(pageSize);
			setPageIndex(pageIndex);
			setList();
		}
		
		private void setList() {
			list = queryHander.getList(pageIndex,pageSize);
		}

		private void setPageIndex(Integer pageIndex) {
			if(pageIndex<1){
				this.pageIndex=1;
			}else if(pageIndex>totalPages){
				this.pageIndex=totalPages;
			}else {
				this.pageIndex=pageIndex;
			}
		}

		private void setTotalPages() {
			totalPages = totalLines%pageSize == 0 ? totalLines/pageSize :totalLines/pageSize+1;
		}

		private void setPageSize(Integer pageSize) {
			if(pageSize<1){
				this.pageSize=1;
			}else if(pageSize>totalLines){
				this.pageSize=totalLines;
			}else {
				this.pageSize=pageSize;
			}
		}

		private void setTotalLines() {
			totalLines = queryHander.getTotalLines();
		}

		@Override
		public List<T> getList() {
			return list;
		}

		@Override
		public Integer getTotalLines() {
			return totalLines;
		}

		@Override
		public Integer getTotalPages() {
			return totalPages;
		}

		@Override
		public Integer getPageIndex() {
			return pageIndex;
		}

		@Override
		public Integer getPageSize() {
			return pageSize;
		}

		@Override
		public Boolean getIsFirst() {
			return pageIndex==1;
		}

		@Override
		public Boolean getIsLast() {
			return pageIndex==totalPages;
		}

		@Override
		public Boolean getHasNext() {
			return pageIndex<totalPages;
		}

		@Override
		public Boolean getHasPrevious() {
			return pageIndex>1;
		}

}
