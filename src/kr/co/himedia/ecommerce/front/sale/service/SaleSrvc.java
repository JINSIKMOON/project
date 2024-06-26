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
 * File			: SaleSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231220113054][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.sale.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.mypage.dao.SaleEvalDao;
import kr.co.himedia.ecommerce.front.sale.dao.SaleDao;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.front.sale.service.SaleSrvc")
public class SaleSrvc {
	
	@Inject
	SaleDao saleDao;
	
	@Inject
	SaleEvalDao saleEvalDao;
	
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = saleDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(saleDao.list(pagingDto));
		
		return pagingListDto;
	}
	
	public PagingListDto main(PagingDto pagingDto) {
	
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = saleDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(saleDao.main(pagingDto));
		
		return pagingListDto;
	}
	
	public SaleDto select(SaleDto saleDto) {
		return saleDao.select(saleDto);
	}
	
	public List<SaleDto> listing(SaleDto saleDto) {
		return saleDao.listing(saleDto);
	}
	
	public List<SaleDto> salelisting(SaleDto saleDto) {
		return saleDao.salelisting(saleDto);
	}
}
