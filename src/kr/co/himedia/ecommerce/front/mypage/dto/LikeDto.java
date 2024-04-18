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
public class LikeDto {
	
	private int rnum;
	private Integer like_code;
	private Integer seq_sle;
	private Integer seq_cst;
	
	
	
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public Integer getLike_code() {
		return like_code;
	}
	public void setLike_code(Integer like_code) {
		this.like_code = like_code;
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
	
}
