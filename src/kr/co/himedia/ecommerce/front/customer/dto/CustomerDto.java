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
 * File			: CustomerDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240306104448][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.dto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-06
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class CustomerDto {

	private int seq_cst;
	private String id;
	private String passwd;
	private int tc_state;
	private String cst_nm;
	private String phone;
	private String postcode;
	private String addr1;
	private String addr2;
	private String cst_email;
	private String flg_sms;
	private String dt_sms;
	private String flg_user_agreement;
	private String dt_user;
	private String flg_personal_data;
	private String dt_personal;
	private String dt_reg;
	private String dt_upt;
	
	private String before_flg_sms;
	
	
	
	public String getBefore_flg_sms() {
		return before_flg_sms;
	}
	public void setBefore_flg_sms(String before_flg_sms) {
		this.before_flg_sms = before_flg_sms;
	}
	public int getSeq_cst() {
		return seq_cst;
	}
	public void setSeq_cst(int seq_cst) {
		this.seq_cst = seq_cst;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getTc_state() {
		return tc_state;
	}
	public void setTc_state(int tc_state) {
		this.tc_state = tc_state;
	}
	public String getCst_nm() {
		return cst_nm;
	}
	public void setCst_nm(String cst_nm) {
		this.cst_nm = cst_nm;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCst_email() {
		return cst_email;
	}
	public void setCst_email(String cst_email) {
		this.cst_email = cst_email;
	}
	public String getFlg_sms() {
		return flg_sms;
	}
	public void setFlg_sms(String flg_sms) {
		this.flg_sms = flg_sms;
	}
	public String getDt_sms() {
		return dt_sms;
	}
	public void setDt_sms(String dt_sms) {
		this.dt_sms = dt_sms;
	}
	public String getFlg_user_agreement() {
		return flg_user_agreement;
	}
	public void setFlg_user_agreement(String flg_user_agreement) {
		this.flg_user_agreement = flg_user_agreement;
	}
	public String getDt_user() {
		return dt_user;
	}
	public void setDt_user(String dt_user) {
		this.dt_user = dt_user;
	}
	public String getFlg_personal_data() {
		return flg_personal_data;
	}
	public void setFlg_personal_data(String flg_personal_data) {
		this.flg_personal_data = flg_personal_data;
	}
	public String getDt_personal() {
		return dt_personal;
	}
	public void setDt_personal(String dt_personal) {
		this.dt_personal = dt_personal;
	}
	public String getDt_reg() {
		return dt_reg;
	}
	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}
	public String getDt_upt() {
		return dt_upt;
	}
	public void setDt_upt(String dt_upt) {
		this.dt_upt = dt_upt;
	}
	
	
}
