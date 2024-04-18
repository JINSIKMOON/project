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
 * File			: SaleApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231220113552][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.sale.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.himedia.ecommerce.front.Common;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;
/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.sale.controller.SaleApi")
public class SaleApi extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SaleApi.class);
	
	@Inject
	SaleSrvc saleSrvc;
	
	/**
	 * @param SaleDto [판매 빈(Bean)]
	 * @return List<SaleDto>
	 * 
	 * @since 2023-12-15
	 * <p>DESCRIPTION: 목록(전체)</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:<br>
	 * - headers	: 요청 헤더를 확인<br>
	 * - consumes	: application/json 형태로 요청 확인<br>
	 * - produces	: application/json 형태로 응답 확인<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.GET)<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")<br>
	 * </p>
	 */
	@RequestMapping(value = "/front/sale/salelisting.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> salelisting(@RequestBody final SaleDto saleDto) {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {
			
			//logger.debug("saleDto.getSearchWord()=" + saleDto.getSearchWord());
			//logger.debug("saleDto.getSearchKey()=" + saleDto.getSearchKey());
			
			if (saleDto.getSearchWord() != null && saleDto.getSearchWord() != "" ) {
				listSaleDtoDto = saleSrvc.salelisting(saleDto);
			}
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".salelisting()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
}
