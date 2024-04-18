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
 * File			: SellerDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231127121746][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sellers.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.seller.common.dao.BaseDao;
import kr.co.himedia.ecommerce.seller.sellers.dto.SellerDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-27
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.seller.sellers.dao.SellerDao")
public class SellerDao extends BaseDao {
	
	public int update(SellerDto sellerDto) {
		return sqlSessionSeller.update("kr.co.himedia.ecommerce.seller.mybatis.sellers.Seller.update", sellerDto);
	}
	
	public SellerDto select(String id) {
		return sqlSessionSeller.selectOne("kr.co.himedia.ecommerce.seller.mybatis.sellers.Seller.select", id);
	}
	
	/**
	 * @param id [판매자 아이디]
	 * @return List<SellerDto>
	 * 
	 * @since 2016-02-12
	 * <p>DESCRIPTION: 목록(전체)</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	public List<SellerDto> listing(String id) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		return sqlSessionSeller.selectList("kr.co.himedia.ecommerce.seller.mybatis.sellers.Seller.listing", map);
	}
	
	public List<SellerDto> list() {
		return sqlSessionSeller.selectList("kr.co.himedia.ecommerce.seller.mybatis.sellers.Seller.list");
	}
	
	public int insert(SellerDto sellerDto) {
		return sqlSessionSeller.insert("kr.co.himedia.ecommerce.seller.mybatis.sellers.Seller.insert", sellerDto); 
	}
	public int sequence() {
		return sqlSessionSeller.selectOne("kr.co.himedia.ecommerce.seller.mybatis.sellers.Seller.sequence"); 
	}

}