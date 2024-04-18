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
 * File			: IndexWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240308172916][ek@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.main.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.Common;
//import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;


/**
 * @version 1.0.0
 * @author ek@himedia.co.kr
 * 
 * @since 2024-03-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.index.controller.MainWeb")
public class MainWeb extends Common{

	@Inject
	SaleSrvc saleSrvc;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(MainWeb.class);
	
	@RequestMapping(value = "/front/main/main.web")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto1 = saleSrvc.main(pagingDto);
			
			PagingListDto _pagingListDto2 = saleSrvc.list(pagingDto);
			
			pagingDto.setCtg_no("101");
			PagingListDto list1Dto = saleSrvc.list(pagingDto);
			
			pagingDto.setCtg_no("103");
			PagingListDto list2Dto = saleSrvc.list(pagingDto);
			
			pagingDto.setCtg_no("105");
			PagingListDto list3Dto = saleSrvc.list(pagingDto);
			
			mav.addObject("paging"	, _pagingListDto2.getPaging());
			mav.addObject("paging"	, pagingListDto1.getPaging());
			mav.addObject("list"	, _pagingListDto2.getList());
			mav.addObject("main"	, pagingListDto1.getList());
			mav.addObject("list1", list1Dto.getList());
			mav.addObject("list2", list2Dto.getList());
			mav.addObject("list3", list3Dto.getList());
			
			mav.setViewName("/front/main/main");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".main()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}

}
