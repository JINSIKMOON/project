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
 * File			: LoginWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231127114852][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.login.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.seller.login.service.LoginSrvc;
import kr.co.himedia.ecommerce.seller.sellers.dto.SellerDto;
import kr.co.himedia.ecommerce.util.Datetime;
//import kr.co.himedia.ecommerce.util.security.HSwithSHA;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-27
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.seller.login.controller.LoginWeb")
public class LoginWeb {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoginWeb.class);
	
	@Inject
	LoginSrvc loginSrvc;
	
	@RequestMapping(value = "/seller/login/logout.web")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			HttpSession session = request.getSession(false);
			
			//logger.debug("ID=" + session.getAttribute("ID"));
			//logger.debug("DT_LOGIN=" + session.getAttribute("DT_LOGIN"));
			
			String id		= (String) session.getAttribute("ID");
			String dt_login	= (String) session.getAttribute("DT_LOGIN");
			
			session.invalidate();
			// session.setAttribute("ID"		, null);
			// session.setAttribute("DT_LOGIN"	, null);
			
			request.setAttribute("script"	, "alert('" + dt_login + "에 로그인한 " + id + "님 안녕히 가세요.')");
			request.setAttribute("redirect"	, "/seller/login/loginForm.web");
				
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".logout()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/login/loginForm.web")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			HttpSession session = request.getSession(true);
			
			if (session.getAttribute("ID") == null
					|| session.getAttribute("ID").equals("")
					|| session.getAttribute("SEQ_SLL") == null
					|| session.getAttribute("SEQ_SLL").equals("")
					|| session.getAttribute("DT_LOGIN") == null
					|| session.getAttribute("DT_LOGIN").equals("")) {
				
				mav.setViewName("/seller/login/loginForm");
			}
			else {
				//logger.debug("ID=" + session.getAttribute("ID"));
				//logger.debug("DT_LOGIN=" + session.getAttribute("DT_LOGIN"));
				
				//request.setAttribute("script"	, "alert('이미 로그인하셨습니다!');");
				request.setAttribute("redirect"	, "/seller/main/main.web");
				
				mav.setViewName("forward:/servlet/result.web");
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/login/loginProc.web", method = RequestMethod.POST)
	public ModelAndView loginProc(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			String id		= request.getParameter("id");
			String passwd	= request.getParameter("passwd");
			
			/** [개선] 정상 또는 실패 시 IP, Time, Agent 등을 보안상 저장 */
			SellerDto sellerDto = loginSrvc.loginProc(id);
						
			if (sellerDto != null
					&& sellerDto.getPasswd().equals(passwd)) {
				
				// 세션에 로그인 정보(로그인 일시 및 ID)를 저장
				HttpSession session = request.getSession(true);
				session.setAttribute("DT_LOGIN", Datetime.getNow("yyyy-MM-dd HH:mm:ss"));
				session.setAttribute("ID", id);
				//session.setAttribute("SEQ_SLL", Integer.toString(sellerDto.getid()));
				session.setAttribute("CD_USR_TYPE", "2"); // 관리자(1), 판매자(2), 구매자(3)
				
				request.setAttribute("script"	, "alert('" + id + "님이 " + session.getAttribute("DT_LOGIN") + "에 로그인하셨습니다.');");
				request.setAttribute("redirect"	, "/seller/product/list.web");
			}
			else {
				
				request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
				request.setAttribute("script"	, "alert(MSG_ERR_ID_PASSWD);");
				request.setAttribute("redirect"	, "/seller/login/loginForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
