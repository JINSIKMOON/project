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
 * File			: SaleWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240313152702][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.seller.sale.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.seller.Common;
import kr.co.himedia.ecommerce.seller.product.dto.ProductDto;
import kr.co.himedia.ecommerce.seller.product.service.ProductSrvc;
import kr.co.himedia.ecommerce.seller.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.seller.sale.service.SaleSrvc;
import kr.co.himedia.ecommerce.util.Files;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-13
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.seller.sale.controller.SaleWeb")
public class SaleWeb extends Common {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SaleWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private SaleSrvc saleSrvc;
	
	@Inject
	private ProductSrvc productSrvc;
	
	@RequestMapping(value = "/seller/sale/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			//pagingDto.setId(Integer.parseInt(getSession(request, "SEQ_SLE")));
			
			PagingListDto pagingListDto = saleSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/seller/sale/list");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/sale/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			ProductDto productDto = new ProductDto();
			//productDto.setId(Integer.parseInt(getSession(request, "SEQ_SLL")));
			
			List<ProductDto> listProduct = productSrvc.listing(productDto);
			
			mav.addObject("listProduct", listProduct);
			mav.setViewName("/seller/sale/writeForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/sale/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			Map<String, String> commonMap = upload(request, response);
			
			 
			String fileNameSave = commonMap.get("fileNameSave");
			String fileNameOrig = commonMap.get("fileNameOrig");
			
			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {
				
				String dirUpload	= staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
				String newFolder	= Files.getFolderName(dirUpload);
				
				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				saleDto.setImg("/" + newFolder + "/" + fileNameSave);
				// boardDto.setFile_orig(fileNameOrig);
			}
			
			saleDto.setSeq_prd(Integer.parseInt(commonMap.get("seq_prd")));
			saleDto.setSle_nm(commonMap.get("sle_nm"));
			saleDto.setDesces(commonMap.get("desces"));
			saleDto.setCtg_no(commonMap.get("ctg_no"));
			saleDto.setPrice_sale(Integer.parseInt(commonMap.get("price_sale")));
			saleDto.setCd_state_sale(Integer.parseInt(commonMap.get("cd_state_prd")));
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (saleSrvc.insert(saleDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_WRITE);");
				request.setAttribute("redirect"	, "/seller/sale/list.web");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_WRITE);");
				request.setAttribute("redirect"	, "/seller/sale/list.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/seller/sale/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
	    ModelAndView mav = new ModelAndView("redirect:/error.web");

	    try {
	        SaleDto _saleDto = saleSrvc.select(saleDto);

	        String ctg_no_1 = "0";
	        String ctg_no_2 = "00";
	        String ctg_no_3 = "00";

	        if (_saleDto.getCtg_no() != null && !_saleDto.getCtg_no().isEmpty() && _saleDto.getCtg_no().length() >= 5) {
	            ctg_no_1 = _saleDto.getCtg_no().substring(0, 1);
	            ctg_no_2 = _saleDto.getCtg_no().substring(1, 3);
	            ctg_no_3 = _saleDto.getCtg_no().substring(3, 5);
	        } 

	        mav.addObject("saleDto", _saleDto);
	        mav.addObject("ctg_no_1", ctg_no_1);    
	        mav.addObject("ctg_no_2", ctg_no_2);    
	        mav.addObject("ctg_no_3", ctg_no_3);    
	        mav.setViewName("/seller/sale/modifyForm");
	    } catch (Exception e) {
	        logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
	    } finally {
	        // 필요에 따라 리소스를 정리하거나 마무리 작업을 수행합니다.
	    }

	    return mav;
	}
	@RequestMapping(value = "/seller/sale/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (saleSrvc.update(saleDto)) {
				request.setAttribute("script"	, "alert(MSG_SCC_MODIFY);");
			}
			else {
				request.setAttribute("script"	, "alert(MSG_ERR_MODIFY);");
			}
			
			request.setAttribute("redirect"	, "/seller/sale/list.web");
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
		@SuppressWarnings("rawtypes")
		private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
			
			Map<String, String> commonMap = new HashMap<String, String>();
			
			String encoding = "utf-8";
			File currentDirPath = new File(dirUpload);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			factory.setRepository(currentDirPath);
			factory.setSizeThreshold(1024 * 1024);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List items = upload.parseRequest(request);
				
				for (int loop = 0; loop < items.size(); loop++) {
					
					FileItem fileItem = (FileItem) items.get(loop);
					
					// INPUT일 경우
					if (fileItem.isFormField()) {
						
						//logger.debug(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
						
						commonMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					}
					// FILE일 경우
					else {
						//logger.debug("----------------------------------");
						//logger.debug("HTML: " + fileItem.getFieldName());
						//logger.debug("파일 이름:" + fileItem.getName());
						//logger.debug("파일 크기:" + fileItem.getSize() + "bytes");
						//logger.debug("----------------------------------");
						
						if (fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}
							
							String fileNameOrig = fileItem.getName().substring(idx + 1);
							String fileNameSave = Files.getFileSveName(fileNameOrig);
							commonMap.put("fileNameOrig", fileNameOrig);
							commonMap.put("fileNameSave", fileNameSave);
							
							//logger.debug("fileNameOrig=" + fileNameOrig);
							//logger.debug("fileNameSave=" + fileNameSave);
							File uploadFile = new File(currentDirPath + File.separator + "temp" + File.separator + fileNameSave);
							fileItem.write(uploadFile);
						}
					}
				}
			}
			catch (Exception e) {
				logger.error("[" + this.getClass().getName() + ".upload()] " + e.getMessage(), e);
			}
			return commonMap;
	}
}
