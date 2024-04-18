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
 * File			: ProductDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312154824][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.product.dto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class ProductDto {
	
	private int rnum;
	private int seq_prd;
	private int seq_ctg;
	private String prd_nm;
	private String prd_img;
	private String desces;
	private int price_cost;
	private int delivery_price;
	private int count_stock;
	private int cd_state_prd;
	private int discount;
	private String flg_delete;
	private String dt_reg;
	private String dt_upt;
	
	public int getCount_stock() {
		return count_stock;
	}
	public void setCount_stock(int count_stock) {
		this.count_stock = count_stock;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getSeq_prd() {
		return seq_prd;
	}
	public void setSeq_prd(int seq_prd) {
		this.seq_prd = seq_prd;
	}
	public int getSeq_ctg() {
		return seq_ctg;
	}
	public void setSeq_ctg(int seq_ctg) {
		this.seq_ctg = seq_ctg;
	}
	public String getPrd_nm() {
		return prd_nm;
	}
	public void setPrd_nm(String prd_nm) {
		this.prd_nm = prd_nm;
	}
	public String getPrd_img() {
		return prd_img;
	}
	public void setPrd_img(String prd_img) {
		this.prd_img = prd_img;
	}
	public String getDesces() {
		return desces;
	}
	public void setDesces(String desces) {
		this.desces = desces;
	}
	public int getPrice_cost() {
		return price_cost;
	}
	public void setPrice_cost(int price_cost) {
		this.price_cost = price_cost;
	}
	public int getDelivery_price() {
		return delivery_price;
	}
	public void setDelivery_price(int delivery_price) {
		this.delivery_price = delivery_price;
	}
	public int getCd_state_prd() {
		return cd_state_prd;
	}
	public void setCd_state_prd(int cd_state_prd) {
		this.cd_state_prd = cd_state_prd;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getFlg_delete() {
		return flg_delete;
	}
	public void setFlg_delete(String flg_delete) {
		this.flg_delete = flg_delete;
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
