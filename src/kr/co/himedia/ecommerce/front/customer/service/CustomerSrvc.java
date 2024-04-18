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
 * File			: CustomerSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240306105141][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.front.customer.dao.CustomerDao;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-06
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc")
public class CustomerSrvc {
	
	@Inject
	CustomerDao customerDao;
	
	@Transactional("txFront")
	
	public boolean updatePw(CustomerDto customerDto) {
		
		int result = customerDao.updatePw(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public CustomerDto findPw(CustomerDto customerDto) {
		return customerDao.findPw(customerDto);		
	}
	
	public CustomerDto findId(CustomerDto customerDto) {
		return customerDao.findId(customerDto);		
	}			
	
	public List<CustomerDto> listing(String id) {
		return customerDao.listing(id);
	}
	public List<CustomerDto> list() {
		return customerDao.list();
	}
	
	public boolean update(CustomerDto customerDto) {
		
		int result = customerDao.update(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public CustomerDto select(CustomerDto customerDto) {
		return customerDao.select(customerDto);
	}
	
	@Transactional("txFront")
	public boolean insert(CustomerDto customerDto) {
		
		customerDto.setSeq_cst(customerDao.sequence());
		
		int result = customerDao.insert(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public CustomerDto findCst_nm(String cst_nm) {
	    // 사용자명을 기준으로 사용자 정보를 조회하는 코드를 작성합니다.
	    // 이 코드는 예시일 뿐이며 실제로는 데이터베이스나 다른 저장소에서 사용자 정보를 조회해야 합니다.
	    
	    // 예시로 임시로 CustomerDto를 생성하여 반환하는 코드를 작성합니다.
	    CustomerDto customerDto = new CustomerDto();
	    customerDto.setCst_nm(cst_nm); // 사용자명 설정
	    
	    // 조회된 사용자 정보를 반환합니다.
	    return customerDto;
	}
}
