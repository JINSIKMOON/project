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
	<script>
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/common/board/list.web";
			frmMain.submit();
		}
		// 페이지가 로드되었을 때 실행되는 함수
		window.onload = function() {
		    // id_in 클래스명을 가진 모든 input 요소를 가져옴
		    var idInputs = document.querySelectorAll('.id_in');
		    
		    // 각 input 요소에 대해 처리
		    idInputs.forEach(function(input) {
		        // input 요소의 값을 가져옴
		        var idValue = input.value;
		        
		        // 뒤의 네 자리를 '*'로 대체하여 새로운 문자열 생성
		        var maskedIdValue = maskId(idValue);
		        
		        // id_out 클래스명을 가진 요소를 찾아서 그 안에 새로운 값을 삽입
		        var idOutput = input.nextElementSibling;
		        if (idOutput && idOutput.classList.contains('id_out')) {
		            idOutput.textContent = maskedIdValue;
		        }
		    });
		};

		// 뒤의 네 자리를 '*'로 대체하는 함수
		function maskId(id) {
		    if (id.length <= 4) return id; // id가 4자리 이하인 경우 그대로 반환
		    
		    var visiblePart = id.slice(0, -4); // 마지막 네 자리를 제외한 부분
		    var maskedPart = '****'; // 마지막 네 자리를 '*'로 대체한 부분
		    
		    return visiblePart + maskedPart;
		}
		</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/common/board/list.web">
<input type="hidden" name="cd_bbs_type" value="${paging.cd_bbs_type}" />
<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<header>
		<% if (cd_usr_type_lnb == 2) { %>
			<%@ include file="/include/seller/top.jsp" %>
		<% } else { %>
			<%@ include file="/include/front/top.jsp" %>
		<% } %>
	</header>
	<section class="content">
		<article>
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
			<table class="type_01" style="margin-left: auto; margin-right: auto;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>제목</th>
						<c:if test="${paging.cd_bbs_type == 3}">
						<th style="width: 10%">작성자</th>
						</c:if>
						<th style="width: 10%">등록일</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="3">등록된 글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${list}" var="list">
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
							<c:if test="${paging.cd_bbs_type == 3}">
							<td>
								<input class="id_in" type="hidden" value="${list.id}">
								<a class="id_out"></a>
							</td>
							</c:if>
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
				<% if (cd_usr_type_lnb == 2) {%>
					<c:if test="${paging.cd_bbs_type != 3}">
						<a href="/common/board/writeForm.web?cd_bbs_type=${paging.cd_bbs_type}" class="btnBasic"> <input type="button" value="등록" /> </a>
					</c:if>
				<%}%>
				<% if (cd_usr_type_lnb == 3) {%>
					<c:if test="${paging.cd_bbs_type == 3}">
						<a href="/common/board/writeForm.web?cd_bbs_type=${paging.cd_bbs_type}" class="btnBasic"> <input type="button" value="등록" /> </a>
					</c:if>
				<%}%>
			</div>
		</article>
		<aside></aside>
	</section>
	</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>