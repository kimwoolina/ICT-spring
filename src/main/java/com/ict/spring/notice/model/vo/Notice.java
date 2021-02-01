package com.ict.spring.notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	private static final long serialVersionUID = 2222L;
	
	private int nid;
	private String ntitle;
	private String nwriter;
	private String ncontent;
	private Date n_create_date;
	private Date n_modify_date;
	private String file_path;
	
	public Notice() {}

	public Notice(int nid, String ntitle, String nwriter, String ncontent, Date n_create_date, Date n_modify_date,
			String file_path) {
		super();
		this.nid = nid;
		this.ntitle = ntitle;
		this.nwriter = nwriter;
		this.ncontent = ncontent;
		this.n_create_date = n_create_date;
		this.n_modify_date = n_modify_date;
		this.file_path = file_path;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNwriter() {
		return nwriter;
	}

	public void setNwriter(String nwriter) {
		this.nwriter = nwriter;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public Date getN_create_date() {
		return n_create_date;
	}

	public void setN_create_date(Date n_create_date) {
		this.n_create_date = n_create_date;
	}

	public Date getN_modify_date() {
		return n_modify_date;
	}

	public void setN_modify_date(Date n_modify_date) {
		this.n_modify_date = n_modify_date;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [nid=" + nid + ", ntitle=" + ntitle + ", nwriter=" + nwriter + ", ncontent=" + ncontent
				+ ", n_create_date=" + n_create_date + ", n_modify_date=" + n_modify_date + ", file_path=" + file_path
				+ "]";
	}

}
