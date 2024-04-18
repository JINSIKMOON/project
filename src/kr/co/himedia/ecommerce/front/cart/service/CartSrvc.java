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
 * File			: CartSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312114810][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.cart.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.buy.dao.PayDao;
//import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
//import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
//import kr.co.himedia.ecommerce.front.buy.dto.PayDto;
import kr.co.himedia.ecommerce.front.cart.dao.CartDao;
import kr.co.himedia.ecommerce.front.cart.dto.CartDtlDto;
import kr.co.himedia.ecommerce.front.cart.dto.CartMstDto;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.front.cart.service.CartSrvc")
public class CartSrvc {

	@Inject
	CartDao cartDao;
	
	@Inject
	PayDao payDao;
	
	public List<CartDtlDto> select(CartMstDto cartMstDto) {
		return cartDao.select(cartMstDto);
	}
	
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = cartDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(cartDao.list(pagingDto));
		
		return pagingListDto;
	}
	
	@Transactional("txFront")
	public boolean insert(CartMstDto cartMstDto, CartDtlDto cartDtlDto) {
		
		int result = 0;
		
		cartMstDto.setSeq_cart_mst(cartDao.sequenceMaster());
		result += cartDao.insertMaster(cartMstDto);
		
		cartDtlDto.setSeq_cart_dtl(cartDao.sequenceDetail());
		cartDtlDto.setSeq_cart_mst(cartMstDto.getSeq_cart_mst());
		cartDtlDto.setId(cartMstDto.getId());
		result += cartDao.insertDetail(cartDtlDto);
		
		if (result == 3) return true; else {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); return
		false;
		}
	}
}

