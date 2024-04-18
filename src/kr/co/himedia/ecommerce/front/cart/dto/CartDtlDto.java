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
 * File			: CartDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312114756][wlstlr@himedia.co.kr][CREATE: Initial Release]
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
public class CartDtlDto {
	
	private int seq_cart_dtl;
	private int seq_cart_mst;
	private int seq_prd;
	private int seq_sle;
	private int count;
	private int price;
	private String flg_delete;
	private String dt_cre;
	
	private String sle_nm;
	private String img;
	private String prd_nm;
	private String id;
	
	public int getSeq_cart_dtl() {
		return seq_cart_dtl;
	}
	public void setSeq_cart_dtl(int seq_cart_dtl) {
		this.seq_cart_dtl = seq_cart_dtl;
	}
	public int getSeq_cart_mst() {
		return seq_cart_mst;
	}
	public void setSeq_cart_mst(int seq_cart_mst) {
		this.seq_cart_mst = seq_cart_mst;
	}
	public int getSeq_prd() {
		return seq_prd;
	}
	public void setSeq_prd(int seq_prd) {
		this.seq_prd = seq_prd;
	}
	public int getSeq_sle() {
		return seq_sle;
	}
	public void setSeq_sle(int seq_sle) {
		this.seq_sle = seq_sle;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFlg_delete() {
		return flg_delete;
	}
	public void setFlg_delete(String flg_delete) {
		this.flg_delete = flg_delete;
	}
	public String getDt_cre() {
		return dt_cre;
	}
	public void setDt_cre(String dt_cre) {
		this.dt_cre = dt_cre;
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
	public String getPrd_nm() {
		return prd_nm;
	}
	public void setPrd_nm(String prd_nm) {
		this.prd_nm = prd_nm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
