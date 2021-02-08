package com.ict.spring.common;

import java.sql.Date;

public class SearchAndPage {
	private String keyword;
	private Date begin;
	private Date end;
	private int startRow;
	private int endRow;
	
	public SearchAndPage() {}

	public SearchAndPage(String keyword, Date begin, Date end, int startRow, int endRow) {
		super();
		this.keyword = keyword;
		this.begin = begin;
		this.end = end;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	@Override
	public String toString() {
		return "SearchAndPage [keyword=" + keyword + ", begin=" + begin + ", end=" + end + ", startRow=" + startRow
				+ ", endRow=" + endRow + "]";
	}
	
}