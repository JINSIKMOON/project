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
 * File			: CartmstDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312121020][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.cart.dto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class CartMstDto {

	private int seq_cart_mst;
	private String id;
	private int cart_t_count;
	private int cart_t_price;
	private int dt_cre;
	private String prd_nm;
	
	public int getSeq_cart_mst() {
		return seq_cart_mst;
	}
	public void setSeq_cart_mst(int seq_cart_mst) {
		this.seq_cart_mst = seq_cart_mst;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCart_t_count() {
		return cart_t_count;
	}
	public void setCart_t_count(int cart_t_count) {
		this.cart_t_count = cart_t_count;
	}
	public int getCart_t_price() {
		return cart_t_price;
	}
	public void setCart_t_price(int cart_t_price) {
		this.cart_t_price = cart_t_price;
	}
	public int getDt_cre() {
		return dt_cre;
	}
	public void setDt_cre(int dt_cre) {
		this.dt_cre = dt_cre;
	}
	public String getPrd_nm() {
		return prd_nm;
	}
	public void setPrd_nm(String prd_nm) {
		this.prd_nm = prd_nm;
	}
	
	
}
