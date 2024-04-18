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
 * File			: BuyWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231208143217][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.buy.controller;


import java.util.ArrayList;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.front.Common;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlListDto;
import kr.co.himedia.ecommerce.front.buy.service.BuySrvc;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;
import kr.co.himedia.ecommerce.util.security.SKwithAES;
import kr.co.himedia.ecommerce.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.buy.controller.BuyWeb")
public class BuyWeb extends Common {
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private CustomerSrvc customerSrvc;
	
	@Inject
	SaleSrvc saleSrvc;
	
	@Inject
	BuySrvc buySrvc;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BuyWeb.class);

	@RequestMapping(value = "/front/buy/orderView.web")
	public ModelAndView orderView(HttpServletRequest request, HttpServletResponse response, BuyDtlListDto buyDtlListDto) {
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			ArrayList<BuyDtlDto> listBuyDtlDto = new ArrayList<BuyDtlDto>();
			
			for (int loop = 0; loop < buyDtlListDto.getBuyList().size(); loop++) {
				System.out.println("seq_sle=" + buyDtlListDto.getBuyList().get(loop).getSeq_sle());
				System.out.println("sle_nm=" + buyDtlListDto.getBuyList().get(loop).getSle_nm());
				System.out.println("img=" + buyDtlListDto.getBuyList().get(loop).getImg());
				System.out.println("count=" + buyDtlListDto.getBuyList().get(loop).getCount());
				System.out.println("price=" + buyDtlListDto.getBuyList().get(loop).getPrice());
				
				listBuyDtlDto.add(buyDtlListDto.getBuyList().get(loop));
			}
			
			String flgMobile	= "N";
			if (Request.isDevice(request, "mobile")) flgMobile = "Y";
			
			CustomerDto customerDto = new CustomerDto();
			String id = (String) request.getSession().getAttribute("ID");
			customerDto.setId(id);
			
			/** Interceptor 추가 시 모든 소스에서 삭제 필요 */
			if (id == null || id.isEmpty()) {
				request.setAttribute("script", "alert('로그인 후 이용해주세요.');");
				request.setAttribute("redirect", "/front/login/loginForm.web");
				mav.setViewName("forward:/servlet/result.web");
				return mav;
			}
			
			customerDto = customerSrvc.select(customerDto);
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.decode(customerDto.getCst_nm()));
			customerDto.setPhone(aes.decode(customerDto.getPhone()));
			customerDto.setPostcode(aes.decode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.decode(customerDto.getAddr1()));
			customerDto.setAddr2(aes.decode(customerDto.getAddr2()));
			
			mav.addObject("flgMobile"		, flgMobile);
			mav.addObject("customerDto"		, customerDto);
			mav.addObject("listBuyDtlDto"	, listBuyDtlDto);
			
			mav.setViewName("/front/buy/orderView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".orderView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
	
	