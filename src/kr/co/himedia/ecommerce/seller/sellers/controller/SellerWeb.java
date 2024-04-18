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
 * File			: SellerWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231123164454][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sellers.controller;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.seller.Common;
import kr.co.himedia.ecommerce.seller.sellers.dto.SellerDto;
import kr.co.himedia.ecommerce.seller.sellers.service.SellerSrvc;
//import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-23
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.seller.sellers.controller.SellerWeb")
public class SellerWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SellerWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private SellerSrvc sellerSrvc;

	@RequestMapping(value = "/seller/sellers/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, SellerDto sellerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			List<SellerDto> list = sellerSrvc.list();
			
			mav.addObject("list", list);
			mav.setViewName("/seller/sellers/list");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	

	@RequestMapping(value = "/seller/sellers/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			//logger.debug("id=" + getSession(request, "ID"));
			
			SellerDto sellerDto = new SellerDto();
			sellerDto.setId(getSession(request, "ID"));
			
			SellerDto _sellerDto = sellerSrvc.select(sellerDto);
			
			// 암호화 확인
			//logger.debug("corp_num=" + _sellerDto.getCorp_num());
			//logger.debug("ceo=" + _sellerDto.getCeo());
			//logger.debug("corp_email=" + _sellerDto.getCorp_email());
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("seller.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes	= new SKwithAES(staticKey);
			
			_sellerDto.setCorp_num(aes.decode(_sellerDto.getCorp_num()));
			_sellerDto.setCeo(aes.decode(_sellerDto.getCeo()));
			_sellerDto.setCorp_email(aes.decode(_sellerDto.getCorp_email()));
			
			// 복호화 확인
			//logger.debug("corp_num=" + _sellerDto.getCorp_num());
			//logger.debug("ceo=" + _sellerDto.getCeo());
			//logger.debug("corp_email=" + _sellerDto.getCorp_email());
			
			mav.addObject("sellerDto", _sellerDto);
			mav.setViewName("/seller/sellers/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/sellers/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, SellerDto sellerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			// 암호화 전
			// logger.debug("corp_num=" + sellerDto.getCorp_num());
			logger.debug("ceo=" + sellerDto.getCeo());
			logger.debug("corp_email=" + sellerDto.getCorp_email());
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("seller.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes	= new SKwithAES(staticKey);
			
			// sellerDto.setCorp_num(aes.encode(sellerDto.getCorp_num()));
			sellerDto.setCeo(aes.encode(sellerDto.getCeo()));
			sellerDto.setCorp_email(aes.encode(sellerDto.getCorp_email()));
			
			// 암호화 후
			// logger.debug("corp_num=" + sellerDto.getCorp_num());
			logger.debug("ceo=" + sellerDto.getCeo());
			logger.debug("corp_email=" + sellerDto.getCorp_email());
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (sellerSrvc.update(sellerDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_MODIFY);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_MODIFY);");
			}
			
			request.setAttribute("redirect"	, "/seller/main/main.web");
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}