/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.mercury
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: myPageDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240327115246][ek@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.mypage.dto;

/**
 * @version 1.0.0
 * @author ek@himedia.co.kr
 * 
 * @since 2024-03-27
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class SaleEvalDto {
	
	private int rnum;
	private int seq_sle_eval;
	private Integer seq_sle;
	private Integer seq_cst;
	private int score;
	private int average;
	private int customer;
	private String id;
	private String review;
	private String dt_reg = "";
	private String sle_nm;
	
	private int linePerPage			= 10;
	private int currentPage			= 1;
	
	private String week 	="";
	private String month 	="";
	private String year		="";
	
	
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSle_nm() {
		return sle_nm;
	}
	public void setSle_nm(String sle_nm) {
		this.sle_nm = sle_nm;
	}
	public Integer getSeq_sle() {
		return seq_sle;
	}
	public void setSeq_sle(Integer seq_sle) {
		this.seq_sle = seq_sle;
	}
	public Integer getSeq_cst() {
		return seq_cst;
	}
	public void setSeq_cst(Integer seq_cst) {
		this.seq_cst = seq_cst;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCustomer() {
		return customer;
	}
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public int getLinePerPage() {
		return linePerPage;
	}
	public void setLinePerPage(int linePerPage) {
		this.linePerPage = linePerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getSeq_sle_eval() {
		return seq_sle_eval;
	}
	public void setSeq_sle_eval(int seq_sle_eval) {
		this.seq_sle_eval = seq_sle_eval;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getDt_reg() {
		return dt_reg;
	}
	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}
	
	

}
