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
 * File			: SaleWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312220714][ek@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.sale.controller;

import java.util.List;

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
import kr.co.himedia.ecommerce.front.mypage.dto.SaleEvalDto;
import kr.co.himedia.ecommerce.front.mypage.service.SaleEvalSrvc;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;

/**
 * @version 1.0.0
 * @author ek@himedia.co.kr
 * 
 * @since 2024-03-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.sale.controller.SaleWeb")
public class SaleWeb  extends Common {
	
	@Inject
	SaleSrvc saleSrvc;
	
	@Inject
	SaleEvalSrvc saleEvalSrvc;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SaleWeb.class);
	
	@RequestMapping(value = "/front/sale/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
				
			PagingListDto pagingListDto = saleSrvc.list(pagingDto);
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/front/sale/list");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
			}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/sale/detail.web")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response,SaleDto saleDto,SaleEvalDto saleEvalDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
				
			SaleDto _saleDto = saleSrvc.select(saleDto);
			List<SaleEvalDto> list = saleEvalSrvc.select(saleEvalDto);
			SaleEvalDto _saleEvalDto = saleEvalSrvc.average(saleEvalDto);
			mav.addObject("saleEvalDto", _saleEvalDto);
			mav.addObject("list", list);
			mav.addObject("saleDto", _saleDto);
			mav.setViewName("/front/sale/detail");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".detail()] " + e.getMessage(), e);
			}
		finally {}
		
		return mav;
	}
}
