package cn.xiaoyu.common;

/**
 * xiaoyu
 */

import java.util.List;

public class PageBean<T> {
    // 当前页
    private Integer pageNo = 1;
    // 每页显示的总条数
    private Integer pageSize = 10;
    // 总条数
    private Integer rowCount;
    // 是否有下一页
    private Integer isMore;
    // 总页数
    private Integer pageCount;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> list;

    public PageBean() {
        super();
    }

    public PageBean(Integer pageNo, Integer pageSize, Integer rowCount) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.pageCount = (this.rowCount+this.pageSize-1)/this.pageSize;
        this.startIndex = (this.pageNo-1)*this.pageSize;
        this.isMore = this.pageNo >= this.pageCount?0:1;
    }

 

    public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

 
    public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

 
    public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

 
}