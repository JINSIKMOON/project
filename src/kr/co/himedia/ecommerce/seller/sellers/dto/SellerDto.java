/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2023 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2023 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SellerDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231124174615][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sellers.dto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-24
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class SellerDto {
	
	private String id;
	private String passwd;
	private String corp_nm;
	private String corp_num;
	private String ceo;
	private String corp_postcode;
	private String corp_addr1;
	private String corp_addr2;
	private String corp_email;
	private String dt_reg;
	private String dt_upt;
	
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
	public String getCorp_nm() {
		return corp_nm;
	}
	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}
	public String getCorp_num() {
		return corp_num;
	}
	public void setCorp_num(String corp_num) {
		this.corp_num = corp_num;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getCorp_postcode() {
		return corp_postcode;
	}
	public void setCorp_postcode(String corp_postcode) {
		this.corp_postcode = corp_postcode;
	}
	public String getCorp_addr1() {
		return corp_addr1;
	}
	public void setCorp_addr1(String corp_addr1) {
		this.corp_addr1 = corp_addr1;
	}
	public String getCorp_addr2() {
		return corp_addr2;
	}
	public void setCorp_addr2(String corp_addr2) {
		this.corp_addr2 = corp_addr2;
	}
	public String getCorp_email() {
		return corp_email;
	}
	public void setCorp_email(String corp_email) {
		this.corp_email = corp_email;
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
