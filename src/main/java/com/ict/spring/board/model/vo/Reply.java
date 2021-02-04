package com.ict.spring.board.model.vo;

import java.sql.Date;

public class Reply implements java.io.Serializable {
	private final static long serialVersionUID = 4444L;
	
	private int rid;
	private String rcontent;
	private int ref_bid;
	private String rwriter;
	private Date r_create_date;
	private Date r_modify_date;
	private String r_status;
	
	public Reply() {}

	public Reply(int rid, String rcontent, int ref_bid, String rwriter, Date r_create_date, Date r_modify_date,
			String r_status) {
		super();
		this.rid = rid;
		this.rcontent = rcontent;
		this.ref_bid = ref_bid;
		this.rwriter = rwriter;
		this.r_create_date = r_create_date;
		this.r_modify_date = r_modify_date;
		this.r_status = r_status;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public int getRef_bid() {
		return ref_bid;
	}

	public void setRef_bid(int ref_bid) {
		this.ref_bid = ref_bid;
	}

	public String getRwriter() {
		return rwriter;
	}

	public void setRwriter(String rwriter) {
		this.rwriter = rwriter;
	}

	public Date getR_create_date() {
		return r_create_date;
	}

	public void setR_create_date(Date r_create_date) {
		this.r_create_date = r_create_date;
	}

	public Date getR_modify_date() {
		return r_modify_date;
	}

	public void setR_modify_date(Date r_modify_date) {
		this.r_modify_date = r_modify_date;
	}

	public String getR_status() {
		return r_status;
	}

	public void setR_status(String r_status) {
		this.r_status = r_status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", rcontent=" + rcontent + ", ref_bid=" + ref_bid + ", rwriter=" + rwriter
				+ ", r_create_date=" + r_create_date + ", r_modify_date=" + r_modify_date + ", r_status=" + r_status
				+ "]";
	}
	
//	private int rId;
//	private String rContent;
//	private int refBid;
//	private String rWriter;
//	private Date rCreateDate;
//	private Date rModifyDate;
//	private String rStatus;
//	
//	public Reply() {}
//
//	public Reply(int rId, String rContent, int refBid, String rWriter, Date rCreateDate, Date rModifyDate,
//			String rStatus) {
//		super();
//		this.rId = rId;
//		this.rContent = rContent;
//		this.refBid = refBid;
//		this.rWriter = rWriter;
//		this.rCreateDate = rCreateDate;
//		this.rModifyDate = rModifyDate;
//		this.rStatus = rStatus;
//	}
//
//	public int getrId() {
//		return rId;
//	}
//
//	public void setrId(int rId) {
//		this.rId = rId;
//	}
//
//	public String getrContent() {
//		return rContent;
//	}
//
//	public void setrContent(String rContent) {
//		this.rContent = rContent;
//	}
//
//	public int getRefBid() {
//		return refBid;
//	}
//
//	public void setRefBid(int refBid) {
//		this.refBid = refBid;
//	}
//
//	public String getrWriter() {
//		return rWriter;
//	}
//
//	public void setrWriter(String rWriter) {
//		this.rWriter = rWriter;
//	}
//
//	public Date getrCreateDate() {
//		return rCreateDate;
//	}
//
//	public void setrCreateDate(Date rCreateDate) {
//		this.rCreateDate = rCreateDate;
//	}
//
//	public Date getrModifyDate() {
//		return rModifyDate;
//	}
//
//	public void setrModifyDate(Date rModifyDate) {
//		this.rModifyDate = rModifyDate;
//	}
//
//	public String getrStatus() {
//		return rStatus;
//	}
//
//	public void setrStatus(String rStatus) {
//		this.rStatus = rStatus;
//	}
//
//	@Override
//	public String toString() {
//		return "Reply [rId=" + rId + ", rContent=" + rContent + ", refBid=" + refBid + ", rWriter=" + rWriter
//				+ ", rCreateDate=" + rCreateDate + ", rModifyDate=" + rModifyDate + ", rStatus=" + rStatus + "]";
//	}

}
