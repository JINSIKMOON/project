<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%
	// 관리자(1), 판매자(2), 구매자(3) 
	int cd_usr_type_lnb = 0;
	try {cd_usr_type_lnb = Integer.parseInt((String)session.getAttribute("CD_USR_TYPE"));}
	catch (Exception e) {}
	%>
	<% if (cd_usr_type_lnb == 2) { %>
		<%@ include file="/include/seller/header.jsp" %>
	<% } else { %>
		<%@ include file="/include/front/header.jsp" %>
	<% } %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script>
	
		function download() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/common/download.web";
			frmMain.target = "frmBlank";
			frmMain.submit();
		}
		
		function remove() {
			if (confirm(MSG_ASK_RMV)) {
				var frmMain = document.getElementById("frmMain");
				frmMain.action = "/common/board/remove.web";
				frmMain.submit();
			}
		}
		
		function replyForm() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/common/board/replyForm.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/common/board/modifyForm.web">
<input type="hidden" name="cd_bbs_type"	value="${boardDto.cd_bbs_type}" />
<input type="hidden" name="seq_bbs"		value="${boardDto.seq_bbs}" />
<input type="hidden" name="seq_cst"		value="${boardDto.seq_cst}" />
<input type="hidden" name="file_save"	value="${boardDto.file_save}" />
<input type="hidden" name="file_orig"	value="${boardDto.file_orig}" />
	<header>
		<% if (cd_usr_type_lnb == 2) { %>
			<%@ include file="/include/seller/top.jsp" %>
		<% } else { %>
			<%@ include file="/include/front/top.jsp" %>
		<% } %>
	</header>
	<section class="content">
		<article>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th width="15%">제목</th>
					<td>
						${boardDto.title}
					</td>
				</tr>
				<%-- <c:if test="${boardDto.cd_bbs_type == 2}">
				<tr>
					<th>분류</th>
					<td>
						<select name="cd_bbs_tab" disabled>
							<option value="1"<c:if test="${boardDto.cd_bbs_tab == 1}"> selected</c:if>>상품</option>
							<option value="2"<c:if test="${boardDto.cd_bbs_tab == 2}"> selected</c:if>>구매</option>
							<option value="3"<c:if test="${boardDto.cd_bbs_tab == 3}"> selected</c:if>>결제</option>
						</select>
					</td>
				</tr>
				</c:if> --%>
				<tr>
					<th>내용</th>
					<td style="height: auto;">
					<br>
						${boardDto.contents}
					</td>
				</tr>
				<c:if test="${boardDto.contents_reply != null && boardDto.contents_reply != ''}">
				<tr>
					<th>답변</th>
					<td style="height: 200px;">
						${boardDto.contents_reply}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty boardDto.file_orig}">
				<tr>
			    <th>파일</th>
			        <td>
			            <c:choose>
			                <c:when test="${fn:containsIgnoreCase(boardDto.file_orig, '.jpg') || fn:containsIgnoreCase(boardDto.file_orig, '.jpeg') || fn:containsIgnoreCase(boardDto.file_orig, '.png') || fn:containsIgnoreCase(boardDto.file_orig, '.gif') || fn:containsIgnoreCase(boardDto.file_orig, '.svg')}">
			                    <img src="/upload/${boardDto.file_save}" height="250px" />
			                </c:when>
			                <c:otherwise>
			                    <a href="javascript:download()" title="${boardDto.file_orig}"> [다운로드] </a>
			                </c:otherwise>
			            </c:choose>
			        </td>
			    </tr>
				</c:if>
				
				<% if (cd_usr_type_lnb == 2) {%>
				<tr>
					<th>등록 일시</th>
					<td>
						${boardDto.dt_reg}
					</td>
				</tr>
				<%}
				else {%>
				<tr>
					<th>등록일</th>
					<td>
						${fn:substring(boardDto.dt_reg, 0, 10)}
					</td>
				</tr>
				<%}%>
				<% if (cd_usr_type_lnb == 2) {%>
				<tr>
					<th>수정 일시</th>
					<td>
						${boardDto.dt_upt}
					</td>
				</tr>
				<%}%>
			</table>
			 <div style="text-align:center !important; margin: 10px;">
				<% if (cd_usr_type_lnb == 2) {%> 			
					 <input type="submit" value="수정" /> 
					 <a href="javascript:remove();" class="btnBasic"> <input type="button" value="삭제" /> </a>
					<c:if test="${boardDto.cd_bbs_type == 3 && boardDto.contents_reply eq ''}"> <a href="javascript:replyForm();" class="btnBasic"> <input type="button" value="답글" /> </a></c:if>
				<%}%>
				<c:if test="${boardDto.cd_bbs_type eq 3 and flgMy eq 'Y'}">
					 <input type="submit" value="수정" /> 
					 <a href="javascript:remove();" class="btnBasic"> <input type="button" value="삭제" /> </a>
				</c:if>
				&nbsp;<a href="/common/board/list.web?cd_bbs_type=${boardDto.cd_bbs_type}" class="btnBasic"> <input type="button" value="목록" /> </a>
			</div> 
			
		<br>
		</article>
		<aside></aside>
	</section>
	</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
<iframe name="frmBlank" id="frmBlank" width="0" height="0"></iframe>
</body>
</html>