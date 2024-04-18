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
 * File			: CustomerWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240306104411][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.front.Common;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-06
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */

@Controller(".co.himedia.ecommerce.front.customer.controller.CustomerWeb")
public class CustomerWeb extends Common{

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(CustomerWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private CustomerSrvc customerSrvc;
	
	@RequestMapping(value = "/front/customer/updatePwresult.web")
	public ModelAndView updatePwresult(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");
	
		try {
			String newPassword = HSwithSHA.encode(request.getParameter("newPassword"));
			customerDto.setPasswd(newPassword);
		
			HttpSession session = request.getSession(false);
			String customerId = (String) session.getAttribute("FOUND_CUSTOMER_ID"); // 세션에서 ID 값 가져옴
			if (customerId != null) {
				try {
					// CustomerDto에 세션에서 가져온 ID 설정
					customerDto.setId(customerId);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
			    request.setAttribute("redirect", "/front/login/loginForm.web");
			    mav.setViewName("forward:/servlet/result.web");
			    return mav;
			}
			logger.info("Updating password for customer with ID: {}", customerDto.getId());
		
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if (customerSrvc.updatePw(customerDto)) {
				request.setAttribute("script", "alert('비밀번호 변경에 성공 하였습니다.');");
				request.setAttribute("redirect", "/front/login/loginForm.web");
			} else {
				request.setAttribute("script", "alert('비밀번호 변경에 실패 하였습니다.');");
				request.setAttribute("redirect", "/front/customer/updatePw.web");
			}
		
			mav.setViewName("forward:/servlet/result.web");
			
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".updatePwresult()] " + e.getMessage(), e);
		}
	
		return mav;
	}

	@RequestMapping(value = "/front/customer/updatePw.web")
	public ModelAndView updatePw(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/customer/updatePw");
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".updatePw()] " + e.getMessage(), e);
		} finally {
		}
		
		return mav;
	}

	@RequestMapping(value = "/front/customer/findPwresult.web")
	public ModelAndView findPwresult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String cst_nm, @RequestParam String cst_email, @RequestParam String id) {
	
		// Logger 객체 생성
		Logger logger = LoggerFactory.getLogger(this.getClass());
	
		ModelAndView mav = new ModelAndView();
	
		try {
			String staticKey = staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes = new SKwithAES(staticKey);
		
			String Name = aes.encode(cst_nm);
			String Email = aes.encode(cst_email);
		
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(id);
			customerDto.setCst_nm(Name);
			customerDto.setCst_email(Email);
		
			CustomerDto _customerDto = customerSrvc.findPw(customerDto);
		
			if (_customerDto != null && _customerDto.getId() != null) {
				// 모든 값이 일치하는 경우 세션에 ID 저장
				HttpSession session = request.getSession();
				session.setAttribute("FOUND_CUSTOMER_ID", _customerDto.getId());
			
				mav.setViewName("forward:/front/customer/updatePw.web");
				mav.addObject("customerDto", _customerDto);
			} else {
				// 입력한 값과 일치하는 데이터가 없는 경우 에러 메시지 설정
				request.setAttribute("script", "alert('일치하는 데이터가 없습니다!');");
				request.setAttribute("redirect", "/front/customer/findPw.web");
				mav.setViewName("forward:/servlet/result.web");
			}
	
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".findPwresult()] " + e.getMessage(), e);
			mav.setViewName("redirect:error.web");
		}
	
		return mav;
	}


		
	
	@RequestMapping(value = "/front/customer/findIdresult.web")
	public ModelAndView findIdresult(HttpServletRequest request, HttpServletResponse response, @RequestParam String cst_nm, @RequestParam String cst_email) {		
		ModelAndView mav = new ModelAndView("redirect:error.web");	
		try {	
			String staticKey = staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");	
			SKwithAES aes = new SKwithAES(staticKey);
			
			String Name = aes.encode(cst_nm);
			String Email = aes.encode(cst_email);
			
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCst_nm(Name);	
			customerDto.setCst_email(Email);	
			
			CustomerDto _customerDto = customerSrvc.findId(customerDto);
		
			if (_customerDto != null) { // 일치하는 데이터가 있을 때만 마스킹 적용
				// 아이디 마스킹 처리
				String maskedId = maskId(_customerDto.getId());
				_customerDto.setId(maskedId);
				
				mav.addObject("customerDto", _customerDto);
				mav.addObject("maskedId", maskedId); // 마스킹된 아이디 추가
			}
			
			mav.setViewName("/front/customer/findIdresult");
			
		} catch (Exception e) {	
			logger.error("[" + this.getClass().getName() + ".findIdresult()] " + e.getMessage(), e);
		}
		return mav;
	}

	private String maskId(String id) {
		// 아이디가 null 이거나 길이가 4 미만인 경우는 마스킹 처리하지 않음
		if (id == null || id.length() < 4) {
			return id;
		}
		
		// 첫 4글자를 제외한 나머지를 마스킹 처리
		char[] maskedChars = new char[id.length() - 4];
		Arrays.fill(maskedChars, '*');
		String maskedPortion = new String(maskedChars);
		
		// 첫 4글자와 마스킹된 문자열을 합쳐서 반환
		return id.substring(0, 4) + maskedPortion;
	}


	@RequestMapping(value = "/front/customer/findId.web")		
	public ModelAndView findId(HttpServletRequest request, HttpServletResponse response) {		
			
		ModelAndView mav = new ModelAndView("redirect:/error.web");	
			
		try {	
			mav.setViewName("/front/customer/findId");
		}	
		catch (Exception e) {	
			logger.error("[" + this.getClass().getName() + ".findId()] " + e.getMessage(), e);
		}	
		finally {}	
			
		return mav;	
	}		
			
	@RequestMapping(value = "/front/customer/findPw.web")		
	public ModelAndView findPw(HttpServletRequest request, HttpServletResponse response) {		
			
		ModelAndView mav = new ModelAndView("redirect:/error.web");	
			
		try {	
			mav.setViewName("/front/customer/findPw");
		}	
		catch (Exception e) {	
			logger.error("[" + this.getClass().getName() + ".findPw()] " + e.getMessage(), e);
		}	
		finally {}	
			
		return mav;	
	}		
	
	
	@RequestMapping(value = "/front/customer/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			List<CustomerDto> list = customerSrvc.list();
			
			mav.addObject("list", list);
			mav.setViewName("/front/customer/list");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/customer/exist.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean exist(@RequestBody final CustomerDto customerDto) {
		
		boolean exist = true;
		List<CustomerDto> listCustomerDto = null;
		
		try {
			//logger.debug("id=" + sellerDto.getId());
			listCustomerDto	= customerSrvc.listing(customerDto.getId());
			
			if (listCustomerDto.size() == 0) exist = false;
			
			debuggingJSON(exist);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".exist()] " + e.getMessage(), e);
		}
		finally {}
		
		return exist;
	}
	
	@RequestMapping(value = "/front/customer/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response	, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (customerDto.getFlg_sms() == null || customerDto.getFlg_sms() == "") {
				customerDto.setFlg_sms("N");
			}
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setPhone(aes.encode(customerDto.getPhone()));
			customerDto.setPostcode(aes.encode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.encode(customerDto.getAddr1()));
			customerDto.setAddr2(aes.encode(customerDto.getAddr2()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			customerDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if (customerSrvc.update(customerDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_MODIFY);");
				request.setAttribute("redirect"	, "/");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_MODIFY);");
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}

	@RequestMapping(value = "/front/customer/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			// 세션에서 ID 가져오기
			String id = (String) request.getSession().getAttribute("ID");
			
			// ID가 없는 경우 JavaScript alert을 이용하여 메시지 띄우기
			if (id == null || id.isEmpty()) {
				request.setAttribute("script", "alert('로그인 후 이용해주세요.');");
				request.setAttribute("redirect", "/");
				mav.setViewName("forward:/servlet/result.web");
				return mav;
			}
			
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(id);
			
			CustomerDto _customerDto = customerSrvc.select(customerDto);
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			_customerDto.setCst_nm(aes.decode(_customerDto.getCst_nm()));
			_customerDto.setPhone(aes.decode(_customerDto.getPhone()));
			_customerDto.setPostcode(aes.decode(_customerDto.getPostcode()));
			_customerDto.setAddr1(aes.decode(_customerDto.getAddr1()));
			_customerDto.setAddr2(aes.decode(_customerDto.getAddr2()));
			_customerDto.setCst_email(aes.decode(_customerDto.getCst_email()));
			
			mav.addObject("customerDto", _customerDto);
			mav.setViewName("/front/customer/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/customer/agreeForm.web")
	public ModelAndView agreeForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/customer/agreeForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".agreeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/customer/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/customer/writeForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/customer/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (customerDto.getFlg_sms() == null || customerDto.getFlg_sms() == "") {
				customerDto.setFlg_sms("N");
			}
			
			// 해쉬 암호화
			customerDto.setPasswd(HSwithSHA.encode(customerDto.getPasswd()));
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setPhone(aes.encode(customerDto.getPhone()));
			customerDto.setPostcode(aes.encode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.encode(customerDto.getAddr1()));
			customerDto.setAddr2(aes.encode(customerDto.getAddr2()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if (customerSrvc.insert(customerDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_WRITE);");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_WRITE);");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
