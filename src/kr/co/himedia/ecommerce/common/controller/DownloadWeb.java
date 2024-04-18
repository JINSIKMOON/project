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
 * File			: DownloadWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231220175201][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.common.controller;

import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.common.controller.DownloadWeb")
public class DownloadWeb {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(DownloadWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	/**
	 * @param pathName [상대 경로를 포함한 저장 파일명]
	 * @param fileName [원본 파일명]
	 * @param model
	 * @return ModelAndView
	 * 
	 * @since 2023-12-21
	 * <p>DESCRIPTION: 파일 다운로드</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/common/download.web", method = RequestMethod.POST)
	public ModelAndView download(String file_save, String file_orig, Model model) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			// [2023-12-22][pluto@himedia.co.kr][TODO: 추후 저장 파일명 미노출]
			File file = new File(staticProperties.getProperty("common.dirUpload", "[UNDEFINED]") + file_save);
			
			if (file == null || file.exists() == false ) {
				
				logger.error("path=" + staticProperties.getProperty("common.dirUpload", "[UNDEFINED]") + file_save);
				mav.setViewName("redirect:/error.web?code=404");
			}
			else {
				model.addAttribute("file", file);
				model.addAttribute("filename", file_orig);
				// model.addAttribute("filesize", boardDownloadDto.getFile_size());
				mav.setViewName("fileDownloadView");
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".download()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
