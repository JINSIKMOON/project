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
 * File			: ProductWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240312154801][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.product.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.seller.Common;
import kr.co.himedia.ecommerce.seller.product.dto.ProductDto;
import kr.co.himedia.ecommerce.seller.product.service.ProductSrvc;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.seller.product.controller.ProductWeb")
public class ProductWeb extends Common {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(ProductWeb.class);
	
	@Inject
	ProductSrvc productSrvc;
	
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/seller/product/stock.web")
	public ModelAndView stock(HttpServletRequest request	, HttpServletResponse response, PagingDto pagingDto){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			//logger.debug("searchKey=" + pagingDto.getSearchKey());
			
			//pagingDto.setId(Integer.parseInt(getSession(request, "SEQ_PRD")));
			PagingListDto pagingListDto = productSrvc.list(pagingDto);
			
			ArrayList<ProductDto> list = (ArrayList<ProductDto>) pagingListDto.getList();
			
			// logger.info("length=" + list.size());
			// logger.info("arrProductDto[0].getDt_reg()=" + list.get(0).getDt_reg());
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			// List<ProductDto> list = productSrvc.list(pagingDto);
			// mav.addObject("list", list);
			mav.setViewName("/seller/product/stock");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".stock()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/seller/product/list.web")
	public ModelAndView list(HttpServletRequest request	, HttpServletResponse response, PagingDto pagingDto){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			//logger.debug("searchKey=" + pagingDto.getSearchKey());
			
			//pagingDto.setId(Integer.parseInt(getSession(request, "SEQ_PRD")));
			PagingListDto pagingListDto = productSrvc.list(pagingDto);
			
			ArrayList<ProductDto> list = (ArrayList<ProductDto>) pagingListDto.getList();
			
			// logger.info("length=" + list.size());
			// logger.info("arrProductDto[0].getDt_reg()=" + list.get(0).getDt_reg());
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			// List<ProductDto> list = productSrvc.list(pagingDto);
			// mav.addObject("list", list);
			mav.setViewName("/seller/product/list");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/product/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/seller/product/writeForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/product/writeProc.web", method = RequestMethod.POST)
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, ProductDto productDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (productSrvc.insert(productDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_WRITE);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_WRITE);");
			}
			
			request.setAttribute("redirect"	, "/seller/product/list.web");
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/product/listStatus.web")
	public ModelAndView listStatus(HttpServletRequest request	, HttpServletResponse response, ProductDto productDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			ProductDto _productDto = productSrvc.select(productDto);
			
			mav.addObject("productDto", _productDto);
			mav.setViewName("/seller/product/listStatus");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".listStatus()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/seller/product/view.web")
	public ModelAndView view(HttpServletRequest request	, HttpServletResponse response, ProductDto productDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			ProductDto _productDto = productSrvc.select(productDto);
			
			mav.addObject("productDto", _productDto);
			mav.setViewName("/seller/product/view");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/product/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response	, ProductDto productDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			ProductDto _productDto = productSrvc.select(productDto);
			
			mav.addObject("productDto", _productDto);
			mav.setViewName("/seller/product/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/product/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response	, ProductDto productDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			//logger.debug("prd_nm=" + productDto.getPrd_nm());
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (productSrvc.update(productDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_MODIFY);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_MODIFY);");
			}
			
			request.setAttribute("redirect"	, "/seller/product/list.web");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/product/remove.web")
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response	, ProductDto productDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (productSrvc.deleteFlag(productDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_REMOVE);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_REMOVE);");
			}
			
			request.setAttribute("redirect"	, "/seller/product/list.web");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".remove()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
