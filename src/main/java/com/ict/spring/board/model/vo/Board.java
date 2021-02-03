package com.ict.spring.board.model.vo;

import java.sql.Date;

public class Board {
	private final static long serialVersionUID = 3333L;
	
	private int bid;
	private String btitle;
	private String bwriter;
	private String bcontent;
	private String original_filename;
	private String rename_filename;
	private int bcount;
	private Date b_create_date;
	private Date b_modify_date;
	private String b_status;
	
	public Board() {}

	public Board(int bid, String btitle, String bwriter, String bcontent, String original_filename,
			String rename_filename, int bcount, Date b_create_date, Date b_modify_date, String b_status) {
		super();
		this.bid = bid;
		this.btitle = btitle;
		this.bwriter = bwriter;
		this.bcontent = bcontent;
		this.original_filename = original_filename;
		this.rename_filename = rename_filename;
		this.bcount = bcount;
		this.b_create_date = b_create_date;
		this.b_modify_date = b_modify_date;
		this.b_status = b_status;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getOriginal_filename() {
		return original_filename;
	}

	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}

	public String getRename_filename() {
		return rename_filename;
	}

	public void setRename_filename(String rename_filename) {
		this.rename_filename = rename_filename;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public Date getB_create_date() {
		return b_create_date;
	}

	public void setB_create_date(Date b_create_date) {
		this.b_create_date = b_create_date;
	}

	public Date getB_modify_date() {
		return b_modify_date;
	}

	public void setB_modify_date(Date b_modify_date) {
		this.b_modify_date = b_modify_date;
	}

	public String getB_status() {
		return b_status;
	}

	public void setB_status(String b_status) {
		this.b_status = b_status;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", btitle=" + btitle + ", bwriter=" + bwriter + ", bcontent=" + bcontent
				+ ", original_filename=" + original_filename + ", rename_filename=" + rename_filename + ", bcount="
				+ bcount + ", b_create_date=" + b_create_date + ", b_modify_date=" + b_modify_date + ", b_status="
				+ b_status + "]";
	}

}
