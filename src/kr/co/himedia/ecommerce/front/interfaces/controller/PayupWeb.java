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
 * File			: PayupWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240207164634][pluto@himedia.co.kr][CREATE: Initial Release]
 *				: [20240207164634][pluto@himedia.co.kr][REPORT: From com.payup.pay.controller.PayController]
 */
package kr.co.himedia.ecommerce.front.interfaces.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.front.Common;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlListDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.buy.service.BuySrvc;
import kr.co.himedia.ecommerce.front.interfaces.component.PayupCmpn;
import kr.co.himedia.ecommerce.util.Datetime;
import kr.co.himedia.ecommerce.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-07
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.interfaces.controller.PayupWeb")
public class PayupWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PayupWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Autowired
	private PayupCmpn payupCmpn;
	
	@Inject
	BuySrvc buySrvc;
	
	@RequestMapping(value = "/front/payup/receiveProcess.api")
	public ModelAndView receiveProcess(@RequestParam Map<String,String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			
			// logger.info("[" + this.getClass().getName() + ".receiveProcess().RES1st] " + param.toString());
			if ("0000".equals(param.get("res_cd"))) {
				
				String res_cd		= param.get("res_cd");
				String ordr_idxx	= param.get("ordr_idxx");
				String res_msg		= param.get("res_msg");
				String enc_data		= param.get("enc_data");
				String enc_info		= param.get("enc_info");
				String tran_cd		= param.get("tran_cd");
				String buyr_mail	= param.get("buyr_mail");
				
				String url = "https://api.testpayup.co.kr/ap/api/payment/" + ordr_idxx + "/pay";
				Map<String,String> apiMap = new HashMap<>();		
				apiMap.put("res_cd",res_cd);
				apiMap.put("ordr_idxx",ordr_idxx);
				apiMap.put("res_msg",res_msg);
				apiMap.put("enc_data",enc_data);
				apiMap.put("enc_info",enc_info);
				apiMap.put("tran_cd",tran_cd);
				apiMap.put("buyr_mail",buyr_mail);
				
				Map<String,Object> apiResult = new HashMap<>();
				apiResult = payupCmpn.JsonApi(request, url, apiMap);
				
				// logger.info("[" + this.getClass().getName() + ".receiveProcess().RES2nd] " + apiResult.toString());
				//System.out.println("API 통신 결과 확인하기 = " + apiResult.toString());
				
				/** 페이업 거래번호 */
				String deal_num = (String) apiResult.get("transactionId");
				boolean isResult = true;
				
				request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
				if ("0000".equals(apiResult.get("responseCode"))) {
					
					// logger.info("[" + this.getClass().getName() + ".receiveProcess().RES2nd.SUCCESS] " + apiResult.toString());
					isResult = buySrvc.update(deal_num, Integer.parseInt(getSession(request, "SEQ_CST")), "Y");
					
					request.setAttribute("script"	, "alert(MSG_SCC_BUY);");
					request.setAttribute("redirect"	, "/front/myPage/buyList.web");
					
				}
				else {
					logger.error("[" + this.getClass().getName() + ".receiveProcess().RES2nd.FAILURE] " + apiResult.toString());
					isResult = buySrvc.update(deal_num, Integer.parseInt(getSession(request, "SEQ_CST")), "N");
					
					request.setAttribute("script"	, "alert(MSG_ERR_BUY);");
					request.setAttribute("redirect"	, "/front/buy/searchForm.web");
				}
				
				// 결제 결과에 대한 업데이트 실패 시
				if (!isResult) {
					request.setAttribute("script"	, "alert(MSG_ERR_COMMON);");
					request.setAttribute("redirect"	, "/front/buy/searchForm.web");
				}
			}
			else {
				logger.error("[" + this.getClass().getName() + ".receiveProcess().RES1st.FAILURE] " + param.toString());
				
				request.setAttribute("script"	, "alert(MSG_ERR_COMMON);");
				request.setAttribute("redirect"	, "/front/buy/searchForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".receiveProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/payup/orderProcess.json", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> orderProcess(@RequestParam Map<String, String> param, HttpServletRequest request, BuyDtlListDto buyDtlListDto) throws NoSuchAlgorithmException {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		try {
			
			ArrayList<BuyDtlDto> listBuyDtlDto = new ArrayList<BuyDtlDto>();
			
			int priceTotal		= 0;			// 배송비를 비포함한 총합계
			int priceDelivery	= 0;			// 배송비
			int countTotal		= 0;
			
			for (int loop = 0; loop < buyDtlListDto.getBuyList().size(); loop++) {
				/*
				System.out.println("seq_sle=" + buyDtlListDto.getBuyList().get(loop).getSeq_sle());
				System.out.println("sle_nm=" + buyDtlListDto.getBuyList().get(loop).getSle_nm());
				System.out.println("count=" + buyDtlListDto.getBuyList().get(loop).getCount());
				System.out.println("price=" + buyDtlListDto.getBuyList().get(loop).getPrice());
				*/
				priceTotal = priceTotal + (buyDtlListDto.getBuyList().get(loop).getPrice() * buyDtlListDto.getBuyList().get(loop).getCount());
				countTotal = countTotal + buyDtlListDto.getBuyList().get(loop).getCount();
				
				 listBuyDtlDto.add(buyDtlListDto.getBuyList().get(loop));
			}
			
			if (priceTotal < 50000) priceDelivery = 3000;
			
			/** 결제 연동 */
			String yyyyMMddHHmmss		 = Datetime.getNow("yyyyMMddHHmmss");
			
			String merchantId			= "himedia";
			String key					= "ac805b30517f4fd08e3e80490e559f8e";
			String orderNumber			= "HM-" + yyyyMMddHHmmss;
			String amount				= Integer.toString(Integer.valueOf(priceTotal + priceDelivery));
			String quota				= "0";
			String itemName				= buyDtlListDto.getBuyList().get(0).getSle_nm() + "을 포함하여 총 " + countTotal + "개 상품";
			String userName				= getSession(request, "CST_NM");
			String userAgent			= "WP";
			String returnUrl			= "returnUrl";
			String signature			= "";	//아래에서 생성
			String timestamp			= yyyyMMddHHmmss;	
			
			signature = payupCmpn.getSHA256Hash(merchantId + "|" + orderNumber + "|" + amount + "|" + key + "|" + timestamp);
			
			String url = "https://api.testpayup.co.kr/ap/api/payment/" + merchantId + "/order";
			Map<String,String> apiMap = new HashMap<>();
			apiMap.put("orderNumber",orderNumber);
			apiMap.put("amount",amount);
			apiMap.put("itemName",itemName);
			apiMap.put("userName",userName);
			apiMap.put("signature",signature);
			apiMap.put("timestamp",timestamp);
			
			// TODO 모바일 개발 시 현행화 예정
			if (Request.isDevice(request, "mobile")) {
				apiMap.put("auth_return","http://210.91.85.54:"
						+ staticProperties.getProperty("common.server.port", "[UNDEFINED]") + "/front/payup/receiveProcess.api");
			}
			else {
				apiMap.put("userAgent",userAgent);
				apiMap.put("returnUrl",returnUrl);
			}
			
			apiMap.put("quota",quota);
			apiMap.put("bypassValue","himediaValue");
			
			returnMap = payupCmpn.JsonApi(request, url, apiMap);
			
			//logger.info("[" + this.getClass().getName() + ".orderProcess().RES] " + returnMap.toString());
			//System.out.println("API 통신 결과 확인하기 = " + returnMap.toString());
			
			if ("0000".equals(returnMap.get("responseCode"))) {
				//logger.info("[" + this.getClass().getName() + ".orderProcess().RES.SUCCESS] " + returnMap.toString());
				
				if (listBuyDtlDto != null) {
					
					/** 페이업 거래번호 */
					String deal_num = (String) returnMap.get("ordr_idxx");
					
					// 마스터 설정
					BuyMstDto buyMstDto = new BuyMstDto();
					buyMstDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
					buyMstDto.setBuy_info(buyDtlListDto.getBuyList().get(0).getSle_nm() + "을 포함하여 총 " + countTotal + "개 상품");
					buyMstDto.setBuy_t_count(countTotal);
					buyMstDto.setBuy_t_price(priceTotal);
					buyMstDto.setBuy_d_price(priceDelivery);
					
					if (!buySrvc.insert(buyMstDto, listBuyDtlDto, deal_num)) {
						// 구매 정보 저장 에러
						returnMap.put("responseCode", "B001");
						returnMap.put("responseMsg", "[Error]Insert Buy Information");
					}
				}
			}
			else {
				logger.error("[" + this.getClass().getName() + ".payProcess().RES.FAILURE] " + returnMap.toString());
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".orderProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return returnMap;
	}
	
	@RequestMapping(value = "/front/payup/payProcess.web")
	public ModelAndView payProcess(@RequestParam Map<String,String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			logger.info("[" + this.getClass().getName() + ".payProcess().REQ] " + param.toString());
			//System.out.println("화면에서 넘어온값 = " + param.toString());
			
			String res_cd		= param.get("res_cd");
			String ordr_idxx	= param.get("ordr_idxx");
			String res_msg		= param.get("res_msg");
			String enc_data		= param.get("enc_data");
			String enc_info		= param.get("enc_info");
			String tran_cd		= param.get("tran_cd");
			String buyr_mail	= param.get("buyr_mail");
			
			String url = "https://api.testpayup.co.kr/ap/api/payment/" + ordr_idxx + "/pay";
			Map<String,String> apiMap = new HashMap<>();		
			apiMap.put("res_cd",res_cd);
			apiMap.put("ordr_idxx",ordr_idxx);
			apiMap.put("res_msg",res_msg);
			apiMap.put("enc_data",enc_data);
			apiMap.put("enc_info",enc_info);
			apiMap.put("tran_cd",tran_cd);
			apiMap.put("buyr_mail",buyr_mail);
			
			Map<String,Object> apiResult = new HashMap<>();
			apiResult = payupCmpn.JsonApi(request, url, apiMap);
			
			logger.info("[" + this.getClass().getName() + ".payProcess().RES] " + apiResult.toString());
			//System.out.println("API 통신 결과 확인하기 = " + apiResult.toString());
			
			/** 페이업 거래번호 */
			String deal_num = (String) apiResult.get("transactionId");
			boolean isResult = true;
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if ("0000".equals(apiResult.get("responseCode"))) {
				
				// logger.info("[" + this.getClass().getName() + ".payProcess().RES.SUCCESS] " + apiResult.toString());
				isResult = buySrvc.update(deal_num, Integer.parseInt(getSession(request, "SEQ_CST")), "Y");
				
				request.setAttribute("script"	, "alert(MSG_SCC_BUY);");
				request.setAttribute("redirect"	, "/front/myPage/buyList.web");
				/*
				mav.setViewName("pay/pay_s");
				mav.addObject("responseMsg"		,apiResult.get("responseMsg"));
				mav.addObject("cardName"		,apiResult.get("cardName"));
				mav.addObject("transactionId"	,apiResult.get("transactionId"));
				*/
			}
			else {
				logger.error("[" + this.getClass().getName() + ".payProcess().RES.FAILURE] " + apiResult.toString());
				isResult = buySrvc.update(deal_num, Integer.parseInt(getSession(request, "SEQ_CST")), "N");
				
				request.setAttribute("script"	, "alert(MSG_ERR_BUY);");
				request.setAttribute("redirect"	, "/front/buy/searchForm.web");
				
				/*
				mav.setViewName("pay/pay_f");
				mav.addObject("responseMsg"		,apiResult.get("responseMsg"));
				*/
			}
			
			// 결제 결과에 대한 업데이트 실패 시
			if (!isResult) {
				request.setAttribute("script"	, "alert(MSG_ERR_COMMON);");
				request.setAttribute("redirect"	, "/front/buy/searchForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".payProcess()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;	
	}

}