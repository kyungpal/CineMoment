package com.cinemoment.movie.mypage.vo;

public class MyPageVO {
	private int morder_seq_num;
	private String member_id;
	private String beginDate;
	private String endDate;
	
	public MyPageVO(String member_id, String beginDate, String endDate) {
		super();
		this.member_id = member_id;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public int getMorder_seq_num() {
		return morder_seq_num;
	}

	public void setMorder_seq_num(int morder_seq_num) {
		this.morder_seq_num = morder_seq_num;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
