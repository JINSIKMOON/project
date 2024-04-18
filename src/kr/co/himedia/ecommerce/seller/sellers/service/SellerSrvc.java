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
 * File			: SellerSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231127130926][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sellers.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.seller.sellers.dao.SellerDao;
import kr.co.himedia.ecommerce.seller.sellers.dto.SellerDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-27
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT: Don't define transaction at class level and Don't define try/catch logic at method level</p>
 */
@Service("kr.co.himedia.ecommerce.seller.sellers.service.SellerSrvc")
public class SellerSrvc {
	
	@Inject
	SellerDao sellerDao;
	
	@Transactional("txSeller")
	public boolean update(SellerDto sellerDto) {
		
		int result = sellerDao.update(sellerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
	}
	
	public SellerDto select(SellerDto sellerDto) {
		return sellerDao.select(sellerDto.getId());
	}
	
	/**
	 * @param id [판매자 아이디]
	 * @return List<SellerDto>
	 * 
	 * @since 2023-12-05
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	public List<SellerDto> listing(String id) {
		return sellerDao.listing(id);
	}
	
	public List<SellerDto> list() {
		return sellerDao.list();
	}
	
	@Transactional("txSeller")
	public boolean insert(SellerDto sellerDto) {

		int result = sellerDao.insert(sellerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
}