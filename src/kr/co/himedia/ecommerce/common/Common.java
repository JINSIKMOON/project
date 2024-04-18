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
 * File			: Common.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231220162449][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.common;

import javax.servlet.http.HttpServletRequest;

import kr.co.himedia.ecommerce.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class Common {
	
	/**
	 * @param request [요청 서블릿]
	 * @param key [키]
	 * @return String
	 * 
	 * @since 2023-12-14
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public static String getSession(HttpServletRequest request, String key) throws Exception {
		
		String value= "0";
		
		String cd_usr_type = Request.getSession(request, "CD_USR_TYPE", "", false);
		
		if (key.equals("register")
				|| key.equals("updater")) {
			if (cd_usr_type.equals("1")) value = Request.getSession(request, "SEQ_MNG", "", false);
			if (cd_usr_type.equals("2")) value = Request.getSession(request, "SEQ_SLL", "", false);
			if (cd_usr_type.equals("3")) value = Request.getSession(request, "SEQ_CST", "", false);
		}
		
		return value;
	}

}
