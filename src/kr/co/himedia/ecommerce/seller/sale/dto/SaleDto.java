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
 * File			: SaleDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240313152141][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sale.dto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-13
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class SaleDto {

	private int rnum;
	private int seq_sle				= 0;
	private int seq_prd				= 0;
	private String sle_nm			= "";
	private String ctg_no			= "";
	private String img				= "";
	private String desces			= "";
	private int price_sale			= 0;
	private int cd_state_sale		= 0;
	private String dt_sale_start	= "";
	private String dt_sale_end		= "";
	private String dt_reg			= "";
	private String dt_upt			= "";
	
	public String getCtg_no() {
		return ctg_no;
	}
	public void setCtg_no(String ctg_no) {
		this.ctg_no = ctg_no;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getSeq_sle() {
		return seq_sle;
	}
	public void setSeq_sle(int seq_sle) {
		this.seq_sle = seq_sle;
	}
	public int getSeq_prd() {
		return seq_prd;
	}
	public void setSeq_prd(int seq_prd) {
		this.seq_prd = seq_prd;
	}
	public String getSle_nm() {
		return sle_nm;
	}
	public void setSle_nm(String sle_nm) {
		this.sle_nm = sle_nm;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDesces() {
		return desces;
	}
	public void setDesces(String desces) {
		this.desces = desces;
	}
	public int getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(int price_sale) {
		this.price_sale = price_sale;
	}
	public int getCd_state_sale() {
		return cd_state_sale;
	}
	public void setCd_state_sale(int cd_state_sale) {
		this.cd_state_sale = cd_state_sale;
	}
	public String getDt_sale_start() {
		return dt_sale_start;
	}
	public void setDt_sale_start(String dt_sale_start) {
		this.dt_sale_start = dt_sale_start;
	}
	public String getDt_sale_end() {
		return dt_sale_end;
	}
	public void setDt_sale_end(String dt_sale_end) {
		this.dt_sale_end = dt_sale_end;
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
