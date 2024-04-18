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
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SystemExceptionCtrl.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240125150707][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.common.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.dto.SystemErrorDto;
import kr.co.himedia.ecommerce.util.Datetime;
import kr.co.himedia.ecommerce.util.Files;
import kr.co.himedia.ecommerce.util.servlet.Request;
/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-01-25
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@ControllerAdvice
public class SystemExceptionCtrl {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SystemExceptionCtrl.class);
	
	@Autowired
	Properties staticProperties;
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param exception [예외]
	 * @return ModelAndView
	 * 
	 * @since 2018-10-18
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView systemException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			
			String uri			= request.getRequestURI();
			String extension	= Files.getFileExtention(uri);
			
			SystemErrorDto systemErrorDto = new SystemErrorDto();
			
			if (exception.getMessage().indexOf("No handler found for") == 0) {
				systemErrorDto.setCode("S404");
				systemErrorDto.setCode_desc("FILE NOT FOUND");
			}
			else if (exception.getMessage().indexOf("Request method '") == 0) {
				systemErrorDto.setCode("S405");
				systemErrorDto.setCode_desc("METHOD NOT ALLOWED");
			}
			else {
				systemErrorDto.setCode("???");
				systemErrorDto.setCode_desc("UNKNOWN");
			}
			
			if (extension == null || extension.equals("")) {
				systemErrorDto.setCode("S404");
				systemErrorDto.setCode_desc("FILE NOT FOUND");
			}
			
			mav.addObject("systemErrorDto", systemErrorDto);
			
			if (extension.equals("api"))		mav.setViewName("system/error-api");
			else if (extension.equals("json"))	mav.setViewName("system/error-json");
			else								mav.setViewName("system/error-web");
			
			logger.error("# REGION ***************************" + staticProperties.getProperty("common.logging.system.exception", "[UNDEFINED]") + "***************************");
			logger.error("# MESSAGE  : " + exception.getMessage());
			logger.error("# URL      : " + Request.getFullURL(request));
			logger.error("# IP       : " + Request.getRemoteAddr(request, staticProperties.getProperty("common.server.web", "[UNDEFINED]")));
			logger.error("# TIME     : " + Datetime.getNow("yyyy-MM-dd HH:mm:ss"));
			logger.error("# END REGION ***********************" + staticProperties.getProperty("common.logging.system.exception", "[UNDEFINED]") + "***************************\n");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".systemException()] " + e.getMessage(), e);
		}
		
		return mav;
	}
}
