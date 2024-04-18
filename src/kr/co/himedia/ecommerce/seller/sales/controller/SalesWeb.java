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
 * File			: salesWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240403160808][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sales.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.seller.sales.dto.SalesDto;
import kr.co.himedia.ecommerce.seller.sales.service.SalesSrvc;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-04-03
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.seller.sales.controller.SalesWeb")
public class SalesWeb {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SalesWeb.class);
	
	@Inject
	private SalesSrvc salesSrvc;
	
	@RequestMapping(value = "/seller/sales/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, SalesDto salesDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (salesDto.getYear() == null || salesDto.getYear() == 0) salesDto.setYear(2024);
			
			List<SalesDto> list = salesSrvc.select(salesDto);
			salesDto.setCorp_nm(list.get(0).getCorp_nm());
			
			mav.addObject("salesDto", salesDto);
			mav.addObject("list", list);
			mav.setViewName("/seller/sales/list");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/sales/chart.web")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response, SalesDto salesDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (salesDto.getYear() == null || salesDto.getYear() == 0) salesDto.setYear(2024);
			
			List<SalesDto> list = salesSrvc.select(salesDto);
			salesDto.setCorp_nm(list.get(0).getCorp_nm());
			
			mav.addObject("salesDto", salesDto);
			mav.addObject("list", list);
			mav.setViewName("/seller/sales/chart");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".chart()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
