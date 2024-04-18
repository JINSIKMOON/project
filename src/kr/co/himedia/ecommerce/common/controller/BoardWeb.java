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
 * File			: BoardWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231201165758][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.common.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.dto.BoardDto;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.common.service.BoardSrvc;
//import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.util.Files;
//import kr.co.himedia.ecommerce.util.security.SKwithAES;
import kr.co.himedia.ecommerce.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-01
 *        <p>
 *        DESCRIPTION:
 *        </p>
 *        <p>
 *        IMPORTANT:
 *        </p>
 */
@Controller("kr.co.himedia.ecommerce.common.controller.BoardWeb")
public class BoardWeb extends Common {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);

	@Autowired
	Properties staticProperties;

	@Inject
	private BoardSrvc boardSrvc;

	/**
	 * @param request  [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return Map<String, String>
	 * 
	 * @since 2023-12-18
	 *        <p>
	 *        DESCRIPTION: 파일 업로드 for 교재
	 *        </p>
	 *        <p>
	 *        IMPORTANT:
	 *        </p>
	 *        <p>
	 *        EXAMPLE:
	 *        </p>
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");

		Map<String, String> boardMap = new HashMap<String, String>();

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

					// logger.debug(fileItem.getFieldName() + "=" + fileItem.getString(encoding));

					boardMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}
				// FILE일 경우
				else {
					// logger.debug("----------------------------------");
					// logger.debug("HTML: " + fileItem.getFieldName());
					// logger.debug("파일 이름:" + fileItem.getName());
					// logger.debug("파일 크기:" + fileItem.getSize() + "bytes");
					// logger.debug("----------------------------------");

					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileNameOrig = fileItem.getName().substring(idx + 1);
						String fileNameSave = Files.getFileSveName(fileNameOrig);
						boardMap.put("fileNameOrig", fileNameOrig);
						boardMap.put("fileNameSave", fileNameSave);

						// logger.debug("fileNameOrig=" + fileNameOrig);
						// logger.debug("fileNameSave=" + fileNameSave);
						File uploadFile = new File(
								currentDirPath + File.separator + "temp" + File.separator + fileNameSave);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".upload()] " + e.getMessage(), e);
		}
		return boardMap;
	}

	/**
	 * @param request  [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈(Bean)]
	 * @return ModelAndView
	 * 
	 * @since 2023-12-04
	 *        <p>
	 *        DESCRIPTION:
	 *        </p>
	 *        <p>
	 *        IMPORTANT:
	 *        </p>
	 *        <p>
	 *        EXAMPLE:
	 *        </p>
	 */
	@RequestMapping(value = "/common/board/replyProc.web", method = RequestMethod.POST)
	public ModelAndView replyProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {
			// 답변은 고객문의일 경우만 가능
			boardDto.setCd_bbs_type(3);
			// 답변은 관리자만 가능
			/* boardDto.setId(Integer.parseInt(getSession(request, "id"))); */
			boardDto.setId(getSession(request, "id"));

			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/common.js\"></script>");

			if (boardDto.getTitle() == null || boardDto.getTitle().trim().isEmpty()) {
				boardDto.setTitle("제목 없음");
			}

			if (boardSrvc.insert(boardDto)) {
				request.setAttribute("script", "alert(MSG_SCC_WRITE);");
			} else {
				request.setAttribute("script", "alert(MSG_ERR_WRITE);");
			}

			request.setAttribute("redirect", "/common/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());

			/* request.setAttribute("redirect" , "/common/board/list.web"); */

			mav.setViewName("forward:/servlet/result.web");

		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".replyProc()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

	/**
	 * @param request  [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈(Bean)]
	 * @return ModelAndView
	 * 
	 * @since 2023-12-04
	 *        <p>
	 *        DESCRIPTION:
	 *        </p>
	 *        <p>
	 *        IMPORTANT:
	 *        </p>
	 *        <p>
	 *        EXAMPLE:
	 *        </p>
	 */
	@RequestMapping(value = "/common/board/replyForm.web")
	public ModelAndView replyForm(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {
			// 현재 글번호가 새로운 글의 부모 글번호로 설정
			boardDto.setSeq_bbs_parent(boardDto.getSeq_bbs());

			mav.setViewName("/common/board/replyForm");
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".replyForm()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

	/**
	 * @param request  [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈(Bean)]
	 * @return ModelAndView
	 * 
	 * @since 2023-12-01
	 *        <p>
	 *        DESCRIPTION:
	 *        </p>
	 *        <p>
	 *        IMPORTANT:
	 *        </p>
	 *        <p>
	 *        EXAMPLE:
	 *        </p>
	 */
	@RequestMapping(value = "/common/board/writeProc.web", method = RequestMethod.POST)
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {

			// boardDto.sleep(3100); // 3.1초

			// [2023-12-18][pluto@himedia.co.kr][TODO: 부모글 일련번호, 게시판 일련번호, 탭 코드, 등록자/수정자 등
			// 현행화]
			boardDto.setSeq_bbs_parent(0);
			/* boardDto.setId(Integer.parseInt(getSession(request, "id"))); */
			/* boardDto.setId(getSession(request, "id")); */

			Map<String, String> boardMap = upload(request, response);

			String fileNameSave = boardMap.get("fileNameSave");
			String fileNameOrig = boardMap.get("fileNameOrig");

			String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");

			String newFolder = Files.getFolderName(dirUpload);

			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {

				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				boardDto.setFile_save("/" + newFolder + "/" + fileNameSave);
				boardDto.setFile_orig(fileNameOrig);

				/*
				 * if (fileNameOrig != null && fileNameOrig.length() != 0) {
				 * 
				 * String dirUpload1 = staticProperties.getProperty("common.dirImgSale",
				 * "[UNDEFINED]"); String newFolder1 = Files.getFolderName(dirUpload1);
				 * 
				 * File srcFile1 = new File(dirUpload1 + File.separator + "temp" +
				 * File.separator + fileNameSave); File destDir1 = new File(dirUpload1 + "/" +
				 * newFolder1); FileUtils.moveFileToDirectory(srcFile1, destDir1, true);
				 * 
				 * boardDto.setImg_data("/" + newFolder1 + "/" + fileNameSave);
				 * boardDto.setFile_orig(fileNameOrig); }
				 */
			}

			boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setCd_bbs_tab(boardMap.get("cd_bbs_tab"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top(boardMap.get("flg_top"));
			boardDto.setSecret(boardMap.get("secret"));
			boardDto.setId(boardMap.get("id"));
			
			int seqCst = 0; // 초기값 설정

			// 세션에서 값이 들어온다면 해당 값을 seqCst에 할당
			String registerSessionValue = getSession(request, "register");
			if (registerSessionValue != null && !registerSessionValue.isEmpty()) {
			    seqCst = Integer.parseInt(registerSessionValue);
			}

			// 나머지 코드에서 seqCst를 사용
			boardDto.setSeq_cst(seqCst);
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/common.js\"></script>");
			if (boardSrvc.insert(boardDto)) {
				request.setAttribute("script", "alert(MSG_SCC_WRITE);");
			} else {
				request.setAttribute("script", "alert(MSG_ERR_WRITE);");
			}

			request.setAttribute("redirect", "/common/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());

			mav.setViewName("forward:/servlet/result.web");

		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

	@RequestMapping(value = "/common/board/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response, String cd_bbs_type) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {

			BoardDto boardDto = new BoardDto();
			boardDto.setCd_bbs_type(Integer.parseInt(getSession(request, "cd_bbs_type")));

			mav.addObject("cd_bbs_type", cd_bbs_type);

			mav.setViewName("/common/board/writeForm");
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

	@RequestMapping(value = "/common/board/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {
			if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
				pagingDto.setCd_bbs_type(1);
			}

			// logger.debug("CD_USR_TYPE=" + Request.getSession(request, "CD_USR_TYPE", "",
			// false));
			// 고객문의 게시판(CD_BBS_TYPE=3)이고 구매자(CD_USR_TYPE=3)인 경우는 자신의 글만 표시
			
			if (pagingDto.getCd_bbs_type() == 3 && Request.getSession(request, "CD_USR_TYPE", "", false) == "3") {
				pagingDto.setId(Integer.parseInt(getSession(request, "id")));
			}

			PagingListDto pagingListDto = boardSrvc.list(pagingDto);

			mav.addObject("paging", pagingListDto.getPaging());
			mav.addObject("list", pagingListDto.getList());

			mav.setViewName("/common/board/list");

		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}
	
	/*
	 * @RequestMapping(value = "/common/board/view.web") public ModelAndView
	 * view(HttpServletRequest request, HttpServletResponse response, BoardDto
	 * boardDto) {
	 * 
	 * ModelAndView mav = new ModelAndView("redirect:/error.web");
	 * 
	 * try { BoardDto _boardDto = boardSrvc.select(boardDto);
	 * 
	 * mav.addObject("boardDto", _boardDto); mav.setViewName("/common/board/view");
	 * 
	 * } catch (Exception e) { logger.error("[" + this.getClass().getName() +
	 * ".view()] " + e.getMessage(), e); } finally {}
	 * 
	 * return mav; }
	 */

	@RequestMapping(value = "/common/board/view.web")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
	    ModelAndView mav = new ModelAndView("redirect:/error.web");
	    try {
	        BoardDto _boardDto = boardSrvc.select(boardDto);
	        
	        String flgMy = "N";
	        
	        if (_boardDto.getSeq_cst() == Integer.parseInt(getSession(request, "id"))) flgMy = "Y";
	        mav.addObject("flgMy", flgMy);
	        
	        if (_boardDto.getSecret() == null || _boardDto.getSecret().isEmpty()) {
	            // 비밀글이 아닌 경우
	            mav.addObject("boardDto", _boardDto);
	            mav.setViewName("/common/board/view");
	        } else {
	        	mav.addObject("boardDto", _boardDto);
	            mav.setViewName("/common/board/secret");
	        }
	        
	        
	    } catch (Exception e) {
	        logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
	    }
	    return mav;
	}

	/*
	 * @RequestMapping(value = "/common/board/secret.web", method =
	 * RequestMethod.POST) public ModelAndView secretSubmit(HttpServletRequest
	 * request, HttpServletResponse response, @RequestParam("seq_bbs") int
	 * seq_bbs, @RequestParam("secret") String secret, BoardDto boardDto) {
	 * ModelAndView mav = new ModelAndView("redirect:/error.web"); try {
	 * 
	 * boardDto.setSeq_bbs(Integer.parseInt(getSession(request, "seq_bbs")));
	 * 
	 * // 비밀번호가 맞는지 확인 if (boardSrvc.checkSecret(seq_bbs, secret)) {
	 * 
	 * System.out.println("aaaaaaaaaaaaaaa");
	 * 
	 * BoardDto _boardDto = boardSrvc.select(boardDto); mav.addObject("boardDto",
	 * _boardDto); mav.setViewName("/common/board/view"); } else {
	 * mav.addObject("seq_bbs", seq_bbs); mav.setViewName("/common/board/secret");
	 * // 비밀번호가 틀렸을 경우 메시지 등을 설정할 수 있습니다. mav.addObject("errorMessage",
	 * "비밀번호가 올바르지 않습니다."); } } catch (Exception e) { logger.error("[" +
	 * this.getClass().getName() + ".secretSubmit()] " + e.getMessage(), e); }
	 * return mav; }
	 */
	
	@RequestMapping(value = "/common/board/secret.web")
	public ModelAndView secretSubmit(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto, @RequestParam int seq_bbs, @RequestParam String secret) {
		
		ModelAndView mav = new ModelAndView("redirect:error.web");
		
		try {	
			
			BoardDto _boardDto = boardSrvc.checkSecret(boardDto);
			
			if (_boardDto == null) {
				request.setAttribute("script", "alert('비밀번호가 틀렸습니다.');");
				request.setAttribute("back", "true");
				mav.setViewName("forward:/servlet/result.web");
			}
			else {
				mav.addObject("boardDto", _boardDto); 
				mav.setViewName("/common/board/view");
	        }
			
		} catch (Exception e) {	
			logger.error("[" + this.getClass().getName() + ".findIdresult()] " + e.getMessage(), e);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/common/board/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {

			BoardDto _boardDto = boardSrvc.select(boardDto);

			mav.addObject("boardDto", _boardDto);
			mav.setViewName("/common/board/modifyForm");
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

	/*
	 * @RequestMapping(value = "/common/board/modifyProc.web")
	 * 
	 * @ResponseBody public ModelAndView modifyProc(MultipartHttpServletRequest
	 * request, HttpServletResponse response , BoardDto boardDto) throws Exception{
	 * 
	 * 
	 * ModelAndView mav = new ModelAndView("redirect:/error.web");
	 * 
	 * try {
	 * 
	 * request.setAttribute("scriptSRC",
	 * "<script type=\"text/javascript\" src=\"/js/common.js\"></script>");
	 * 
	 * if (boardSrvc.update(boardDto)) { request.setAttribute("script" ,
	 * "alert(MSG_SCC_MODIFY);"); } else { request.setAttribute("script" ,
	 * "alert(MSG_ERR_MODIFY);"); } request.setAttribute("redirect" ,
	 * "/common/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());
	 * 
	 * mav.setViewName("forward:/servlet/result.web"); } catch (Exception e) {
	 * logger.error("[" + this.getClass().getName() + ".modifyProc()] " +
	 * e.getMessage(), e); } finally {}
	 * 
	 * return mav; }
	 */

	@RequestMapping(value = "/common/board/modifyProc.web", method = RequestMethod.POST)
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {

			boardDto.setSeq_bbs_parent(0);

			Map<String, String> boardMap = upload(request, response);

			String fileNameSave = boardMap.get("fileNameSave");
			String fileNameOrig = boardMap.get("fileNameOrig");

			String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");

			String newFolder = Files.getFolderName(dirUpload);

			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {

				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				boardDto.setFile_save("/" + newFolder + "/" + fileNameSave);
				boardDto.setFile_orig(fileNameOrig);

			}

			boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));
			boardDto.setSeq_bbs(Integer.parseInt(boardMap.get("seq_bbs")));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top(boardMap.get("flg_top"));

			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/common.js\"></script>");
			if (boardSrvc.update(boardDto)) {
				request.setAttribute("script", "alert(MSG_SCC_WRITE);");
			} else {
				request.setAttribute("script", "alert(MSG_ERR_WRITE);");
			}

			request.setAttribute("redirect", "/common/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());

			mav.setViewName("forward:/servlet/result.web");

		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

	@RequestMapping(value = "/common/board/remove.web")
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");

		try {

			File file = new File(
					staticProperties.getProperty("common.dirUpload", "[UNDEFINED]") + boardDto.getFile_save());

			if (file.exists()) {
				logger.debug("delete=" + staticProperties.getProperty("common.dirUpload", "[UNDEFINED]")
						+ boardDto.getFile_save());
				file.delete();
			}

			/* boardDto.setUpdater(Integer.parseInt(getSession(request, "updater"))); */
			String updaterStr = getSession(request, "updater");
			if (!updaterStr.isEmpty()) {
				boardDto.setUpdater(Integer.parseInt(updaterStr));
			}

			/*
			 * if (boardSrvc.deleteFlag(boardDto)) { request.setAttribute("script" ,
			 * "alert(MSG_SCC_REMOVE);");
			 * mav.setViewName("redirect:/common/board/list.web?cd_bbs_type=" +
			 * boardDto.getCd_bbs_type());
			 * 
			 * mav.setViewName("redirect:/common/board/list.web");
			 * 
			 * }
			 */
			if (boardSrvc.deleteFlag(boardDto)) {
				request.setAttribute("script", "alert(MSG_SCC_REMOVE);");
				mav.setViewName("redirect:/common/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());

				/* mav.setViewName("redirect:/common/board/list.web"); */

			} else {
				request.setAttribute("script", "alert(MSG_ERR_REMOVE);");
			}

			/*
			 * request.setAttribute("redirect" , "/common/board/list.web?cd_bbs_type=" +
			 * boardDto.getCd_bbs_type()); request.setAttribute("redirect" ,
			 * "/common/board/list.web"); mav.setViewName("forward:/servlet/result.web");
			 */
		} catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".remove()] " + e.getMessage(), e);
		} finally {
		}

		return mav;
	}

}
