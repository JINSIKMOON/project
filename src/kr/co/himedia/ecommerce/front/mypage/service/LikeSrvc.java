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
 * File			: MyPageSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240327115438][ek@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.mypage.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.mypage.dao.LikeDao;
import kr.co.himedia.ecommerce.front.mypage.dto.LikeDto;

/**
 * @version 1.0.0
 * @author ek@himedia.co.kr
 * 
 * @since 2024-03-27
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */

@Service("kr.co.himedia.ecommerce.front.mypage.service.LikeSrvc")
public class LikeSrvc {
	
	@Inject
	LikeDao likeDao;
	
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = likeDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(likeDao.list(pagingDto));
		
		return pagingListDto;
	}
	
	public List<LikeDto> select(LikeDto likeDto) {
		return likeDao.select(likeDto);
	}

	@Transactional("txCommon")
	public boolean insert(LikeDto likeDto) {
		
		// 신규 글 번호 설정
		likeDto.setLike_code(likeDao.sequence());
		
		int result = likeDao.insert(likeDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
}
