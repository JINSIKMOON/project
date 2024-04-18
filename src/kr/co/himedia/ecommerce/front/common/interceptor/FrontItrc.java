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
 * File			: FrontItrc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240308141820][wlstlr@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.common.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.himedia.ecommerce.front.common.component.MenuCmpn;
import kr.co.himedia.ecommerce.front.common.component.SessionCmpn;

/**
 * @version 1.0.0
 * @author wlstlr@himedia.co.kr
 * 
 * @since 2024-03-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class FrontItrc extends HandlerInterceptorAdapter {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(FrontItrc.class);
	
	 @Autowired
	SessionCmpn sessionCmpn;
	 
	@Autowired
	MenuCmpn menuCmpn;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
		
		try {
			//if (!sessionCmpn.isSession(request)) {
				//response.sendRedirect(request.getContextPath() + "/reject.web");
				//return false;
			//}
			//else {
				menuCmpn.getMenu(request, response);
			//}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".preHandle()] " + e.getMessage(), e);
		}
		
		return true;
	}
}
