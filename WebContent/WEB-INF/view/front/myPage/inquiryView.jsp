<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
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
	<style>
	#primary {
		margin-left: -180px; /* 왼쪽으로 30px 이동 */
	}	
	</style>
	<script>
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/front/myPage/inquiryView.web";
			frmMain.submit();
		}
		</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/front/myPage/inqueryView.web">
<input type="hidden" name="cd_bbs_type" value="${paging.cd_bbs_type}" />
<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<header>
			<%@ include file="/include/front/top.jsp" %>
	</header>
	<div id="content" class="site-content">
		<nav>
		<%@ include file="/include/front/myPage/lnb.jsp" %>
		</nav>
			<!-- #secondary -->
			<div id="primary" class="content-area column two-thirds">
			<div class="brdSearchArea" style="text-align:left !important;">
				<select name="searchKey">
					<option value="title"<c:if test="${paging.searchKey == 'title'}"> selected</c:if>>제목</option>
					<option value="contents"<c:if test="${paging.searchKey == 'contents'}"> selected</c:if>>내용</option>
					<option value="title+contents"<c:if test="${paging.searchKey == 'title+contents'}"> selected</c:if>>제목 또는 내용</option>
				</select>
				<input type="text" name="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<br>
			<div class="brdInfo">Total: ${paging.totalLine}[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table style="width:950px;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>제목</th>
						<th style="width: 20%">작성일</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty mylist}">
						<tr>
							<td colspan="3">등록된 글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${mylist}" var="list">
						<c:set var="trColor" value="${list.rnum % 2}"/>
						<tr bgcolor="#<c:if test="${trColor == 1}">FFFFFF</c:if><c:if test="${trColor != 1}">F8F8F8</c:if>">
							<td>
								${list.rnum}
							</td>
							<td style="text-align: left">
								<a href="/common/board/view.web?seq_bbs=${list.seq_bbs}">
								<c:if test="${paging.cd_bbs_type == 3}">
									<c:if test="${list.seq_reply > 0}">[답변 완료] </c:if>
									<c:if test="${list.seq_reply == null or list.seq_reply == 0}">[답변 미등록] </c:if>
								</c:if>
									${list.title}
								</a>
							</td>
							<td>
								${list.dt_reg}
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div style="text-align:center !important; margin: 10px">
			<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
			<%
				/* 관리자(1) 판매자(2) 구매자(3) */
				/* 공지사항(1) 자주 찾는 질문(2) 질의 응답(3) */ 
				%>
				<% if (cd_usr_type_lnb == 3) {%>
					<c:if test="${paging.cd_bbs_type == 3}">
						<a href="/common/board/writeForm.web?cd_bbs_type=${paging.cd_bbs_type}" class="btnBasic"><input type = "button" value= "문의등록"/> </a>
					</c:if>
				<%}%>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>
	<br>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>