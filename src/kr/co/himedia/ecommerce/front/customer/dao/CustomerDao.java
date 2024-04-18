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
 * File			: CustomerDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240306104542][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.front.common.dao.BaseDao;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-06
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.front.customer.dao.CustomerDao")
public class CustomerDao extends BaseDao{
	
	public int updatePw(CustomerDto customerDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.updatePw", customerDto); 
	}
	
	public CustomerDto findPw(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.findPw", customerDto);
	}

	public List<CustomerDto> listing(String id) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.listing", map);
	}
	
	public List<CustomerDto> list() {
		return sqlSessionFront.selectList("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.list");
	}
	
	public CustomerDto findId(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.findId", customerDto);
	}
	
	public int update(CustomerDto customerDto) {
		return sqlSessionFront.update("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.update", customerDto); 
	}
	
	public CustomerDto select(CustomerDto customerDto) {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.select", customerDto);
	}
	
	public int insert(CustomerDto customerDto) {
		return sqlSessionFront.insert("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.insert", customerDto); 
	}
	
	public int sequence() {
		return sqlSessionFront.selectOne("kr.co.himedia.ecommerce.front.mybatis.customer.Customer.sequence");
	}
}
