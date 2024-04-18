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
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: PayDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240208163602][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.buy.dto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class PayDto {
	
	private int seq_pay				= 0;
	private int seq_buy_mst			= 0;
	private String deal_num			= "";
	private String cd_pay_method	= "";
	private String flg_success		= "";
	private String dt_reg			= "";
	
	private String id			= "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq_pay() {
		return seq_pay;
	}
	public void setSeq_pay(int seq_pay) {
		this.seq_pay = seq_pay;
	}
	public int getSeq_buy_mst() {
		return seq_buy_mst;
	}
	public void setSeq_buy_mst(int seq_buy_mst) {
		this.seq_buy_mst = seq_buy_mst;
	}
	public String getDeal_num() {
		return deal_num;
	}
	public void setDeal_num(String deal_num) {
		this.deal_num = deal_num;
	}
	public String getCd_pay_method() {
		return cd_pay_method;
	}
	public void setCd_pay_method(String cd_pay_method) {
		this.cd_pay_method = cd_pay_method;
	}
	public String getFlg_success() {
		return flg_success;
	}
	public void setFlg_success(String flg_success) {
		this.flg_success = flg_success;
	}
	public String getDt_reg() {
		return dt_reg;
	}
	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}

}