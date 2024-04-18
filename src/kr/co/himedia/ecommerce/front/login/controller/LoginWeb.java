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
 *				: [20231130173445][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.login.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.login.service.LoginSrvc;
import kr.co.himedia.ecommerce.util.Datetime;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-11-30
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.login.controller.LoginWeb")
public class LoginWeb {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoginWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	LoginSrvc loginSrvc;
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2023-12-07
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/front/login/callback.web")
	public ModelAndView callback(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = null;
		try {
			session = request.getSession();
			String clientId = "K6ndNhHkpQIM2IuV52wZ";
			String clientSecret = "Ac7D_CMm7r";
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String redirectURI = URLEncoder.encode("http://127.0.0.1:8080/front/login/callback.web", "UTF-8");

			String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
					+ "&client_id=" + clientId
					+ "&client_secret=" + clientSecret
					+ "&redirect_uri=" + redirectURI
					+ "&code=" + code
					+ "&state=" + state;

			String accessToken = "";
			String refresh_token = "";

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;

			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuilder res = new StringBuilder();

			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();

			if (responseCode == 200) {
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());

				JSONObject jsonObject = (JSONObject) obj;
				accessToken = (String) jsonObject.get("access_token");
				refresh_token = (String) jsonObject.get("refresh_token");

				String proURL = "https://openapi.naver.com/v1/nid/me";
				String header = "Bearer " + accessToken;
				url = new URL(proURL);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Authorization", header);
				responseCode = con.getResponseCode();
				if (responseCode == 200) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				res = new StringBuilder();
				while ((inputLine = br.readLine()) != null) {
					res.append(inputLine);
				}
				br.close();
				if (responseCode == 200) {
					JSONParser parsing1 = new JSONParser();
					Object obj1 = parsing1.parse(res.toString());
					JSONObject jsonObj1 = (JSONObject) obj1;
				
					JSONObject resObj = (JSONObject) jsonObj1.get("response");
					String id = (String) resObj.get("id");
					String email = (String) resObj.get("email");
					String name = (String) resObj.get("name");
					String phone = (String) resObj.get("mobile");
				}
		}
		session.setAttribute("userLoggedIn", true);
		mav.setViewName("forward:/index.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		if(session != null) {
			session.setAttribute("userLoggedIn", false);
			}
			mav.setViewName("redirect:/error.web");
		}

		return mav;
	}

	
	@RequestMapping(value = "/front/login/naverloginForm.web")
	public ModelAndView naverloginForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String clientId = "K6ndNhHkpQIM2IuV52wZ";
			String redirectURI = URLEncoder.encode("http://127.0.0.1:8080/front/login/callback.web", "UTF-8");
			SecureRandom random = new SecureRandom();
			String state = new BigInteger(130, random).toString();
			String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
							+ "&client_id=" + clientId + "&redirect_uri=" + redirectURI + "&state=" + state;
			HttpSession session = request.getSession();
			session.setAttribute("state", state);
			request.setAttribute("redirect", apiURL);
			mav.setViewName("redirect:" + apiURL); // 수정된 부분
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".naverloginForm()] " + e.getMessage(), e);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/login/logout.web")
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
			request.setAttribute("redirect"	, "/front/login/loginForm.web");
				
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".logout()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2023-12-07
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/login/loginProc.web", method = RequestMethod.POST)
	public ModelAndView loginProc(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			String id		= request.getParameter("id");
			String passwd	= request.getParameter("passwd");
			
			/** [개선] 정상 또는 실패 시 IP, Time, Agent 등을 보안상 저장 */
			CustomerDto customerDto = loginSrvc.loginProc(id);
			
			// 해쉬 암호화
			if (customerDto != null
					&&  HSwithSHA.encode(passwd).equals(customerDto.getPasswd())) {
				
				// 대칭키 암호화
				String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
				SKwithAES aes		= new SKwithAES(staticKey);
				
				// 세션에 로그인 정보(로그인 일시 및 ID)를 저장
				HttpSession session = request.getSession(true);
				session.setAttribute("DT_LOGIN", Datetime.getNow("yyyy-MM-dd HH:mm:ss"));
				session.setAttribute("ID", customerDto.getId());
				// [주의] Request.getSession()를 사용하기 위해서는 문자열로 저장할 것(사유: 반환형은 문자열만 허용)
				session.setAttribute("SEQ_CST", Integer.toString(customerDto.getSeq_cst()));
				session.setAttribute("CST_NM", aes.decode(customerDto.getCst_nm())); // 관리자(1), 판매자(2), 구매자(3)
				session.setAttribute("CD_USR_TYPE", "3"); // 관리자(1), 판매자(2), 구매자(3)
				
				request.setAttribute("script"	, "alert('" + id + "님이 " + session.getAttribute("DT_LOGIN") + "에 로그인하셨습니다.');");
				request.setAttribute("redirect"	, "/");
			}
			else {
				request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
				request.setAttribute("script"	, "alert(MSG_ERR_ID_PASSWD);");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/login/loginForm.web")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
		
			HttpSession session = request.getSession(true);
			
			if (session.getAttribute("ID") == null
					|| session.getAttribute("ID").equals("")
					|| session.getAttribute("SEQ_CST") == null
					|| session.getAttribute("SEQ_CST").equals("")
					|| session.getAttribute("DT_LOGIN") == null
					|| session.getAttribute("DT_LOGIN").equals("")) {
			
				mav.setViewName("/front/login/loginForm");
			}
			else {
				//logger.debug("ID=" + session.getAttribute("ID"));
				//logger.debug("DT_LOGIN=" + session.getAttribute("DT_LOGIN"));
				
				request.setAttribute("script"	, "alert('이미 로그인하셨습니다!');");
				request.setAttribute("redirect"	, "/index.jsp");
				
				mav.setViewName("forward:/servlet/result.web");
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}

}
