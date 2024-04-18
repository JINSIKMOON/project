<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>
		function onSubmit() {
			var frmMain = document.getElementById("frmMain");
			frmMain.submit();
		}
	</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/seller/sales/list.web">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<nav>
			<%@ include file="/include/seller/sales/lnb.jsp" %>
		</nav>
		<article>
			<h1>${salesDto.corp_nm}</h1>
			<div style="text-align:right; padding-right: 65px">
				<select name="year" onchange="onSubmit();">
					<option value="2022"<c:if test="${salesDto.year == 2022}"> selected</c:if>>2022년</option>
					<option value="2023"<c:if test="${salesDto.year == 2023}"> selected</c:if>>2023년</option>
					<option value="2024"<c:if test="${salesDto.year == 2024}"> selected</c:if>>2024년</option>
				</select>
			</div>
			<table class="type_01" style="margin-left: auto; margin-right: auto;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>${salesDto.year}년</th>
						<th>매출액</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="3">등록된 매출 정보가 없습니다.</td>
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
								${list.month} 월
							</td>
							<td>
								<fmt:formatNumber value="${list.sumSale}" type="number" />원
							</td>
						</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
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