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
 * File			: CtgDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240307143900][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.product.dto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-07
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class CtgDto {

	private int seq_ctg;
	private String ctg_nm;
	private int parents_ctg;
	private int level;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSeq_ctg() {
		return seq_ctg;
	}
	public void setSeq_ctg(int seq_ctg) {
		this.seq_ctg = seq_ctg;
	}
	public String getCtg_nm() {
		return ctg_nm;
	}
	public void setCtg_nm(String ctg_nm) {
		this.ctg_nm = ctg_nm;
	}
	public int getParents_ctg() {
		return parents_ctg;
	}
	public void setParents_ctg(int parents_ctg) {
		this.parents_ctg = parents_ctg;
	}
	
	
}
