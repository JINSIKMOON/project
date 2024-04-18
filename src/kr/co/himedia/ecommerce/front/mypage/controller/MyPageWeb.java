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
 * File			: MyPageWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240102172647][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.mypage.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.common.service.BoardSrvc;
import kr.co.himedia.ecommerce.front.Common;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.buy.service.BuySrvc;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.front.mypage.dto.LikeDto;
import kr.co.himedia.ecommerce.front.mypage.dto.SaleEvalDto;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;
import kr.co.himedia.ecommerce.util.security.SKwithAES;
import kr.co.himedia.ecommerce.front.mypage.service.LikeSrvc;
import kr.co.himedia.ecommerce.front.mypage.service.SaleEvalSrvc;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-01-02
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.mypage.controller.MyPageWeb")
public class MyPageWeb extends Common {
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	BuySrvc buySrvc;
	
	@Inject
	SaleSrvc saleSrvc;
	
	@Inject 
	SaleEvalSrvc saleEvalSrvc;
	
	@Inject 
	LikeSrvc likeSrvc;
	
	@Inject 
	CustomerSrvc customerSrvc;
	
	@Inject 
	BoardSrvc boardSrvc;
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(MyPageWeb.class);
	
	@RequestMapping(value = "/front/myPage/inquiryView.web")
	public ModelAndView inquiryView(HttpServletRequest request, HttpServletResponse response,HttpSession session,PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			pagingDto.setCd_bbs_type(3);
			pagingDto.setSeq_cst(Integer.parseInt(getSession(request,"SEQ_CST")));
			
			PagingListDto pagingListDto = boardSrvc.mylist(pagingDto);
			
			mav.addObject("paging", pagingListDto.getPaging());
			mav.addObject("mylist", pagingListDto.getList());

			mav.setViewName("/front/myPage/inquiryView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".inquiryView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/likeProc.web")
	public ModelAndView likeProc(HttpServletRequest request, HttpServletResponse response, LikeDto likeDto, @RequestParam("seq_sle") int seq_sle) {
		ModelAndView mav = new ModelAndView("forward:/servlet/result.web");
	
		try {
			// 세션에 저장된 seq_cst 가져오기
			likeDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
		
			likeDto.setSeq_sle(seq_sle);
		
			// tb_like에 추가
			if (likeSrvc.insert(likeDto)) {
				request.setAttribute("script", "alert('좋아요를 했습니다.'); window.history.back();");
			} else {
				request.setAttribute("script", "alert('좋아요 처리에 실패했습니다.');");
			}
		} catch (Exception e) {
			// 예외 발생 시 로깅
			logger.error("[" + this.getClass().getName() + ".likeProc()] " + e.getMessage(), e);
			request.setAttribute("script", "alert('서버 오류가 발생했습니다.');");
		}
	
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/likeView.web")
	public ModelAndView likeView(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			pagingDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			LikeDto likeDto = new LikeDto();
			PagingListDto pagingListDto = likeSrvc.list(pagingDto);
			
			mav.addObject("likeDto",likeDto);
			mav.addObject("paging" , pagingListDto.getPaging());
			mav.addObject("list" ,pagingListDto.getList());	
			
			mav.setViewName("/front/myPage/likeView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".likeView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/front/myPage/reviewMng.web")
	public ModelAndView reviewMng(HttpServletRequest request, HttpServletResponse response, HttpSession session,PagingDto pagingDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");
			
		try {
			
			pagingDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			PagingListDto pagingListDto = saleEvalSrvc.review(pagingDto);
			
			mav.addObject("paging" , pagingListDto.getPaging()); 
			mav.addObject("review" ,pagingListDto.getList());	
			
			mav.setViewName("/front/myPage/reviewMng");
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".reviewMng()] " + e.getMessage(), e);
		}

		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/recentView.web")
	public ModelAndView recentView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/myPage/recentView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".recentView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/reviewProc.web")
	public ModelAndView reviewProc(HttpServletRequest request, HttpServletResponse response, SaleEvalDto saleEvalDto) {
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if (saleEvalSrvc.insert(saleEvalDto)) {
				request.setAttribute("script", "alert(MSG_SCC_WRITE); setTimeout(function() { window.close(); }, 500);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_WRITE);");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".reviewProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/reviewForm.web")
	public ModelAndView reviewForm(HttpServletRequest request, HttpServletResponse response,SaleDto saleDto, int seq_sle,CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			saleDto = saleSrvc.select(saleDto);
			saleDto.setSeq_sle(seq_sle);
			
			customerDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			mav.addObject("saleDto", saleDto);
			mav.addObject("customerDto", customerDto);
			mav.setViewName("/front/myPage/reviewForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".reviewForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/view.web")
	public ModelAndView view(HttpServletRequest request	, HttpServletResponse response, int seq_buy_mst) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BuyMstDto buyMstDto= new BuyMstDto();
			buyMstDto.setSeq_buy_mst(seq_buy_mst);
			
			List<BuyDtlDto> list = buySrvc.select(buyMstDto);
			
			mav.addObject("list", list);
			mav.setViewName("/front/myPage/view");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/buyList.web")
	public ModelAndView buyList(HttpServletRequest request	, HttpServletResponse response,PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String id = (String) request.getSession().getAttribute("ID");
			
			if (id == null || id.isEmpty()) {
			request.setAttribute("script","alert('로그인 후 이용해주세요.');"); 
			request.setAttribute("redirect", "/");
			mav.setViewName("forward:/servlet/result.web"); return mav; }
			
			pagingDto.setId(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			PagingListDto pagingListDto = buySrvc.list(pagingDto);
			
			mav.addObject("paging" , pagingListDto.getPaging()); 
			mav.addObject("list" ,pagingListDto.getList());

			mav.setViewName("/front/myPage/buyList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".buyList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/myPage/main.web")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response,PagingDto pagingDto ){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			CustomerDto customerDto = new CustomerDto();
			
			String id = (String) request.getSession().getAttribute("ID");
			
			if (id == null || id.isEmpty()) {
				request.setAttribute("script", "alert('로그인 후 이용해주세요.');");
				request.setAttribute("redirect", "/front/login/loginForm.web");
				mav.setViewName("forward:/servlet/result.web");
				return mav;
			}
			customerDto.setId(id);
			
			CustomerDto _customerDto = customerSrvc.select(customerDto);
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			_customerDto.setCst_nm(aes.decode(_customerDto.getCst_nm()));
			
			PagingListDto pagingListDto1 = saleSrvc.main(pagingDto);
			
			mav.addObject("paging"	, pagingListDto1.getPaging());
			mav.addObject("main"	, pagingListDto1.getList());
			mav.addObject("customerDto",_customerDto);
			mav.setViewName("/front/myPage/main");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".main()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}

}
