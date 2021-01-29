package com.ict.spring.board.model.vo;

import java.sql.Date;

public class Board {
	private int bId;
	private String bTitle;
	private String bWriter;
	private String bContent;
	private String originalFileName;
	private String renameFileName;
	private int bCount;
	private Date bCreateDate;
	private Date bModifyDate;
	private String bStatus;
	
	public Board() {}

	public Board(int bId, String bTitle, String bWriter, String bContent, String originalFileName,
			String renameFileName, int bCount, Date bCreateDate, Date bModifyDate, String bStatus) {
		super();
		this.bId = bId;
		this.bTitle = bTitle;
		this.bWriter = bWriter;
		this.bContent = bContent;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.bCount = bCount;
		this.bCreateDate = bCreateDate;
		this.bModifyDate = bModifyDate;
		this.bStatus = bStatus;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public Date getbCreateDate() {
		return bCreateDate;
	}

	public void setbCreateDate(Date bCreateDate) {
		this.bCreateDate = bCreateDate;
	}

	public Date getbModifyDate() {
		return bModifyDate;
	}

	public void setbModifyDate(Date bModifyDate) {
		this.bModifyDate = bModifyDate;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	@Override
	public String toString() {
		return "Board [bId=" + bId + ", bTitle=" + bTitle + ", bWriter=" + bWriter + ", bContent=" + bContent
				+ ", originalFileName=" + originalFileName + ", renameFileName=" + renameFileName + ", bCount=" + bCount
				+ ", bCreateDate=" + bCreateDate + ", bModifyDate=" + bModifyDate + ", bStatus=" + bStatus + "]";
	}
	
	
}
