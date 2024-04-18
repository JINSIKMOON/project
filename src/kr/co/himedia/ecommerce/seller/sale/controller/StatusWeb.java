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
 * File			: SatusWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240402143305][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sale.controller;

import java.util.List;
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

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.seller.Common;
import kr.co.himedia.ecommerce.seller.sale.dto.StatusMstDto;
import kr.co.himedia.ecommerce.seller.sale.service.StatusSrvc;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-04-02
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.seller.sale.controller.StatusWeb")
public class StatusWeb extends Common {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(StatusWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private StatusSrvc statusSrvc;
	
	@RequestMapping(value = "/seller/sale/modifyFormStatus.web")
	public ModelAndView modifyFormStatus(HttpServletRequest request, HttpServletResponse response, StatusMstDto statusMstDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			StatusMstDto _statusMstDto = statusSrvc.select(statusMstDto);
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			// 구매자 정보(성명) 복호화(마스킹은 JSP에서 처리)
			_statusMstDto.setCst_nm(aes.decode(_statusMstDto.getCst_nm()));
			
			mav.addObject("statusMstDto", _statusMstDto);
			mav.setViewName("/seller/sale/modifyFormStatus");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyFormStatus()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/sale/modifyProcStatus.web")
	public ModelAndView modifyProcStatus(HttpServletRequest request, HttpServletResponse response, StatusMstDto statusMstDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			//logger.debug("prd_nm=" + productDto.getPrd_nm());
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (statusSrvc.update(statusMstDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_MODIFY);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_MODIFY);");
			}
			
			request.setAttribute("redirect"	, "/seller/sale/listStatus.web");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProcStatus()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/seller/sale/listStatus.web")
	public ModelAndView listStatus(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto = statusSrvc.list(pagingDto);
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			// 구매자 정보(성명) 복호화(마스킹은 JSP에서 처리)
			List<StatusMstDto> listStatusMstDto = (List<StatusMstDto>) pagingListDto.getList();
			//logger.debug("listStatusMstDto.size=" + listStatusMstDto.size());
			StatusMstDto statusMstDto = null;
			
			for (int loop = 0; loop < listStatusMstDto.size(); loop++) {
				statusMstDto = listStatusMstDto.get(loop);
				if(statusMstDto.getCst_nm() != null);
				statusMstDto.setCst_nm(aes.decode(statusMstDto.getCst_nm()));
			}
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/seller/sale/listStatus");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".listStatus()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
